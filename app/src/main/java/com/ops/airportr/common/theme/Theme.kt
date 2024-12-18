package com.ops.airportr.common.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColorScheme(
    primary = colorPrimaryDarkTheme,
    background = colorPrimaryDarkTheme,
    onBackground = colorPrimaryDarkTheme,
    onPrimary = colorPrimaryDarkTheme
)

private val LightColorPalette = lightColorScheme(

    primary = air_purple,
    background = air_purple,
    onBackground = air_purple,
    onPrimary = air_purple
)

@Composable
fun OpsAirportrTheme(darkTheme: Boolean = true, content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typo,
        shapes = Shapes,
        content = content
    )
}

