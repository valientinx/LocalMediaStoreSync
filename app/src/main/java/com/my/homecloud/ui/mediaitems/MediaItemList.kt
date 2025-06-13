package com.my.homecloud.ui.mediaitems

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // so how to use different items functions here?
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MediaItemList(
    modifier: Modifier = Modifier,
    list: List<MediaItemData>
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth().background(Color.Cyan)
    ) {
        items(
            items = list,
            key = { item -> item.id }
        ) { item ->
            MediaItemView(
                taskName = item.label,
                imageUri = item.uri,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
