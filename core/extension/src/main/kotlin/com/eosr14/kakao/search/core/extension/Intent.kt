package com.eosr14.kakao.search.core.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.URLUtil

fun Context.startExternalUrl(url: String) {
    Intent(Intent.ACTION_VIEW, url.checkHttpUrl()).also {
        startActivity(it)
    }
}

fun String.checkHttpUrl(): Uri {
    return if (!URLUtil.isHttpUrl(this) && !URLUtil.isHttpsUrl(this)) {
        Uri.parse("https://$this")
    } else {
        Uri.parse(this)
    }
}