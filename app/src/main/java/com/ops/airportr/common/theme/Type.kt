package com.ops.airportr.common.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ops.airportr.R

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)

val customTextLabelStyle = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    color = dark_blue
)

val customTextLabelSmallStyle = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.Bold,
    fontSize = 12.sp,
    color = WhiteColor
)




val fonts get() = FontFamily( listOf(Font(R.font.urbanist_light), Font(R.font.urbanist_regular), Font(
    R.font.urbanist_bold),Font(R.font.urbanist_extra_bold)))
