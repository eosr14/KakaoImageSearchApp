package com.eosr14.kakao.search.core.designsystem.component

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.eosr14.kakao.search.core.designsystem.R
import com.eosr14.kakao.search.core.designsystem.theme.TextType

@Composable
fun KakaoSearchTextField(
    modifier: Modifier,
    value: String,
    onSearchKeyboardAction: () -> Unit,
    onValueChange: (String) -> Unit
) {
    SearchTextField(
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearchKeyboardAction() }
        ),
        placeholder = {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = stringResource(id = R.string.search_placeholder),
                style = TextType.Medium18_B(),
                color = colorResource(id = R.color.grayscale_30)
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                tint = colorResource(id = R.color.grayscale_70),
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        value = value,
        onValueChange = onValueChange
    )
}