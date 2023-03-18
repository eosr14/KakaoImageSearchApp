package com.eosr14.kakao.search.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.eosr14.kakao.search.core.designsystem.extension.toSp

object TextType {

    @Composable
    fun Medium24_B() = TextStyle(
        fontSize = 24f.toSp(),
        fontWeight = FontWeight.Bold,
    )

    @Composable
    fun Medium22_B() = TextStyle(
        fontSize = 22f.toSp(),
        fontWeight = FontWeight.Bold,
    )

    @Composable
    fun Medium18_B() = TextStyle(
        fontSize = 18f.toSp(),
        fontWeight = FontWeight.Bold,
    )

    @Composable
    fun Medium16_B() = TextStyle(
        fontSize = 16f.toSp(),
        fontWeight = FontWeight.Bold,
    )
    @Composable
    fun Medium16_R() = TextStyle(
        fontSize = 16f.toSp(),
        fontWeight = FontWeight.Normal,
    )

    @Composable
    fun Medium14_B() = TextStyle(
        fontSize = 14f.toSp(),
        fontWeight = FontWeight.Bold,
    )
    @Composable
    fun Medium13_R() = TextStyle(
        fontSize = 13f.toSp(),
        fontWeight = FontWeight.Normal,
    )
}
