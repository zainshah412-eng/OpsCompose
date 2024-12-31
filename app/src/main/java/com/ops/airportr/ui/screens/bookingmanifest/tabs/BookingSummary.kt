package com.ops.airportr.ui.screens.bookingmanifest.tabs

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ops.airportr.R
import com.ops.airportr.common.AppActionValues
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.theme.air_red
import com.ops.airportr.common.theme.air_yellow
import com.ops.airportr.common.theme.badgeTextColor
import com.ops.airportr.common.theme.customEditTextColorDarkTheme
import com.ops.airportr.common.theme.customTextLabelSmallStyle
import com.ops.airportr.common.theme.dark_blue
import com.ops.airportr.common.theme.dim_green_color
import com.ops.airportr.common.theme.editTextBorderStrockColor
import com.ops.airportr.common.theme.light_orange
import com.ops.airportr.common.theme.light_white
import com.ops.airportr.common.theme.white
import com.ops.airportr.common.utils.BookingDetailsSingleton
import com.ops.airportr.common.utils.abcTagBackGroundColor
import com.ops.airportr.common.utils.convertDateForBagCheckIn
import com.ops.airportr.common.utils.convertDateWithoutZoneTime
import com.ops.airportr.common.utils.convertIntoDateTimeFormat
import com.ops.airportr.common.utils.overSizeBagTagBackGroundColor
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnFlightTagsBackGround
import com.ops.airportr.common.utils.returnJobsNumberCircleBackground
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.domain.model.bookingdetails.BookingJourneyDetail
import com.ops.airportr.domain.model.bookingdetails.Job
import com.ops.airportr.ui.componts.Space

