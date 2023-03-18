package com.eosr14.kakao.search.core.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class VideoResponse(
    val documents: List<Video>,
    val meta: Meta
)

data class Video(
    @Json(name = "title")
    val title: String,

    @Json(name = "url")
    val url: String,

    @Json(name = "datetime")
    val dateTime: String,

    @Json(name = "play_time")
    val playTime: Long,

    @Json(name = "thumbnail")
    val thumbnail: String,

    @Json(name = "author")
    val author: String
)
