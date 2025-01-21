package com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingmanifest

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.ops.airportr.common.theme.air_awesome_purple_100
import com.ops.airportr.common.theme.air_orange
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.theme.air_red
import com.ops.airportr.common.theme.air_yellow
import com.ops.airportr.common.theme.badgeTextColor
import com.ops.airportr.common.theme.customEditTextColorDarkTheme
import com.ops.airportr.common.theme.customTextLabelSmallStyle
import com.ops.airportr.common.theme.editTextBorderStrockColor
import com.ops.airportr.common.theme.green
import com.ops.airportr.common.theme.light_white
import com.ops.airportr.common.theme.white
import com.ops.airportr.common.utils.BookingDetailsSingleton
import com.ops.airportr.common.utils.abcTagBackGroundColor
import com.ops.airportr.common.utils.callbacks.ImageLoadCallbacks
import com.ops.airportr.common.utils.moveOnNewScreen
import com.ops.airportr.common.utils.overSizeBagTagBackGroundColor
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.common.utils.returnLeadPassengerBackGround
import com.ops.airportr.domain.model.bookingdetails.BookingJourneyDetail
import com.ops.airportr.domain.model.bookingdetails.Job
import com.ops.airportr.domain.model.bookingdetails.Passenger
import com.ops.airportr.domain.model.bookingdetails.PassengerLuggage
import com.ops.airportr.route.Screen
import com.ops.airportr.ui.componts.Space
import com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingmanifest.item.BagsItem