@Composable
fun BookingSummaryScreen(
    navController: NavController
) {
    val activity = LocalContext.current as? Activity
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()
    var bookingDetails: BookingDetailsSingleton
    var detailModel by remember { mutableStateOf(BookingJourneyDetail()) }

    val jobs = remember { mutableStateOf(ArrayList<Job>()) }
    //TODO: On Resume
    //TODO:========================================================
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    LaunchedEffect(currentBackStackEntry.value?.lifecycle) {
        currentBackStackEntry.value?.lifecycle?.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                // viewModel.loadData() // Call your ViewModel function
                bookingDetails = BookingDetailsSingleton()
                bookingDetails.init()
                detailModel = bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)!!

            }
        })
    }


    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        bookingDetails = BookingDetailsSingleton()
        bookingDetails.init()
        detailModel = bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)!!
        jobs.value.clear()
        detailModel.jobs?.let { jobs.value.addAll(it) }
        // Header (Static Content)
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(returnBackGroundColor(isDarkTheme))
            ) {


                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(returnBackGroundColor(isDarkTheme))
                        .padding(
                            top = 20.dp,
                            start = 10.dp,
                            end = 10.dp
                        )
                ) {

                    val (userName, bookingReference, tagsBox, jobsBox, bookerContactBox) = createRefs()
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

                }


            }
        }

        // LazyColumn Items (Dynamic Content)
        itemsIndexed(jobs.value) { index, job ->
            JobInnerItem(
                itemAtPos = job,
                index,
                onClick = {

                }
            )
        }


        // Footer (Static Content)
        item {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(returnBackGroundColor(isDarkTheme))
                    .padding(
                        top = 20.dp,
                        start = 10.dp,
                        end = 10.dp
                    )
            ) {

                val (bookerContactBox, flightJourneyBox) = createRefs()
                Column(modifier = Modifier
                    .constrainAs(bookerContactBox) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
                    .padding(start = 10.dp, end = 10.dp)) {
                    Text(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        text = stringResource(
                            id = R.string.booker_contact_detials
                        ),
                        style = MaterialTheme.typography.labelLarge, // Use your custom text style here
                        fontSize = 22.sp,
                        color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        text = bookingDetails.getBookingName(),
                        style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                        fontSize = 18.sp,
                        color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        text = bookingDetails.getBookingEmail(),
                        style = MaterialTheme.typography.labelSmall, // Use your custom text style here
                        fontSize = 14.sp,
                        color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        text = bookingDetails.getBookingContactNum(),
                        style = MaterialTheme.typography.labelSmall, // Use your custom text style here
                        fontSize = 14.sp,
                        color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                    )
                    Divider(
                        modifier = Modifier
                            .padding(top = 10.dp),
                        thickness = 0.5.dp, // Adjust this value for thickness
                        color = if (isDarkTheme) editTextBorderStrockColor else light_white
                    )


                }

                Column(modifier = Modifier
                    .constrainAs(flightJourneyBox) {
                        top.linkTo(bookerContactBox.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .padding(start = 10.dp, end = 10.dp, top = 5.dp))
                {
                    Text(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        text = stringResource(
                            id = R.string.flights_in_this_jour
                        ),
                        style = MaterialTheme.typography.labelLarge, // Use your custom text style here
                        fontSize = 22.sp,
                        color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen._35sdp)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier,
                            textAlign = TextAlign.Start,
                            text = bookingDetails.flightInfo?.requestFlightNumber ?: "",
                            style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                            fontSize = 16.sp,
                            color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                        )

                        Image(
                            painter = painterResource(id = R.drawable.air_france),
                            contentDescription = null, // provide content description if needed
                            modifier = Modifier
                                .height(dimensionResource(id = R.dimen._35sdp))
                                .width(dimensionResource(id = R.dimen._120sdp)), // make the image background transparent
                            contentScale = ContentScale.Fit // scale the image to fill the Box
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen._35sdp)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.flighttakeoff),
                            contentDescription = null, // provide content description if needed
                            modifier = Modifier
                                .height(dimensionResource(id = R.dimen._25sdp))
                                .width(dimensionResource(id = R.dimen._25sdp)), // make the image background transparent
                            contentScale = ContentScale.Fit // scale the image to fill the Box
                        )

                        Image(
                            painter = painterResource(id = R.drawable.flightland),
                            contentDescription = null, // provide content description if needed
                            modifier = Modifier
                                .height(dimensionResource(id = R.dimen._25sdp))
                                .width(dimensionResource(id = R.dimen._25sdp)), // make the image background transparent
                            contentScale = ContentScale.Fit // scale the image to fill the Box
                        )
                    }

                    Divider(
                        modifier = Modifier
                            .padding(top = 10.dp),
                        thickness = 1.dp, // Adjust this value for thickness
                        color = if (isDarkTheme) editTextBorderStrockColor else light_white
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen._35sdp)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .offset(x = 4.dp) // Equivalent to layout_marginStart
                                .background(
                                    color = if (isDarkTheme) returnFlightTagsBackGround(
                                        isDarkTheme
                                    )
                                    else returnLabelAirPurple100Color(isDarkTheme), // Replace with your badge background color
                                    shape = RoundedCornerShape(4.dp) // Adjust radius as needed
                                )
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 4.dp
                                ) // Equivalent to paddingHorizontal, paddingTop, paddingBottom
                            //.visible(false) // Replace with your visibility logic
                        ) {

                            Text(
                                text = bookingDetails.flightStatusDetail?.departureAirport?.airportCode
                                    ?: "",
                                color = returnLabelDarkBlueColor(isDarkTheme), // Text color
                                style = customTextLabelSmallStyle, // Use your custom text style
                                fontSize = 10.sp,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }


                        Box(
                            modifier = Modifier
                                .offset(x = 4.dp) // Equivalent to layout_marginStart
                                .background(
                                    color = if (isDarkTheme) returnFlightTagsBackGround(
                                        isDarkTheme
                                    )
                                    else returnLabelAirPurple100Color(isDarkTheme), // Replace with your badge background color
                                    shape = RoundedCornerShape(4.dp) // Adjust radius as needed
                                )
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 4.dp
                                ) // Equivalent to paddingHorizontal, paddingTop, paddingBottom
                            //.visible(false) // Replace with your visibility logic
                        ) {

                            Text(
                                text = bookingDetails.flightStatusDetailLastStop?.arrivalAirport?.airportCode
                                    ?: "",
                                color = returnLabelDarkBlueColor(isDarkTheme), // Text color
                                style = customTextLabelSmallStyle, // Use your custom text style
                                fontSize = 10.sp,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen._35sdp)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier,
                            textAlign = TextAlign.Start,
                            text = bookingDetails.departureDateTimeDetails?.scheduledDateTimeLocal?.convertDateWithoutZoneTime()
                                ?: "",
                            style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                            fontSize = 20.sp,
                            color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                        )


                        bookingDetails.flightStatusDetailsList?.let { flightStatusDetailsList ->
                            for (obj in flightStatusDetailsList) {
                                if (obj.isFinalDestination) {
                                    Text(
                                        modifier = Modifier,
                                        textAlign = TextAlign.Start,
                                        text = obj.arrivalDateTimeDetails?.scheduledDateTimeLocal?.convertDateWithoutZoneTime()
                                            ?: "",
                                        style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                        fontSize = 20.sp,
                                        color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                                    )
                                    break
                                }
                            }
                        }

                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen._25sdp)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier,
                            textAlign = TextAlign.Start,
                            text = bookingDetails.flightStatusDetail?.departureAirport?.airportCityName
                                ?: "",
                            style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                            fontSize = 12.sp,
                            color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                        )
                        Text(
                            modifier = Modifier,
                            textAlign = TextAlign.Start,
                            text = bookingDetails.flightStatusDetailLastStop?.arrivalAirport?.airportCityName
                                ?: "",
                            style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                            fontSize = 12.sp,
                            color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                        )

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen._25sdp)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier,
                            textAlign = TextAlign.Start,
                            text = bookingDetails.departureDateTimeDetails?.scheduledDate?.convertDateForBagCheckIn()
                                ?: "",
                            style = MaterialTheme.typography.labelSmall, // Use your custom text style here
                            fontSize = 14.sp,
                            color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                        )
                        bookingDetails.flightStatusDetailsList?.let { flightStatusDetailsList ->
                            for (obj in flightStatusDetailsList) {
                                if (obj.isFinalDestination) {
                                    Text(
                                        modifier = Modifier,
                                        textAlign = TextAlign.Start,
                                        text = obj.arrivalDateTimeDetails?.scheduledDate?.convertDateForBagCheckIn()
                                            ?: "",
                                        style = MaterialTheme.typography.labelSmall, // Use your custom text style here
                                        fontSize = 14.sp,
                                        color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                    )
                                    break
                                }
                            }
                        }

                    }

                    Divider(
                        modifier = Modifier
                            .padding(top = 10.dp),
                        thickness = 0.5.dp, // Adjust this value for thickness
                        color = if (isDarkTheme) editTextBorderStrockColor else light_white
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        text = stringResource(
                            id = R.string.flags
                        ),
                        style = MaterialTheme.typography.labelLarge, // Use your custom text style here
                        fontSize = 22.sp,
                        color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                    )
                    Space(10, 0)
                    Box(
                        modifier = Modifier
                            .offset(x = 4.dp) // Equivalent to layout_marginStart
                            .background(
                                color = if (bookingDetails.bookingJourneyDetail?.isPreAcceptanceEligible == true)
                                    dim_green_color else light_orange, // Replace with your badge background color
                                shape = RoundedCornerShape(4.dp) // Adjust radius as needed
                            )
                            .fillMaxWidth()

                            .padding(
                                horizontal = 10.dp,
                                vertical = 10.dp,
                            ) // Equivalent to paddingHorizontal, paddingTop, paddingBottom
                        //.visible(false) // Replace with your visibility logic
                    ) {

                        Text(
                            text = stringResource(id = R.string.pre_acceptance),
                            color = dark_blue, // Text color
                            style = MaterialTheme.typography.labelMedium, // Use your custom text style
                            fontSize = 16.sp,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                    }
                    AnimatedCollapsibleBox()
                }

            }
        }
    }


}


