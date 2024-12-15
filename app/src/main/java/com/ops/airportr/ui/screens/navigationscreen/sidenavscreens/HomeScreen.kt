package com.ops.airportr.ui.screens.navigationscreen.sidenavscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ops.airportr.R
import com.ops.airportr.common.theme.WhiteColor

import com.ops.airportr.common.theme.customTextLabelStyle
import com.ops.airportr.common.theme.fonts
import com.ops.airportr.common.theme.navigationBarColor
import com.ops.airportr.data.viewmodel.MainViewModel
import com.ops.airportr.route.sidenavigationscreens.SideNavigationScreens
import com.ops.airportr.ui.componts.AutoSlidingPager
import com.ops.airportr.ui.componts.Space
import com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.MultiFloatingActionButtons

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


                Box(
                    modifier = Modifier
                        .fillMaxSize() // Set content scale to crop
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_pink_credit_card),
                        contentDescription = null, // provide content description if needed
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent), // make the image background transparent
                        contentScale = ContentScale.FillBounds // scale the image to fill the Box
                    )
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        androidx.compose.material3.Text(
                            text = stringResource(id = R.string.login_text),
                            fontFamily = fonts,
                            style = customTextLabelStyle,
                            color = WhiteColor,
                            modifier = Modifier
                                .padding(
                                    top = 16.dp,
                                    end = 16.dp
                                )// Aligns the text to the end (right side)
                        )

                        Space(50, 0)

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            AutoSlidingPager()
                            Image(
                                painter = painterResource(id = R.drawable.airportr_logo),
                                contentDescription = null, // provide content description if needed
                                modifier = Modifier
                                    .size(width = 100.dp, height = 100.dp)
                                    .background(Color.Transparent)
                                    .align(Alignment.TopCenter), // make the image background transparent
                                contentScale = ContentScale.FillBounds // scale the image to fill the Box
                            )

                        }


                    }
                }
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