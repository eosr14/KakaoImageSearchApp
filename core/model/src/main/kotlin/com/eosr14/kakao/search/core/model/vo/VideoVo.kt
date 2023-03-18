package com.eosr14.kakao.search.core.model.vo

import com.eosr14.kakao.search.core.model.dto.Video

data class VideoVo(
    val title: String,
    val url: String,
    val dateTime: String,
    val playTime: Long,
    val thumbnail: String,
    val author: String
)

fun Video.toVo() = VideoVo(
    title,
    url,
    dateTime,
    playTime,
    thumbnail,
    author
)