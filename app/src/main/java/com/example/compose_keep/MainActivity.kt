package com.example.compose_keep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import com.example.compose_keep.ui.compose.ProvideDarkTheme
import com.example.compose_keep.ui.home.HomeScreen
import com.example.compose_keep.ui.theme.ComposekeepTheme
import com.google.accompanist.insets.ProvideWindowInsets

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val darkTheme by viewModel.darkTheme.collectAsState()
            ProvideWindowInsets {
                ProvideDarkTheme(isDarkTheme = darkTheme) {
                    ComposekeepTheme {
                        HomeScreen()
                    }
                }
            }
        }
    }
}
