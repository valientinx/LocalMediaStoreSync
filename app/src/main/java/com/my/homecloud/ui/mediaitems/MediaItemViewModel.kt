package com.my.homecloud.ui.mediaitems

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class MediaItemViewModel : ViewModel() {
    private val _mediaItems = getMediaItems().toMutableStateList()
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

}

private fun getMediaItems() = List(30) { i -> MediaItemData(i, "Task # $i") }