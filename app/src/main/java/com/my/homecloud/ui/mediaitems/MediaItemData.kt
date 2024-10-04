package com.my.homecloud.ui.mediaitems

import android.net.Uri

data class MediaItemData(
    val id: Int,
    val label: String,
    val uri: Uri
) {}