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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel


const val TAG: String = "MediaItemScreen"
@Composable
fun StatelessCounter(title: String, count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .padding(16.dp)
        .background(Color.Blue)) {
        if (count > 0) {
            Text("You've had $count glasses of $title")
        }

        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun StatefullCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableIntStateOf(0) }
    var juiceCount by remember { mutableStateOf(0) }
    StatelessCounter(title = "water", count = count, onIncrement = { count++ }, modifier.padding(0.dp,0.dp,0.dp,0.dp))
//    StatelessCounter(title = "juice", count = juiceCount, onIncrement = { juiceCount++ }, modifier.padding(0.dp,0.dp,0.dp,0.dp))
}

@Composable
fun MediaItemScreen(modifier: Modifier = Modifier,
                    mediaItemViewModel: MediaItemViewModel = viewModel(),
                    cResolver: ContentResolver) {
    Column(modifier = modifier
        .padding(16.dp)
        .background(Color.Green)) {
        StatefullCounter(modifier)
//        val list = remember { mediaItemViewModel.mediaItems.toMutableStateList() }
        val state = mediaItemViewModel.viewState.collectAsState()
        val state2 = mediaItemViewModel.viewState.collectAsState()
        Log.d(TAG, "items ${state.value}")
        Log.v(TAG, "items2 ${state2.value}")
        MediaItemList(list = state.value,
            onCloseTask = { mediaItem -> mediaItemViewModel.remove(mediaItem)},
            onCheckMedia = { mediaItem, checked -> mediaItemViewModel.changeTaskChecked(mediaItem, checked) })
        // load only once at first refresh
        LaunchedEffect(Unit) {
            mediaItemViewModel.startLoadingImages(cResolver)
        }
    }
}
