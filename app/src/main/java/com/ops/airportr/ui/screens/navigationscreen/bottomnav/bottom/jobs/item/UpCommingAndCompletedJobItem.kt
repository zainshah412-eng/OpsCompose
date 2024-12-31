package com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.jobs.item

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ops.airportr.AppApplication
import com.ops.airportr.R
import com.ops.airportr.common.AppActionValues
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.theme.air_red
import com.ops.airportr.common.theme.air_yellow
import com.ops.airportr.common.theme.badgeTextColor
import com.ops.airportr.common.theme.customEditTextColorDarkTheme
import com.ops.airportr.common.theme.customTextLabelSmallStyle
import com.ops.airportr.common.theme.editTextBorderStrockColor
import com.ops.airportr.common.theme.light_white
import com.ops.airportr.common.theme.white
import com.ops.airportr.common.utils.abcTagBackGroundColor
import com.ops.airportr.common.utils.convertIntoDateTimeFormat
import com.ops.airportr.common.utils.convertIntoRelativeDateWithTimeSlot
import com.ops.airportr.common.utils.overSizeBagTagBackGroundColor
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.domain.model.joblist.retrievejobs.response.FlightStatusInformation
import com.ops.airportr.domain.model.joblist.retrievejobs.response.Passenger
import com.ops.airportr.domain.model.joblist.retrievejobs.response.RetrieveJob
import com.ops.airportr.ui.componts.Space

