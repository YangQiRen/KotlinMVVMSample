package com.yang.kotlinmvvmsample.data.paging.datasource

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yang.kotlinmvvmsample.data.api.CharacterApi
import com.yang.kotlinmvvmsample.data.model.Character

class CharactersPagingDataSource(private val service: CharacterApi) :
    PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getAllCharacters(pageNumber)
            val pagedResponse = response.body()
            val data = pagedResponse?.results

            var nextPageNumber: Int? = null
            if (pagedResponse?.pageInfo?.next != null) {
                val uri = Uri.parse(pagedResponse.pageInfo.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int = 1
}