package com.example.compose_keep.ui.home

import androidx.lifecycle.ViewModel
import com.example.compose_keep.model.Memo
import com.example.compose_keep.model.MemoId
import com.example.compose_keep.ui.memos.DisplayType
import kotlin.random.Random
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val _memos = MutableStateFlow<List<Memo>>(emptyList())
    val memos = _memos.asStateFlow()

    private val _displayType = MutableStateFlow(DisplayType.Staggered)
    val displayType = _displayType.asStateFlow()

    init {
        _memos.value = List(20) {
            Memo(
                id = MemoId(it),
                title = "Title".repeat(Random.nextInt(0, 10)),
                description = "description".repeat(Random.nextInt(1, 10))
            )
        }
    }

    fun onClickGridButton() {
        _displayType.update {
            when (it) {
                DisplayType.Staggered -> DisplayType.Linear
                DisplayType.Linear -> DisplayType.Staggered
            }
        }
    }
}