@Composable
fun UpComingAndCompletedJobItem(
    context: Context,
    onClick: (item: RetrieveJob) -> kotlin.Unit,
    itemAtPos: MutableState<ArrayList<RetrieveJob>>
) {
    val isDarkTheme = isSystemInDarkTheme()
    LazyColumn {
        items(itemAtPos.value) { itemAtPos ->
            Box(
                modifier = Modifier
                    .fillMaxSize() // Set content scale to crop
            ) {

                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(returnBackGroundColor(isDarkTheme))
                        .padding(10.dp)
                        .clickable {
                            onClick(itemAtPos)
                        }
                ) {
                    val (tagsBox, spaceEnd, rlNotification, relParent,
                        tvPassengerTitle, tvFlightNumber,
                        tvDateTime,
                        bagImg, tvBagCount, tvAddress, tvAddressNumber) = createRefs()

                    Row(
                        modifier = Modifier
                            .constrainAs(tagsBox) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(spaceEnd.start)
                                width = Dimension.fillToConstraints
                            },
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (itemAtPos?.jobs?.get(0)?.jobType == AppActionValues.ACCEPTANCE_ACTION_VALUE) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp)) // Apply rounded corners
                                    .background(abcTagBackGroundColor(isDarkTheme)) // Set background color
                                    .border(
                                        0.dp,
                                        abcTagBackGroundColor(isDarkTheme),
                                        RoundedCornerShape(5.dp)
                                    )
                                    .padding(top = 6.dp, bottom = 6.dp, start = 10.dp, end = 10.dp)

                            ) {
                                Text(
                                    text = "ABC",
                                    color = if (isDarkTheme) badgeTextColor else customEditTextColorDarkTheme, // Text color
                                    style = MaterialTheme.typography.labelSmall, // Use your custom text style
                                    fontSize = 10.sp,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            Space(height = 0, width = 5)
                        }
                        if (itemAtPos.bookingInformation?.isCancelled == true) {

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
                        if (itemAtPos.jobs?.get(0)?.jobType == AppActionValues.REPATRAITION_ACTION_VALUE) {

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
                        var isOverSized = false
                        for (obj in itemAtPos.bookingInformation?.virtualBagClassification!!) {
                            if (obj.type == 3) {
                                isOverSized = true
                                break
                            }
                        }
                        if (isOverSized) {
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

                        if (itemAtPos.bookingInformation?.passengers?.size!! > 0) {
                            var passengerCheckedInFlag = itemAtPos.flightStatusInformation?.let {
                                itemAtPos.jobs?.get(0)?.jobActivityStatus?.let { it1 ->
                                    itemAtPos.jobs?.get(0)?.jobType?.let { it2 ->
                                        onReturnCheckedInFlag(
                                            itemAtPos.bookingInformation?.passengers!!,
                                            it,
                                            it1,
                                            it2

                                        )
                                    }
                                }
                            }
                            if (passengerCheckedInFlag == true) {
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
                    val activeBooking = AppApplication.sessionManager.activeBookingDetails
                    if (activeBooking != null) {
                        Box(
                            modifier = Modifier
                                .constrainAs(rlNotification) {
                                    top.linkTo(parent.top)
                                    end.linkTo(parent.end)
                                }
                                .padding(top = 10.dp)
                                .clip(RoundedCornerShape(5.dp)) // Apply rounded corners
                                .background(air_purple) // Set background color
                                .border(0.dp, air_purple, RoundedCornerShape(5.dp))
                                .height(dimensionResource(id = R.dimen._9sdp))
                                .width(dimensionResource(id = R.dimen._9sdp))
                        )
                    }

                    Box(modifier = Modifier
                        .constrainAs(relParent) {
                            top.linkTo(tagsBox.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .fillMaxWidth()
                        .padding(top = 10.dp))
                    {
                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(returnBackGroundColor(isDarkTheme))
                        ) {
                            if (itemAtPos.bookingInformation?.passengers?.size!! > 0) {
                                Text(
                                    modifier = Modifier
                                        .constrainAs(tvPassengerTitle) {
                                            top.linkTo(parent.top)
                                            start.linkTo(parent.start)
                                        }
                                        .padding(top = 5.dp)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Start,
                                    text = stringResource(
                                        id = R.string.pName,
                                        itemAtPos.bookingInformation!!.passengers!![0].firstName
                                            ?: "",
                                        itemAtPos.bookingInformation!!.passengers!![0].lastName
                                            ?: ""
                                    ),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 18.sp,
                                    color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                                )
                            } else {
                                Text(
                                    modifier = Modifier
                                        .constrainAs(tvPassengerTitle) {
                                            top.linkTo(parent.top)
                                            start.linkTo(parent.start)
                                        }
                                        .padding(top = 5.dp)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Start,
                                    text = stringResource(
                                        id = R.string.pName,
                                        itemAtPos.bookingInformation!!.bookingCreatorUser?.firstName
                                            ?: "",
                                        itemAtPos.bookingInformation!!.bookingCreatorUser?.lastName
                                            ?: ""
                                    ),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 18.sp,
                                    color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                                )
                            }

                            if (itemAtPos.bookingInformation!!.passengers!!.size > 0) {
                                var noOBagsRepatriate = 0
                                var noOBagsAcceptance = 0
                                for (objPassenger in itemAtPos.bookingInformation!!.passengers!!) {
                                    for (objLuggage in objPassenger.passengerLuggage!!) {
                                        if (objLuggage.isDowngraded == true) {
                                            noOBagsRepatriate++
                                        } else {
                                            noOBagsAcceptance++
                                        }
                                    }
                                }
                                if (itemAtPos.jobs!![0].jobType == 6) {
                                    Text(
                                        modifier = Modifier
                                            .constrainAs(tvBagCount) {
                                                top.linkTo(tvPassengerTitle.bottom)
                                                end.linkTo(parent.end)
                                            }
                                            .padding(top = 2.dp, start = 10.dp),
                                        textAlign = TextAlign.Start,
                                        text = noOBagsRepatriate.toString(),
                                        style = MaterialTheme.typography.labelLarge, // Use your custom text style here
                                        fontSize = 22.sp,
                                        color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                                    )
                                } else {
                                    Text(
                                        modifier = Modifier
                                            .constrainAs(tvBagCount) {
                                                top.linkTo(tvPassengerTitle.bottom)
                                                end.linkTo(parent.end)
                                            }
                                            .padding(top = 5.dp, start = 10.dp),
                                        textAlign = TextAlign.Start,
                                        text = noOBagsRepatriate.toString(),
                                        style = MaterialTheme.typography.labelLarge, // Use your custom text style here
                                        fontSize = 22.sp,
                                        color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                                    )
                                }
                            } else {
                                Text(
                                    modifier = Modifier
                                        .constrainAs(tvBagCount) {
                                            top.linkTo(tvPassengerTitle.bottom)
                                            end.linkTo(parent.end)
                                        }
                                        .padding(top = 5.dp, start = 10.dp),
                                    textAlign = TextAlign.Start,
                                    text = itemAtPos.bookingInformation!!.totalBookedBags.toString(),
                                    style = MaterialTheme.typography.labelLarge, // Use your custom text style here
                                    fontSize = 22.sp,
                                    color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                                )

                            }


                            Image(
                                painter = painterResource(id = R.drawable.delivery_bag_icon),
                                contentDescription = null, // provide content description if needed
                                modifier = Modifier
                                    .constrainAs(bagImg) {
                                        top.linkTo(tvPassengerTitle.bottom)
                                        end.linkTo(tvBagCount.start)
                                    }
                                    .height(dimensionResource(id = R.dimen._35sdp))
                                    .width(dimensionResource(id = R.dimen._35sdp)), // make the image background transparent
                                contentScale = ContentScale.FillWidth // scale the image to fill the Box
                            )
                            Text(
                                modifier = Modifier
                                    .constrainAs(tvFlightNumber) {
                                        top.linkTo(tvPassengerTitle.bottom)
                                        start.linkTo(parent.start)
                                        end.linkTo(bagImg.start)
                                        width = Dimension.fillToConstraints

                                    }
                                    .padding(top = 5.dp),
                                textAlign = TextAlign.Start,
                                text = itemAtPos.bookingInformation!!.bookingReference ?: "",
                                style = MaterialTheme.typography.labelSmall, // Use your custom text style here
                                fontSize = 22.sp,
                                color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                            )

                            if (itemAtPos.jobs?.get(0)?.jobType == 6) {
                                if (itemAtPos.jobs?.get(0)?.jobStartDateTimeUTC.isNullOrEmpty()
                                    || itemAtPos.jobs?.get(0)?.jobEndDateTimeUTC.isNullOrEmpty()
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .constrainAs(tvDateTime) {
                                                top.linkTo(bagImg.bottom)
                                                start.linkTo(parent.start)
                                                end.linkTo(parent.end)
                                            }
                                            .padding(top = 5.dp)
                                            .fillMaxWidth(),
                                        textAlign = TextAlign.Start,
                                        text = itemAtPos.jobs?.get(0)!!.startDueDateTimeUTC.convertIntoDateTimeFormat()
                                            .let {
                                                itemAtPos.jobs?.get(0)!!.endDueDateTimeUTC.convertIntoDateTimeFormat()
                                                    .let { it1 ->
                                                        stringResource(
                                                            id =
                                                            R.string.date_data,
                                                            it,
                                                            it1
                                                        )
                                                    }
                                            },
                                        style = MaterialTheme.typography.displayLarge, // Use your custom text style here
                                        fontSize = 22.sp,
                                        color = returnLabelDarkBlueColor(isDarkTheme)// Replace with your desired color
                                    )
                                } else {
                                    val time =
                                        itemAtPos.jobs?.get(0)!!.jobStartDateTimeUTC!!.convertIntoDateTimeFormat()
                                            .let {
                                                itemAtPos.jobs?.get(0)!!.jobEndDateTimeUTC?.convertIntoDateTimeFormat()
                                                    ?.let { it1 ->
                                                        stringResource(
                                                            id =
                                                            R.string.date_data,
                                                            it,
                                                            it1
                                                        )
                                                    }
                                            }
                                    Text(
                                        modifier = Modifier
                                            .constrainAs(tvDateTime) {
                                                top.linkTo(bagImg.bottom)
                                                start.linkTo(parent.start)
                                                end.linkTo(parent.end)
                                            }
                                            .padding(top = 5.dp)
                                            .fillMaxWidth(),
                                        textAlign = TextAlign.Start,
                                        text = time ?: "",
                                        style = MaterialTheme.typography.displayLarge, // Use your custom text style here
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 22.sp,
                                        color = returnLabelDarkBlueColor(isDarkTheme)// Replace with your desired color
                                    )
                                }
                            } else {
                                val time = itemAtPos.bookingInformation!!.slotDurationMinutes?.let {
                                    itemAtPos.bookingInformation!!.collectionDateTimeUTC!!.convertIntoRelativeDateWithTimeSlot(
                                        context, it
                                    )
                                }
                                Text(
                                    modifier = Modifier
                                        .constrainAs(tvDateTime) {
                                            top.linkTo(bagImg.bottom)
                                            start.linkTo(parent.start)
                                            end.linkTo(parent.end)
                                        }
                                        .padding(top = 5.dp)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Start,
                                    text = time ?: "",
                                    style = MaterialTheme.typography.displayLarge, // Use your custom text style here
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 22.sp,
                                    color = returnLabelDarkBlueColor(isDarkTheme)// Replace with your desired color
                                )
                            }

                            Text(
                                modifier = Modifier
                                    .constrainAs(tvAddressNumber) {
                                        top.linkTo(tvDateTime.bottom)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                    }
                                    .padding(top = 5.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Start,
                                text = stringResource(
                                    id =
                                    R.string.pName,
                                    itemAtPos.bookingInformation!!.collectionLocation?.addressLine1
                                        ?: "",
                                    itemAtPos.bookingInformation!!.collectionLocation?.addressLine2
                                        ?: ""
                                ),
                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                fontSize = 16.sp,
                                color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                            )

                            Text(
                                modifier = Modifier
                                    .constrainAs(tvAddress) {
                                        top.linkTo(tvAddressNumber.bottom)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                    }
                                    .padding(top = 5.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Start,
                                text = itemAtPos.bookingInformation!!.collectionLocation?.addressPostCode
                                    ?: "",
                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                fontSize = 16.sp,
                                color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                            )

                        }

                    }

                }

            }
            Divider(
                thickness = 0.5.dp, // Adjust this value for thickness
                modifier = Modifier.fillMaxWidth(),
                color = if(isDarkTheme)editTextBorderStrockColor  else light_white
            )
        }
    }

}


fun onReturnCheckedInFlag(
    itemAtPos: ArrayList<Passenger>,
    flightStatusInformation: FlightStatusInformation,
    jobActivityStatus: Int, jobType: Int
): Boolean {
    var passengerCheckedInFlag = false
    if (jobType == AppActionValues.ACCEPTANCE_ACTION_VALUE) {
        for (obj in itemAtPos) {
            if (obj.checkedInStatus == 1 &&
                flightStatusInformation.flightStatusDetails.get(0).airlineCode == "BA" &&
                jobActivityStatus == AppActionValues.JOB_COMPLETED
            ) {
                passengerCheckedInFlag = true
            }
        }
    } else if (jobType == AppActionValues.REPATRAITION_ACTION_VALUE) {
        for (obj in itemAtPos) {
            if (obj.checkedInStatus == 1 &&
                flightStatusInformation.flightStatusDetails.get(0).airlineCode == "BA"
            ) {
                passengerCheckedInFlag = true
            }
        }
    }
    return passengerCheckedInFlag
}