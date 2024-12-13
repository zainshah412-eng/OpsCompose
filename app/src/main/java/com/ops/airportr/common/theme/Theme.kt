package com.ops.airportr.common.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = colorPrimaryDarkTheme,
    background = colorPrimaryDarkTheme,
    onBackground = colorPrimaryDarkTheme,
    onPrimary = colorPrimaryDarkTheme
)

private val LightColorPalette = lightColors(

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
        colors = colors,
        typography = Typo,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun updateStatusBarColor(
    color: Color = SnackbarDefaults.backgroundColor,
    iconDark: Boolean = false
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = iconDark
        )
    }
}