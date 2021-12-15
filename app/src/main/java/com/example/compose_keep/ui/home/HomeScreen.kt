package com.example.compose_keep.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.MicNone
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.compose_keep.ui.common.IconImageButton
import com.example.compose_keep.ui.compose.LocalDarkTheme
import com.example.compose_keep.ui.memos.DisplayType
import com.example.compose_keep.ui.memos.Memos
import com.example.compose_keep.ui.preview.DarkThemeProvider
import com.example.compose_keep.ui.theme.BlueGray800
import com.example.compose_keep.ui.theme.ComposekeepTheme
import com.google.accompanist.insets.navigationBarsHeight
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
    HomeScreen(
        memos = memos,
        displayType = displayType,
        onClickGridButton = homeViewModel::onClickGridButton,
        onClickAddFab = mainViewModel::onChangeTheme,
    )
}

@Composable
private fun HomeScreen(
    memos: List<Memo>,
    displayType: DisplayType,
    onClickGridButton: () -> Unit = {},
    onClickAddFab: () -> Unit = {},
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val topBarState = rememberTopBarState(topPadding = 8.dp)

    Scaffold(
        modifier = Modifier.nestedScroll(topBarState.nestedScrollConnection),
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomBar()
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
                    onClickNavigationIcon = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    },
                    onClickGridButton = onClickGridButton
                )
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun BottomBar(
    modifier: Modifier = Modifier,
    onClickCheckBox: () -> Unit = {},
    onClickDraw: () -> Unit = {},
    onClickMic: () -> Unit = {},
    onClickImage: () -> Unit = {},
) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        modifier = modifier.navigationBarsHeight(additional = 54.dp),
        cutoutShape = RoundedCornerShape(30)
    ) {
        Row(modifier = Modifier.navigationBarsPadding()) {
            IconImageButton(
                imageVector = Icons.Outlined.CheckBox,
                contentDescription = "check",
                onClick = onClickCheckBox
            )

            IconImageButton(
                Icons.Filled.Brush,
                contentDescription = "draw",
                onClick = onClickDraw
            )

            IconImageButton(
                Icons.Outlined.MicNone,
                contentDescription = "mic",
                onClick = onClickMic
            )

            IconImageButton(
                Icons.Outlined.Image,
                contentDescription = "image",
                onClick = onClickImage
            )
        }
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
    @PreviewParameter(DarkThemeProvider::class) darkTheme: Boolean,
) {
    ComposekeepTheme(darkTheme = darkTheme) {
        val memos = List(20) {
            Memo(
                id = MemoId(it),
                title = "title $it",
                description = "description".repeat(Random.nextInt(1, 10))
            )
        }
        HomeScreen(
            memos = memos,
            displayType = DisplayType.Staggered,
            onClickGridButton = {},
        )
    }
}
