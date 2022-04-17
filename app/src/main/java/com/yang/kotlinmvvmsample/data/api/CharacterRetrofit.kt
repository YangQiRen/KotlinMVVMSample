package com.yang.kotlinmvvmsample.data.api

import com.yang.baselibs.http.RetrofitFactory

object CharacterRetrofit : RetrofitFactory<CharacterApi>() {

    override fun baseUrl(): String = CharacterApi.BASE_URL

    override fun attachService(): Class<CharacterApi> = CharacterApi::class.java

}