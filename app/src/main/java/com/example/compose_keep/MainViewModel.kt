package com.example.compose_keep

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _darkTheme = MutableStateFlow<Boolean?>(true) // TODO
    val darkTheme = _darkTheme.asStateFlow()

    fun onChangeTheme() {
        _darkTheme.update { it?.not() }
    }
}
