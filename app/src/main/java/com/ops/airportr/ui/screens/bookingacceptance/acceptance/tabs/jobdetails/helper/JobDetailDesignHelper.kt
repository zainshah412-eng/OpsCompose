package com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.helper

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.mapbox.geojson.Point
import com.ops.airportr.AppApplication
import com.ops.airportr.R
import com.ops.airportr.common.theme.white
import com.ops.airportr.common.utils.extension.compareDates
import com.ops.airportr.common.utils.extension.formatDuration
import com.ops.airportr.common.utils.mapbox.GetTimeAndDistanceBtwTwoPoints
import com.ops.airportr.common.utils.returnJobsNumberCircleBackgroundOnJobInComplete
import com.ops.airportr.common.utils.returnLabelAirPurpleColor
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.ui.componts.CustomButton
import com.ops.airportr.ui.componts.Space
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.bookingDetails
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TimeLockAlert(
    onCancelClick: () -> Unit,
    onAssignClick: () -> Unit,
    isDarkTheme: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen._14sdp))
    ) {
        // Alert Title
        Text(
            text = stringResource(id = R.string.assign_to_yourself),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen._28sdp),
                    bottom = dimensionResource(id = R.dimen._10sdp)
                ),
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            color = returnLabelDarkBlueColor(isDarkTheme)
        )


        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._20sdp)))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Reset Button

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 5.dp),

                ) {
                CustomButton(
                    name = stringResource(id = R.string.no_cancel),
                    onButtonClick = {
                        onCancelClick()
                    },
                    paddingTop = 10,
                    paddingHorizontal = 0,
                    modifier = Modifier.height(60.dp),
                    containerColor = returnJobsNumberCircleBackgroundOnJobInComplete(isDarkTheme),
                    textColor = returnLabelAirPurpleColor(isDarkTheme),
                    isEnabled = true,
                    focusedElevation = 0,
                    defaultElevation = 0,
                )
            }
            Space(height = 10, width = 10)
            // Apply Button
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 5.dp)
            ) {
                CustomButton(
                    name = stringResource(id = R.string.yes_assign),
                    onButtonClick = {
                        onAssignClick()
                    },
                    paddingTop = 10,
                    paddingHorizontal = 0,
                    modifier = Modifier.height(60.dp),
                    containerColor = returnLabelAirPurpleColor(isDarkTheme),
                    textColor = white,
                    isEnabled = true
                )
            }
        }

    }
}

@Composable
fun returnETABox(
    eta: String,
    isDarkTheme: Boolean,
    backGroundColor: Color,
    textColor: Color
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp)) // Apply rounded corners
            .background(backGroundColor) // Set background color
            .border(
                0.dp,
                backGroundColor,
                RoundedCornerShape(5.dp)
            )
            .padding(
                top = 6.dp,
                bottom = 6.dp,
                start = 10.dp,
                end = 10.dp
            )

    ) {
        Text(
            text = eta,
            color = textColor, // Text color
            style = MaterialTheme.typography.labelSmall, // Use your custom text style
            fontSize = 10.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun conditionalAcceptanceBottomSheet(
    onAssignClick: () -> Unit,
    isDarkTheme: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen._14sdp))
    ) {
        // Alert Title
        Text(
            text = stringResource(id = R.string.reminder),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen._5sdp),
                    bottom = dimensionResource(id = R.dimen._10sdp)
                ),
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            color = returnLabelDarkBlueColor(isDarkTheme)
        )


        Text(
            text = stringResource(id = R.string.this_is_a_conditional_acceptance_booking),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen._5sdp),
                    bottom = dimensionResource(id = R.dimen._10sdp)
                ),
            textAlign = TextAlign.Start,
            color = returnLabelDarkBlueColor(isDarkTheme)
        )


        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._20sdp)))


        Box(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 5.dp),

            ) {
            CustomButton(
                name = stringResource(id = R.string.alert_passenger_button),
                onButtonClick = {
                    onAssignClick()
                },
                paddingTop = 10,
                paddingHorizontal = 0,
                modifier = Modifier.height(60.dp),
                containerColor = returnLabelAirPurpleColor(isDarkTheme),
                textColor = white,
                isEnabled = true,
                focusedElevation = 0,
                defaultElevation = 0,
            )
        }

    }
}

