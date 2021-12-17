package com.example.compose_keep.ui.common

import androidx.compose.foundation.Image
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_keep.R
import com.example.compose_keep.ui.compose.LocalDarkTheme

@Preview
@Composable
fun Logo(
    modifier: Modifier = Modifier,
) {
    val colorFilter = if (LocalDarkTheme.current) {
        ColorFilter.tint(MaterialTheme.colors.onBackground)
    } else null
    Image(
        painter = painterResource(id = R.drawable.ic_google_2015_logo),
        contentDescription = "",
        colorFilter = colorFilter,
        modifier = modifier
    )
}
