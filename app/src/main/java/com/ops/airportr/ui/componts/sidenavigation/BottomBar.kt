package com.ops.airportr.ui.componts.sidenavigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
import com.ops.airportr.common.theme.air_awesome_purple_100
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.theme.custom_white
import com.ops.airportr.common.theme.fonts
import com.ops.airportr.route.sidenavigationscreens.BottomScreens


@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    screens: List<BottomScreens.BottomNavigationScreens>,
    navController: NavController
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = custom_white
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val currentDestination = navBackStackEntry?.destination
        val contentColor = Color.White
        screens.forEach { screen ->
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                        contentDescription = "icon",
                        tint = if (selected) air_purple else air_awesome_purple_100
                    )
                },
                label = {
                    Text(
                        screen.title,
                        color = if (selected) air_purple else air_awesome_purple_100
                    )
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo = navController.graph.startDestinationId
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
fun BottomBarNewDesign(
    modifier: Modifier = Modifier,
    screens: List<BottomScreens.BottomNavigationScreens>,
    navController: NavController
) {

    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination

        Row(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, top = 8.dp, bottom = 15.dp)
                .background(
                    air_awesome_light_white,
                    shape = RoundedCornerShape(35.dp)
                )
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach { screen ->
                AddItem_(
                    screen = screen,
                    currentDestination = currentRoute,
                    navController = navController
                )
            }
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
                androidx.compose.material3.Text(
                    text = screen.title,
                    color = contentColor,
                    textAlign = TextAlign.Center,
                    fontFamily = fonts
                )
            }
        }
    }
}



