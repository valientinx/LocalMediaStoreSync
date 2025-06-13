package com.my.homecloud.event

sealed class MediaItemEvent {
    data class ShowMessage(val message: String) : MediaItemEvent()
    // Alte evenimente unice (ex: navigare, dialoguri etc.)
}

