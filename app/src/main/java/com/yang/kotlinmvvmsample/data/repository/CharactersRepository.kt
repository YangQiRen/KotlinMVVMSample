package com.yang.kotlinmvvmsample.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yang.baselibs.base.BaseRepository
import com.yang.kotlinmvvmsample.data.api.CharacterRetrofit
import com.yang.kotlinmvvmsample.data.model.Character
import com.yang.kotlinmvvmsample.data.paging.datasource.CharactersPagingDataSource
import kotlinx.coroutines.flow.Flow

class CharactersRepository: BaseRepository() {

    private val service by lazy {
        CharacterRetrofit.service
    }

    fun getAllCharacters(): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { CharactersPagingDataSource(service) }
    ).flow
}