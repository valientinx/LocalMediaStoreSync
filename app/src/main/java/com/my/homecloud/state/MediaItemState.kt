package com.my.homecloud.state

sealed class MediaItemState {
    object Idle : MediaItemState()
    object Loading : MediaItemState()
    data class Loaded(val items: List<com.my.homecloud.ui.mediaitems.MediaItemData>) : MediaItemState()
    data class Error(val message: String) : MediaItemState()
}

