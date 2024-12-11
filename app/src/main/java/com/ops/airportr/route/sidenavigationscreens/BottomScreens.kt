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
        object Favorite : BottomNavigationScreens(
            Graph.FAVORITE, "Favorite",
            icon = R.drawable.ic_records,
            icon_focused = R.drawable.ic_record_dark
        )

        object NearBy : BottomNavigationScreens(
            Graph.NEAR_BY, "Nearby", icon = R.drawable.ic_records,
            icon_focused = R.drawable.ic_record_dark
        )

        object Reserved : BottomNavigationScreens(
            Graph.RESERVED, "Reserved", icon = R.drawable.ic_profile,
            icon_focused = R.drawable.ic_profile_dark
        )

        object Saved : BottomNavigationScreens(
            Graph.SAVED, "Saved", icon = R.drawable.ic_profile,
            icon_focused = R.drawable.ic_profile_dark
        )

    }


}

val screensInHomeFromBottomNav = listOf(
    BottomScreens.BottomNavigationScreens.Favorite,
    BottomScreens.BottomNavigationScreens.NearBy,
    BottomScreens.BottomNavigationScreens.Reserved,
    BottomScreens.BottomNavigationScreens.Saved
)