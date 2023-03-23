package com.eosr14.kakao.search.core.preferences.repository

import com.eosr14.kakao.search.core.extension.moshi.fromListJson
import com.eosr14.kakao.search.core.extension.moshi.toJson
import com.eosr14.kakao.search.core.model.Bookmark
import com.eosr14.kakao.search.core.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

internal class BookmarkRepositoryImpl @Inject constructor(
    private val preferences: AppPreferences
) : BookmarkRepository {

    override fun getBookmarks(): List<Bookmark> = preferences.bookmarks.fromListJson()

    override fun addBookmark(bookmark: Bookmark) {
        with(getBookmarks().toMutableList()) {
            add(bookmark)
            updateBookmarks()
        }
    }

    override fun deleteBookmark(uniqueField: String) {
        with(getBookmarks().toMutableList()) {
            removeIf { it.url == uniqueField }
            updateBookmarks()
        }
    }

    override fun hasBookmark(uniqueField: String): Boolean =
        getBookmarks().find { it.url == uniqueField } != null

    private fun List<Bookmark>.updateBookmarks() {
        toJson()?.let {
            preferences.bookmarks = it
        }
    }
}
