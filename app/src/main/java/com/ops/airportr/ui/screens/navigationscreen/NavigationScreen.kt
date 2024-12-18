package com.ops.airportr.ui.screens.navigationscreen

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ops.airportr.data.viewmodel.MainViewModel
import com.ops.airportr.route.sidenavigationscreens.SideNavigationHost
import com.ops.airportr.route.sidenavigationscreens.screensInHomeFromBottomNav
import com.ops.airportr.ui.componts.BackPressHandler
import com.ops.airportr.ui.componts.sidenavigation.BottomBar
import com.ops.airportr.ui.componts.sidenavigation.Drawer
import com.ops.airportr.ui.componts.sidenavigation.TopBar
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen(
    navHostController: NavHostController
) {
    val viewModel: MainViewModel = viewModel()
    val navController = rememberNavController()

    // Handle back press
    val activity = LocalContext.current as? Activity
    BackPressHandler(activity) {
        if (!navHostController.popBackStack()) {
            // Finish the activity to close the app
            activity?.finish()
        }
    }

    val bottomBar: @Composable () -> Unit = {
        BottomBar(
            navController = navController,
            screens = screensInHomeFromBottomNav
        )
    }

    Scaffold(
        topBar = {
            // If you want to remove the top bar, you can just leave this empty
        },
        bottomBar = { bottomBar() },
        content = { innerPadding ->
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
    )
}













