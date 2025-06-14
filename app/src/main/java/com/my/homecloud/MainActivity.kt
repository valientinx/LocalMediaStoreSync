package com.my.homecloud

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.composecodelab.ui.theme.LocalMediaStoreSyncTheme
import com.my.homecloud.ui.mediaitems.MediaItemScreen

class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your app.
                setOurUiContent()
            } else {
                // Explain to the user that the feature is unavailable because the
                // features require a permission that the user has denied.
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            Log.d(TAG, "onCreate setOurUiContent")
           setOurUiContent()
        } else {
            Log.d(TAG, "onCreate requestPermissionLauncher")
            // You can directly ask for the permission.
            requestPermissionLauncher.launch(permission)
        }
        Log.d(TAG, "onCreate end")
    }

    private fun setOurUiContent() {
        setContent {
            LocalMediaStoreSyncTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
        Log.d(TAG, "setOurUiContent end")
    }

    @Composable
    private fun MyApp(
        modifier: Modifier = Modifier, names: List<String> = listOf("World", "Compose")
    ) {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.secondary,
        ) {
            MediaItemScreen(cResolver = contentResolver)
        }
    }

    @Preview
    @Composable
    fun MyAppPreview() {
        LocalMediaStoreSyncTheme {
            MyApp()
        }
    }
}

