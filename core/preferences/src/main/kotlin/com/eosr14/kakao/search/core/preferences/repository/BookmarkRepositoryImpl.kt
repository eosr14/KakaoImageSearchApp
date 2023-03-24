package com.eosr14.kakao.search.core.preferences.repository

import com.eosr14.kakao.search.core.extension.moshi.fromListJson
import com.eosr14.kakao.search.core.extension.moshi.toJson
import com.eosr14.kakao.search.core.model.Bookmark
import com.eosr14.kakao.search.core.preferences.AppPreferences
import javax.inject.Inject

internal class BookmarkRepositoryImpl @Inject constructor(
    private val preferences: AppPreferences
) : BookmarkRepository {

    override fun getBookmarks(): List<Bookmark> = preferences.bookmarks.fromListJson()

    override fun addBookmark(bookmark: Bookmark, onSuccessUpdate: () -> Unit) {
        with(getBookmarks().toMutableList()) {
            add(bookmark)
            updateBookmarks(onSuccessUpdate)
        }
    }

    override fun deleteBookmark(uniqueField: String, onSuccessUpdate: () -> Unit) {
        with(getBookmarks().toMutableList()) {
            removeIf { it.uniqueField == uniqueField }
            updateBookmarks(onSuccessUpdate)
        }
    }

    override fun hasBookmark(uniqueField: String): Boolean =
        getBookmarks().find { it.uniqueField == uniqueField } != null

    private fun List<Bookmark>.updateBookmarks(onSuccessUpdate: () -> Unit) {
        toJson()?.let {
            preferences.bookmarks = it
            onSuccessUpdate()
        }
    }
}
