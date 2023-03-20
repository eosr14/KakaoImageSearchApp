package com.eosr14.kakao.search.feature.home.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eosr14.kakao.search.core.model.dto.SearchItem
import java.io.IOException


data class KakaoPagingResult(
    val page: Int? = null,
    val value: List<SearchItem>
)

open class KakaoPagingSource(
    private val apiCall: suspend (Int, Int) -> KakaoPagingResult
) : PagingSource<Int, SearchItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchItem> {
        val page = params.key ?: 1
        return try {
            val response = apiCall(page, params.loadSize)
            LoadResult.Page(
                data = response.value,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.value.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SearchItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}