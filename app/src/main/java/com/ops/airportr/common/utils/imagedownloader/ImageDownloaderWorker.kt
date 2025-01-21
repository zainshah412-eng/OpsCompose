package com.ops.airportr.common.utils.imagedownloader

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class ImageDownloaderWorker(private val context: Context, private val title: String, private val description: String) {

    suspend fun downloadAndSaveImage(imageUrl: String): Uri? {
        return withContext(Dispatchers.IO) {
            val bitmap = downloadImageFromUrl(imageUrl)
            bitmap?.let { saveImageToGallery(context, it) }
        }
    }

    private fun downloadImageFromUrl(imageUrl: String): Bitmap? {
        return try {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.apply {
                connectTimeout = 10_000
                readTimeout = 15_000
                doInput = true
                connect()
            }
            val inputStream = connection.inputStream
            BitmapFactory.decodeStream(inputStream).also {
                inputStream.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun saveImageToGallery(context: Context, bitmap: Bitmap): Uri? {
        val filename = "${System.currentTimeMillis()}.jpg"
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, filename)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val resolver = context.contentResolver
        return try {
            val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            uri?.let {
                val outputStream = resolver.openOutputStream(it) ?: return@let null
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.close()
            }
            uri
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
