package com.ops.airportr.common.utils

import androidx.compose.ui.graphics.Color
import com.ops.airportr.common.theme.air_awesome_purple_100
import com.ops.airportr.common.theme.air_awesome_purple_200
import com.ops.airportr.common.theme.air_awesome_purple_light_50
import com.ops.airportr.common.theme.air_awesome_white
import com.ops.airportr.common.theme.air_green_light
import com.ops.airportr.common.theme.air_green_light_100
import com.ops.airportr.common.theme.air_green_low_light
import com.ops.airportr.common.theme.air_orange100
import com.ops.airportr.common.theme.air_orange_lite
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.theme.air_purple_light
import com.ops.airportr.common.theme.air_purple_medium_light
import com.ops.airportr.common.theme.badgeCircleTextColor
import com.ops.airportr.common.theme.badgeColor
import com.ops.airportr.common.theme.badgeTextColor
import com.ops.airportr.common.theme.bottomTabBackGroundColor
import com.ops.airportr.common.theme.brown
import com.ops.airportr.common.theme.brown100
import com.ops.airportr.common.theme.buttonBackgroundColorDarkTheme
import com.ops.airportr.common.theme.buttonTextColorDarkTheme
import com.ops.airportr.common.theme.colorPrimaryDarkTheme
import com.ops.airportr.common.theme.customEditTextColorDarkTheme
import com.ops.airportr.common.theme.custom_white
import com.ops.airportr.common.theme.dark_blue
import com.ops.airportr.common.theme.editTextBorderStrockColor
import com.ops.airportr.common.theme.green
import com.ops.airportr.common.theme.grey_100
import com.ops.airportr.common.theme.lead_passenger_box_color
import com.ops.airportr.common.theme.light_white
import com.ops.airportr.common.theme.orange
import com.ops.airportr.common.theme.orange100
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
    return if (isDarkTheme) green else air_green_low_light
}
fun overSizeBagTagBackGroundColor (isDarkTheme:Boolean): Color {
    return if (isDarkTheme) badgeColor else air_orange_lite
}
fun bottomNavBackGroundColor (isDarkTheme:Boolean): Color {
    return if (isDarkTheme) colorPrimaryDarkTheme else custom_white
}

fun returnJobsNumberCircleBackground(isDarkTheme: Boolean):Color{
    return if (isDarkTheme)  brown else air_awesome_purple_light_50
}

fun returnJobsNumberCircleBackgroundOnJobComplete(isDarkTheme: Boolean):Color{
    return if (isDarkTheme)  green else air_green_light_100
}

fun returnJobsNumberCircleBackgroundOnJobInComplete(isDarkTheme: Boolean):Color{
    return if (isDarkTheme)  badgeCircleTextColor else air_purple
}

fun returnFlightTagsBackGround(isDarkTheme: Boolean): Color {
    return if(isDarkTheme) buttonBackgroundColorDarkTheme else air_purple_light
}
fun returnFlagsBackGround(isDarkTheme: Boolean): Color {
    return if(isDarkTheme) buttonBackgroundColorDarkTheme else grey_100
}
fun returnConditionalAcceptanceBackGround(isDarkTheme: Boolean): Color {
    return if(isDarkTheme) air_orange100 else orange100
}
fun returnLeadPassengerBackGround(isDarkTheme: Boolean): Color {
    return if(isDarkTheme) badgeColor else lead_passenger_box_color
}
fun returnSealAndIataTagsColor(isDarkTheme: Boolean): Color {
    return if(isDarkTheme) buttonTextColorDarkTheme else air_awesome_purple_200
}

fun returnLineColorForAuditTrailItem(isDarkTheme: Boolean):Color {
    return if(isDarkTheme) editTextBorderStrockColor else light_white
}
fun returnBagDetailStatusBackGroundBox(isDarkTheme: Boolean):Color {
    return if(isDarkTheme) brown100 else air_orange_lite
}
fun returnBagStatusBackGroundBox(isDarkTheme: Boolean):Color {
    return if(isDarkTheme) orange100 else orange
}
fun returnETABackGroundBox(isDarkTheme: Boolean):Color {
    return if(isDarkTheme) air_awesome_white else air_awesome_white
}