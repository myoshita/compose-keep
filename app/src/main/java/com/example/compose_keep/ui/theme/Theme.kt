package com.example.compose_keep.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.example.compose_keep.ui.compose.LocalDarkTheme

private val DarkColorPalette = darkColors(
    primary = Blue200,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Gray900,
    surface = BlueGray900,
    onSurface = BlueGray100,
    onBackground = BlueGray50,
    onSecondary = Gray600,
)

private val LightColorPalette = lightColors(
    primary = LightBlue900,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = BlueGray50,
    surface = Indigo50,
    onSurface = Gray700,
    onBackground = Gray900,
    onSecondary = Gray400,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ComposekeepTheme(
    darkTheme: Boolean = LocalDarkTheme.current,
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}