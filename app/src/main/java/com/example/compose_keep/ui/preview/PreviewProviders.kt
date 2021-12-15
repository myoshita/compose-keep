package com.example.compose_keep.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class DarkThemeProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(false, true)
}
