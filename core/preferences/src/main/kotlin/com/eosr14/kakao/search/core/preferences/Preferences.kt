package com.eosr14.kakao.search.core.preferences

import android.content.SharedPreferences
import com.eosr14.kakao.search.core.preferences.delegate.stringPreferences
import javax.inject.Inject

class AppPreferences @Inject constructor(
    val sharedPreferences: SharedPreferences
) {
    var bookmarks: String by stringPreferences(
        key = KEY_BOOKMARKS,
        defaultValue = ""
    )

    companion object {
        private const val KEY_BOOKMARKS: String = "key_bookmarks"
    }
}