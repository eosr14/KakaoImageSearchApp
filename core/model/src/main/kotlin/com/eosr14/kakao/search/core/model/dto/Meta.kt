package com.eosr14.kakao.search.core.model.dto

import com.squareup.moshi.Json

data class Meta(
    @Json(name = "total_count")
    val totalCount: Int,

    @Json(name = "pageable_count")
    val pageableCount: Int,

    @Json(name = "is_end")
    val isEnd: Boolean
)
