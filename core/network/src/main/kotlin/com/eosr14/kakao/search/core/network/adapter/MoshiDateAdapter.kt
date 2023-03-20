package com.eosr14.kakao.search.core.network.adapter

import com.eosr14.kakao.search.core.extension.ISO_8601_FORMAT
import com.squareup.moshi.*
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class MoshiDateAdapter : JsonAdapter<Date>() {
    private val format = SimpleDateFormat(ISO_8601_FORMAT, Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    @FromJson
    override fun fromJson(reader: JsonReader): Date {
        return try {
            val dateAsString = if (reader.peek() == JsonReader.Token.NULL) {
                reader.nextNull()
            } else {
                reader.nextString()
            }
            dateAsString?.let { format.parse(it) } ?: Date()
        } catch (e: Exception) {
            Timber.e("MoshiDateAdapter fromJson exception = $e")
            Date()
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Date?) {
        val formattedString = value?.let { format.format(it) } ?: value.toString()
        writer.value(formattedString)
    }
}