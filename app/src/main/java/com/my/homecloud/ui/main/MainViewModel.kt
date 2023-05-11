package com.my.homecloud.ui.main

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.*

class MainViewModel() : ViewModel(), ImageLoadedListener {


    private val _images = MutableLiveData<List<MediaStoreImage>>()
    // ??
    val images: LiveData<List<MediaStoreImage>> get() = _images

//    private var contentObserver: ContentObserver? = null

    private lateinit var  imageCoLoader: ImageCoLoader

    init {
        imageCoLoader = ImageCoLoader( viewModelScope, this)
    }

    /**
     * Performs a one shot load of images from [MediaStore.Images.Media.EXTERNAL_CONTENT_URI] into
     * the [_images] [LiveData] above.
     */
    fun loadImages(contentResolver: ContentResolver) {
        imageCoLoader.loadImages(contentResolver)
    }

    /**
     * Since we register a [ContentObserver], we want to unregister this when the `ViewModel`
     * is being released.
     */
    override fun onCleared() {
        imageCoLoader.onCleared()
    }

    override fun onDataLoaded(imageList: List<MediaStoreImage>) {
        Log.i(TAG, "Loaded ${imageList.size} images")
//        _images.postValue(imageList)
    }
}

/**
 * Convenience extension method to register a [ContentObserver] given a lambda.
 */
private fun ContentResolver.registerObserver(
    uri: Uri,
    observer: (selfChange: Boolean) -> Unit
): ContentObserver {
    val contentObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
        override fun onChange(selfChange: Boolean) {
            observer(selfChange)
        }
    }
    registerContentObserver(uri, true, contentObserver)
    return contentObserver
}


    private const val TAG = "MainActivityVM"