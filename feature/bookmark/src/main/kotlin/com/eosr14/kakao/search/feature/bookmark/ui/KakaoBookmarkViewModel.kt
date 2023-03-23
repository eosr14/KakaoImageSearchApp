package com.eosr14.kakao.search.feature.bookmark.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eosr14.kakao.search.core.model.Bookmark
import com.eosr14.kakao.search.core.preferences.repository.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KakaoBookmarkViewModel @Inject internal constructor(
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    private val _bookmarks = MutableStateFlow<List<Bookmark>>(listOf())
    val bookmarks: StateFlow<List<Bookmark>> = _bookmarks.asStateFlow()

    fun getBookmarks() {
        viewModelScope.launch {
            val bookmarks = bookmarkRepository.getBookmarks()
            _bookmarks.emit(bookmarks)
        }
    }
}