@Composable
fun allPassengerBottomSheet(
    onAssignClick: () -> Unit,
    isDarkTheme: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen._14sdp))
    ) {
        var description = ""
        bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.firstOrNull()?.flightStatus?.flightStatusDetails?.firstOrNull()
            ?.let { flightStatusDetail ->
                val airlines = setOf("SWISS", "Lufthansa", "Austrian", "Edelweiss Air")
                val airlineCodes = setOf("2L")
                if (flightStatusDetail.airlineName in airlines ||
                    flightStatusDetail.operatingAirlineName in airlines ||
                    flightStatusDetail.operatingAirlineCode in airlineCodes
                ) {
                    description = stringResource(id = R.string.alert_passenger_description)
                } else {
                    description =
                        stringResource(id = R.string.alert_passenger_description_for_swiss)
                }
            }

        Text(
            text = description,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen._5sdp),
                    bottom = dimensionResource(id = R.dimen._10sdp)
                ),
            textAlign = TextAlign.Start,
            color = returnLabelDarkBlueColor(isDarkTheme)
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._20sdp)))

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 5.dp),
        ) {
            CustomButton(
                name = stringResource(id = R.string.alert_passenger_button),
                onButtonClick = {
                    onAssignClick()
                },
                paddingTop = 10,
                paddingHorizontal = 0,
                modifier = Modifier.height(60.dp),
                containerColor = returnLabelAirPurpleColor(isDarkTheme),
                textColor = white,
                isEnabled = true,
                focusedElevation = 0,
                defaultElevation = 0,
            )
        }

    }
}

@Composable
fun timeLockInSideGeoLocationBottomSheet(
    onAssignClick: () -> Unit,
    onCancelClick: () -> Unit,
    isDarkTheme: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen._14sdp))
    ) {

        Text(
            text = stringResource(id = R.string.time_lock_title),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen._5sdp),
                    bottom = dimensionResource(id = R.dimen._10sdp)
                ),
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            color = returnLabelDarkBlueColor(isDarkTheme)
        )

        Text(
            text = HtmlCompat.fromHtml(
                stringResource(id = R.string.you_have) +
                        " ${bookingDetails.bookingJourneyDetail?.collectionDateTimeUTC?.compareDates()} " +
                        stringResource(
                            id = R.string.time_lock_body_text1
                        ),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            ).toString(),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen._5sdp),
                    bottom = dimensionResource(id = R.dimen._10sdp)
                ),
            textAlign = TextAlign.Start,
            color = returnLabelDarkBlueColor(isDarkTheme)
        )

        Text(
            text = stringResource(id = R.string.time_lock_body_text2),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen._5sdp),
                    bottom = dimensionResource(id = R.dimen._10sdp)
                ),
            textAlign = TextAlign.Start,
            color = returnLabelDarkBlueColor(isDarkTheme)
        )

        Text(
            text = stringResource(id = R.string.time_lock_body_text3),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen._5sdp),
                    bottom = dimensionResource(id = R.dimen._10sdp)
                ),
            textAlign = TextAlign.Start,
            color = returnLabelDarkBlueColor(isDarkTheme)
        )


        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._20sdp)))

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 5.dp),
        ) {
            CustomButton(
                name = stringResource(id = R.string.time_lock_positive_button),
                onButtonClick = {
                    onAssignClick()
                },
                paddingTop = 10,
                paddingHorizontal = 0,
                modifier = Modifier.height(60.dp),
                containerColor = returnLabelAirPurpleColor(isDarkTheme),
                textColor = white,
                isEnabled = true,
                focusedElevation = 0,
                defaultElevation = 0,
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 5.dp),

            ) {
            CustomButton(
                name = stringResource(id = R.string.time_lock_negative_button),
                onButtonClick = {
                    //onCancelClick()
                },
                paddingTop = 10,
                paddingHorizontal = 0,
                modifier = Modifier.height(60.dp),
                containerColor = returnJobsNumberCircleBackgroundOnJobInComplete(isDarkTheme),
                textColor = returnLabelAirPurpleColor(isDarkTheme),
                isEnabled = true,
                focusedElevation = 0,
                defaultElevation = 0,
            )
        }

    }
}

