package com.my.homecloud.ui.mediaitems

import android.content.ContentResolver
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


const val TAG: String = "MediaItemScreen"


@Composable
fun MediaItemScreen(
    modifier: Modifier = Modifier,
    mediaItemViewModel: MediaItemViewModel = viewModel(),
    cResolver: ContentResolver
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .background(Color.Gray)
    ) {
        val state = mediaItemViewModel.viewState.collectAsState()

        LaunchedEffect(Unit) {
            mediaItemViewModel.startLoadingImages(cResolver)
        }

        MediaItemList(list = state.value)

    }
}
