package com.eosr14.kakao.search.core.model.vo

import com.eosr14.kakao.search.core.model.dto.Image

data class ImageVo(
    val collection: String,
    val thumbnailUrl: String,
    val imageUrl: String,
    val width: Int,
    val height: Int,
    val displaySiteName: String,
    val docUrl: String,
    val dateTime: String
)

fun Image.toVo() = ImageVo(
    collection,
    thumbnailUrl,
    imageUrl,
    width,
    height,
    displaySiteName,
    docUrl,
    dateTime
)