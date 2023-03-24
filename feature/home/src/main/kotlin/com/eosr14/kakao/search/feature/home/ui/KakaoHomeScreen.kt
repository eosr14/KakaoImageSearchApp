package com.eosr14.kakao.search.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.hilt.navigation.compose.hiltViewModel
import com.eosr14.kakao.search.core.designsystem.component.*
import com.eosr14.kakao.search.core.designsystem.type.KakaoSearchItemType
import com.eosr14.kakao.search.core.extension.startExternalUrl
import com.eosr14.kakao.search.core.extension.toFormatString
import com.eosr14.kakao.search.core.model.Image
import com.eosr14.kakao.search.core.model.SearchItem
import com.eosr14.kakao.search.core.model.Video
import com.eosr14.kakao.search.core.model.toBookmarkItem
import com.eosr14.kakao.search.feature.home.databinding.FragmentKakaoHomeBinding
import com.eosr14.kakao.search.feature.home.paging.OnBottomReached


@Composable
fun KakaoHomeFragmentInCompose() {
    AndroidViewBinding(FragmentKakaoHomeBinding::inflate)
}

@Composable
fun KakaoHomeScreen(
    viewModel: KakaoHomeViewModel = hiltViewModel()
) {
    val searchItems = viewModel.searchItems
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            SearchTextField {
                with(viewModel) {
                    getSearchItems()
                    getBookmarks()
                }
            }
        }
        item {
            Spacer(modifier = Modifier.size(10.dp))
        }


        itemsIndexed(searchItems) { index, item ->
            SearchItem(index = index, item = item)
        }
    }

    listState.OnBottomReached {
        viewModel.updateMoreItems()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchTextField(
    viewModel: KakaoHomeViewModel = hiltViewModel(),
    onClickSearch: () -> Unit
) {
    val textState = viewModel.text
    val keyboardController = LocalSoftwareKeyboardController.current
    KakaoSearchTextField(modifier = Modifier
        .padding(top = 30.dp)
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(horizontal = 20.dp)
        .clip(RoundedCornerShape(15.dp))
        .background(colorResource(id = com.eosr14.kakao.search.core.designsystem.R.color.grayscale_10)),
        value = textState,
        onSearchKeyboardAction = {
            if (textState.isNotEmpty()) {
                onClickSearch()
                keyboardController?.hide()
            }
        },
        onValueChange = { viewModel.setSearchText(it) })
}

@Composable
private fun SearchItem(
    item: SearchItem,
    index: Int,
    viewModel: KakaoHomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val onClickItem: (String) -> Unit = { context.startExternalUrl(it) }

    when (item) {
        is Image -> {
            val title = stringResource(
                id = com.eosr14.kakao.search.core.designsystem.R.string.collection,
                item.collection,
                item.displaySiteName
            )
            KakaoSearchItem(
                type = item.type,
                thumbnailPath = item.thumbnailUrl,
                onClickItem = { onClickItem(item.docUrl) },
                columnContent = {
                    ImageColumnContent(
                        title = title,
                        dateTime = item.dateTime.toFormatString()
                    )
                },
                hasBookmark = item.hasBookmark,
                onClickBookmark = {
                    viewModel.onClickBookmark(
                        item.toBookmarkItem(title),
                        index,
                        KakaoSearchItemType.IMAGE
                    )
                })
        }

        is Video -> {
            val title = item.title
            KakaoSearchItem(
                type = item.type,
                thumbnailPath = item.thumbnail,
                playTime = item.playTime,
                onClickItem = { onClickItem(item.url) },
                columnContent = {
                    VideoColumnContent(
                        title = title,
                        author = item.author,
                        dateTime = item.dateTime.toFormatString()
                    )
                },
                hasBookmark = item.hasBookmark,
                onClickBookmark = {
                    viewModel.onClickBookmark(
                        item.toBookmarkItem(title),
                        index,
                        KakaoSearchItemType.VIDEO
                    )
                })
        }
    }
}

/**
 * Compose Paging3 라이브러리를 사용하는 경우, 단일 아이템의 업데이트를 처리할 방법이 없습니다.
 * Bookmark UI Update처리를 위하여 Paging3을 사용하지 않고 인피니티 스크롤 처리를 진행하였습니다.
 */
//@Composable
//private fun SearchItemWithPaging(
//    item: SearchItem?,
//    viewModel: KakaoHomeViewModel = hiltViewModel()
//) {
//    val context = LocalContext.current
//    val onClickItem: (String) -> Unit = { context.startExternalUrl(it) }
//    item?.let {
//        when (it) {
//            is Image -> {
//                val title = stringResource(
//                    id = com.eosr14.kakao.search.core.designsystem.R.string.collection,
//                    it.collection,
//                    it.displaySiteName
//                )
//                KakaoSearchItem(type = it.type,
//                    thumbnailPath = it.thumbnailUrl,
//                    onClickItem = { onClickItem(it.docUrl) },
//                    columnContent = {
//                        ImageColumnContent(
//                            title = title,
//                            dateTime = it.dateTime.toFormatString()
//                        )
//                    },
//                    hasBookmark = it.hasBookmark,
//                    onClickBookmark = { viewModel.onClickBookmark(it.toBookmarkItem(title)) })
//            }
//
//            is Video -> {
//                val title = it.title
//                KakaoSearchItem(type = it.type,
//                    thumbnailPath = it.thumbnail,
//                    playTime = it.playTime,
//                    onClickItem = { onClickItem(it.url) },
//                    columnContent = {
//                        VideoColumnContent(
//                            title = title,
//                            author = it.author,
//                            dateTime = it.dateTime.toFormatString()
//                        )
//                    },
//                    hasBookmark = it.hasBookmark,
//                    onClickBookmark = { viewModel.onClickBookmark(it.toBookmarkItem(title)) })
//            }
//        }
//    }
//}