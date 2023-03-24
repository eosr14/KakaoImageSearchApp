package com.eosr14.kakao.search.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.eosr14.kakao.search.core.designsystem.R
import com.eosr14.kakao.search.core.designsystem.theme.TextType
import com.eosr14.kakao.search.core.designsystem.type.KakaoSearchItemType
import com.eosr14.kakao.search.core.extension.SIMPLE_DATE_FORMAT_TYPE2
import com.eosr14.kakao.search.core.extension.toFormatString

@Composable
fun KakaoSearchItem(
    type: KakaoSearchItemType,
    thumbnailPath: String,
    playTime: Long? = null,
    onClickItem: () -> Unit,
    columnContent: @Composable ColumnScope.() -> Unit,
    hasBookmark: Boolean = false,
    onClickBookmark: (() -> Unit)? = null,
) {
    Column(
        Modifier
            .padding(horizontal = 20.dp)
            .clickable { onClickItem() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                SubcomposeAsyncImage(
                    model = thumbnailPath,
                    contentDescription = null,
                    loading = { CircularProgressIndicator() },
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                playTime?.let {
                    Text(
                        text = it.toFormatString(SIMPLE_DATE_FORMAT_TYPE2),
                        style = TextType.Medium11_R(),
                        color = Color.White,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(end = 5.dp, bottom = 5.dp)
                            .align(Alignment.BottomEnd)
                            .background(
                                color = colorResource(id = R.color.black_50),
                                shape = RoundedCornerShape(2.dp)
                            )
                            .padding(vertical = 3.dp, horizontal = 3.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                SearchTip(stringResource(id = type.titleRes), type)
                columnContent()
            }

            onClickBookmark?.let {
                IconButton(
                    onClick = { it() },
                ) {
                    Icon(
                        painter = if (hasBookmark) {
                            painterResource(id = R.drawable.baseline_bookmark_24)
                        } else {
                            painterResource(id = R.drawable.baseline_bookmark_border_24)
                        },
                        contentDescription = null
                    )
                }
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = colorResource(id = R.color.grayscale_10)
        )
    }
}

@Composable
fun SearchTip(
    text: String,
    searchType: KakaoSearchItemType
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(6.dp))
            .background(colorResource(id = searchType.colorRes)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = searchType.drawableRes),
            modifier = Modifier
                .padding(start = 4.dp, end = 2.dp),
            contentDescription = null
        )
        Text(
            text = stringResource(id = searchType.titleRes),
            style = TextType.Medium16_B(),
            color = Color.Black,
            modifier = Modifier.padding(end = 4.dp)
        )
    }
}

@Composable
fun ImageColumnContent(
    title: String,
    dateTime: String
) {
    Text(
        text = title,
        style = TextType.Medium22_B(),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    Text(
        text = dateTime,
        style = TextType.Medium18_R(),
        color = colorResource(id = R.color.grayscale_50)
    )
}

@Composable
fun VideoColumnContent(
    title: String,
    author: String?,
    dateTime: String
) {
    Text(
        text = title,
        style = TextType.Medium22_B(),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
    author?.let {
        Text(
            text = it,
            style = TextType.Medium18_B(),
        )
    }
    Text(
        text = dateTime,
        style = TextType.Medium18_R(),
        color = colorResource(id = R.color.grayscale_50)
    )
}