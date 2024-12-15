package com.ops.airportr.ui.screens.navigationscreen

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ops.airportr.data.viewmodel.MainViewModel
import com.ops.airportr.route.sidenavigationscreens.SideNavigationHost
import com.ops.airportr.route.sidenavigationscreens.screensInHomeFromBottomNav
import com.ops.airportr.ui.componts.BackPressHandler
import com.ops.airportr.ui.componts.sidenavigation.BottomBar
import com.ops.airportr.ui.componts.sidenavigation.BottomBarNewDesign
import com.ops.airportr.ui.componts.sidenavigation.Drawer
import com.ops.airportr.ui.componts.sidenavigation.TopBar
import kotlinx.coroutines.launch


@Composable
fun NavigationScreen(
    navHostController: NavHostController
) {
    val viewModel: MainViewModel = viewModel()
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val activity = LocalContext.current as? Activity

    //  val currentScreen by viewModel.currentScreen.observeAsState()


//    BackPressHandler {
//        scope.launch {
//            if (scaffoldState.drawerState.isOpen) {
//                scaffoldState.drawerState.close()
//            }
//        }
//    }
    BackPressHandler(activity) {
        if (!navHostController.popBackStack()) {
            // Finish the activity to close the app
            activity?.finish()
        }
    }

    val topBar: @Composable () -> Unit = {
        TopBar(
            title = "",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        )
    }
//    if (currentScreen == Screens.DrawerScreens.MyProfile) {
//        topBar = {
//            TopBar(
//                title = Screens.DrawerScreens.MyProfile.title,
//                buttonIcon = Icons.Filled.ArrowBack,
//                onButtonClicked = {
//                    navController.popBackStack()
//                }
//            )
//        }
//    }

    val bottomBar: @Composable () -> Unit = {
//        if (currentScreen == Screens.DrawerScreens.Home || currentScreen is Screens.HomeScreens) {
//
//        }
        BottomBar(
            navController = navController,
            screens = screensInHomeFromBottomNav
        )
    }

    androidx.compose.material.Scaffold(
        bottomBar = {
            bottomBar()
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            Drawer { route ->
                scope.launch {
                    scaffoldState.drawerState.close()
                }
                navController.navigate(route) {
                    popUpTo = navController.graph.startDestinationId
                    launchSingleTop = true
                }
            }
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)  // Apply the scaffold padding here
        ) {
            // Your screen content goes here
            SideNavigationHost(
                navController = navController,
                viewModel = viewModel,
                navHostController
            )
        }
    }
}


