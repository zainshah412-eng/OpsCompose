package com.ops.airportr.common.utils

import androidx.compose.ui.graphics.Color
import com.ops.airportr.common.theme.air_awesome_purple_100
import com.ops.airportr.common.theme.air_green_low_light
import com.ops.airportr.common.theme.air_orange_lite
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.theme.air_purple_light
import com.ops.airportr.common.theme.air_purple_medium_light
import com.ops.airportr.common.theme.badgeColor
import com.ops.airportr.common.theme.badgeTextColor
import com.ops.airportr.common.theme.bottomTabBackGroundColor
import com.ops.airportr.common.theme.brown
import com.ops.airportr.common.theme.buttonBackgroundColorDarkTheme
import com.ops.airportr.common.theme.colorPrimaryDarkTheme
import com.ops.airportr.common.theme.customEditTextColorDarkTheme
import com.ops.airportr.common.theme.custom_white
import com.ops.airportr.common.theme.dark_blue
import com.ops.airportr.common.theme.purple_100
import com.ops.airportr.common.theme.white

fun returnLabelDarkBlueColor (isDarkTheme:Boolean): Color {
    return if (isDarkTheme) white else dark_blue
}
fun returnLabelAirPurpleColor (isDarkTheme:Boolean): Color {
    return if (isDarkTheme) purple_100 else air_purple
}
fun returnLabelAirPurple100Color (isDarkTheme:Boolean): Color {
    return if (isDarkTheme) customEditTextColorDarkTheme else air_awesome_purple_100
}
fun returnBackGroundColor (isDarkTheme:Boolean): Color {
    return if (isDarkTheme) colorPrimaryDarkTheme else white
}

fun abcTagBackGroundColor (isDarkTheme:Boolean): Color {
    return if (isDarkTheme) brown else air_green_low_light
}
fun overSizeBagTagBackGroundColor (isDarkTheme:Boolean): Color {
    return if (isDarkTheme) badgeColor else air_orange_lite
}
fun bottomNavBackGroundColor (isDarkTheme:Boolean): Color {
    return if (isDarkTheme) colorPrimaryDarkTheme else custom_white
}

fun returnJobsNumberCircleBackground(isDarkTheme: Boolean):Color{
    return if (isDarkTheme)  brown else air_purple_medium_light
}

fun returnFlightTagsBackGround(isDarkTheme: Boolean): Color {
    return if(isDarkTheme) buttonBackgroundColorDarkTheme else air_purple_light
}