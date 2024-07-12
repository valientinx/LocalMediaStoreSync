package com.my.homecloud.ui.mediaitems

import android.content.ContentResolver
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
            .background(Color.Green)
    ) {
        val state = mediaItemViewModel.viewState.collectAsState()

        LaunchedEffect(Unit) {
            mediaItemViewModel.startLoadingImages(cResolver)
        }

        MediaItemList(list = state.value,
            onCloseTask = { mediaItem -> mediaItemViewModel.remove(mediaItem) },
            onCheckMedia = { mediaItem, checked ->
                mediaItemViewModel.changeTaskChecked(
                    mediaItem,
                    checked
                )
            })
    }
}
