package com.eosr14.kakao.search.core.designsystem.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Float.toSp() = with(LocalDensity.current) { Dp(this@toSp).toSp() }

