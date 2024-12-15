package com.ops.airportr.route

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ops.airportr.ui.screens.loginscreen.LoginScreen
import com.ops.airportr.ui.screens.navigationscreen.NavigationScreen
import com.ops.airportr.ui.screens.resetpassword.ResetPasswordScreen
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
            route = Screen.SplashScreen.route
        ) {
            SplashScreen(navController)
        }
        composable(
            route = Screen.WelcomeScreen.route
        ) {
            WelcomeScreen(navController)
        }
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(navController)
        }
        composable(
            route = Screen.HomeScreen.route
        ) {
            NavigationScreen(navController)
        }

        composable(
            route = Screen.ResetPassword.route
        ) {
            ResetPasswordScreen(navController)
        }

    }
}