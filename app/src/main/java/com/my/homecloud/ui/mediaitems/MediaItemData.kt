package com.my.homecloud.ui.mediaitems

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class MediaItemData (val id: Int,
                          val label: String,
                          val checked: MutableState<Boolean> = mutableStateOf(false)){}