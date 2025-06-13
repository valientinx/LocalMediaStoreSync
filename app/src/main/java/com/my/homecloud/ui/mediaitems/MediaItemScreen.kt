package com.my.homecloud.ui.mediaitems

import android.content.ContentResolver
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Text
import com.my.homecloud.intent.MediaItemIntent
import com.my.homecloud.state.MediaItemState


const val TAG: String = "MediaItemScreen"


@Composable
fun MediaItemScreen(
    modifier: Modifier = Modifier,
    mediaItemViewModel: MediaItemViewModel = viewModel(),
    cResolver: ContentResolver
) {
    val state = mediaItemViewModel.state.collectAsState()
    val eventFlow = mediaItemViewModel.event

    LaunchedEffect(Unit) {
        mediaItemViewModel.processIntent(MediaItemIntent.LoadImages, cResolver)
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .background(Color.Gray)
    ) {
        when (val s = state.value) {
            is MediaItemState.Idle -> Text(text = "Idle")
            is MediaItemState.Loading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { Text(text = "Loading...") }
            is MediaItemState.Loaded -> {
                if (s.items.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) { Text(text = "No images found") }
                } else {
                    MediaItemList(list = s.items)
                }
            }
            is MediaItemState.Error -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { Text(text = s.message) }
        }
    }
    // Exemplu de colectare evenimente (snackbar, toast etc.)
    /*
    LaunchedEffect(eventFlow) {
        eventFlow.collect { event ->
            when (event) {
                is MediaItemEvent.ShowMessage -> // Afiseaza mesaj
            }
        }
    }
    */
}
