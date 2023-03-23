package com.eosr14.kakao.search.core.extension.moshi

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import timber.log.Timber
import java.util.*

fun getMoshi(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(Date::class.java, MoshiDateAdapter().nullSafe())
    .build()

inline fun <reified T> Moshi.toJson(data: T?): String? {
    if (data == null) return null
    return try {
        getAdapter<T>().toJson(data)
    } catch (t: Throwable) {
        Timber.e(t)
        null
    }
}

inline fun <reified T> Moshi.fromJson(json: String?): T? {
    if (json.isNullOrBlank()) return null
    return try {
        getAdapter<T>().fromJson(json)
    } catch (t: Throwable) {
        Timber.e(t)
        null
    }
}

inline fun <reified T> Moshi.fromListJson(json: String?): List<T>? {
    if (json.isNullOrBlank()) return null
    return try {
        getListAdapter<T>().fromJson(json)
    } catch (t: Throwable) {
        Timber.e(t)
        null
    }
}

inline fun <reified T> T.toJson(): String? {
    return getMoshi().toJson(this)
}

inline fun <reified T> String.fromJson(): T? {
    return getMoshi().fromJson(this)
}

inline fun <reified T> String.fromListJson(): List<T> {
    return getMoshi().fromListJson(this) ?: listOf()
}

inline fun <reified T> Moshi.getAdapter(): JsonAdapter<T> = adapter(T::class.java)

inline fun <reified T> Moshi.getListAdapter(): JsonAdapter<List<T>> {
    val type = Types.newParameterizedType(List::class.java, T::class.java)
    return adapter(type)
}