package com.ops.airportr.ui.screens.splashscreen

import androidx.compose.animation.core.EaseInOutElastic
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ops.airportr.R
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.theme.white
import com.ops.airportr.common.utils.moveOnNewScreen
import com.ops.airportr.route.Screen
import com.ops.airportr.ui.componts.LoaderDialog
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navHostController: NavHostController
) {
    val context = LocalContext.current
    var scale by remember {
        mutableStateOf(0.dp)
    }
    val animateAble = animateDpAsState(
        targetValue = scale,
        animationSpec = tween(1000, 0, easing = EaseInOutElastic), label = ""
    )
    LaunchedEffect(key1 = true) {
        delay(3000L)
        navHostController.moveOnNewScreen(Screen.WelcomeScreen.route, true)
//        if (AppApplication.sessionManager.isLoggedIn) {
//            navHostController.moveOnNewScreen(Screen.HomeScreen.route, true)
//        } else {
//            navHostController.moveOnNewScreen(Screen.WelcomeScreen.route, true)
//        }

    }
//    LoaderDialog(showDialog = true)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(air_purple),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.airportr_logo),
            contentDescription = "logo",
            colorFilter = ColorFilter.tint(white),
            modifier = Modifier
                .size(200.dp, 120.dp)
        )
    }
}


@Composable
@Preview
fun SplashScreenPreview() {
    val navController = rememberNavController()
    SplashScreen(navController)
}