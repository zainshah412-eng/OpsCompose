package com.ops.airportr.ui.screens.bookingmanifest.tabs

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
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
import com.ops.airportr.common.theme.white
import com.ops.airportr.common.utils.BookingDetailsSingleton
import com.ops.airportr.common.utils.abcTagBackGroundColor
import com.ops.airportr.common.utils.overSizeBagTagBackGroundColor
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.domain.model.bookingdetails.BookingJourneyDetail
import com.ops.airportr.domain.model.bookingdetails.Job
import com.ops.airportr.ui.componts.Space
import com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingsummary.item.JobInnerItem

@Composable
fun BookingJobsScreen(
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

        }
    }
}