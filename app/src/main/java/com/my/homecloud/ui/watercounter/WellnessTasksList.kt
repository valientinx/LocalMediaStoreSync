package com.my.homecloud.ui.watercounter

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // so how to use different items functions here?
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import kotlinx.coroutines.withContext




@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    onCloseTask: (WellnessTask) -> Unit,
    list: List<WellnessTask> = remember { getWellnessTasks() }
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list,
            key = { task -> task.id }
        ) { task ->
            WellnessTaskItem(taskName = task.label, onClose = { onCloseTask(task)})
        }
    }

}
