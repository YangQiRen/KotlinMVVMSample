package com.yang.kotlinmvvmsample.ui.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yang.baselibs.base.BaseViewModel
import com.yang.kotlinmvvmsample.data.model.Character
import com.yang.kotlinmvvmsample.data.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CharactersViewModel : BaseViewModel() {

    private val repository by lazy {
        CharactersRepository()
    }

    var charactersFlow: Flow<PagingData<Character>> = flowOf()

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() {
        launchPagingAsync({
            repository.getAllCharacters().cachedIn(viewModelScope)
        }, {
            charactersFlow = it
        })
    }
}