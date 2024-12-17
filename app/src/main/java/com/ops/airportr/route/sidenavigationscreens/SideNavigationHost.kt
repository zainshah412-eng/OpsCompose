package com.ops.airportr.route.sidenavigationscreens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ops.airportr.data.viewmodel.MainViewModel
import com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.AirportsScreen
import com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.jobs.JobsScreen
import com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.ProfileScreen
import com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.ScannerScreen

@Composable
fun SideNavigationHost(
    navController: NavController,
    viewModel: MainViewModel,
    navHostController: NavHostController
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = BottomScreens.BottomNavigationScreens.Jobs.route
    ) {
        composable(BottomScreens.BottomNavigationScreens.Jobs.route) {
            JobsScreen(
                navController = navController,
                navHostController = navHostController
            )
        }
        composable(BottomScreens.BottomNavigationScreens.Scanner.route) { ScannerScreen(viewModel = viewModel) }
        composable(BottomScreens.BottomNavigationScreens.Airports.route) { AirportsScreen(viewModel = viewModel) }
        composable(BottomScreens.BottomNavigationScreens.Profile.route) { ProfileScreen(viewModel = viewModel) }
    }
}