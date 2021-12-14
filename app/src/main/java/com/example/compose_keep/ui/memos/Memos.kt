package com.example.compose_keep.ui.memos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_keep.model.Memo
import com.example.compose_keep.model.MemoId
import com.example.compose_keep.ui.common.StaggeredVerticalGrid
import com.example.compose_keep.ui.theme.ComposekeepTheme
import com.google.accompanist.insets.statusBarsHeight

enum class DisplayType {
    Linear, Staggered
}

@Composable
fun Memos(
    memos: List<Memo>,
    displayType: DisplayType,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier,
    ) {
        item {
            Spacer(modifier = Modifier.statusBarsHeight(additional = 48.dp + 8.dp))
        }
        when (displayType) {
            DisplayType.Linear -> {
                item {
                    Spacer(modifier = Modifier.size(8.dp))
                }
                items(memos) {
                    MemoItem(memo = it, modifier = Modifier.padding(4.dp))
                }
                item {
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
            DisplayType.Staggered -> {
                item {
                    StaggeredVerticalGrid(
                        maxColumnWidth = 220.dp,
                        modifier = modifier
                            .padding(vertical = 8.dp)
                    ) {
                        memos.forEach {
                            MemoItem(
                                memo = it,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }
                }
            }
        }
    }

}

@Composable
private fun MemoItem(
    memo: Memo,
    modifier: Modifier = Modifier,
    onClick: (Memo) -> Unit = {},
) {
    val strokeColor = MaterialTheme.colors.onSecondary
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10),
        border = BorderStroke(1.dp, strokeColor),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .clickable { onClick(memo) }
                .padding(16.dp)
        ) {
            Text(text = memo.title)
            Text(
                text = memo.description,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMemoItem() {
    ComposekeepTheme {
        MemoItem(memo = Memo(MemoId(1), "title", "description"))
    }
}
