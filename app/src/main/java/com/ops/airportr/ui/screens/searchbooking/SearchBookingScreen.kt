package com.ops.airportr.ui.screens.searchbooking

import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.ops.airportr.AppApplication
import com.ops.airportr.R
import com.ops.airportr.common.AppActionValues
import com.ops.airportr.common.AppConstants
import com.ops.airportr.common.theme.air_awesome_purple_100
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.theme.air_red
import com.ops.airportr.common.theme.air_yellow
import com.ops.airportr.common.theme.badgeTextColor
import com.ops.airportr.common.theme.customEditTextColorDarkTheme
import com.ops.airportr.common.theme.customTextHeadingStyle
import com.ops.airportr.common.theme.customTextLabelSmallStyle
import com.ops.airportr.common.theme.fontsRegular
import com.ops.airportr.common.theme.white
import com.ops.airportr.common.utils.abcTagBackGroundColor
import com.ops.airportr.common.utils.extension.moveOnNewScreen
import com.ops.airportr.common.utils.overSizeBagTagBackGroundColor
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.common.utils.returnLabelAirPurpleColor
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.common.utils.extension.urlForAcceptance
import com.ops.airportr.domain.model.bookingdetails.Job
import com.ops.airportr.route.Screen
import com.ops.airportr.ui.componts.LoaderDialog
import com.ops.airportr.ui.componts.Space
import com.ops.airportr.ui.screens.searchbooking.item.JobItem

