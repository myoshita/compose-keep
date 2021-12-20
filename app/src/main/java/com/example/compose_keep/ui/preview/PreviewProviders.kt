package com.example.compose_keep.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.compose_keep.ui.compose.ProvideDarkTheme
import com.example.compose_keep.ui.theme.ComposekeepTheme
import com.google.accompanist.insets.ProvideWindowInsets

class ThemeParameter : PreviewParameterProvider<ThemeProvider> {
    override val values: Sequence<ThemeProvider>
        get() = sequenceOf(ThemeProviderImpl(true), ThemeProviderImpl((false)))
}

interface ThemeProvider {
    @Composable
    operator fun invoke(content: @Composable () -> Unit)
}

@JvmInline
private value class ThemeProviderImpl(private val darkTheme: Boolean) : ThemeProvider {
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
