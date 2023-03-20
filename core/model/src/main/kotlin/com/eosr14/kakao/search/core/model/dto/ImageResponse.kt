package com.eosr14.kakao.search.core.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class ImageResponse(
    val documents: List<Image>,
    val meta: Meta
)

data class Image(
    @Json(name = "collection")
    val collection: String,

    @Json(name = "thumbnail_url")
    val thumbnailUrl: String,

    @Json(name = "image_url")
    val imageUrl: String,

    @Json(name = "width")
    val width: Int,

    @Json(name = "height")
    val height: Int,

    @Json(name = "display_sitename")
    val displaySiteName: String,

    @Json(name = "doc_url")
    val docUrl: String,

    @Json(name = "datetime")
    val dateTime: Date
) : SearchItem(dateTime)
