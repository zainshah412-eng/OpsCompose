package com.ops.airportr.common.utils.imagedownloader

import android.net.Uri
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ops.airportr.common.utils.returnLabelAirPurple100Color

@Composable
fun ImageDownloader(
    imageUrl: String,
    title: String,
    description: String,
    isDarkTheme: Boolean,
    onDownloadComplete: (Uri?) -> Unit
) {
    val context = LocalContext.current
    var downloadState by remember { mutableStateOf<DownloadState>(DownloadState.Idle) }

    LaunchedEffect(imageUrl) {
        downloadState = DownloadState.InProgress

        val downloader = ImageDownloaderWorker(context, title, description)
        downloader.downloadAndSaveImage(imageUrl).let { uri ->
            downloadState = if (uri != null) DownloadState.Success(uri) else DownloadState.Error
            onDownloadComplete(uri)
        }
    }

    when (downloadState) {
        is DownloadState.InProgress -> {
            Text(
                text = "Downloading...",
                color = returnLabelAirPurple100Color(isDarkTheme),
                style = MaterialTheme.typography.labelMedium,
                fontSize = 14.sp
            )
        }

        is DownloadState.Success -> {
            Text(
                text = "Download Complete!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium,
                fontSize = 14.sp
            )
        }

        is DownloadState.Error -> {
            Text(
                text = "Failed to download image.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelMedium,
                fontSize = 14.sp
            )
        }

        else -> {}
    }
}
sealed class DownloadState {
    object Idle : DownloadState()
    object InProgress : DownloadState()
    data class Success(val uri: Uri) : DownloadState()
    object Error : DownloadState()
}
