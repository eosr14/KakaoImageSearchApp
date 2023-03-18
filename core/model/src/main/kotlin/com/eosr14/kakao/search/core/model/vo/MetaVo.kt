package com.eosr14.kakao.search.core.model.vo

import com.eosr14.kakao.search.core.model.dto.Meta

data class MetaVo(
    val totalCount: Int,
    val pageableCount: Int,
    val isEnd: Boolean
)

fun Meta.toVo() = MetaVo(
    totalCount,
    pageableCount,
    isEnd
)