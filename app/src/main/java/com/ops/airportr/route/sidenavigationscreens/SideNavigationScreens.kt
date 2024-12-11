package com.ops.airportr.route.sidenavigationscreens

import com.ops.airportr.R
import com.ops.airportr.route.Graph.HOME
import com.ops.airportr.route.Graph.LOGOUT
import com.ops.airportr.route.Graph.PROFILE

sealed class SideNavigationScreens(val route: String, val title: String) {
    sealed class SideDrawerScreens(
        route: String,
        val icon: Int,
        title: String
    ) : SideNavigationScreens(route, title) {
        object Home : SideDrawerScreens(HOME, R.drawable.ic_profile, "Home")
        object MyProfile :
            SideDrawerScreens(PROFILE, R.drawable.ic_records, "My Profile")

        //   object QRCode : DrawerScreens("qr-code", R.drawable.ic_profile_dark, "QR Code")
        object Logout : SideDrawerScreens(LOGOUT, R.drawable.ic_results, "Logout")

    }
}



val screensFromDrawer = listOf(
    SideNavigationScreens.SideDrawerScreens.Home,
    SideNavigationScreens.SideDrawerScreens.MyProfile,
    SideNavigationScreens.SideDrawerScreens.Logout
)


