package com.eosr14.kakao.search.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.eosr14.kakao.search.navigation.KakaoNavGraph


@Composable
fun KakaoMain() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { KakaoBottomNavigation(navController = navController) }
    ) {
        Box(Modifier.padding(it)) {
            KakaoNavGraph(navController = navController)
        }
    }
}