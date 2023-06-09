package com.eosr14.kakao.search

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.eosr14.kakao.search.core.designsystem.theme.KakaoAppTheme
import com.eosr14.kakao.search.ui.KakaoMain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KakaoActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KakaoAppTheme {
                KakaoMain()
            }
        }
    }
}