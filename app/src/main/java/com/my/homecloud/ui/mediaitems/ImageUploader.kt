package com.my.homecloud.ui.mediaitems

import android.content.Context
import android.net.Uri
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

object ImageUploader {
    private val client = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun uploadImage(context: Context, uri: Uri, serverUrl: String): Boolean {
        return withContext(Dispatchers.IO) {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val bytes = inputStream?.readBytes() ?: return@withContext false
            val response: HttpResponse = client.submitFormWithBinaryData(
                url = serverUrl,
                formData = formData {
                    append("file", bytes, Headers.build {
                        append(HttpHeaders.ContentType, "image/jpeg")
                        append(HttpHeaders.ContentDisposition, "filename=upload.jpg")
                    })
                }
            )
            response.status.isSuccess()
        }
    }
}

