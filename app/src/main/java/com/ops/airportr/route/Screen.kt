package com.ops.airportr.route

import com.ops.airportr.route.Graph.ACCEPTANCE_SCREEN
import com.ops.airportr.route.Graph.BAG_DETAIL
import com.ops.airportr.route.Graph.BOOKING_DETAIL
import com.ops.airportr.route.Graph.GIVE_FEEDBACK
import com.ops.airportr.route.Graph.HOME
import com.ops.airportr.route.Graph.JOB_DETAILS
import com.ops.airportr.route.Graph.LOGIN
import com.ops.airportr.route.Graph.MESSAGES
import com.ops.airportr.route.Graph.NOTES
import com.ops.airportr.route.Graph.PORTR_CODE
import com.ops.airportr.route.Graph.PROCESS_BAG
import com.ops.airportr.route.Graph.PROFILE_DETAIL
import com.ops.airportr.route.Graph.RESET_PASSWORD
import com.ops.airportr.route.Graph.SEARCH_BOOKING
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
    object SearchBooking: Screen(SEARCH_BOOKING)
    object BookingDetail: Screen(BOOKING_DETAIL)
    object BagDetail: Screen(BAG_DETAIL)
    object ProcessBag: Screen(PROCESS_BAG)
    object Acceptance: Screen(ACCEPTANCE_SCREEN)
    object JobDetails: Screen(JOB_DETAILS)
    object Messages: Screen(MESSAGES)
    object Notes: Screen(NOTES)

}