@Composable
fun timeLockOutsideGeoLocationBottomSheet(
    onAssignClick: () -> Unit,
    onCancelClick: () -> Unit,
    isDarkTheme: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen._14sdp))
    ) {

        Text(
            text = stringResource(id = R.string.are_you_with_the_passenger),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen._5sdp),
                    bottom = dimensionResource(id = R.dimen._10sdp)
                ),
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            color = returnLabelDarkBlueColor(isDarkTheme)
        )

        Text(
            text = stringResource(id = R.string.your_location_is_currently),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen._5sdp),
                    bottom = dimensionResource(id = R.dimen._10sdp)
                ),
            textAlign = TextAlign.Start,
            color = returnLabelDarkBlueColor(isDarkTheme)
        )

        Text(
            text = stringResource(id = R.string.if_you_believe_this_is),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen._5sdp),
                    bottom = dimensionResource(id = R.dimen._10sdp)
                ),
            textAlign = TextAlign.Start,
            color = returnLabelDarkBlueColor(isDarkTheme)
        )

        Text(
            text = stringResource(id = R.string.this_booking_will_be_audited_by),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen._5sdp),
                    bottom = dimensionResource(id = R.dimen._10sdp)
                ),
            textAlign = TextAlign.Start,
            color = returnLabelDarkBlueColor(isDarkTheme)
        )


        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._20sdp)))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 5.dp),
            ) {
                CustomButton(
                    name = stringResource(id = R.string.time_lock_positive_button),
                    onButtonClick = {
                        onAssignClick()
                    },
                    paddingTop = 10,
                    paddingHorizontal = 0,
                    modifier = Modifier.height(60.dp),
                    containerColor = returnLabelAirPurpleColor(isDarkTheme),
                    textColor = white,
                    isEnabled = true,
                    focusedElevation = 0,
                    defaultElevation = 0,
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 5.dp),

                ) {
                CustomButton(
                    name = stringResource(id = R.string.time_lock_negative_button),
                    onButtonClick = {
                        //onCancelClick()
                    },
                    paddingTop = 10,
                    paddingHorizontal = 0,
                    modifier = Modifier.height(60.dp),
                    containerColor = returnJobsNumberCircleBackgroundOnJobInComplete(isDarkTheme),
                    textColor = returnLabelAirPurpleColor(isDarkTheme),
                    isEnabled = true,
                    focusedElevation = 0,
                    defaultElevation = 0,
                )
            }
        }

    }
}


@Composable
 fun getTimeAndDistanceFromDestination(
    getTimeAndDistanceBtwTwoPoints: GetTimeAndDistanceBtwTwoPoints,
    onEtaDistanceDuration: (Double, Double) -> Unit,
    etaLoader: (Boolean) -> Unit
) {
    val currentLat = AppApplication.sessionManager.getLastUserLocation.latitude
    val currentLng = AppApplication.sessionManager.getLastUserLocation.longitude

    val destinationLat =
        bookingDetails.bookingJourneyDetail?.collectionLocation?.geoCoord?.latitude

    val destinationLng =
        bookingDetails.bookingJourneyDetail?.collectionLocation?.geoCoord?.longitude

    if (currentLat != null && currentLng != null && destinationLat != null && destinationLng != null) {
        val origin = Point.fromLngLat(currentLng, currentLat)
        val destination = Point.fromLngLat(destinationLng, destinationLat)
        getTimeAndDistanceBtwTwoPoints.setOnGetDistanceAndDurationListener(object :
            GetTimeAndDistanceBtwTwoPoints.OnGetDistanceAndDurationListener {
            override fun onSuccess(distance: Double, duration: Double) {
                Log.wtf("getDistanceAndTime2", "onSuccess -> $distance")
                Log.wtf("getDuration", "onSuccess -> $duration")
                etaLoader(false)
                onEtaDistanceDuration(distance, duration)
            }

            override fun onFailed(message: String) {
                Log.wtf("getDistanceAndTime2", "onFailed -> $message")
                // (activity as AcceptanceJobAct).hideLoader()
                etaLoader(false)
            }

            override fun onLoading(isLoading: Boolean) {
                Log.wtf("getDistanceAndTime1", "onLoading -> $isLoading")
                if (AppApplication.sessionManager.userDetails.userId ==
                    AppApplication.sessionManager.currentChampId && isLoading
                ) {
                    etaLoader(true)
                }

            }

        })
        getTimeAndDistanceBtwTwoPoints.getDirectionalRoute(origin, destination)
    }
}

