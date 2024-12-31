package com.ops.airportr.route

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ops.airportr.ui.screens.bookingmanifest.BookingDetailScreen
import com.ops.airportr.ui.screens.bookingmanifest.tabs.BookingManifestScreen
import com.ops.airportr.ui.screens.loginscreen.LoginScreen
import com.ops.airportr.ui.screens.navigationscreen.NavigationScreen
import com.ops.airportr.ui.screens.profile.GiveFeedbackScreen
import com.ops.airportr.ui.screens.profile.PortrCodeScreen
import com.ops.airportr.ui.screens.profile.ProfileDetailScreen
import com.ops.airportr.ui.screens.profile.whatsnew.WhatsNewScreen
import com.ops.airportr.ui.screens.resetpassword.ResetPasswordScreen
import com.ops.airportr.ui.screens.searchbooking.SearchBookingScreen
import com.ops.airportr.ui.screens.splashscreen.SplashScreen
import com.ops.airportr.ui.screens.welcomescreen.WelcomeScreen

@Composable
fun Navigation(navController: NavHostController) {

    BackHandler {
        // Check if there are any screens in the back stack
        if (navController.previousBackStackEntry != null) {
            navController.popBackStack()  // Navigate back to the previous screen
        } else {
            // Optionally show a dialog or perform any other action
            // when the back stack is empty (to prevent app closing)
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(
            route = Screen.SplashScreen.route, enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300, easing = LinearEasing)
                )
            }
        ) {
            SplashScreen(navController)
        }


        composable(
            route = Screen.WelcomeScreen.route, enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300, easing = LinearEasing)
                )
            }
        ) {
            WelcomeScreen(navController)
        }



        composable(
            route = Screen.LoginScreen.route, enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300, easing = LinearEasing)
                )
            }
        ) {
            LoginScreen(navController)
        }


        composable(
            route = Screen.HomeScreen.route, enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300, easing = LinearEasing)
                )
            }
        ) {
            NavigationScreen(navController)
        }


        composable(
            route = Screen.ResetPassword.route, enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300, easing = LinearEasing)
                )
            }
        ) {
            ResetPasswordScreen(navController)
        }


        composable(
            route = Screen.PortrCode.route, enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300, easing = LinearEasing)
                )
            }
        ) {
            PortrCodeScreen(navController)
        }


        composable(
            route = Screen.ProfileDetail.route, enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300, easing = LinearEasing)
                )
            }
        ) {
            ProfileDetailScreen(navController)
        }

        composable(
            route = Screen.WhatsNewDetail.route, enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300, easing = LinearEasing)
                )
            }
        ) {
            WhatsNewScreen(navController)
        }



        composable(
            route = Screen.GiveFeedBack.route, enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300, easing = LinearEasing)
                )
            }
        ) {
            GiveFeedbackScreen(navController)
        }



        composable(
            route = Screen.SearchBooking.route, enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300, easing = LinearEasing)
                )
            }
        ) {
            SearchBookingScreen(navController)
        }



        composable(
            route = Screen.BookingDetail.route, enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300, easing = LinearEasing)
                )
            }
        ) {
            BookingDetailScreen(navController)
        }


    }
}