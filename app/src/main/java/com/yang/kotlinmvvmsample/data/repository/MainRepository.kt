package com.yang.kotlinmvvmsample.data.repository

import com.yang.baselibs.base.BaseRepository
import com.yang.kotlinmvvmsample.data.api.MainRetrofit
import com.yang.kotlinmvvmsample.data.model.Banner
import com.yang.kotlinmvvmsample.data.model.CollectionArticle
import com.yang.kotlinmvvmsample.data.model.CollectionResponseBody

class MainRepository : BaseRepository() {

    private val mainService by lazy {
        MainRetrofit.service
    }

    suspend fun login(username: String, password: String): Any? {
        return apiCall { mainService.login(username, password) }
    }

    suspend fun logout(): Any? {
        return apiCall { mainService.logout() }
    }

    suspend fun getBanner(): List<Banner>? {
        return apiCall { mainService.getBanner() }
    }

    suspend fun getCollectList(page: Int): CollectionResponseBody<CollectionArticle>? {
        return apiCall { mainService.getCollectList(page) }
    }
}