package com.example.compose_keep.ui.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.MicNone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.compose_keep.ui.common.IconImageButton
import com.example.compose_keep.ui.preview.ThemeParameter
import com.example.compose_keep.ui.preview.ThemeProvider
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding

enum class BottomBarItems {
    CheckBox, Draw, Mic, Image
}

private val BottomBarItems.icon: ImageVector
    get() = when (this) {
        BottomBarItems.CheckBox -> Icons.Outlined.CheckBox
        BottomBarItems.Draw -> Icons.Filled.Brush
        BottomBarItems.Mic -> Icons.Outlined.MicNone
        BottomBarItems.Image -> Icons.Outlined.Image
    }

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    height: Dp = 54.dp,
    onClickItem: (BottomBarItems) -> Unit = {},
) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        modifier = modifier.navigationBarsHeight(additional = height),
        cutoutShape = RoundedCornerShape(30)
    ) {
        CompositionLocalProvider(LocalContentAlpha provides 1f) {
            Row(modifier = Modifier.navigationBarsPadding()) {
                BottomBarItems.values().forEach { item ->
                    IconImageButton(
                        imageVector = item.icon,
                        contentDescription = item.name,
                        onClick = {
                            onClickItem(item)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBottomBar(
    @PreviewParameter(ThemeParameter::class) themeProvider: ThemeProvider,
) {
    themeProvider {
        BottomBar()
    }
}