@Composable
fun AnimatedCollapsibleBox() {
    var isExpanded by remember { mutableStateOf(true) } // Track expanded/collapsed state
    val boxHeight by animateDpAsState(targetValue = if (isExpanded) 200.dp else 50.dp) // Animate height
    val boxColor by animateColorAsState(targetValue = if (isExpanded) Color.Blue else Color.Gray) // Animate color

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(boxHeight)
                .background(boxColor)
                .animateContentSize(), // Animate content changes
            contentAlignment = Alignment.Center
        ) {
            this@Column.AnimatedVisibility(visible = isExpanded) {
                Text(
                    text = "Expanded Content",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall
                )
            }
            if (!isExpanded) {
                Text(
                    text = "Collapsed",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { isExpanded = !isExpanded }) {
            Text(text = if (isExpanded) "Collapse" else "Expand")
        }
    }
}


@Composable
fun JobInnerItem(
    itemAtPos: Job,
    index: Int,
    onClick: (Job) -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()

    Box(modifier = Modifier.fillMaxWidth()) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(returnBackGroundColor(isDarkTheme))
                .padding(10.dp)
                .clickable {
                    onClick(itemAtPos)
                }
        ) {
            val (roundedNumber, jobTitle, driverName, dateAndTime, jobStatus, dividerTag) = createRefs()

            if (itemAtPos.jobType == 1 || itemAtPos.jobType == 6 || itemAtPos.jobType == 7) {
                Box(
                    modifier = Modifier
                        .constrainAs(roundedNumber) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            bottom.linkTo(parent.bottom)
                        }
                        .offset(x = 4.dp) // Equivalent to layout_marginStart
                        .background(
                            color = if (isDarkTheme) returnJobsNumberCircleBackground(
                                isDarkTheme
                            )
                            else returnLabelAirPurple100Color(isDarkTheme), // Replace with your badge background color
                            shape = RoundedCornerShape(15.dp) // Adjust radius as needed
                        )
                        .padding(
                            horizontal = 10.dp,
                            vertical = 4.dp
                        ) // Equivalent to paddingHorizontal, paddingTop, paddingBottom
                    //.visible(false) // Replace with your visibility logic
                ) {
                    Text(
                        text = index.toString(),
                        color = returnLabelDarkBlueColor(isDarkTheme), // Text color
                        style = customTextLabelSmallStyle, // Use your custom text style
                        fontSize = 10.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }


                Box(
                    modifier = Modifier
                        .constrainAs(jobStatus) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .offset(x = 4.dp) // Equivalent to layout_marginStart
                        .background(
                            color = if (isDarkTheme) returnJobsNumberCircleBackground(
                                isDarkTheme
                            )
                            else returnLabelAirPurple100Color(isDarkTheme), // Replace with your badge background color
                            shape = RoundedCornerShape(4.dp) // Adjust radius as needed
                        )
                        .padding(
                            horizontal = 10.dp,
                            vertical = 4.dp
                        ) // Equivalent to paddingHorizontal, paddingTop, paddingBottom
                    //.visible(false) // Replace with your visibility logic
                ) {

                    Text(
                        text = if (itemAtPos.jobActivityStatus == 5)
                            stringResource(id = R.string.done) else stringResource(id = R.string.to_do),
                        color = returnLabelDarkBlueColor(isDarkTheme), // Text color
                        style = customTextLabelSmallStyle, // Use your custom text style
                        fontSize = 10.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                var jobName = ""
                when (itemAtPos.jobType) {
                    1 -> {
                        jobName = stringResource(id = R.string.job_acceptance)
                    }

                    6 -> {
                        jobName = stringResource(id = R.string.job_repatriation)
                    }

                    7 -> {
                        jobName = stringResource(id = R.string.job_inject)
                    }

                }

                Text(
                    modifier = Modifier
                        .constrainAs(jobTitle) {
                            top.linkTo(parent.top)
                            start.linkTo(roundedNumber.end)
                        }
                        .padding(top = 5.dp, start = 10.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    text = jobName,
                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                    fontSize = 18.sp,
                    color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                )

                Text(
                    modifier = Modifier
                        .constrainAs(driverName) {
                            top.linkTo(jobTitle.bottom)
                            start.linkTo(roundedNumber.end)
                            end.linkTo(jobStatus.start)
                            width = Dimension.fillToConstraints

                        }
                        .padding(top = 5.dp, start = 10.dp),
                    textAlign = TextAlign.Start,
                    text = stringResource(
                        id =
                        R.string.champ_data,
                        itemAtPos.currentChampion?.firstName ?: "",
                        itemAtPos.currentChampion?.lastName ?: ""
                    ),
                    style = MaterialTheme.typography.labelSmall, // Use your custom text style here
                    fontSize = 16.sp,
                    color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                )

                Text(
                    modifier = Modifier
                        .constrainAs(dateAndTime) {
                            top.linkTo(driverName.bottom)
                            start.linkTo(roundedNumber.end)
                            end.linkTo(jobStatus.start)
                            width = Dimension.fillToConstraints

                        }
                        .padding(top = 5.dp, start = 10.dp),
                    textAlign = TextAlign.Start,
                    text = stringResource(
                        id =
                        R.string.date_data,
                        itemAtPos.startDueDateTimeUTC?.convertIntoDateTimeFormat() ?: "",
                        itemAtPos.endDueDateTimeUTC?.convertIntoDateTimeFormat() ?: ""
                    ),
                    style = MaterialTheme.typography.labelSmall, // Use your custom text style here
                    fontSize = 16.sp,
                    color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                )
                Divider(
                    modifier = Modifier
                        .constrainAs(dividerTag) {
                            top.linkTo(dateAndTime.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints

                        }
                        .padding(top = 10.dp),
                    thickness = 0.5.dp, // Adjust this value for thickness
                    color = if (isDarkTheme) editTextBorderStrockColor else light_white
                )
            }
        }

    }

}