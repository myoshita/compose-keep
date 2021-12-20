package com.example.compose_keep.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.compose_keep.ui.compose.ProvideDarkTheme
import com.example.compose_keep.ui.theme.ComposekeepTheme
import com.google.accompanist.insets.ProvideWindowInsets

class ThemeProvider : PreviewParameterProvider<ThemeExecutor> {
    override val values: Sequence<ThemeExecutor>
        get() = sequenceOf(ThemeExecutorImpl(true), ThemeExecutorImpl((false)))
}

interface ThemeExecutor {
    @Composable
    operator fun invoke(content: @Composable () -> Unit)
}

@JvmInline
private value class ThemeExecutorImpl(private val darkTheme: Boolean) : ThemeExecutor {
    @Composable
    override operator fun invoke(content: @Composable () -> Unit) {
        ProvideWindowInsets {
            ProvideDarkTheme(isDarkTheme = darkTheme) {
                ComposekeepTheme {
                    content()
                }
            }
        }
    }
}
