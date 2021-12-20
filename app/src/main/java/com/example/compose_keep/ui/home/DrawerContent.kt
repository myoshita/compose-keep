package com.example.compose_keep.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_keep.ui.common.Logo
import com.example.compose_keep.ui.preview.ThemeParameter
import com.example.compose_keep.ui.preview.ThemeProvider

enum class DrawerItems {
    Memo,
    Reminder,
    Create,
    Archive,
    Delete,
    Settings,
    HelpAndFeedback,
}

private val DrawerItems.icon: ImageVector
    get() = when (this) {
        DrawerItems.Memo -> Icons.Outlined.Lightbulb
        DrawerItems.Reminder -> Icons.Filled.NotificationsNone
        DrawerItems.Create -> Icons.Filled.Add
        DrawerItems.Archive -> Icons.Outlined.Archive
        DrawerItems.Delete -> Icons.Outlined.DeleteOutline
        DrawerItems.Settings -> Icons.Outlined.Settings
        DrawerItems.HelpAndFeedback -> Icons.Outlined.HelpOutline
    }

private val DrawerItems.title: String
    get() = when (this) {
        DrawerItems.Memo -> "メモ"
        DrawerItems.Reminder -> "リマインダー"
        DrawerItems.Create -> "新しいラベルを作成"
        DrawerItems.Archive -> "アーカイブ"
        DrawerItems.Delete -> "ゴミ箱"
        DrawerItems.Settings -> "設定"
        DrawerItems.HelpAndFeedback -> "ヘルプとフィードバック"
    }

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    onClickDrawerItem: (DrawerItems) -> Unit = {},
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.padding(16.dp),
        ) {
            Logo(
                modifier = Modifier
                    .height(24.dp)
                    .align(Alignment.Bottom)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Keep",
                fontSize = 22.sp,
            )
        }
        DrawerItems.values().forEach {
            DrawerItem(
                imageVector = it.icon,
                text = it.title,
                isSelected = it == DrawerItems.Memo, // TODO
                onClick = {
                    onClickDrawerItem(it)
                }
            )
        }
    }
}

@Composable
private fun DrawerItem(
    imageVector: ImageVector,
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val backgroundColor = if (isSelected) {
        MaterialTheme.colors.surface
    } else {
        MaterialTheme.colors.background
    }
    val accentColor = if (isSelected) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.onBackground
    }
    Surface(
        shape = RoundedCornerShape(
            topEndPercent = 50,
            bottomEndPercent = 50
        ),
        color = backgroundColor,
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .padding(start = 16.dp)
        ) {
            Box(modifier = Modifier.size(48.dp)) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = text,
                    tint = accentColor,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Text(
                text = text,
                modifier = Modifier.align(Alignment.CenterVertically),
                color = accentColor,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDrawerContent(
    @PreviewParameter(ThemeParameter::class) themeProvider: ThemeProvider,
) {
    themeProvider {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            DrawerContent()
        }
    }
}
