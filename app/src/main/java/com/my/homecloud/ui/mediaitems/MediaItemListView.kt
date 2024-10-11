package com.my.homecloud.ui.mediaitems

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale

@Composable
fun MediaItemView(
    taskName: String,
    imageUri: Uri,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUri)
                    .scale(Scale.FILL)
                    .build(),
                contentScale = ContentScale.FillBounds
            ),
            contentDescription = "Selected image from SD card",
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .padding(start = 16.dp)
        )
        Text(
            modifier = Modifier
                .padding(start = 16.dp),
            text = taskName
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMediaItemView() {
    MediaItemView(
        taskName = "Sample Task",
        imageUri = Uri.parse("content://media/external/images/media/117")
    )
}

