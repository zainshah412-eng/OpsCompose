package com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom

import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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
import com.ops.airportr.common.theme.dark_blue
import com.ops.airportr.common.theme.navigationBarColor
import com.ops.airportr.common.theme.white
import com.ops.airportr.data.viewmodel.MainViewModel
import com.ops.airportr.route.sidenavigationscreens.BottomScreens
import kotlinx.coroutines.launch
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun JobsScreen(
    viewModel: MainViewModel,
    navController: NavController,
    navHostController: NavHostController,
) {
    viewModel.setCurrentScreen(BottomScreens.BottomNavigationScreens.Jobs)
    var upComingShowFlag by remember { mutableStateOf(false) }

    // State to manage the visibility of the bottom sheet
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var floatingButtonVisible by remember { mutableStateOf(false) }
    var onSheetTypeClicked by remember {
        mutableStateOf("1") // 1 for Floating 2 for Filter
    }
    // Automatically hide the sheet when it's dismissed (e.g., user clicks outside)
    LaunchedEffect(sheetState.isVisible) {
        if (!sheetState.isVisible) {
            isBottomSheetVisible = false
            floatingButtonVisible = true
        }
    }
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


    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // State to show or hide the date picker dialog
    val showDialog = remember { mutableStateOf(false) }
    val selectedStartDate = remember { mutableStateOf("") }
    val selectedEndDate = remember { mutableStateOf("") }
    val isStartDateClick = remember { mutableStateOf(true) }

    // Show date picker dialog when the button is clicked
    if (showDialog.value) {
        // Date Picker Dialog
        DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDay ->
                if (isStartDateClick.value) {
                    selectedStartDate.value = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    showDialog.value = false
                } else {
                    selectedEndDate.value = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    showDialog.value = false
                }
            },
            year, month, day
        ).show()
    }
    Scaffold(
        floatingActionButton = {
            if (floatingButtonVisible) {
                MultiFloatingActionButtons(
                    onClick = {
                        // Your onClick logic here
                        coroutineScope.launch {
                            onSheetTypeClicked = "1"
                            sheetState.show() // Show the bottom sheet
                        }
                        isBottomSheetVisible = true
                        floatingButtonVisible = false
                    }
                )
            }
        }

    ) { paddingValues ->
        // Main content
        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(if (onSheetTypeClicked == "1") 250.dp else 350.dp)
                        .padding(6.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    if (onSheetTypeClicked == "1") {
                        onFloatingBottomSheet(onClick = {
                            coroutineScope.launch {
                                sheetState.hide()
                            }
                            isBottomSheetVisible = false
                            floatingButtonVisible = true

                        })


                    } else {
                        OnFilterSheet(onStartDateClick = {
//                            coroutineScope.launch {
//                                sheetState.hide()
//                            }
//                            isBottomSheetVisible = false
//                            floatingButtonVisible = true
                            isStartDateClick.value = true
                            showDialog.value = true
                        }, onEndDateClick = {
                            isStartDateClick.value = false
                            showDialog.value = true
                        },
                            startDate = selectedStartDate.value,
                            endDate = selectedEndDate.value
                        )
                    }


                }
            },
            sheetShape = MaterialTheme.shapes.large.copy(
                topEnd = CornerSize(20.dp),
                topStart = CornerSize(20.dp)
            ), // Rounded top corners only
            sheetBackgroundColor = white // Optional: custom background color
        ) {
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
                            .width(40.dp)
                            .clickable {
                                coroutineScope.launch {
                                    onSheetTypeClicked = "2"
                                    sheetState.show() // Show the bottom sheet
                                }
                                isBottomSheetVisible = true
                                floatingButtonVisible = false
                            }, // make the image background transparent
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


            }
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
            modifier = Modifier
                .padding(16.dp)
                .size(56.dp) // This ensures the FAB remains round
                .clip(CircleShape)
        ) {
            FloatingActionButton(
                onClick,
                containerColor = air_purple,
                contentColor = air_purple,
                modifier = Modifier.size(56.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_more_vert_white), // Replace with your drawable's ID
                    contentDescription = "Example Icon",
                    modifier = Modifier.size(30.dp) // Adjust the size as needed
                )
            }

        }
    }
}

