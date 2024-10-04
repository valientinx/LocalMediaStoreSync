package com.my.homecloud.ui.mediaitems

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // so how to use different items functions here?
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MediaItemList(
    modifier: Modifier = Modifier,
    list: List<MediaItemData>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list,
            key = { item -> item.id }
        ) { item ->
            MediaItemView(taskName = item.label, imageUri = item.uri)
        }
    }
}