@Composable
fun SearchBookingScreen(
    navHostController: NavHostController,
    viewModel: SearchBookingViewModel = hiltViewModel(),
) {
    val activity = LocalContext.current as? Activity
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()

    var snackBarShowFlag by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showLoader by remember { mutableStateOf(false) }
    var bookingRef by remember { mutableStateOf(("")) }
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(returnBackGroundColor(isDarkTheme))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp, end = 12.dp, top = 12.dp)
        ) {
            val (searchBar, bookingInfoBox, noBookingFound, viewBookingDetails) = createRefs()
            Box(
                modifier = Modifier
                    .constrainAs(searchBar) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(60.dp)
                    .fillMaxWidth()
                    .border(
                        1.dp,
                        returnLabelAirPurpleColor(isDarkTheme),
                        RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        //  onStartDateClick()
                    }
                    .padding(start = 4.dp, end = 4.dp, top = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = null, // provide content description if needed
                        modifier = Modifier
                            .padding(start = 14.dp)
                            .height(30.dp)
                            .width(30.dp)
                            .clickable {
                                navHostController.popBackStack()
                            }, // make the image background transparent
                        contentScale = ContentScale.Inside, // scale the image to fill the Box
                        colorFilter = ColorFilter.tint(returnLabelDarkBlueColor(isDarkTheme))
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    TextField(
                        value = bookingRef,
                        onValueChange = { bookingRef = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.search_hint),
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 14.sp,
                                color = returnLabelDarkBlueColor(isDarkTheme),
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        textStyle = TextStyle(fontFamily = fontsRegular),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = returnLabelAirPurpleColor(isDarkTheme),
                            disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            backgroundColor = returnBackGroundColor(isDarkTheme),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            cursorColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = stringResource(id = R.string.search_label),
                        style = MaterialTheme.typography.labelLarge,
                        color = returnLabelAirPurpleColor(isDarkTheme),
                        fontSize = 14.sp,
                        modifier = Modifier
                            .width(80.dp)
                            .clickable {
                                viewModel.searchBooking(
                                    context.urlForAcceptance() + AppConstants.GET_BOOKING_DETAIL,
                                    bookingRef
                                )
                            }
                    )

                }
            }

            if (state.response != null && state.response?.bookingDetails != null) {

                state.response?.bookingDetails?.let { it1 ->
                    AppApplication.sessionManager.saveBookingDetails(it1)
                }

                Box(modifier = Modifier.constrainAs(bookingInfoBox) {
                    top.linkTo(searchBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(returnBackGroundColor(isDarkTheme))
                            .padding(top = 20.dp)
                    ) {

                        val (userName, bookingReference, tagsBox, jobsBox) = createRefs()

                        var detailModel = state.response?.bookingDetails!!.bookingJourneyDetails[0]
                        val jobs = remember { mutableStateOf(ArrayList<Job>()) }
                        if (detailModel.passengers.isNullOrEmpty()) {
                            Text(
                                modifier = Modifier
                                    .constrainAs(userName) {
                                        top.linkTo(parent.top)
                                        start.linkTo(parent.start)
                                    }
                                    .padding(top = 5.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Start,
                                text = stringResource(
                                    id = R.string.pName,
                                    detailModel.bookingContact?.firstName
                                        ?: "",
                                    detailModel.bookingContact?.lastName
                                        ?: ""
                                ),
                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                fontSize = 18.sp,
                                color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                            )
                        } else {
                            Text(
                                modifier = Modifier
                                    .constrainAs(userName) {
                                        top.linkTo(parent.top)
                                        start.linkTo(parent.start)
                                    }
                                    .padding(top = 5.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Start,
                                text = stringResource(
                                    id = R.string.pName,
                                    detailModel.passengers[0].firstName
                                        ?: "",
                                    detailModel.passengers[0].lastName
                                        ?: ""
                                ),
                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                fontSize = 18.sp,
                                color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                            )
                        }

                        Text(
                            modifier = Modifier
                                .constrainAs(bookingReference) {
                                    top.linkTo(userName.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    width = Dimension.fillToConstraints

                                }
                                .padding(top = 5.dp),
                            textAlign = TextAlign.Start,
                            text = detailModel.bookingReference ?: "",
                            style = MaterialTheme.typography.labelSmall, // Use your custom text style here
                            fontSize = 18.sp,
                            color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                        )
                        Row(
                            modifier = Modifier
                                .constrainAs(tagsBox) {
                                    top.linkTo(bookingReference.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    width = Dimension.fillToConstraints
                                }
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp)) // Apply rounded corners
                                    .background(abcTagBackGroundColor(isDarkTheme)) // Set background color
                                    .border(
                                        0.dp,
                                        abcTagBackGroundColor(isDarkTheme),
                                        RoundedCornerShape(5.dp)
                                    )
                                    .padding(
                                        top = 6.dp,
                                        bottom = 6.dp,
                                        start = 10.dp,
                                        end = 10.dp
                                    )

                            ) {
                                val isAbcBooking =
                                    detailModel.addOnProductsOverview?.isNullOrEmpty() != true &&
                                            detailModel.addOnProductsOverview.get(
                                                0
                                            ).addOnProductType.equals(
                                                1
                                            ) == true
                                Text(
                                    text = if (isAbcBooking) "ABC" else "CCD",
                                    color = if (isDarkTheme) badgeTextColor else customEditTextColorDarkTheme, // Text color
                                    style = MaterialTheme.typography.labelSmall, // Use your custom text style
                                    fontSize = 10.sp,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            Space(height = 0, width = 5)

                            if (detailModel.isCancelled == true) {

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(5.dp)) // Apply rounded corners
                                        .background(air_red) // Set background color
                                        .border(0.dp, air_red, RoundedCornerShape(5.dp))
                                        .padding(6.dp)

                                ) {
                                    Text(
                                        text = stringResource(id = R.string.cancelled),
                                        color = white, // Text color
                                        style = customTextLabelSmallStyle, // Use your custom text style
                                        fontSize = 10.sp,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                                Space(height = 0, width = 5)
                            }
                            if (detailModel.jobs?.get(0)?.jobType == AppActionValues.REPATRAITION_ACTION_VALUE) {

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(5.dp)) // Apply rounded corners
                                        .background(air_purple) // Set background color
                                        .border(0.dp, air_purple, RoundedCornerShape(5.dp))
                                        .padding(6.dp)

                                ) {
                                    Text(
                                        text = stringResource(id = R.string.repatriated),
                                        color = white, // Text color
                                        style = customTextLabelSmallStyle, // Use your custom text style
                                        fontSize = 10.sp,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                                Space(height = 0, width = 5)
                            }

                            if (detailModel.hasOversizedBag) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(5.dp)) // Apply rounded corners
                                        .background(overSizeBagTagBackGroundColor(isDarkTheme)) // Set background color
                                        .border(
                                            0.dp,
                                            overSizeBagTagBackGroundColor(isDarkTheme),
                                            RoundedCornerShape(5.dp)
                                        )
                                        .padding(6.dp)

                                ) {
                                    Text(
                                        text = stringResource(id = R.string.oversize),
                                        color = if (isDarkTheme) badgeTextColor else customEditTextColorDarkTheme, // Text color
                                        style = MaterialTheme.typography.labelSmall, // Use your custom text style
                                        fontSize = 10.sp,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                                Space(height = 0, width = 5)
                            }


                            if (detailModel.isConditionalAcceptance == true) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(5.dp)) // Apply rounded corners
                                        .background(air_yellow) // Set background color
                                        .border(0.dp, air_yellow, RoundedCornerShape(5.dp))
                                        .height(dimensionResource(id = R.dimen._9sdp))
                                        .width(dimensionResource(id = R.dimen._9sdp))
                                )
                            }

                        }

                        Column(modifier = Modifier
                            .constrainAs(jobsBox) {
                                top.linkTo(tagsBox.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(viewBookingDetails.top)
                                width = Dimension.fillToConstraints
                                height = Dimension.wrapContent
                            }) {
                            jobs.value.clear()
                            detailModel.jobs?.let { jobs.value.addAll(it) }
                            JobItem(
                                context,
                                onClick = { jobObj ->
                                    var name = ""
                                    var flag = false
                                    var seal = ""
                                    var jobObject: Job = Job()
                                    if (jobObj.jobType == AppActionValues.ACCEPTANCE_ACTION_VALUE) {
                                        if (!detailModel.bookingContact?.firstName.isNullOrEmpty())
                                            name =
                                                "${
                                                    detailModel.bookingContact?.firstName
                                                } ${detailModel.bookingContact?.lastName}"

                                        if (jobObj.currentChampion?.championId == null) {
                                            AppApplication.sessionManager.saveCurrentChampionId("")
                                        } else {
                                            jobObj.currentChampion?.championId?.let {
                                                AppApplication.sessionManager.saveCurrentChampionId(
                                                    it
                                                )
                                            }
                                        }
                                        for (obj in detailModel.jobs!!) {
                                            if (obj.jobType == AppActionValues.ACCEPTANCE_ACTION_VALUE) {
                                                jobObject = obj
                                                break
                                            }
                                        }

                                        if (jobObject != null) {
                                            // Convert jobObject to JSON
                                            val jobJson = Gson().toJson(jobObject)

                                            // Construct route with arguments
                                            val encodedName = Uri.encode(name)
                                            val encodedJobJson = Uri.encode(jobJson)
                                            val route =
                                                Screen.ProcessBag.route + "/${encodedName}/${encodedJobJson}"

                                            // Navigate to the new screen
                                            navHostController.moveOnNewScreen(route, false)
                                        }


                                    }

                                },
                                itemAtPos = jobs
                            )

                            Text(
                                modifier = Modifier
                                    .padding(top = 25.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                        navHostController.moveOnNewScreen(
                                            Screen.BookingDetail.route,
                                            false
                                        )
                                    },
                                textAlign = TextAlign.Center,
                                text = stringResource(id = R.string.view_booking_detail),
                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                fontSize = 14.sp,
                                color = returnLabelAirPurpleColor(isDarkTheme), // Replace with your desired color
                            )
                        }


                    }


                }
            } else {
                Column(
                    modifier = Modifier
                        .constrainAs(bookingInfoBox) {
                            top.linkTo(searchBar.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            height = Dimension.fillToConstraints
                        }
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.oops),
                        contentDescription = stringResource(id = R.string.no_bookings_here), // provide content description if needed
                        modifier = Modifier
                            .padding(top = 14.dp)
                            .height(120.dp)
                            .width(120.dp), // make the image background transparent
                        contentScale = ContentScale.Crop // scale the image to fill the Box
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.no_bookings_here),
                        style = customTextHeadingStyle, // Use your custom text style here
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = returnLabelDarkBlueColor(isDarkTheme)// Replace with your desired color
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 5.dp, start = 10.dp, end = 10.dp)
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.try_adjusting_your_filters),
                        style = customTextLabelSmallStyle, // Use your custom text style here
                        fontSize = 12.sp,
                        color = air_awesome_purple_100, // Replace with your desired color
                    )


                }
            }
        }

    }

    if (state.error != null) {
        errorMessage = state.error ?: context.getString(R.string.no_internet)
        snackBarShowFlag = true
    }
    if (state.isLoading) {
        Log.wtf("StateLoadingAuth", "Called")
        showLoader = true
        LoaderDialog(showDialog = showLoader)
    }
}