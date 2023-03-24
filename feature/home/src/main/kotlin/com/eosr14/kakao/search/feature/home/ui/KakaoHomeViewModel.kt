package com.eosr14.kakao.search.feature.home.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eosr14.kakao.search.core.designsystem.type.KakaoSearchItemType
import com.eosr14.kakao.search.core.model.Bookmark
import com.eosr14.kakao.search.core.model.Image
import com.eosr14.kakao.search.core.model.SearchItem
import com.eosr14.kakao.search.core.model.Video
import com.eosr14.kakao.search.core.network.service.KakaoService
import com.eosr14.kakao.search.core.network.utils.NETWORK_DEFAULT_SIZE
import com.eosr14.kakao.search.core.preferences.repository.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KakaoHomeViewModel @Inject internal constructor(
    private val service: KakaoService,
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    private val _text = mutableStateOf("")
    val text get() = _text.value

    private val page = mutableStateOf(1)

    var searchItems = mutableStateListOf<SearchItem>()

    fun getSearchItems() = requestItems(true)

    fun updateMoreItems() {
        if (text.isEmpty()) {
            return
        }
        requestItems()
    }

    fun setSearchText(textState: String) {
        _text.value = textState
    }


    fun onClickBookmark(
        bookmark: Bookmark,
        index: Int,
        type: KakaoSearchItemType
    ) {
        if (hasBookmark(bookmark.uniqueField)) {
            bookmarkRepository.deleteBookmark(bookmark.uniqueField) {
                updateBookmark(index, type, false)
            }
        } else {
            bookmarkRepository.addBookmark(bookmark) {
                updateBookmark(index, type, true)
            }
        }
    }


    fun getBookmarks() {
        bookmarkRepository.getBookmarks()
    }

    private fun hasBookmark(uniqueField: String): Boolean =
        bookmarkRepository.hasBookmark(uniqueField)

    private fun updateBookmark(
        updateIndex: Int,
        type: KakaoSearchItemType,
        hasBookmark: Boolean
    ) {
        val item = when (type) {
            KakaoSearchItemType.IMAGE -> (searchItems[updateIndex] as Image).copy(hasBookmark = hasBookmark)
            KakaoSearchItemType.VIDEO -> (searchItems[updateIndex] as Video).copy(hasBookmark = hasBookmark)
        }
        searchItems[updateIndex] = item
    }

    private fun requestItems(isInitial: Boolean = false) {
        val apiSize = NETWORK_DEFAULT_SIZE / 2
        val apiPage = page.value
        viewModelScope.launch {
            val images = service.getImages(query = text, page = apiPage, size = apiSize)
            val videos = service.getVideos(query = text, page = apiPage, size = apiSize)
            val sortByItems = sortBySearchItems(images.documents, videos.documents)
            val items = if (isInitial) {
                sortByItems.map {
                    when (it.type) {
                        KakaoSearchItemType.IMAGE -> (it as Image).copy(hasBookmark = hasBookmark(it.uniqueField))
                        KakaoSearchItemType.VIDEO -> (it as Video).copy(hasBookmark = hasBookmark(it.uniqueField))
                    }
                }
            } else {
                sortByItems
            }
            searchItems.addAll(items)
            page.value = page.value + 1
        }
    }

    private fun sortBySearchItems(
        images: List<Image>,
        videos: List<Video>
    ): List<SearchItem> {
        return (images + videos).sortedByDescending { it.sortTime }
    }


    /**
     * Compose Paging3 라이브러리를 사용하는 경우, 단일 아이템의 업데이트를 처리할 방법이 없습니다.
     * Bookmark UI Update처리를 위하여 Paging3을 사용하지 않고 인피니티 스크롤 처리를 진행하였습니다.
     */
//    private val _searchItems = MutableStateFlow<List<SearchItem>>(listOf())
//    val searchItems: StateFlow<List<SearchItem>> = _searchItems.asStateFlow()

//    val items: Flow<PagingData<SearchItem>> = Pager(
//        config = PagingConfig(
//            initialLoadSize = NETWORK_DEFAULT_SIZE,
//            pageSize = NETWORK_DEFAULT_SIZE,
//            enablePlaceholders = false
//        ),
//        initialKey = null,
//        pagingSourceFactory = {
//            KakaoPagingSource { page, size ->
//                if (text.isEmpty()) {
//                    return@KakaoPagingSource KakaoPagingResult(null, listOf())
//                }
//                val apiSize = size / 2
//                val images = service.getImages(query = text, page = page, size = apiSize)
//                val videos = service.getVideos(query = text, page = page, size = apiSize)
//                KakaoPagingResult(page, sortBySearchItems(images.documents, videos.documents))
//            }
//        })
//        .flow
//        .map { pagingData ->
//            pagingData.map {
//                it.apply {
//                    it.hasBookmark = hasBookmark(it.uniqueField)
//                }
//            }
//        }
//        .cachedIn(viewModelScope)
}