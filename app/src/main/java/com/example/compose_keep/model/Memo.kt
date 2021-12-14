package com.example.compose_keep.model

@JvmInline
value class MemoId(val value: Int)

data class Memo(
    val id: MemoId,
    val title: String,
    val description: String,
)
