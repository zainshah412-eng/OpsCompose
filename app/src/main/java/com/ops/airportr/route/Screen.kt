package com.ops.airportr.route

import com.ops.airportr.route.Graph.HOME
import com.ops.airportr.route.Graph.LOGIN
import com.ops.airportr.route.Graph.RESET_PASSWORD
import com.ops.airportr.route.Graph.SPLASH
import com.ops.airportr.route.Graph.WELCOME

sealed class Screen(val route: String) {
    object SplashScreen : Screen(SPLASH)
    object LoginScreen : Screen(LOGIN)
    object WelcomeScreen : Screen(WELCOME)
    object HomeScreen: Screen(HOME)
    object ResetPassword: Screen(RESET_PASSWORD)
}
