package com.ops.airportr.ui.componts.sidenavigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ops.airportr.common.theme.air_awesome_light_white
import com.ops.airportr.common.utils.bottomNavBackGroundColor
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.common.utils.returnLabelAirPurpleColor
import com.ops.airportr.route.sidenavigationscreens.BottomScreens


@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    screens: List<BottomScreens.BottomNavigationScreens>,
    navController: NavController,
    isDarkTheme: Boolean
) {

    NavigationBar(
        modifier = modifier,
        containerColor = bottomNavBackGroundColor(isDarkTheme),
        tonalElevation = 8.dp // Background color of the Bottom Bar
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            // Check if the current route matches the screen's route
            val selected = currentRoute == screen.route

            NavigationBarItem(
                icon = {
                    Image(
                        painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        screen.title,
                        color = if (selected) returnLabelAirPurpleColor(isDarkTheme) else returnLabelAirPurple100Color(
                            isDarkTheme
                        )
                    )
                },
                selected = selected,
                onClick = {
                    navController.navigate(screen.route) {
                        // Ensure the selected screen is always pushed on top
//                        popUpTo = navController.graph.startDestinationId
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}


@Composable
fun AddItem_(
    screen: BottomScreens.BottomNavigationScreens,
    currentDestination: NavDestination?,
    navController: NavController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    //val selected = (currentDestination?.id ?: false) == screen.route

    val background =
        if (selected) air_awesome_light_white else Color.Transparent

    val contentColor = Color.White
    //if (selected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .height(50.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }), contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(1.dp)
        ) {

            androidx.compose.material3.Icon(
                painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                contentDescription = "icon",
                tint = Color.White
            )

            AnimatedVisibility(visible = selected) {
                Text(
                    text = screen.title,
                    color = contentColor,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}



