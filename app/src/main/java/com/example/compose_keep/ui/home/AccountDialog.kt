package com.example.compose_keep.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.compose_keep.ui.common.Logo
import com.example.compose_keep.ui.preview.ThemeExecutor
import com.example.compose_keep.ui.preview.ThemeProvider

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AccountDialog(
    onDismissRequest: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        AccountDialogBody(
            onClickClose = onDismissRequest
        )
    }
}

@Composable
private fun AccountDialogBody(
    onClickClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        CompositionLocalProvider(LocalContentAlpha provides 1f) {
            Column {
                TopBar(onClickClose = onClickClose)
                AccountItem(
                    icon = Icons.Filled.AccountCircle,
                    name = "Name",
                    mailAddress = "example@example.com",
                )
                Spacer(modifier = Modifier.height(8.dp))
                AccountManageButton(
                    onClick = {},
                )
                Spacer(modifier = Modifier.height(32.dp))
                Divider()
                MenuItem(
                    imageVector = Icons.Filled.PersonAddAlt,
                    description = "別のアカウントを追加",
                    onClick = {}
                )
                MenuItem(
                    imageVector = Icons.Outlined.ManageAccounts,
                    description = "このデバイスのアカウントを管理",
                    onClick = {}
                )
                Divider()
                BottomMenu(
                    onClickPrivacyPolicy = { /*TODO*/ },
                    onClickTermsOfUse = { /*TODO*/ },
                )
            }
        }

    }
}

@Composable
private fun AccountManageButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Spacer(modifier = Modifier.width(64.dp))
        Surface(
            modifier = modifier,
            border = BorderStroke(1.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.12f)),
            shape = RoundedCornerShape(50),
            color = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground
        ) {
            Text(
                text = "Googleアカウントを管理",
                style = MaterialTheme.typography.button,
                modifier = Modifier
                    .clickable { onClick() }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
private fun TopBar(
    onClickClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = onClickClose,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "close",
            )
        }
        Logo(
            modifier = Modifier
                .align(Alignment.Center)
                .height(24.dp)
        )
    }
}

@Composable
private fun AccountItem(
    icon: ImageVector,
    name: String,
    mailAddress: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier.size(64.dp)) {
            Icon(
                imageVector = icon,
                contentDescription = "icon",
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.Center)
            )
        }

        Column {
            Text(
                text = name,
                style = MaterialTheme.typography.subtitle1,
            )
            Text(
                text = mailAddress,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
private fun MenuItem(
    imageVector: ImageVector,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .width(64.dp)
                .padding(vertical = 16.dp)
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = description,
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.Center),
            )
        }
        Text(
            text = description,
            style = MaterialTheme.typography.subtitle2,
        )
    }
}

@Composable
private fun BottomMenu(
    onClickPrivacyPolicy: () -> Unit,
    onClickTermsOfUse: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        BottomMenuItem(
            title = "プライバシーポリシー",
            onClick = onClickPrivacyPolicy
        )
        Text(text = "・")
        BottomMenuItem(
            title = "利用規約",
            onClick = onClickTermsOfUse
        )
    }
}

@Composable
private fun BottomMenuItem(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .clip(CircleShape.copy(CornerSize(10)))
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .clickable { onClick() }
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAccountDialog(
    @PreviewParameter(ThemeProvider::class) themeExecutor: ThemeExecutor
) {
    themeExecutor {
        AccountDialogBody(
            onClickClose = {}
        )
    }
}