@Composable
fun BookingManifestScreen(
    navController: NavController
) {
    val imageLoadCallbacks = remember { ImageLoadCallbacks() }

    val activity = LocalContext.current as? Activity
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()
    var bookingDetails: BookingDetailsSingleton
    var detailModel by remember { mutableStateOf(BookingJourneyDetail()) }

    val jobs = remember { mutableStateOf(ArrayList<Job>()) }
    val passengersList = remember { mutableStateOf(ArrayList<Passenger>()) }
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
    bookingDetails = BookingDetailsSingleton()
    bookingDetails.init()
    detailModel = bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)!!

    if (!bookingDetails.passengersList.isNullOrEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(returnBackGroundColor(isDarkTheme))
        ) {
            bookingDetails = BookingDetailsSingleton()
            bookingDetails.init()
            detailModel = bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)!!
            jobs.value.clear()
            detailModel.jobs?.let { jobs.value.addAll(it) }


            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(returnBackGroundColor(isDarkTheme))
                    .padding(
                        top = 10.dp,
                        start = 10.dp,
                        end = 10.dp
                    )
            ) {

                val (bagCount, tagsBox, passengersRef) = createRefs()
                Row(
                    modifier = Modifier
                        .constrainAs(tagsBox) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        modifier = Modifier
                            .padding(top = 5.dp, end = 10.dp),
                        textAlign = TextAlign.Start,
                        text = detailModel.bookingReference ?: "",
                        style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                        fontSize = 18.sp,
                        color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                    )

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

                passengersList.value.clear()
                bookingDetails.passengersList?.let {
                    passengersList.value.addAll(it)
                }
                var totalCollectedBags = 0
                passengersList.value.forEach { model ->
                    totalCollectedBags += model.passengerLuggage?.size ?: 0
                }

                Text(
                    modifier = Modifier
                        .constrainAs(bagCount) {
                            top.linkTo(tagsBox.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints

                        }
                        .padding(top = 5.dp),
                    textAlign = TextAlign.Start,
                    text = stringResource(
                        id = R.string.no_of_bags,
                        totalCollectedBags.toString(),
                        bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.bagsCount
                            ?: ""
                    ),
                    style = MaterialTheme.typography.labelSmall, // Use your custom text style here
                    fontSize = 12.sp,
                    color = if (totalCollectedBags == bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(
                            0
                        )?.bagsCount
                    )
                        green else air_orange, // Replace with your desired color
                )

                LazyColumn(
                    modifier = Modifier.constrainAs(passengersRef) {
                        top.linkTo(bagCount.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
                ) {

                    // LazyColumn Items (Dynamic Content)
                    itemsIndexed(passengersList.value) { index, passengerItem ->

                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .wrapContentHeight()
                        ) {
                            val (leadPassengerTag, passengerName, pnrRef) = createRefs()

                            // Lead Passenger Tag
                            if (index == 0) {
                                Box(
                                    modifier = Modifier
                                        .constrainAs(leadPassengerTag) {
                                            top.linkTo(parent.top)
                                            start.linkTo(parent.start)
                                        }
                                        .background(
                                            color = returnLeadPassengerBackGround(isDarkTheme),
                                            shape = RoundedCornerShape(4.dp)
                                        )
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.lead),
                                        color = returnLabelDarkBlueColor(isDarkTheme),
                                        style = customTextLabelSmallStyle,
                                        fontSize = 10.sp,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }

                            // Passenger Name
                            Text(
                                modifier = Modifier
                                    .constrainAs(passengerName) {
                                        top.linkTo(leadPassengerTag.bottom)
                                        start.linkTo(parent.start)
                                    }
                                    .padding(top = 5.dp, end = 10.dp),
                                textAlign = TextAlign.Start,
                                text = passengerItem.firstName?.let { fn ->
                                    passengerItem.lastName?.let { ln -> "$fn $ln" } ?: fn
                                } ?: passengerItem.lastName.orEmpty(),
                                style = MaterialTheme.typography.labelMedium,
                                fontSize = 18.sp,
                                color = returnLabelDarkBlueColor(isDarkTheme),
                            )

                            // PNR Reference
                            Text(
                                modifier = Modifier
                                    .constrainAs(pnrRef) {
                                        top.linkTo(passengerName.bottom)
                                        start.linkTo(parent.start)
                                    }
                                    .padding(top = 5.dp),
                                textAlign = TextAlign.Start,
                                text = if (bookingDetails.bookingJourneyDetail?.pnr?.isEmpty() == true) {
                                    stringResource(
                                        id = R.string.pName,
                                        stringResource(id = R.string.pnr),
                                        "-"
                                    )
                                } else {
                                    stringResource(
                                        id = R.string.pName, stringResource(id = R.string.pnr),
                                        bookingDetails.bookingJourneyDetail?.pnr ?: ""
                                    )
                                },
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 12.sp,
                                color = returnLabelAirPurple100Color(isDarkTheme),
                            )
                        }

                        var previousBags = 1
                        if (index == 0) {
                            previousBags = 1
                        } else {
                            var posCount = index
                            while (posCount > 0) {
                                previousBags += passengersList.value[posCount - 1].passengerLuggage?.size
                                    ?: 0
                                posCount -= 1
                            }
                        }

                        val passengerLuggageList =
                            remember { mutableStateOf(ArrayList<PassengerLuggage>()) }
                        // Luggage List
                        passengerLuggageList.value.clear()
                        passengerItem.passengerLuggage?.let { passengerLuggageList.value.addAll(it) }
                        // Inner LazyColumn
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp) // Constrain height to avoid infinite constraints
                        ) {
                            itemsIndexed(passengerLuggageList.value) { innerindex, bagItem ->
                                BagsItem(
                                    itemAtPos = bagItem,
                                    index = innerindex,
                                    previousBags,
                                    totalCollectedBags,
                                    passengerItem.passengerId ?: "",
                                    passengerItem.firstName ?: ("" + passengerItem.lastName) ?: "",
                                    imageLoadCallbacks,
                                    onClick = { luggageIndex, passId, passName ->
                                        val route =
                                            Screen.ProcessBag.route + "/${bookingDetails.bookingJourneyDetail?.bookingReference ?: ""}/" +
                                                    "${bookingDetails.bookingJourneyDetail?.bookingJourneyReference ?: ""}/" +
                                                    "$passName/$passId/${luggageIndex.passengerLuggageId ?: ""}/${luggageIndex.passengerLuggageCode ?: ""}"

                                        navController.moveOnNewScreen(
                                            route,
                                            false
                                        )
                                    }
                                )
                            }
                        }

                        Divider(
                            thickness = 0.5.dp, // Adjust this value for thickness
                            modifier = Modifier.fillMaxWidth(),
                            color = if (isDarkTheme) editTextBorderStrockColor else light_white
                        )
                    }
                }
            }

        }


    } else {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp, end = 12.dp, top = 12.dp)
        ) {
            val (bookingInfoBox) = createRefs()
            Column(
                modifier = Modifier
                    .constrainAs(bookingInfoBox) {
                        top.linkTo(parent.top)
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
                    contentDescription = stringResource(id = R.string.no_bags), // provide content description if needed
                    modifier = Modifier
                        .padding(top = 14.dp)
                        .height(120.dp)
                        .width(120.dp), // make the image background transparent
                    contentScale = ContentScale.Crop // scale the image to fill the Box
                )

                Text(
                    modifier = Modifier
                        .padding(top = 5.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.no_bags_message),
                    style = customTextLabelSmallStyle, // Use your custom text style here
                    fontSize = 12.sp,
                    color = air_awesome_purple_100, // Replace with your desired color
                )


            }
        }
    }


}