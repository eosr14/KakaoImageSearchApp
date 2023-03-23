package com.eosr14.kakao.search.core.extension

import java.text.SimpleDateFormat
import java.util.*

const val ISO_8601_FORMAT = "yyyy-MM-dd'T'hh:mm:ss.SSSZ"
const val SIMPLE_DATE_FORMAT_TYPE1 = "yyyy.MM.dd HH:mm"
const val SIMPLE_DATE_FORMAT_TYPE2 = "mm:ss"

fun Date.toFormatString(
    format: String = SIMPLE_DATE_FORMAT_TYPE1,
    locale: Locale = Locale.getDefault()
): String {
    return SimpleDateFormat(format, locale).format(this)
}

fun Long.toFormatString(
    format: String = SIMPLE_DATE_FORMAT_TYPE2,
    locale: Locale = Locale.getDefault()
): String {
    return SimpleDateFormat(format, locale).format(this)
}