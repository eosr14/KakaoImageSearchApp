package com.eosr14.kakao.search.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eosr14.kakao.search.core.navigation.KakaoScreens
import com.eosr14.kakao.search.feature.home.ui.KakaoHomeFragmentInCompose

@Composable
fun KakaoNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = KakaoScreens.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(KakaoScreens.Home.route) {
            KakaoHomeFragmentInCompose()
        }

        composable(KakaoScreens.Bookmark.route) {
//            KakaoHomeFragmentInCompose()
        }
    }
}