@Composable
fun onFloatingBottomSheet(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 22.dp)
    ) {

        // First RelativeLayout equivalent (with centered Text and Image)
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.actions),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.objective_regular)),
                    fontSize = 16.sp,
                    letterSpacing = 0.1.sp
                ),
                modifier = Modifier.align(Alignment.Center)
            )

            IconButton(
                onClick = { onClick() },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cross_outline),
                    contentDescription = "Cross Icon",
                    modifier = Modifier.size(24.dp),
                    tint = air_purple,
                )
            }
        }

        // Second RelativeLayout equivalent (bags cover)
        AnimatedVisibility(visible = false) { // Set to `true` to make it visible
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 20.dp)
                    .align(Alignment.Start)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bag),
                    contentDescription = "Bag Icon",
                    modifier = Modifier.size(20.dp),
                    tint = air_purple
                )
                Text(
                    text = stringResource(id = R.string.accept),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.objective_bold)),
                        fontSize = 12.sp,
                        letterSpacing = 0.1.sp
                    ),
                    color = air_purple,
                    modifier = Modifier.padding(start = 14.dp)
                )
            }
        }

        // Third RelativeLayout equivalent (organize bags)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bag),
                contentDescription = "Bag Icon",
                modifier = Modifier.size(20.dp),
                tint = air_purple
            )
            Text(
                text = stringResource(id = R.string.organise_bags),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.objective_bold)),
                    fontSize = 12.sp,
                    letterSpacing = 0.1.sp
                ),
                color = air_purple,
                modifier = Modifier.padding(start = 14.dp)
            )
        }

        // Fourth LinearLayout equivalent (Tag Inject)
        AnimatedVisibility(visible = false) { // Set to `true` to make it visible
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 25.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_tag),
                    contentDescription = "Tag Icon",
                    modifier = Modifier.size(20.dp),
                    tint = air_purple
                )
                Text(
                    text = stringResource(id = R.string.tag),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.objective_bold)),
                        fontSize = 12.sp,
                        letterSpacing = 0.1.sp
                    ),
                    color = air_purple,
                    modifier = Modifier.padding(start = 14.dp)
                )
            }
        }

        // Fifth LinearLayout equivalent (Take Custody)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 25.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = "Star Icon",
                modifier = Modifier.size(20.dp),
                tint = air_purple
            )
            Text(
                text = stringResource(id = R.string.take_custody),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.objective_bold)),
                    fontSize = 12.sp,
                    letterSpacing = 0.1.sp
                ),
                color = air_purple,
                modifier = Modifier.padding(start = 14.dp)
            )
        }

        // Sixth LinearLayout equivalent (Deliver to Passenger)
        AnimatedVisibility(visible = false) { // Set to `true` to make it visible
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 25.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user_outline),
                    contentDescription = "User Icon",
                    modifier = Modifier.size(20.dp),
                    tint = air_purple
                )
                Text(
                    text = stringResource(id = R.string.deliver_to_passenger),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.objective_bold)),
                        fontSize = 12.sp,
                        letterSpacing = 0.1.sp
                    ),
                    color = air_purple,
                    modifier = Modifier.padding(start = 14.dp)
                )
            }
        }

        // Seventh LinearLayout equivalent (Place in Order)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 25.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_box),
                contentDescription = "Box Icon",
                modifier = Modifier.size(20.dp),
                tint = air_purple
            )
            Text(
                text = stringResource(id = R.string.place),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.objective_bold)),
                    fontSize = 12.sp,
                    letterSpacing = 0.1.sp
                ),
                color = air_purple,
                modifier = Modifier.padding(start = 14.dp)
            )
        }
    }
}

