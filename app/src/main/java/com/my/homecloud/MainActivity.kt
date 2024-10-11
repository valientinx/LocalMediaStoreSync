package com.my.homecloud

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
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
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
           setOurUiContent()
        } else {
            // You can directly ask for the permission.
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun setOurUiContent() {
        setContent {
            LocalMediaStoreSyncTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
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