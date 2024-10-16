package com.my.homecloud.ui.mediaitems

import android.content.ContentResolver
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.homecloud.ui.data.ImageCoLoader
import com.my.homecloud.ui.data.ImageLoadedListener
import com.my.homecloud.ui.data.MediaStoreImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MediaItemViewModel : ViewModel() {
    private val _mediaItems = mutableListOf<MediaItemData>()

    private val _viewState = MutableStateFlow(listOf<MediaItemData>())
    val viewState: StateFlow<List<MediaItemData>> = _viewState.asStateFlow()

    fun remove(item: MediaItemData) {
        _mediaItems.remove(item)
        _viewState.update { _mediaItems }
    }

    fun startLoadingImages(contentResolver: ContentResolver) = viewModelScope.launch {
        val loader = ImageCoLoader(viewModelScope, object : ImageLoadedListener {
            override fun onDataLoaded(imageList: List<MediaStoreImage>) {
                _mediaItems.addAll(imageList.map {
                    Log.d(TAG, "onDataLoaded: ${it.contentUri}")
                    MediaItemData(
                        it.id.toInt(),
                        it.displayName,
                        it.contentUri
                    )
                })
                // update ui
                _viewState.update { _mediaItems }
            }
        })
        loader.loadImages(contentResolver)
    }
}
