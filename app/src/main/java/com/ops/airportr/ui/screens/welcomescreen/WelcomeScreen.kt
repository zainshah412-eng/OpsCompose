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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ops.airportr.R
import com.ops.airportr.common.theme.background

import com.ops.airportr.common.theme.customTextLabelStyle
import com.ops.airportr.common.theme.custom_white
import com.ops.airportr.common.theme.fonts
import com.ops.airportr.common.theme.navigationBarColor
import com.ops.airportr.route.Screen
import com.ops.airportr.ui.componts.AutoSlidingPager
import com.ops.airportr.ui.componts.BackPressHandler
import com.ops.airportr.ui.componts.Space


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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(navigationBarColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Text(
                text = stringResource(id = R.string.login_text),
                color = background,
                style = customTextLabelStyle,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        top = 20.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )// Aligns the text to the end (right side)
                    .clickable {
                        // navHostController.moveOnNewScreen(Screen.LoginScreen.route, true)
                        navHostController.navigate(Screen.LoginScreen.route) {
                            launchSingleTop =
                                true  // Ensures that if you are already on the HomeScreen, it won't add it again to the stack
                        }

                    }

            )

            Space(10, 0)

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
                    Text(
                        text = stringResource(id = R.string.login_text),
                        fontFamily = fonts,
                        style = customTextLabelStyle,
                        color = custom_white,
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
                            painter = painterResource(id = R.drawable.ic_pink_credit_card),
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
        // Add the text at the bottom of the screen
        Text(
            text = stringResource(id = R.string.login_text),
            color = custom_white,
            style = customTextLabelStyle,
            modifier = Modifier
                .align(Alignment.BottomCenter) // Aligns the text at the bottom center
                .padding(16.dp) // Optional padding at the bottom
        )
    }

}

@Composable
@Preview
fun WelcomeScreenPreview() {
    val navController = rememberNavController()
    WelcomeScreen(navController)
}