@Composable
fun OnFilterSheet(
    onStartDateClick: () -> Unit,
    onEndDateClick: () -> Unit,
    startDate: String = "",
    endDate: String = ""
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 30.dp, end = 16.dp, bottom = 30.dp)
            .verticalScroll(scrollState)
    ) {

        // Title TextView (tvFilter)
        Text(
            text = stringResource(id = R.string.filter),
            style = MaterialTheme.typography.h6,
            fontFamily = FontFamily(Font(R.font.objective_bold)),
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Date Layout
        Row(modifier = Modifier.fillMaxWidth()) {

            // Start Date
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = R.string.start_date),
                    style = MaterialTheme.typography.body2,
                    fontFamily = FontFamily(Font(R.font.objective_regular)),
                    fontSize = 12.sp,
                    color = dark_blue
                )

                // Start Date Picker
                Box(
                    modifier = Modifier
                        .height(45.dp)
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray)
                        .clickable {
                            onStartDateClick()
                        }
                        .padding(start = 12.dp, end = 12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.CenterStart),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Start Date",
                            modifier = Modifier.size(18.dp),
                            tint = dark_blue
                        )
                        Text(
                            text = if (startDate.equals("")) stringResource(id = R.string.start_date) else startDate,
                            modifier = Modifier
                                .padding(start = 8.dp),
                            style = MaterialTheme.typography.body2,
                            fontFamily = FontFamily(Font(R.font.objective_regular)),
                            fontSize = 12.sp,
                            color = dark_blue
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // End Date
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = R.string.end_date),
                    style = MaterialTheme.typography.body2,
                    fontFamily = FontFamily(Font(R.font.objective_regular)),
                    fontSize = 12.sp,
                    color = dark_blue
                )

                // End Date Picker
                Box(
                    modifier = Modifier
                        .height(45.dp)
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray)
                        .padding(start = 12.dp, end = 12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.CenterStart)
                            .clickable {
                                onEndDateClick()
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "End Date",
                            modifier = Modifier.size(18.dp),
                            tint = dark_blue
                        )
                        Text(
                            text = if (endDate.equals("")) stringResource(id = R.string.end_date) else endDate,
                            modifier = Modifier
                                .padding(start = 8.dp),
                            style = MaterialTheme.typography.body2,
                            fontFamily = FontFamily(Font(R.font.objective_regular)),
                            fontSize = 12.sp,
                            color = dark_blue
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))



        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Airport Section
            Text(
                text = stringResource(id = R.string.air_port),
                style = MaterialTheme.typography.body2,
                fontFamily = FontFamily(Font(R.font.objective_regular)),
                fontSize = 12.sp,
                color = dark_blue
            )
            Text(
                text = "0 Selected",
                style = MaterialTheme.typography.body2,
                fontFamily = FontFamily(Font(R.font.objective_bold)),
                fontSize = 12.sp,
                color = air_purple
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Toggle Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.show_cancelled_booking),
                style = MaterialTheme.typography.body2,
                fontFamily = FontFamily(Font(R.font.objective_regular)),
                fontSize = 12.sp,
                color = dark_blue
            )

            Switch(
                checked = false,
                onCheckedChange = { /* Handle toggle */ },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Blue
                )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Divider
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.height(22.dp))

        // Bottom Buttons Section
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            // Reset Button
            Text(
                text = stringResource(id = R.string.reset),
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp)
                    .clickable { /* Handle reset click */ },
                style = MaterialTheme.typography.button,
                fontFamily = FontFamily(Font(R.font.objective_bold)),
                fontSize = 14.sp
            )

            // Apply Button
            Text(
                text = stringResource(id = R.string.apply),
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp)
                    .clickable { /* Handle apply click */ },
                style = MaterialTheme.typography.button,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.objective_bold)),
                fontSize = 14.sp
            )
        }
    }
}

