package com.ops.airportr.route

import com.ops.airportr.route.Graph.GIVE_FEEDBACK
import com.ops.airportr.route.Graph.HOME
import com.ops.airportr.route.Graph.LOGIN
import com.ops.airportr.route.Graph.PORTR_CODE
import com.ops.airportr.route.Graph.PROFILE_DETAIL
import com.ops.airportr.route.Graph.RESET_PASSWORD
import com.ops.airportr.route.Graph.SPLASH
import com.ops.airportr.route.Graph.WELCOME
import com.ops.airportr.route.Graph.WHATS_NEW

sealed class Screen(val route: String) {
    object SplashScreen : Screen(SPLASH)
    object LoginScreen : Screen(LOGIN)
    object WelcomeScreen : Screen(WELCOME)
    object HomeScreen: Screen(HOME)
    object ResetPassword: Screen(RESET_PASSWORD)
    object PortrCode: Screen(PORTR_CODE)
    object ProfileDetail: Screen(PROFILE_DETAIL)
    object WhatsNewDetail: Screen(WHATS_NEW)
    object GiveFeedBack: Screen(GIVE_FEEDBACK)

}