@Composable
fun calculateRemainingTimeAndDis(
    context: Context,
    duration: Double,
    time: (String) -> Unit,
    textColorCode: (Color) -> Unit,
    backgroundColorCode: (Color) -> Unit
) {
    var timeSlot = ""
    for (obj in bookingDetails.bookingJourneyDetail?.jobs!!) {
        if (obj.jobType == 1) {
            var conditionalAcceptanceColorPair: Pair<Color, Color> =
                Pair(
                    colorResource(id = R.color.dim_orange_15),
                    colorResource(id = R.color.dark_orange)
                )

            val whenReach = getSlotDifference(
                obj.startDueDateTimeUTC,
                obj.endDueDateTimeUTC,
                duration.toInt()
            )
            if (whenReach.equals("orange_early") || whenReach.equals("orange_late")) {
                timeSlot =
                    if (whenReach.equals("orange_early")) stringResource(id = R.string.arriving_early)
                    else stringResource(id = R.string.arriving_late)
                conditionalAcceptanceColorPair =
                    Pair(
                        colorResource(id = R.color.dim_orange_15),
                        colorResource(id = R.color.dark_orange)
                    )


            } else if (whenReach.equals("red_early") || whenReach.equals("red_late")) {
                timeSlot =
                    if (whenReach.equals("red_early")) stringResource(id = R.string.arriving_early) else stringResource(
                        id = R.string.arriving_late
                    )
                conditionalAcceptanceColorPair = Pair(
                    colorResource(id = R.color.light_red),
                    colorResource(id = R.color.red)
                )
            } else if (whenReach.equals("in_slot")) {
                timeSlot = stringResource(id = R.string.arriving_on_time)
                conditionalAcceptanceColorPair = Pair(
                    colorResource(id = R.color.air_purple),
                    colorResource(id = R.color.air_awesome_purple_light_50)
                )
            }
            time(
                stringResource(
                    id = R.string.eta_time_duration,
                    context.formatDuration(
                        duration
                    ),
                    timeSlot
                )
            )
            textColorCode(conditionalAcceptanceColorPair.second)
            backgroundColorCode(conditionalAcceptanceColorPair.first)
        }
    }

    var distance = AppApplication.sessionManager.distance
    var activeBooking = AppApplication.sessionManager.activeBookingDetails
}
 fun getSlotDifference(start: String?, end: String?, seconds: Int): String {
    if (!start.isNullOrEmpty() && !end.isNullOrEmpty()) {
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)

            val startTime = dateFormat.parse(start)
            val endTime = dateFormat.parse(end)

            val calendar = java.util.Calendar.getInstance()
            calendar.time = Date()
            if (seconds >= 0) {
                calendar.add(java.util.Calendar.SECOND, seconds)
            }

            val formattedDate = dateFormat.format(calendar.time)

            val currentTime = dateFormat.parse(formattedDate)

            val fifteenMinutes = 15 * 60 * 1000 // 15 minutes in milliseconds

            val startTimeMinus15Minutes = Date(startTime!!.time - fifteenMinutes)
            val endTimePlus15Minutes = Date(endTime!!.time + fifteenMinutes)

            return if (currentTime.after(startTimeMinus15Minutes) && currentTime.before(
                    startTime
                )
            ) {
                "orange_early"
            } else if (currentTime.after(endTime) && currentTime.before(endTimePlus15Minutes)) {
                "orange_late"
            } else if (currentTime.before(startTimeMinus15Minutes)
            ) {
                "red_early"
            } else if (currentTime.after(
                    endTimePlus15Minutes
                )
            ) {
                "red_late"
            } else {
                "in_slot"
            }
        } catch (e: Exception) {
            Log.wtf("getSlotDifference", "Exception -> ${e.message}")
        }
    }
    return ""
}
