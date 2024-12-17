package com.ops.airportr.ui.screens.navigationscreen.sidenavscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ops.airportr.common.theme.navigationBarColor
import com.ops.airportr.data.viewmodel.MainViewModel
import com.ops.airportr.route.sidenavigationscreens.SideNavigationScreens
import com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.jobs.MultiFloatingActionButtons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    navHostController: NavHostController
) {
    viewModel.setCurrentScreen(SideNavigationScreens.SideDrawerScreens.Home)

    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButtons(
                onClick = {

                }
            )
        }

    ) { paddingValues ->
        // Main content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(navigationBarColor)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {


            }
        }
    }

}




@Composable
@Preview
fun HomeScreenPreview() {
    val viewModel: MainViewModel = viewModel()
    val navController = rememberNavController()
    HomeScreen(
        viewModel = viewModel, navHostController = navController
    )
}