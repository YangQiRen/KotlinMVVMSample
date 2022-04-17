package com.yang.kotlinmvvmsample.ui.main

import androidx.lifecycle.MutableLiveData
import com.yang.baselibs.base.BaseViewModel
import com.yang.kotlinmvvmsample.data.model.Banner
import com.yang.kotlinmvvmsample.data.model.CollectionArticle
import com.yang.kotlinmvvmsample.data.model.CollectionResponseBody
import com.yang.kotlinmvvmsample.data.repository.MainRepository

class MainViewModel : BaseViewModel() {

    val mLoginData: MutableLiveData<Any> = MutableLiveData()

    val mLogoutData: MutableLiveData<Any> = MutableLiveData()

    val mBannerList: MutableLiveData<List<Banner>> = MutableLiveData()

    val mCollectResponseBody: MutableLiveData<CollectionResponseBody<CollectionArticle>> = MutableLiveData()

    val errorMsg: MutableLiveData<String> = MutableLiveData()

    private val repository by lazy {
        MainRepository()
    }

    fun login(username: String, password: String) {
        launchOnUI({
            showLoading.postValue(true)
            mLoginData.value = repository.login(username, password)
            showLoading.postValue(false)
        })
    }

    fun logout() {
        launchOnUI({
            showLoading.postValue(true)
            mLogoutData.value = repository.logout()
            showLoading.postValue(false)
        })
    }

    fun getBannerList() {
        launchOnUI({
            showLoading.postValue(true)
            mBannerList.value = repository.getBanner()
            showLoading.postValue(false)
        })
    }

    fun getCollectList(page: Int) {
        launchOnUI({
            showLoading.postValue(true)
            mCollectResponseBody.value = repository.getCollectList(page)
            showLoading.postValue(false)
        })
    }
}