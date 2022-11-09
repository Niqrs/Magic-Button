package com.niqr.magicbutton.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.niqr.magicbutton.data.model.MagickColor

class MagickColorPaginationSource(
    private val repo: MagickColorRepository
): PagingSource<Int, MagickColor>() {

    override fun getRefreshKey(state: PagingState<Int, MagickColor>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MagickColor> {
        return try { //Loading from high to low
            val lastId = repo.lastId()
            val pageId = params.key ?: lastId

            val response = repo.magickColor(pageId)

            val prevKey = if (pageId > 0) pageId + 1 else null
            val nextKey = if (pageId > 0) pageId - 1 else null

            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}