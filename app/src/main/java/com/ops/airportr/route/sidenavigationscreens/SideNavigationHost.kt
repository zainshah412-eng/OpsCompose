package com.ops.airportr.route.sidenavigationscreens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ops.airportr.data.viewmodel.MainViewModel
import com.ops.airportr.ui.componts.dialog.LogoutDialog
import com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.FavoriteScreen
import com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.NearbyScreen
import com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.ReservedScreen
import com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.SavedScreen
import com.ops.airportr.ui.screens.navigationscreen.sidenavscreens.HomeScreen

@Composable
fun SideNavigationHost(
    navController: NavController,
    viewModel: MainViewModel,
    navHostController: NavHostController
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = SideNavigationScreens.SideDrawerScreens.Home.route
    ) {
        composable(SideNavigationScreens.SideDrawerScreens.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                navHostController = navHostController
            )
        }
        composable(SideNavigationScreens.SideDrawerScreens.MyProfile.route) {
            HomeScreen(
                viewModel = viewModel,
                navHostController = navHostController
            )
        }
        composable(BottomScreens.BottomNavigationScreens.Favorite.route) { FavoriteScreen(viewModel = viewModel) }
        composable(BottomScreens.BottomNavigationScreens.NearBy.route) { NearbyScreen(viewModel = viewModel) }
        composable(BottomScreens.BottomNavigationScreens.Reserved.route) { ReservedScreen(viewModel = viewModel) }
        composable(BottomScreens.BottomNavigationScreens.Saved.route) { SavedScreen(viewModel = viewModel) }
        composable(SideNavigationScreens.SideDrawerScreens.Logout.route) {
            LogoutDialog(
                navHostController
            )
        }
    }
}