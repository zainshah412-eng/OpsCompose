package com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingmanifest.processbag

import android.app.Activity
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
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
import coil.compose.AsyncImage
import com.ops.airportr.R
import com.ops.airportr.common.AppActionValues
import com.ops.airportr.common.theme.badgeTextColor
import com.ops.airportr.common.theme.customEditTextColorDarkTheme
import com.ops.airportr.common.theme.editTextBorderStrockColor
import com.ops.airportr.common.theme.light_white
import com.ops.airportr.common.utils.BookingDetailsSingleton
import com.ops.airportr.common.utils.callbacks.ImageLoadCallbacks
import com.ops.airportr.common.utils.callbacks.LoadImageUsingCallback
import com.ops.airportr.common.utils.convertDateForBagDetail
import com.ops.airportr.common.utils.getJobTypeInjectDetails
import com.ops.airportr.common.utils.removeLastChar
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnBagDetailStatusBackGroundBox
import com.ops.airportr.common.utils.returnBagStatusBackGroundBox
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.domain.model.bookingdetails.BookingJourneyDetail

@Composable
fun ProcessBagScreen(
    navHostController: NavController,
    bookingRef: String,
    bookingJourneyRef: String,
    passengerName: String,
    passengerId: String,
    passengerLuggageId: String,
    passengerLuggageCode: String
) {
    val activity = LocalContext.current as? Activity
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()
    var bookingDetails: BookingDetailsSingleton
    var detailModel by remember { mutableStateOf(BookingJourneyDetail()) }
    var isInjectionBooking by remember { mutableStateOf(false) }
    var imagesList = remember {
        mutableStateOf(ArrayList<String>())
    }
    var noOfSeals by remember { mutableStateOf(0) }
    val sealsList = StringBuilder(10000)
    val currentBackStackEntry = navHostController.currentBackStackEntryAsState()
    LaunchedEffect(currentBackStackEntry.value?.lifecycle) {
        currentBackStackEntry.value?.lifecycle?.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                // viewModel.loadData() // Call your ViewModel function
                bookingDetails = BookingDetailsSingleton()
                bookingDetails.init(passengerId, passengerLuggageId)
                detailModel = bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)!!

            }
        })
    }
    val imageLoadCallbacks = remember { ImageLoadCallbacks() }


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(returnBackGroundColor(isDarkTheme))
    ) {
        bookingDetails = BookingDetailsSingleton()
        bookingDetails.init(passengerId, passengerLuggageId)
        detailModel = bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)!!
        var dateTime = ""
        noOfSeals = 0
        sealsList.clear()
        val airPortCode =
            bookingDetails.flightStatusDetail?.departureAirport?.airportCode
        val airPortTerminal =
            bookingDetails.flightStatusDetail?.departureAirport?.airportTerminal
        for (obj in bookingDetails.jobsList!!) {
            if (obj.jobType == AppActionValues.INJECTION_ACTION_VALUE) {
                isInjectionBooking = true
                dateTime = obj.startDueDateTimeUTC?.convertDateForBagDetail() ?: ""
            }
        }
        val journeyStatus: String? =
            bookingDetails.bookingJourneyDetail?.journeyStatus?.toString()
        try {
            if (bookingDetails.passengerLuggage?.bookingLuggageTamperAwareLiteSeals!!.size > 0) {
                for (objSeal in bookingDetails.passengerLuggage?.bookingLuggageTamperAwareLiteSeals!!) {
                    if (objSeal.tamperAwareSealStatus == 0) {
                        noOfSeals = noOfSeals!! + 1
                        sealsList.append(objSeal.tamperAwareSealCode + ",")
                    }
                }
            }
        } catch (e: Exception) {
            Log.wtf("EXCEPTION", e.toString())
        }

        val (topBar, bagStatusBox, bagDetails) = createRefs()
        Row(modifier = Modifier
            .constrainAs(topBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .height(dimensionResource(id = R.dimen._60sdp))
            .fillMaxSize()
            .shadow(elevation = 12.dp)
            .background(returnBackGroundColor(isDarkTheme)),
            verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = null, // provide content description if needed
                modifier = Modifier
                    .padding(start = 14.dp, end = 14.dp)
                    .height(30.dp)
                    .width(30.dp)
                    .clickable {
                        navHostController.popBackStack()
                    }, // make the image background transparent
                contentScale = ContentScale.Inside, // scale the image to fill the Box
                colorFilter = ColorFilter.tint(returnLabelDarkBlueColor(isDarkTheme))
            )

            Text(
                text = stringResource(id = R.string.process_bag),
                style = MaterialTheme.typography.labelSmall,
                color = returnLabelDarkBlueColor(isDarkTheme),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(14.dp),
            )
        }

        Box(modifier = Modifier
            .constrainAs(bagStatusBox) {
                top.linkTo(topBar.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
            .padding(start = 15.dp, end = 15.dp, top = 15.dp)) {


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp)) // Apply rounded corners
                    .background(returnBagDetailStatusBackGroundBox(isDarkTheme)) // Set background color
                    .border(
                        0.dp,
                        returnBagDetailStatusBackGroundBox(isDarkTheme),
                        RoundedCornerShape(5.dp)
                    )
                    .padding(
                        top = 6.dp,
                        bottom = 6.dp,
                        start = 6.dp,
                        end = 6.dp
                    )

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            modifier = Modifier
                                .padding(top = 5.dp, end = 10.dp),
                            textAlign = TextAlign.Start,
                            text = if (isInjectionBooking) {
                                "Inject @ $airPortCode T$airPortTerminal"
                            } else {
                                "Delivery @ $airPortCode T$airPortTerminal"
                            },
                            style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                            fontSize = 16.sp,
                            color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 5.dp, end = 10.dp),
                            textAlign = TextAlign.Start,
                            text = dateTime,
                            style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                            fontSize = 14.sp,
                            color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp)) // Apply rounded corners
                            .background(returnBagStatusBackGroundBox(isDarkTheme)) // Set background color
                            .border(
                                0.dp,
                                returnBagStatusBackGroundBox(isDarkTheme),
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
                            text = journeyStatus?.getJobTypeInjectDetails(context) ?: "",
                            color = if (isDarkTheme) badgeTextColor else customEditTextColorDarkTheme, // Text color
                            style = MaterialTheme.typography.labelSmall, // Use your custom text style
                            fontSize = 10.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }
            }
        }

        Box(modifier = Modifier
            .constrainAs(bagDetails) {
                top.linkTo(bagStatusBox.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
            .padding(start = 15.dp, end = 15.dp)) {
            Column {

                Text(
                    modifier = Modifier
                        .padding(top = 15.dp),
                    textAlign = TextAlign.Start,
                    text = "${
                        bookingDetails.passenger?.firstName
                    } ${
                        bookingDetails.passenger?.lastName
                    }",
                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                    fontSize = 16.sp,
                    color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                )
                Row {
                    // PNR Reference
                    Text(
                        modifier = Modifier
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

                    Text(
                        modifier = Modifier
                            .padding(top = 5.dp),
                        textAlign = TextAlign.Start,
                        text = stringResource(
                            id = R.string.pName,
                            stringResource(id = R.string.booking_text),
                            bookingDetails.bookingJourneyDetail?.bookingReference ?: ""
                        ),
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 12.sp,
                        color = returnLabelAirPurple100Color(isDarkTheme),
                    )


                }
                Divider(
                    thickness = 0.5.dp, // Adjust this value for thickness
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    color = if (isDarkTheme) editTextBorderStrockColor else light_white
                )

                Row(
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen._60sdp))
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            modifier = Modifier
                                .padding(top = 5.dp, end = 10.dp),
                            textAlign = TextAlign.Start,
                            text = stringResource(id = R.string.iata),
                            style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                            fontSize = 16.sp,
                            color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                        )
                        Text(
                            textAlign = TextAlign.Start,
                            text = if (bookingDetails.passengerLuggage?.iataCode.isNullOrEmpty()) {
                                stringResource(id = R.string.scan_iata_tag)
                            } else {
                                bookingDetails.passengerLuggage?.iataCode ?: ""
                            },
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 12.sp,
                            color = returnLabelAirPurple100Color(isDarkTheme),
                        )
                    }
                    if (!bookingDetails.passengerLuggage?.iataCode.isNullOrEmpty()) {
                        Image(
                            painter = painterResource(id = R.drawable.check_circle),
                            contentDescription = null, // provide content description if needed
                            modifier = Modifier
                                .padding(start = 14.dp)
                                .height(30.dp)
                                .width(30.dp)
                                .clickable {
                                    navHostController.popBackStack()
                                }, // make the image background transparent
                            contentScale = ContentScale.Inside, // scale the image to fill the Box
                        )
                    }
                }

                Divider(
                    thickness = 0.5.dp, // Adjust this value for thickness
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    color = if (isDarkTheme) editTextBorderStrockColor else light_white
                )

                Row(
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen._60sdp))
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            modifier = Modifier
                                .padding(top = 5.dp, end = 10.dp),
                            textAlign = TextAlign.Start,
                            text = stringResource(id = R.string.seal_title_text),
                            style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                            fontSize = 16.sp,
                            color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                        )
                        Text(
                            textAlign = TextAlign.Start,
                            text = if (noOfSeals > 0) {
                                removeLastChar(sealsList.toString())
                            } else {
                                "-"
                            },
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 12.sp,
                            color = returnLabelAirPurple100Color(isDarkTheme),
                        )
                    }
                    if (noOfSeals > 0) {
                        Image(
                            painter = painterResource(id = R.drawable.check_circle),
                            contentDescription = null, // provide content description if needed
                            modifier = Modifier
                                .padding(start = 14.dp)
                                .height(30.dp)
                                .width(30.dp)
                                .clickable {
                                    navHostController.popBackStack()
                                }, // make the image background transparent
                            contentScale = ContentScale.Inside, // scale the image to fill the Box
                        )
                    }
                }

                Divider(
                    thickness = 0.5.dp, // Adjust this value for thickness
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    color = if (isDarkTheme) editTextBorderStrockColor else light_white
                )

                Row(
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen._60sdp))
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            modifier = Modifier
                                .padding(top = 5.dp, end = 10.dp),
                            textAlign = TextAlign.Start,
                            text = stringResource(id = R.string.photo),
                            style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                            fontSize = 16.sp,
                            color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                        )
                        if (bookingDetails.passengerLuggage?.passengerLuggageImageId!!.size < 1) {
                            Text(
                                textAlign = TextAlign.Start,
                                text = stringResource(id = R.string.take_a_picture_of_the_bag),
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 12.sp,
                                color = returnLabelAirPurple100Color(isDarkTheme),
                            )
                        }
                    }
                    if (bookingDetails.passengerLuggage?.passengerLuggageImageId!!.size > 0) {
                        Image(
                            painter = painterResource(id = R.drawable.check_circle),
                            contentDescription = null, // provide content description if needed
                            modifier = Modifier
                                .padding(start = 14.dp)
                                .height(30.dp)
                                .width(30.dp)
                                .clickable {
                                    navHostController.popBackStack()
                                }, // make the image background transparent
                            contentScale = ContentScale.Inside, // scale the image to fill the Box
                        )
                    }
                }
                imagesList.value.clear()
                bookingDetails.passengerLuggage?.passengerLuggageImageId?.let {
                    imagesList.value.addAll(
                        it
                    )
                }
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    // LazyColumn Items (Dynamic Content)
                    itemsIndexed(imagesList.value) { index, imageItem ->
                        LoadImageUsingCallback(
                            imageItem,
                            imageLoadCallbacks
                        )
                    }
                }
            }
        }
    }


}



