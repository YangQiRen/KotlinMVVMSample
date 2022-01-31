package com.yang.kotlinmvvmsample.model.api

import com.yang.baselibs.base.BaseResponse
import com.yang.kotlinmvvmsample.model.bean.Banner
import com.yang.kotlinmvvmsample.model.bean.CollectionArticle
import com.yang.kotlinmvvmsample.model.bean.CollectionResponseBody
import retrofit2.http.*

interface MainApi {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    @GET("/banner/json")
    suspend fun getBanner(): BaseResponse<List<Banner>>

    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(@Field("username") username: String, @Field("password") password: String): BaseResponse<Any>

    @GET("user/logout/json")
    suspend fun logout(): BaseResponse<Any>

    @GET("lg/collect/list/{page}/json")
    suspend fun getCollectList(@Path("page") page: Int): BaseResponse<CollectionResponseBody<CollectionArticle>>

}