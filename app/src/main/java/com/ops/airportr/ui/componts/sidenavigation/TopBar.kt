package com.ops.airportr.ui.componts.sidenavigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String = "",
    buttonIcon: ImageVector,
    onButtonClicked: () -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.primary, // Default to Material 3's primary color
    contentColor: Color = MaterialTheme.colorScheme.onPrimary  // Default text/icon color
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = contentColor // Set text color
            )
        },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() }) {
                Icon(
                    imageVector = buttonIcon,
                    contentDescription = null,
                    tint = contentColor // Set icon color
                )
            }
        },

    )
}
