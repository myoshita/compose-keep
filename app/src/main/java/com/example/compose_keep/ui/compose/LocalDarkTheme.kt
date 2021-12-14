package com.example.compose_keep.ui.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

val LocalDarkTheme = ProvidableCompositionDarkTheme()

@Composable
fun ProvideDarkTheme(isDarkTheme: Boolean?, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalDarkTheme provides isDarkTheme) {
        content()
    }
}

@JvmInline
value class ProvidableCompositionDarkTheme(
    private val delegate: ProvidableCompositionLocal<Boolean?> = compositionLocalOf { null }
) {
    val current: Boolean
        @Composable get() = delegate.current ?: isSystemInDarkTheme()

    infix fun provides(isDarkTheme: Boolean?) = delegate provides isDarkTheme
}