package com.eosr14.kakao.search.core.designsystem.type

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.eosr14.kakao.search.core.designsystem.R

enum class KakaoSearchItemType(
    @DrawableRes val drawableRes: Int,
    @ColorRes val colorRes: Int,
    @StringRes val titleRes: Int
) {
    IMAGE(
        R.drawable.baseline_image_24,
        R.color.violet_10,
        R.string.image
    ),
    VIDEO(
        R.drawable.baseline_video_settings_24,
        R.color.red_30,
        R.string.video
    )
}