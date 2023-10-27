package com.my.homecloud

import android.Manifest
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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

    companion object {
        val TAG = "MainActivity"
    }

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
        modifier: Modifier = Modifier, names: List<String> = listOf("World", "Compose")
    ) {


        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.secondary,
        ) {

            Column(
                modifier = modifier.padding(vertical = 24.dp)
            ) {
                for (name in names) {
                    Greetings()
                }
            }
        }
    }

//    @Composable
//    fun OnboardingScreen(modifier: Modifier = Modifier) {
//        var shouldShowOnboarding by remember { mutableStateOf(true) }
//    }

    @Composable
    fun Greeting(name: String) {
        var expanded by rememberSaveable { mutableStateOf(false) }
        val extraPadding by animateDpAsState(
            if (expanded) 48.dp else 0.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            ),
            label = "expandRowAnimation"
        )
        val extraColor by animateColorAsState(
            if (expanded) MaterialTheme.colorScheme.secondary
            else MaterialTheme.colorScheme.primary,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            ),
            label = "expandRowAnimation"
        )

        Surface(color = extraColor,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            content = {
                Row(modifier = Modifier.padding(24.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(
                                bottom = extraPadding.coerceAtLeast(0.dp)
                            )
                    ) {
                        Text(text = "Hello $name!")
                        Text(text = "Hello 2 $name!", style = MaterialTheme.typography.labelSmall)
                    }
                    ElevatedButton(onClick = {
                        expanded = !expanded
                        Log.d(TAG, "Expanded $expanded")
                    }) {
                        Text(if (expanded) "Show less" else "Show more")
                    }
                }
            })
    }

    @Composable
    private fun Greetings(
        modifier: Modifier = Modifier,
        names: List<String> = List(1000) { "$it" }
    ) {
        LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
            items(items = names) { name ->
                Greeting(name = name)
            }
        }
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