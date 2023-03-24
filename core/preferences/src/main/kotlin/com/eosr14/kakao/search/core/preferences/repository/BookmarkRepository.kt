package com.eosr14.kakao.search.core.preferences.repository

import com.eosr14.kakao.search.core.model.Bookmark


interface BookmarkRepository {
    fun getBookmarks(): List<Bookmark>
    fun addBookmark(bookmark: Bookmark, onSuccessUpdate: () -> Unit)
    fun deleteBookmark(uniqueField: String, onSuccessUpdate: () -> Unit)
    fun hasBookmark(uniqueField: String): Boolean
}