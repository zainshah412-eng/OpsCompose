package com.ops.airportr.common.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView


private val DarkColorPalette = darkColorScheme(
    primary = customEditTextColorDarkTheme,

    //PrimaryColor is For Text Color

    secondary = white,


    //Secondary Color is for Labels

    background = badgeTextColor,

    //BAckGround andButton Box Color

    error = colorPrimaryDarkTheme,

    surface = buttonBackgroundColorDarkTheme,
)

//Error COlor

private val LightColorPalette = lightColorScheme(
    primary = air_purple,

    secondary = dark_blue,

    background = white,


    error = colorPrimaryDarkTheme,


    surface = air_purple_awesome_light,


    )


@Composable
fun OpsAirportrTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }
    val view = LocalView.current
    //  if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = white.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
    //  }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typo,
        content = content
    )
}

//primaryContainer = colorPrimaryDarkTheme,
//    onPrimaryContainer = colorPrimaryDarkTheme,
//    inversePrimary = colorPrimaryDarkTheme,
//
//    secondaryContainer = colorPrimaryDarkTheme,
//    onSecondaryContainer = colorPrimaryDarkTheme,
//    tertiary = colorPrimaryDarkTheme,
//    onTertiary = colorPrimaryDarkTheme,
//    tertiaryContainer = colorPrimaryDarkTheme,
//    onTertiaryContainer = colorPrimaryDarkTheme,
//
//    surface = colorPrimaryDarkTheme,
//    onSurface = colorPrimaryDarkTheme,
//    surfaceVariant = colorPrimaryDarkTheme,
//    onSurfaceVariant = colorPrimaryDarkTheme,
//    surfaceTint = colorPrimaryDarkTheme,
//    inverseSurface = colorPrimaryDarkTheme,
//    inverseOnSurface = colorPrimaryDarkTheme,
//    errorContainer = colorPrimaryDarkTheme,
//    onErrorContainer = colorPrimaryDarkTheme,
//    outline = colorPrimaryDarkTheme,
//    outlineVariant = colorPrimaryDarkTheme,
//    scrim = colorPrimaryDarkTheme,