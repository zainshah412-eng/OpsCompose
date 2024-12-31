package com.ops.airportr.common.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ops.airportr.R

// Set of Material typography styles to start with
val Typo = Typography(
    displayLarge = TextStyle(
        fontFamily = fontsExtraBold,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp,
        color = dark_blue
    ),
    displayMedium = TextStyle(
        fontFamily = fontsMedium,
        fontWeight = FontWeight.Light,
        fontSize = 28.sp,
        color = dark_blue
    ),
    displaySmall = TextStyle(
        fontFamily = fontsLight,
        fontWeight = FontWeight.Light,
        fontSize = 26.sp,
        color = dark_blue
    ),
    headlineLarge = TextStyle(
        fontFamily = fontsBold,
        fontWeight = FontWeight.Light,
        fontSize = 35.sp,
        color = dark_blue
    ),
    headlineMedium = TextStyle(
        fontFamily = fontsMedium,
        fontWeight = FontWeight.Light,
        fontSize = 33.sp,
        color = dark_blue
    ),
    headlineSmall = TextStyle(
        fontFamily = fontsLight,
        fontWeight = FontWeight.Light,
        fontSize = 30.sp,
        color = dark_blue
    ),
    titleLarge = TextStyle(
        fontFamily = fontsBold,
        fontWeight = FontWeight.Light,
        fontSize = 28.sp,
        color = dark_blue
    ),
    titleMedium = TextStyle(
        fontFamily = fontsMedium,
        fontWeight = FontWeight.Light,
        fontSize = 26.sp,
        color = dark_blue
    ),
    titleSmall = TextStyle(
        fontFamily = fontsLight,
        fontWeight = FontWeight.Light,
        fontSize = 24.sp,
        color = dark_blue
    ),
    bodyLarge = TextStyle(
        fontFamily = fontsBold,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp,
        color = dark_blue
    ),
    bodyMedium = TextStyle(
        fontFamily = fontsMedium,
        fontWeight = FontWeight.Light,
        fontSize = 18.sp,
        color = dark_blue
    ),
    bodySmall = TextStyle(
        fontFamily = fontsRegular,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        color = dark_blue
    ),
    labelLarge = TextStyle(
        fontFamily = fontsBold,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = dark_blue
    ),
    labelMedium = TextStyle(
        fontFamily = fontsMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = dark_blue
    ),
    labelSmall = TextStyle(
        fontFamily = fontsRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = dark_blue
    )

)

val customTextLabelStyle = TextStyle(
    fontFamily = fontsBold,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    color = dark_blue
)

val customTextLabelSmallStyle = TextStyle(
    fontFamily = fontsMedium,
    fontWeight = FontWeight.Bold,
    fontSize = 12.sp,
    color = WhiteColor
)

val customTextDescriptionStyle = TextStyle(
    fontFamily = fontsRegular,
    fontWeight = FontWeight.Normal,
    fontSize = 18.sp,
    color = light_grey
)

val customTextHeadingStyle = TextStyle(
    fontFamily = fontsBold,
    fontWeight = FontWeight.Light,
    fontSize = 28.sp,
    color = dark_blue
)

val fontsItatic
    get() = FontFamily(
        listOf(
            Font(R.font.objective_italic),
        )
    )
val fontsLight
    get() = FontFamily(
        listOf(
            Font(R.font.objective_light),
        )
    )
val fontsRegular
    get() = FontFamily(
        listOf(

            Font(R.font.objective_regular),

        )
    )
val fontsMedium
    get() = FontFamily(
        listOf(
            Font(R.font.objective_medium),

        )
    )
val fontsBold
    get() = FontFamily(
        listOf(
            Font(R.font.objective_bold),
        )
    )
val fontsExtraBold
    get() = FontFamily(
        listOf(
            Font(R.font.objective_extra_bold)
        )
    )
