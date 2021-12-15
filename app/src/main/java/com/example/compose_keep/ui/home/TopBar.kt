package com.example.compose_keep.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Splitscreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.compose_keep.ui.common.IconImageButton
import com.example.compose_keep.ui.memos.DisplayType
import com.example.compose_keep.ui.theme.ComposekeepTheme
import com.google.accompanist.insets.LocalWindowInsets
import kotlin.math.roundToInt

@Composable
internal fun TopBar(
    modifier: Modifier = Modifier,
    state: TopBarState = rememberTopBarState(),
    displayType: DisplayType = DisplayType.Staggered,
    onClickNavigationIcon: () -> Unit = {},
    onClickGridButton: () -> Unit = {},
) {
    Surface(
        shape = CircleShape,
        modifier = modifier
            .padding(top = state.topPadding)
            .padding(horizontal = 16.dp)
            .onSizeChanged {
                state.toolbarHeight = it.height.toFloat()
            }
            .offset {
                IntOffset(0, state.topBarOffset.roundToInt())
            }
    ) {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.surface,
            modifier = Modifier
                .height(48.dp)
                .clickable { }
        ) {
            Row {
                IconImageButton(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = "Menu",
                    onClick = onClickNavigationIcon
                )
                Text(
                    text = "メモを検索",
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                )
                val gridIcon = when (displayType) {
                    DisplayType.Staggered -> Icons.Filled.GridView
                    DisplayType.Linear -> Icons.Outlined.Splitscreen
                }
                IconImageButton(
                    imageVector = gridIcon,
                    contentDescription = "Grid",
                    onClick = onClickGridButton,
                )
                IconImageButton(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Account",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTopBar() {
    ComposekeepTheme {
        TopBar()
    }
}

@Composable
internal fun rememberTopBarState(topPadding: Dp = 0.dp) =
    remember(topPadding) {
        TopBarState(topPadding)
    }

internal class TopBarState(
    val topPadding: Dp,
) {
    var topBarOffset by mutableStateOf(0f)
        private set

    var toolbarHeight by mutableStateOf(0f)

    val nestedScrollConnection: NestedScrollConnection
        @Composable get() {
            val statusBarHeight = statusBarHeight()
            val paddingPx = with(LocalDensity.current) { topPadding.toPx() }
            val topBarHeight = toolbarHeight + statusBarHeight + paddingPx
            return object : NestedScrollConnection {
                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                    val delta = available.y
                    val newOffset = topBarOffset + delta
                    topBarOffset = newOffset.coerceIn(-topBarHeight, 0f)
                    return super.onPreScroll(available, source)
                }
            }
        }

    @Composable
    private fun statusBarHeight(): Int {
        val inset = LocalWindowInsets.current
        return with(inset.statusBars) { top - bottom }
    }
}
