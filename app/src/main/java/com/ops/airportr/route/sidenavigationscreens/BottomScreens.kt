package com.ops.airportr.route.sidenavigationscreens

import com.ops.airportr.R
import com.ops.airportr.route.Graph

sealed class BottomScreens(val route: String, val title: String) {

    sealed class BottomNavigationScreens(
        route: String,
        title: String,
        val icon: Int,
        val icon_focused: Int
    ) : SideNavigationScreens(
        route,
        title
    ) {
        object Jobs : BottomNavigationScreens(
            Graph.FAVORITE, "Jobs",
            icon = R.drawable.check_circle_outline,
            icon_focused = R.drawable.check_circle_fill
        )

        object Scanner : BottomNavigationScreens(
            Graph.NEAR_BY, "Scanner", icon = R.drawable.barcode_outline,
            icon_focused = R.drawable.barcode_fill
        )

        object Airports : BottomNavigationScreens(
            Graph.RESERVED, "Airports", icon = R.drawable.airplane_outline,
            icon_focused = R.drawable.airplane_fill
        )

        object Profile : BottomNavigationScreens(
            Graph.SAVED, "Profile", icon = R.drawable.user_outline,
            icon_focused = R.drawable.user_fill
        )

    }


}

val screensInHomeFromBottomNav = listOf(
    BottomScreens.BottomNavigationScreens.Jobs,
    BottomScreens.BottomNavigationScreens.Scanner,
    BottomScreens.BottomNavigationScreens.Airports,
    BottomScreens.BottomNavigationScreens.Profile
)