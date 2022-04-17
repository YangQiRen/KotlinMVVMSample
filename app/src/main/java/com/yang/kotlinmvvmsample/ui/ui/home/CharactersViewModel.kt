package com.yang.kotlinmvvmsample.ui.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yang.baselibs.base.BaseViewModel
import com.yang.kotlinmvvmsample.data.api.CharacterRetrofit
import com.yang.kotlinmvvmsample.data.model.Character
import com.yang.kotlinmvvmsample.data.paging.datasource.CharactersPagingDataSource
import kotlinx.coroutines.flow.Flow

class CharactersViewModel : BaseViewModel() {

    val characters: Flow<PagingData<Character>> =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { CharactersPagingDataSource(CharacterRetrofit.service) }
        ).flow.cachedIn(viewModelScope)

}