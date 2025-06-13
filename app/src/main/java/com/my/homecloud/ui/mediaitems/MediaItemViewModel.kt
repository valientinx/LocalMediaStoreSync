package com.my.homecloud.ui.mediaitems

import android.content.ContentResolver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.homecloud.event.MediaItemEvent
import com.my.homecloud.intent.MediaItemIntent
import com.my.homecloud.state.MediaItemState
import com.my.homecloud.ui.data.ImageCoLoader
import com.my.homecloud.ui.data.ImageLoadedListener
import com.my.homecloud.ui.data.MediaStoreImage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MediaItemViewModel : ViewModel() {
    companion object {
        private const val TAG = "MediaItemViewModel"
    }

    private val _state = MutableStateFlow<MediaItemState>(MediaItemState.Idle)
    val state: StateFlow<MediaItemState> = _state.asStateFlow()

    private val _event = Channel<MediaItemEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    private val _mediaItems = mutableListOf<MediaItemData>()

    fun processIntent(intent: MediaItemIntent, contentResolver: ContentResolver) {
        when (intent) {
            is MediaItemIntent.LoadImages -> loadImages(contentResolver)
            is MediaItemIntent.RemoveImage -> removeImage(intent.item)
        }
    }

    private fun loadImages(contentResolver: ContentResolver) {
        _state.value = MediaItemState.Loading
        viewModelScope.launch {
            val loader = ImageCoLoader(viewModelScope, object : ImageLoadedListener {
                override fun onDataLoaded(imageList: List<MediaStoreImage>) {
                    _mediaItems.clear()
                    _mediaItems.addAll(imageList.map {
                        MediaItemData(
                            it.id.toInt(),
                            it.displayName,
                            it.contentUri
                        )
                    })
                    _state.value = MediaItemState.Loaded(_mediaItems)
                }
            })
            try {
                loader.loadImages(contentResolver)
            } catch (e: Exception) {
                _state.value = MediaItemState.Error(e.message ?: "Unknown error")
                _event.trySend(MediaItemEvent.ShowMessage(e.message ?: "Unknown error"))
            }
        }
    }

    private fun removeImage(item: MediaItemData) {
        _mediaItems.remove(item)
        _state.value = MediaItemState.Loaded(_mediaItems)
        viewModelScope.launch {
            _event.send(MediaItemEvent.ShowMessage("Image removed"))
        }
    }
}
