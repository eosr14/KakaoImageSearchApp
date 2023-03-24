package com.eosr14.kakao.search.feature.bookmark.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.hilt.navigation.compose.hiltViewModel
import com.eosr14.kakao.search.core.designsystem.component.ImageColumnContent
import com.eosr14.kakao.search.core.designsystem.component.KakaoSearchItem
import com.eosr14.kakao.search.core.designsystem.component.VideoColumnContent
import com.eosr14.kakao.search.core.designsystem.theme.TextType
import com.eosr14.kakao.search.core.designsystem.type.KakaoSearchItemType
import com.eosr14.kakao.search.core.extension.startExternalUrl
import com.eosr14.kakao.search.core.extension.toFormatString
import com.eosr14.kakao.search.core.model.Bookmark
import com.eosr14.kakao.search.feature.bookmark.R
import com.eosr14.kakao.search.feature.bookmark.databinding.FragmentKakaoBookmarkBinding


@Composable
fun KakaoBookmarkFragmentInCompose() {
    AndroidViewBinding(FragmentKakaoBookmarkBinding::inflate)
}

@Composable
fun KakaoBookmarkScreen(
    viewModel: KakaoBookmarkViewModel = hiltViewModel()
) {
    val bookmarks by viewModel.bookmarks.collectAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.getBookmarks()
    }

    if (bookmarks.isEmpty()) {
        EmptyBookmark()
    } else {
        BookmarkList(bookmarks)
    }
}

@Composable
fun EmptyBookmark() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.bookmarks_empty_message),
            style = TextType.Medium22_B()
        )
    }
}

@Composable
fun BookmarkList(
    bookmarks: List<Bookmark>,
    viewModel: KakaoBookmarkViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .padding(vertical = 30.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(bookmarks) {
            KakaoSearchItem(
                type = it.type,
                thumbnailPath = it.thumbnailUrl,
                onClickItem = { context.startExternalUrl(it.url) },
                columnContent = {
                    when (it.type) {
                        KakaoSearchItemType.IMAGE -> ImageColumnContent(
                            title = it.title,
                            dateTime = it.dateTime.toFormatString()
                        )
                        KakaoSearchItemType.VIDEO -> VideoColumnContent(
                            title = it.title,
                            author = it.author,
                            dateTime = it.dateTime.toFormatString()
                        )
                    }
                }
            )
        }
    }
}