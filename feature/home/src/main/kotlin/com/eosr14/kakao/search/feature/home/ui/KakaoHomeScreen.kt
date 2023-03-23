package com.eosr14.kakao.search.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.eosr14.kakao.search.core.designsystem.component.*
import com.eosr14.kakao.search.core.extension.startExternalUrl
import com.eosr14.kakao.search.core.extension.toFormatString
import com.eosr14.kakao.search.core.model.Image
import com.eosr14.kakao.search.core.model.SearchItem
import com.eosr14.kakao.search.core.model.Video
import com.eosr14.kakao.search.core.model.toBookmarkItem
import com.eosr14.kakao.search.feature.home.databinding.FragmentKakaoHomeBinding


@Composable
fun KakaoHomeFragmentInCompose() {
    AndroidViewBinding(FragmentKakaoHomeBinding::inflate)
}

@Composable
fun KakaoHomeScreen(
    viewModel: KakaoHomeViewModel = hiltViewModel()
) {
    val searchItems: LazyPagingItems<SearchItem> =
        viewModel.getSearchItems().collectAsLazyPagingItems()
    val context = LocalContext.current
    val onClickItem: (String) -> Unit = { context.startExternalUrl(it) }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            SearchTextField {
                searchItems.refresh()
            }
        }
        item {
            Spacer(modifier = Modifier.size(10.dp))
        }
        items(searchItems) { searchItems ->
            searchItems?.let {
                when (it) {
                    is Image -> {
                        val title = stringResource(
                            id = com.eosr14.kakao.search.core.designsystem.R.string.collection,
                            it.collection,
                            it.displaySiteName
                        )
                        KakaoSearchItem(type = it.type,
                            thumbnailPath = it.thumbnailUrl,
                            onClickItem = { onClickItem(it.docUrl) },
                            columnContent = {
                                ImageColumnContent(
                                    title = title,
                                    dateTime = it.dateTime.toFormatString()
                                )
                            },
                            hasBookmark = viewModel.hasBookmark(it.uniqueField),
                            onClickBookmark = {
                                viewModel.onClickBookmark(it.toBookmarkItem(title), it)
                            })
                    }

                    is Video -> {
                        val title = it.title
                        KakaoSearchItem(type = it.type,
                            thumbnailPath = it.thumbnail,
                            playTime = it.playTime,
                            onClickItem = { onClickItem(it.url) },
                            columnContent = {
                                VideoColumnContent(
                                    title = title,
                                    author = it.author,
                                    dateTime = it.dateTime.toFormatString()
                                )
                            },
                            hasBookmark = viewModel.hasBookmark(it.uniqueField),
                            onClickBookmark = {
                                viewModel.onClickBookmark(it.toBookmarkItem(title), it)
                            })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchTextField(
    viewModel: KakaoHomeViewModel = hiltViewModel(), onClickSearch: () -> Unit
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