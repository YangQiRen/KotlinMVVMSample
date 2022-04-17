package com.yang.kotlinmvvmsample.data.api

import com.yang.kotlinmvvmsample.data.model.Character
import com.yang.kotlinmvvmsample.data.model.PagedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

    @GET("character/")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<PagedResponse<Character>>
}