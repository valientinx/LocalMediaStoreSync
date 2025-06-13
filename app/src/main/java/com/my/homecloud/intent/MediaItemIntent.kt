package com.my.homecloud.intent

import com.my.homecloud.ui.mediaitems.MediaItemData

sealed class MediaItemIntent {
    object LoadImages : MediaItemIntent()
    data class RemoveImage(val item: MediaItemData) : MediaItemIntent()
    // Adaugă aici alte intenții dacă este nevoie
}

