package com.eosr14.kakao.search.core.preferences.delegate

import androidx.core.content.edit
import com.eosr14.kakao.search.core.preferences.AppPreferences
import kotlin.reflect.KProperty

fun stringPreferences(key: String, defaultValue: String) =
    StringPreferenceDelegate(key, defaultValue)

class StringPreferenceDelegate(
    private val key: String,
    private val defaultValue: String
) {
    operator fun getValue(preferences: AppPreferences, property: KProperty<*>): String {
        return preferences.sharedPreferences.getString(key, null) ?: let {
            setValue(preferences, property, defaultValue)
            defaultValue
        }
    }

    operator fun setValue(preferences: AppPreferences, property: KProperty<*>, value: String) {
        preferences.sharedPreferences.edit { putString(key, value) }
    }
}
