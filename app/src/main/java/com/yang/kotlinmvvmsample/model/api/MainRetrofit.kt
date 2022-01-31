package com.yang.kotlinmvvmsample.model.api

import com.yang.baselibs.http.RetrofitFactory

object MainRetrofit : RetrofitFactory<MainApi>() {

    override fun baseUrl(): String = MainApi.BASE_URL

    override fun attachService(): Class<MainApi> = MainApi::class.java

}