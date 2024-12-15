package com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ops.airportr.R
import com.ops.airportr.common.theme.air_awesome_purple_100
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.theme.customTextHeadingStyle
import com.ops.airportr.common.theme.customTextLabelSmallStyle
import com.ops.airportr.common.theme.navigationBarColor
import com.ops.airportr.common.theme.white
import com.ops.airportr.data.viewmodel.MainViewModel
import com.ops.airportr.route.sidenavigationscreens.BottomScreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobsScreen(
    viewModel: MainViewModel,
    navController: NavController,
    navHostController: NavHostController,
) {
    viewModel.setCurrentScreen(BottomScreens.BottomNavigationScreens.Jobs)
    var upComingShowFlag by remember { mutableStateOf(false) }

    // State to manage the visibility of the bottom sheet
//    val bottomSheetState = rememberModalBottomSheetState(
//        skipPartiallyExpanded = true
//    )
    val scope = rememberCoroutineScope()

    var showSheet by remember { mutableStateOf(false) }


    //TODO: On Resume
    //TODO:========================================================
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    LaunchedEffect(currentBackStackEntry.value?.lifecycle) {
        currentBackStackEntry.value?.lifecycle?.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                // viewModel.loadData() // Call your ViewModel function
                Log.wtf("HELLO******", "CALLED")
            }
        })
    }
    //TODO:==========================================================


    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButtons(
                onClick = {
                    // Your onClick logic here
                    showSheet = true
                }
            )
        }

    ) { paddingValues ->
        // Main content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(navigationBarColor)
                .padding(paddingValues)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(white)
            ) {
                val (title, scannerImg, searchImg, filterImg, selectorBox,
                    upComingText, upComingCount, completedText, completedCount) = createRefs()
                Text(
                    text = stringResource(id = R.string.jobs),
                    style = customTextHeadingStyle,
                    fontSize = 35.sp,
                    modifier = Modifier
                        .constrainAs(title) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                        .padding(14.dp),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_filters_outline_24),
                    contentDescription = null, // provide content description if needed
                    modifier = Modifier
                        .constrainAs(filterImg) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                        .padding(top = 14.dp, end = 14.dp)
                        .height(40.dp)
                        .width(40.dp), // make the image background transparent
                    contentScale = ContentScale.Inside // scale the image to fill the Box
                )

                Image(
                    painter = painterResource(id = R.drawable.search_outline),
                    contentDescription = null, // provide content description if needed
                    modifier = Modifier
                        .constrainAs(searchImg) {
                            top.linkTo(parent.top)
                            end.linkTo(filterImg.start)
                        }
                        .padding(top = 14.dp)
                        .height(40.dp)
                        .width(40.dp), // make the image background transparent
                    contentScale = ContentScale.Inside // scale the image to fill the Box
                )

                Image(
                    painter = painterResource(id = R.drawable.qr_code),
                    contentDescription = null, // provide content description if needed
                    modifier = Modifier
                        .constrainAs(scannerImg) {
                            top.linkTo(parent.top)
                            end.linkTo(searchImg.start)
                        }
                        .padding(top = 14.dp)
                        .height(40.dp)
                        .width(40.dp), // make the image background transparent
                    contentScale = ContentScale.Inside // scale the image to fill the Box
                )

                Row(
                    modifier = Modifier
                        .constrainAs(selectorBox) {
                            top.linkTo(title.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .fillMaxWidth()
                        .background(white)
                        .height(70.dp)
                )
                {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .clickable {
                                upComingShowFlag = true
                            }// Equivalent to layout_weight="1"
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 5.dp)
                                .height(50.dp)// Equivalent to layout_marginStart and layout_marginEnd
                        )
                        {
                            ConstraintLayout(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                // Upcoming Text
                                Text(
                                    modifier = Modifier
                                        .constrainAs(upComingText) {
                                            top.linkTo(parent.top)
                                            start.linkTo(parent.start)
                                            end.linkTo(parent.end)
                                            bottom.linkTo(parent.bottom)
                                        },
                                    textAlign = TextAlign.Center,
                                    text = stringResource(id = R.string.upcoming),
                                    style = customTextLabelSmallStyle, // Use your custom text style here
                                    fontSize = 10.sp,
                                    color = if (upComingShowFlag) air_purple else air_awesome_purple_100, // Replace with your desired color
                                )


                                // Notification Badge
                                Box(
                                    modifier = Modifier
                                        .constrainAs(upComingCount) {
                                            top.linkTo(upComingText.top)
                                            start.linkTo(upComingText.end)
                                        }
                                        .offset(x = 4.dp) // Equivalent to layout_marginStart
                                        .background(
                                            color = air_purple, // Replace with your badge background color
                                            shape = RoundedCornerShape(8.dp) // Adjust radius as needed
                                        )
                                        .padding(
                                            horizontal = 4.dp,
                                            vertical = 2.dp
                                        ) // Equivalent to paddingHorizontal, paddingTop, paddingBottom
                                    //.visible(false) // Replace with your visibility logic
                                ) {
                                    Text(
                                        text = "22",
                                        color = Color.White, // Text color
                                        style = customTextLabelSmallStyle, // Use your custom text style
                                        fontSize = 8.sp,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }

                        }

                        // Bottom Line
                        Divider(
                            color = if (upComingShowFlag) air_purple else white, // Replace with your view border color
                            thickness = 1.dp, // Equivalent to layout_height="@dimen/_1sdp"
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .clickable {
                                upComingShowFlag = false
                            }// Equivalent to layout_weight="1"
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 5.dp)
                                .height(50.dp)// Equivalent to layout_marginStart and layout_marginEnd
                        )
                        {
                            ConstraintLayout(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                // Upcoming Text
                                Text(
                                    modifier = Modifier
                                        .constrainAs(upComingText) {
                                            top.linkTo(parent.top)
                                            start.linkTo(parent.start)
                                            end.linkTo(parent.end)
                                            bottom.linkTo(parent.bottom)
                                        },
                                    textAlign = TextAlign.Center,
                                    text = stringResource(id = R.string.completed),
                                    style = customTextLabelSmallStyle, // Use your custom text style here
                                    fontSize = 10.sp,
                                    color = if (!upComingShowFlag) air_purple else air_awesome_purple_100, // Replace with your desired color
                                )


                                // Notification Badge
                                Box(
                                    modifier = Modifier
                                        .constrainAs(upComingCount) {
                                            top.linkTo(upComingText.top)
                                            start.linkTo(upComingText.end)
                                        }
                                        .offset(x = 4.dp) // Equivalent to layout_marginStart
                                        .background(
                                            color = air_purple, // Replace with your badge background color
                                            shape = RoundedCornerShape(8.dp) // Adjust radius as needed
                                        )
                                        .padding(
                                            horizontal = 4.dp,
                                            vertical = 2.dp
                                        ) // Equivalent to paddingHorizontal, paddingTop, paddingBottom
                                    //.visible(false) // Replace with your visibility logic
                                ) {
                                    Text(
                                        text = "22",
                                        color = Color.White, // Text color
                                        style = customTextLabelSmallStyle, // Use your custom text style
                                        fontSize = 8.sp,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }

                        }

                        // Bottom Line
                        Divider(
                            color = if (!upComingShowFlag) air_purple else white, // Replace with your view border color
                            thickness = 1.dp, // Equivalent to layout_height="@dimen/_1sdp"
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

//            if (showSheet) {
//                ModalBottomSheet(
//                    sheetState = bottomSheetState,
//                    onDismissRequest = { showSheet = false }
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Text(text = "This is a Bottom Sheet")
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Button(onClick = {
//                            scope.launch { bottomSheetState.hide() }
//                                .invokeOnCompletion { showSheet = false }
//                        }) {
//                            Text("Close Bottom Sheet")
//                        }
//                    }
//                }
//            }
        }
    }


}

@Composable
fun MultiFloatingActionButtons(onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(16.dp)
        ) {
            FloatingActionButton(
                onClick,
                backgroundColor = air_purple,
                contentColor = Color.White,
                modifier = Modifier.size(56.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_plus_circle_fill_24), // Replace with your drawable's ID
                    contentDescription = "Example Icon",
                    modifier = Modifier.size(56.dp) // Adjust the size as needed
                )
            }

        }
    }
}