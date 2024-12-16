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

val customTextDescriptionStyle = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.Normal,
    fontSize = 18.sp,
    color = light_grey
)

val customTextHeadingStyle = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 28.sp,
    color = dark_blue
)


val customTextLabelBoldStyle = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 12.sp,
    color = WhiteColor
)




val fonts get() = FontFamily( listOf(Font(R.font.objective_regular), Font(R.font.objective_regular), Font(
    R.font.objective_bold),Font(R.font.objective_extra_bold)))
