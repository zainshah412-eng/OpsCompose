package com.ops.airportr.ui.screens.welcomescreen

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ops.airportr.R
import com.ops.airportr.common.theme.air_awesome_white
import com.ops.airportr.common.theme.dark_blue
import com.ops.airportr.route.Screen
import com.ops.airportr.ui.componts.BackPressHandler
import com.ops.airportr.ui.screens.loginscreen.OverlappingImagesBottom
import com.ops.airportr.ui.screens.loginscreen.OverlappingImagesTop


@Composable
fun WelcomeScreen(
    navHostController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val activity = LocalContext.current as? Activity



    BackPressHandler(activity) {
        if (!navHostController.popBackStack()) {
            // Finish the activity to close the app
            activity?.finish()
        }
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Use your desired background color
    ) {
        val (topBox, logoImage, weightBox, bottomBox) = createRefs()

        // Top Box
        Box(
            modifier = Modifier.constrainAs(topBox) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            OverlappingImagesTop() // Replace with your composable for overlapping images
        }

        // Logo Image
        Image(
            painter = painterResource(id = R.drawable.airportr_logo),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(logoImage) {
                    top.linkTo(parent.top) // Constrain below the topBox
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 40.dp) // Padding for logo image
                .height(120.dp), // Height for the logo
            contentScale = ContentScale.Inside // Adjust image scaling as needed
        )
        // Bottom Box
        Box(
            modifier = Modifier.constrainAs(bottomBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        ) {
            OverlappingImagesBottom() // Replace with your composable for bottom overlapping images
        }
        // Weight Box
        Box(
            modifier = Modifier
                .constrainAs(weightBox) {
                    top.linkTo(logoImage.bottom) // Constrain below the logoImage
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    // Constrain above the bottom box
                } // Set background color
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)// Fill width, no need for fillMaxSize as height is constrained
            ) {
                // First child with weight 1
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = dimensionResource(id = R.dimen._32sdp),
                            top = dimensionResource(id = R.dimen._25sdp),
                            end = dimensionResource(id = R.dimen._32sdp),
                            bottom = dimensionResource(id = R.dimen._12sdp)
                        )
                        .weight(1f)
                        .clickable { /* Handle click */ },
                    elevation = dimensionResource(id = R.dimen._20sdp),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen._20sdp)),
                    backgroundColor = air_awesome_white
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.primary.copy(alpha = 0.1f))
                            .clickable(onClick = {
                                navHostController.navigate(Screen.HomeScreen.route) {
                                    popUpTo(0) { inclusive = true }
                                }
                            }),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_acceptance_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(dimensionResource(id = R.dimen._70sdp)),
                            // Equivalent to IconTint
                        )

                        Text(
                            text = stringResource(id = R.string.bags_check_in_main),
                            modifier = Modifier.padding(top = dimensionResource(id = R.dimen._15sdp)),
                            textAlign = TextAlign.Center,
                            color = dark_blue,
                            style = TextStyle(
                                fontSize = dimensionResource(id = R.dimen._16ssp).value.sp,
                                fontFamily = FontFamily(Font(R.font.objective_medium)) // Replace with your custom font
                            )
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = dimensionResource(id = R.dimen._32sdp),
                            top = dimensionResource(id = R.dimen._25sdp),
                            end = dimensionResource(id = R.dimen._32sdp),
                            bottom = dimensionResource(id = R.dimen._12sdp)
                        )
                        .weight(1f)
                        .clickable { /* Handle click */ },
                    elevation = dimensionResource(id = R.dimen._20sdp),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen._20sdp)),
                    backgroundColor = air_awesome_white
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.primary.copy(alpha = 0.1f))
                            .clickable(onClick = { /* Handle inner click */ }),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_ccd_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(dimensionResource(id = R.dimen._70sdp)) // Equivalent to IconTint
                        )

                        Text(
                            text = stringResource(id = R.string.ccd),
                            modifier = Modifier.padding(top = dimensionResource(id = R.dimen._15sdp)),
                            textAlign = TextAlign.Center,
                            color = dark_blue,
                            style = TextStyle(
                                fontSize = dimensionResource(id = R.dimen._16ssp).value.sp,
                                fontFamily = FontFamily(Font(R.font.objective_medium)) // Replace with your custom font
                            )
                        )
                    }
                }

            }
        }


    }


}

@Composable
@Preview
fun WelcomeScreenPreview() {
    val navController = rememberNavController()
    WelcomeScreen(navController)
}