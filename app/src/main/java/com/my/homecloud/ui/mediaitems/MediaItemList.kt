package com.my.homecloud.ui.mediaitems

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // so how to use different items functions here?
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun MediaItemList(
    modifier: Modifier = Modifier,
    onCloseTask: (MediaItemData) -> Unit,
    onCheckMedia: (MediaItemData, Boolean) -> Unit,
    list: List<MediaItemData>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list,
            key = { task -> task.id }
        ) { task ->
            MediaItemView(taskName = task.label,
                onClose = { onCloseTask(task) },
                checked = task.checked.value,
                onCheckedChange = { onCheckMedia(task, it) },)
        }
    }

}
