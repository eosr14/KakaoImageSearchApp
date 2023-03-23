package com.eosr14.kakao.search.core.model

import com.eosr14.kakao.search.core.designsystem.type.KakaoSearchItemType
import java.util.*

data class Bookmark(
    val url: String,
    val type: KakaoSearchItemType,
    val thumbnailUrl: String,
    val title: String,
    val author: String? = null,
    val dateTime: Date
)