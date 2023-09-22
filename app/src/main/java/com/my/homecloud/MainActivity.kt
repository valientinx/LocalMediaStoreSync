package com.my.homecloud

import android.Manifest
import android.content.IntentSender
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecodelab.ui.theme.LocalMediaStoreSyncTheme

/** The request code for requesting [Manifest.permission.READ_EXTERNAL_STORAGE] permission. */
private const val READ_EXTERNAL_STORAGE_REQUEST = 0x1045

/**
 * Code used with [IntentSender] to request user permission to delete an image with scoped storage.
 */
private const val DELETE_PERMISSION_REQUEST = 0x1033

class MainActivity : ComponentActivity() {

//    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocalMediaStoreSyncTheme {
                // A surface container using the 'background' color from the theme

                MyApp(modifier = Modifier.fillMaxSize())

            }
        }
    }

    @Composable
    private fun MyApp(
        modifier: Modifier = Modifier,
        names: List<String> = listOf("World", "Compose")
    ) {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.primary,
        ) {

            Column(modifier = modifier.padding(vertical = 4.dp)) {
                for (name in names) {
                    Greeting(name = name)
                }
            }


        }
    }

    @Composable
    fun Greeting(name: String) {
        Surface(color = MaterialTheme.colorScheme.secondary, modifier = Modifier.wrapContentSize(),
            content = {
                Row(modifier = Modifier.padding(24.dp)) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(text = "Hello $name!")
                        Text(text = "Hello 2 $name!")
                    }
                    ElevatedButton(
                        onClick = { /* TODO */ }
                    ) {
                        Text("Show more")
                    }
                }
            })
    }


// preview methods

//    @Preview(showBackground = true)
//    @Composable
//    fun DefaultPreview() {
//        LocalMediaStoreSyncTheme {
//            Greeting("Android")
//        }
//    }

    @Preview
    @Composable
    fun MyAppPreview() {
        LocalMediaStoreSyncTheme {
            MyApp()
        }
    }
}