package com.eosr14.kakao.search.feature.home.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eosr14.kakao.search.core.model.Bookmark
import com.eosr14.kakao.search.core.model.Image
import com.eosr14.kakao.search.core.model.SearchItem
import com.eosr14.kakao.search.core.model.Video
import com.eosr14.kakao.search.core.network.service.KakaoService
import com.eosr14.kakao.search.core.network.utils.NETWORK_DEFAULT_SIZE
import com.eosr14.kakao.search.core.preferences.repository.BookmarkRepository
import com.eosr14.kakao.search.feature.home.paging.KakaoPagingResult
import com.eosr14.kakao.search.feature.home.paging.KakaoPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class KakaoHomeViewModel @Inject internal constructor(
    private val service: KakaoService,
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    private val _text = mutableStateOf("")
    val text get() = _text.value

    fun getSearchItems(): Flow<PagingData<SearchItem>> = Pager(
        config = PagingConfig(
            initialLoadSize = NETWORK_DEFAULT_SIZE,
            pageSize = NETWORK_DEFAULT_SIZE,
            enablePlaceholders = false
        ),
        initialKey = null,
        pagingSourceFactory = {
            KakaoPagingSource { page, size ->
                if (text.isEmpty()) {
                    return@KakaoPagingSource KakaoPagingResult(null, listOf())
                }
                val apiSize = size / 2
                val images = service.getImages(query = text, page = page, size = apiSize)
                val videos = service.getVideos(query = text, page = page, size = apiSize)
                KakaoPagingResult(page, sortBySearchItems(images.documents, videos.documents))
            }
        }
    )
        .flow
        .cachedIn(viewModelScope)


    fun setSearchText(textState: String) {
        _text.value = textState
    }

    fun onClickBookmark(bookmark: Bookmark, searchItem: SearchItem) {
        if (hasBookmark(bookmark.url)) {
            bookmarkRepository.deleteBookmark(bookmark.url)
        } else {
            bookmarkRepository.addBookmark(bookmark)
        }
    }

    fun hasBookmark(uniqueField: String): Boolean = bookmarkRepository.hasBookmark(uniqueField)

    private fun sortBySearchItems(
        images: List<Image>,
        videos: List<Video>
    ): List<SearchItem> {
        return (images + videos).sortedByDescending { it.sortTime }
    }
}