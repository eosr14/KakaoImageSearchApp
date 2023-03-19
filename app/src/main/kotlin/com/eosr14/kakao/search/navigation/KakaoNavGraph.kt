package com.eosr14.kakao.search.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eosr14.kakao.search.core.navigation.KakaoScreens

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
//            GalleryHomeScreen(downloadHelper)
        }

        composable(KakaoScreens.Bookmark.route) {
//            GalleryBookmarkScreen(downloadHelper)
        }
    }
}
