package com.my.homecloud.ui.mediaitems

import android.content.ContentResolver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.homecloud.ui.data.ImageCoLoader
import com.my.homecloud.ui.data.ImageLoadedListener
import com.my.homecloud.ui.data.MediaStoreImage

class MediaItemViewModel : ViewModel() {
    private val _mediaItems = mutableListOf<MediaItemData>()

    val mediaItems: List<MediaItemData>
        get() = _mediaItems


    fun remove(item: MediaItemData) {
        _mediaItems.remove(item)
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

            }
        })
        loader.loadImages(contentResolver)
    }

}
