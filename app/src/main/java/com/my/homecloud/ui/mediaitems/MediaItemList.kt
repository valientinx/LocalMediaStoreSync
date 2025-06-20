package com.my.homecloud.ui.mediaitems

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // so how to use different items functions here?
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch

@Composable
fun MediaItemList(
    modifier: Modifier = Modifier,
    list: List<MediaItemData>
) {
    val context = LocalContext.current
    var selectedItem by remember { mutableStateOf<MediaItemData?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var isUploading by remember { mutableStateOf(false) }
    var uploadResult by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val serverUrl = "http://your-server-url/upload" // TODO: set your Ktor server upload endpoint

    if (showDialog && selectedItem != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Upload Image") },
            text = { Text("Do you want to upload this image?") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    isUploading = true
                    coroutineScope.launch {
                        val success = ImageUploader.uploadImage(context, selectedItem!!.uri, serverUrl)
                        isUploading = false
                        uploadResult = if (success) "Upload successful" else "Upload failed"
                    }
                }) { Text("Yes") }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) { Text("No") }
            }
        )
    }
    if (isUploading) {
        // Optionally show a progress indicator
    }
    if (uploadResult != null) {
        // Optionally show a snackbar or dialog with uploadResult
    }
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Cyan),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            items = list,
            key = { item -> item.id }
        ) { item ->
            MediaItemView(
                taskName = item.label,
                imageUri = item.uri,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedItem = item
                        showDialog = true
                    }
            )
        }
    }
}
