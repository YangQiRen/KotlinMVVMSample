package com.yang.baselibs.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yang.baselibs.ext.showToast
import com.yang.baselibs.http.exception.HttpException
import com.yang.baselibs.utils.LogUtil
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel(), LifecycleObserver {

    val showLoading = MutableLiveData<Boolean>()

    /**
     * 處理異常狀況
     * errorCode=0以外都會進異常，也可以自行定義errorCode跟CustomException讓他單純處理onException
     */
    fun launchOnUI(
        block: suspend CoroutineScope.() -> Unit,
        onException: ((Throwable) -> Unit)? = null
    ) {
        val handler = CoroutineExceptionHandler { _, throwable ->
            LogUtil.e("CoroutineExceptionHandler ${throwable.message ?: "launchOnUI"}" )
            showLoading.postValue(false)
            when (throwable) {
                is HttpException -> {
                    showToast(throwable.errorMsg)
                }
            }
            onException?.invoke(throwable)
        }
        viewModelScope.launch(Dispatchers.Main + handler) {
            block()
        }
    }

    inline fun <T> launchPagingAsync(
        crossinline execute: suspend () -> Flow<T>,
        crossinline onSuccess: (Flow<T>) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val result = execute()
                onSuccess(result)
            } catch (ex: Exception) {
                LogUtil.e(ex.toString())
            }
        }
    }
}