package com.example.compose_keep.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
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
import androidx.compose.ui.unit.dp
import com.example.compose_keep.ui.theme.ComposekeepTheme

enum class DrawerItems(val icon: ImageVector, val title: String) {
    Memo(Icons.Outlined.Lightbulb, "メモ"),
    Reminder(Icons.Filled.NotificationsNone, "リマインダー"),
    Create(Icons.Filled.Add, "新しいラベルを作成"),
    Archive(Icons.Outlined.Archive, "アーカイブ"),
    Delete(Icons.Outlined.DeleteOutline, "ゴミ箱"),
    Settings(Icons.Outlined.Settings, "設定"),
    HelpAndFeedback(Icons.Outlined.HelpOutline, "ヘルプとフィードバック")
}

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Google Keep",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(16.dp)
        )
        DrawerItems.values().forEach {
            DrawerItem(
                imageVector = it.icon,
                text = it.title,
                isSelected = it == DrawerItems.Memo // TODO
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
        shape = CircleShape.copy(
            topStart = CornerSize(0),
            bottomStart = CornerSize(0),
            topEnd = CornerSize(50),
            bottomEnd = CornerSize(50)
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
private fun PreviewDrawerContent() {
    ComposekeepTheme {
        DrawerContent()
    }
}
