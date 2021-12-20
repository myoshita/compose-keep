package com.example.compose_keep.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_keep.MainViewModel
import com.example.compose_keep.model.Memo
import com.example.compose_keep.model.MemoId
import com.example.compose_keep.ui.compose.LocalDarkTheme
import com.example.compose_keep.ui.memos.DisplayType
import com.example.compose_keep.ui.memos.Memos
import com.example.compose_keep.ui.preview.ThemeParameter
import com.example.compose_keep.ui.preview.ThemeProvider
import com.example.compose_keep.ui.theme.BlueGray800
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.random.Random
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    mainViewModel: MainViewModel = viewModel(),
) {
    val memos by homeViewModel.memos.collectAsState()
    val displayType by homeViewModel.displayType.collectAsState()

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = LocalDarkTheme.current.not()
    val statusBarColor = MaterialTheme.colors.background.copy(alpha = 0.8f)
    SideEffect {
        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = useDarkIcons,
        )
    }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val topBarState = rememberTopBarState(topPadding = 8.dp)
    var showAccountDialog by remember { mutableStateOf(false) }

    HomeScreen(
        memos = memos,
        displayType = displayType,
        scaffoldState = scaffoldState,
        topBarState = topBarState,
        showAccountDialog = showAccountDialog,
        onClickGridButton = homeViewModel::onClickGridButton,
        onClickAddFab = mainViewModel::onChangeTheme,
        onClickOpenDrawer = {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        },
        onClickAccount = {
            showAccountDialog = true
        },
        accountDialogDismissRequest = {
            showAccountDialog = false
        }
    )

}

@Composable
private fun HomeScreen(
    memos: List<Memo>,
    displayType: DisplayType,
    scaffoldState: ScaffoldState,
    topBarState: TopBarState,
    showAccountDialog: Boolean,
    onClickGridButton: () -> Unit = {},
    onClickAddFab: () -> Unit = {},
    onClickOpenDrawer: () -> Unit = {},
    onClickAccount: () -> Unit = {},
    accountDialogDismissRequest: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.nestedScroll(topBarState.nestedScrollConnection),
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomBar(
                onClickItem = {

                }
            )
        },
        drawerContent = {
            DrawerContent(
                modifier = Modifier
                    .statusBarsPadding()
                    .navigationBarsPadding()
            )
        },
        drawerBackgroundColor = MaterialTheme.colors.background,
        drawerScrimColor = BlueGray800.copy(alpha = 0.32f),
        floatingActionButton = {
            AddButton(
                onClick = onClickAddFab
            )
        },
        isFloatingActionButtonDocked = true,
        content = { padding ->
            Box {
                Memos(
                    memos = memos,
                    displayType = displayType,
                    contentPadding = padding,
                )
                TopBar(
                    state = topBarState,
                    modifier = Modifier.statusBarsPadding(),
                    displayType = displayType,
                    onClickNavigationIcon = onClickOpenDrawer,
                    onClickGridButton = onClickGridButton,
                    onClickAccount = onClickAccount
                )
            }
        }
    )

    if (showAccountDialog) {
        AccountDialog(
            onDismissRequest = accountDialogDismissRequest
        )
    }
}

@Preview
@Composable
fun AddButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(30)
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "Add",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen(
    @PreviewParameter(ThemeParameter::class) themeProvider: ThemeProvider
) {
    themeProvider {
        val memos = List(20) {
            Memo(
                id = MemoId(it),
                title = "Title $it",
                description = "Description".repeat(Random.nextInt(1, 10))
            )
        }
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        val topBarState = rememberTopBarState(topPadding = 8.dp)
        var showAccountDialog by remember { mutableStateOf(false) }

        HomeScreen(
            memos = memos,
            displayType = DisplayType.Staggered,
            scaffoldState = scaffoldState,
            topBarState = topBarState,
            showAccountDialog = showAccountDialog,
            onClickGridButton = {},
            onClickOpenDrawer = {
                scope.launch { scaffoldState.drawerState.open() }
            },
            onClickAccount = {
                showAccountDialog = true
            },
            accountDialogDismissRequest = {
                showAccountDialog = false
            }
        )
    }
}
