package com.my.homecloud.ui.mediaitems

import android.content.ContentResolver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.homecloud.ui.data.ImageCoLoader
import com.my.homecloud.ui.data.ImageLoadedListener
import com.my.homecloud.ui.data.MediaStoreImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MediaItemViewModel : ViewModel() {
    private val _mediaItems = mutableListOf<MediaItemData>()

    private val _viewState = MutableStateFlow(listOf<MediaItemData>())
    val viewState: StateFlow<List<MediaItemData>> = _viewState.asStateFlow()

    fun remove(item: MediaItemData) {
        _mediaItems.remove(item)
        _viewState.update { _mediaItems }
    }
    fun changeTaskChecked(item: MediaItemData, checked: Boolean) {
        _mediaItems.find { it.id == item.id }?.let { task ->
            task.checked.value = checked
        }
    }

    fun startLoadingImages(contentResolver: ContentResolver) {
        val loader: ImageCoLoader = ImageCoLoader(viewModelScope, object : ImageLoadedListener {
            override fun onDataLoaded(imageList: List<MediaStoreImage>) {
                _mediaItems.addAll(imageList.map { MediaItemData(it.id.toInt(),  it.displayName) })
                // update ui
                _viewState.update { _mediaItems }
            }
        })
        loader.loadImages(contentResolver)
    }

}
