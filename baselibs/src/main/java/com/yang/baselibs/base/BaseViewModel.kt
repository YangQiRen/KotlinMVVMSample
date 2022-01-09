package com.yang.baselibs.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import com.yang.baselibs.ext.showToast
import com.yang.baselibs.http.exception.HttpException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BaseViewModel : ViewModel(), LifecycleObserver {

    val showLoading = MutableLiveData<Boolean>()

    fun launchOnUI(
        block: suspend CoroutineScope.() -> Unit,
        onException: ((Throwable) -> Unit)? = null
    ) {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Logger.e(throwable, throwable.message ?: "launchOnUI")
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
}