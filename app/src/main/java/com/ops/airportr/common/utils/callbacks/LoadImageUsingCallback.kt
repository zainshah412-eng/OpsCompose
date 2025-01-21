package com.ops.airportr.common.utils.callbacks

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ops.airportr.R

@Composable
fun LoadImageUsingCallback(
    imageId: String,
    imageLoadCallbacks: ImageLoadCallbacks,
    modifier: Modifier = Modifier
) {
    var imageUrl by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(imageId) {
        imageLoadCallbacks.loadImageUrl(imageId) { url ->
            imageUrl = url
            isLoading = false
        }
    }

    if (isLoading) {
        // Show a loading indicator
        CircularProgressIndicator(modifier = modifier)
    } else if (!imageUrl.isNullOrEmpty()) {
        // Load the image using Coil
        AsyncImage(
            model = imageUrl,
            contentDescription = "Loaded Image",
            modifier = modifier
                .height(dimensionResource(id = R.dimen._110sdp))
                .width(dimensionResource(id = R.dimen._100sdp))
                .clip(RoundedCornerShape(4.dp))
        )
    } else {
        // Show an error placeholder
        Text(
            text = "Failed to load image",
            color = Color.Red,
            modifier = modifier
        )
    }
}