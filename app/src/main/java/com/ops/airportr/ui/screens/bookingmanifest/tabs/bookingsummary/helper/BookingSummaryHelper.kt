package com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingsummary.helper

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ops.airportr.R
import com.ops.airportr.common.theme.air_orange
import com.ops.airportr.common.theme.avocarto
import com.ops.airportr.common.theme.dark_blue
import com.ops.airportr.common.theme.dim_orange_color
import com.ops.airportr.common.theme.green
import com.ops.airportr.common.theme.light_red
import com.ops.airportr.common.theme.red
import com.ops.airportr.common.utils.BookingDetailsSingleton
import com.ops.airportr.common.utils.extension.convertDateForHHMM
import com.ops.airportr.common.utils.extension.convertDateInto_dd_MMM_yyyy
import com.ops.airportr.common.utils.imagedownloader.ImageDownloaderWorker
import com.ops.airportr.common.utils.returnConditionalAcceptanceBackGround
import com.ops.airportr.common.utils.returnFlagsBackGround
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.domain.model.bookingdetails.Passenger
import com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingsummary.item.ConditionalAcceptanceItem
import kotlinx.coroutines.launch

@Composable
fun ExcessChargesBox(isDarkTheme: Boolean, bookingDetails: BookingDetailsSingleton) {
    var isExpanded by remember { mutableStateOf(false) } // Track expanded/collapsed state
    val boxHeight by animateDpAsState(targetValue = if (isExpanded) 240.dp else 50.dp) // Animate height
    val boxColor by animateColorAsState(targetValue = if (isExpanded) dim_orange_color else avocarto) // Animate color
    val context = LocalContext.current
    val title = "Image Download"
    val description = "Your image has been downloaded successfully."
    val scope = rememberCoroutineScope()
    // Create ImageDownloaderWorker instance
    val imageDownloader = remember {
        ImageDownloaderWorker(context, title, description)
    }
    val isAbcBooking =
        bookingDetails.bookingJourneyDetail?.addOnProductsOverview?.isNullOrEmpty() != true &&
                bookingDetails.bookingJourneyDetail?.addOnProductsOverview?.get(0)?.addOnProductType?.equals(
                    1
                ) == true

    bookingDetails.bookingJourneyDetail?.excessBaggagePayment?.let { exc ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(boxHeight)
                    .background(
                        boxColor,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .animateContentSize(), // Animate content changes
                contentAlignment = Alignment.Center
            ) {
                this@Column.AnimatedVisibility(visible = isExpanded) {

                    Column {
                        Box(
                            modifier = Modifier
                                // .offset(x = 4.dp) // Equivalent to layout_marginStart
                                .background(
                                    color = avocarto, // Replace with your badge background color
                                    shape = RoundedCornerShape(4.dp) // Adjust radius as needed
                                )
                                .fillMaxWidth()
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(dimensionResource(id = R.dimen._50sdp))
                                    .padding(
                                        horizontal = 10.dp,
                                        vertical = 10.dp,
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically

                            ) {
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Start,
                                    text = if (isAbcBooking) stringResource(id = R.string.excess_charges) else stringResource(
                                        id = R.string.custom_charges
                                    ),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 16.sp,
                                    color = dark_blue, // Replace with your desired color
                                )

                                Image(
                                    painter = painterResource(id = R.drawable.dropup),
                                    contentDescription = null, // provide content description if needed
                                    modifier = Modifier
                                        .height(dimensionResource(id = R.dimen._20sdp))
                                        .width(dimensionResource(id = R.dimen._20sdp))
                                        .clickable {
                                            isExpanded = !isExpanded

                                        }, // make the image background transparent
                                    contentScale = ContentScale.Fit,
                                    colorFilter = ColorFilter.tint(Color.Black)// scale the image to fill the Box
                                )
                            }
                        }
                        Column(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
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
                                    text = stringResource(id = R.string.time_label),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 12.sp,
                                    color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                )
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Start,
                                    text = exc.paymentDateTime?.convertDateForHHMM() ?: "",
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
                                    text = stringResource(id = R.string.date_label),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 12.sp,
                                    color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                )
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Start,
                                    text = exc.paymentDateTime?.convertDateInto_dd_MMM_yyyy() ?: "",
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
                                    text = stringResource(id = R.string.paid_label),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 12.sp,
                                    color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                )
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Start,
                                    text = if (exc.isExcessBaggagePaymentSettled) stringResource(R.string.yes) else stringResource(
                                        R.string.no
                                    ),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 12.sp,
                                    color = if (exc.isExcessBaggagePaymentSettled) green else red, // Replace with your desired color
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
                                    text = stringResource(id = R.string.payment_method),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 12.sp,
                                    color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                )
                                if (!exc.totalCharge.toString().isNullOrEmpty()) {
                                    // Display payment method based on `paymentMode`
                                    val paymentMethodText = when (exc.paymentMode) {
                                        1 -> stringResource(id = R.string.automatic)
                                        2 -> stringResource(id = R.string.manual)
                                        else -> stringResource(id = R.string.dash)
                                    }

                                    Text(
                                        modifier = Modifier,
                                        textAlign = TextAlign.Start,
                                        text = paymentMethodText,
                                        style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                        fontSize = 12.sp,
                                        color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                    )
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
                                    text = stringResource(id = R.string.amount_label),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 12.sp,
                                    color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                )
                                if (!exc.totalCharge.toString().isNullOrEmpty()) {
                                    Text(
                                        modifier = Modifier,
                                        textAlign = TextAlign.Start,
                                        text = "${exc.currencySymbol} " + exc.totalCharge.toString(),
                                        style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                        fontSize = 12.sp,
                                        color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                    )
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
                                    text = stringResource(id = R.string.receipt_label),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 12.sp,
                                    color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                )
                                if (!exc.receiptImageURL.isNullOrEmpty()) {
                                    Text(
                                        modifier = Modifier
                                            .clickable {
                                                exc.receiptImageURL?.let {
                                                    scope.launch {
                                                        imageDownloader.downloadAndSaveImage(it)
                                                    }
                                                }
                                            },
                                        textAlign = TextAlign.Start,
                                        text = stringResource(id = R.string.download_receipt),
                                        style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                        fontSize = 14.sp,
                                        color = dark_blue, // Replace with your desired color
                                    )
                                }

                            }
                        }
                    }
                }
                if (!isExpanded) {
                    Box(
                        modifier = Modifier
                            // .offset(x = 4.dp) // Equivalent to layout_marginStart
                            .background(
                                color = avocarto, // Replace with your badge background color
                                shape = RoundedCornerShape(4.dp) // Adjust radius as needed
                            )
                            .fillMaxWidth()
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimensionResource(id = R.dimen._50sdp))
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 10.dp,
                                )
                                .clickable {
                                    isExpanded = !isExpanded
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Text(
                                modifier = Modifier,
                                textAlign = TextAlign.Start,
                                text = if (isAbcBooking) stringResource(id = R.string.excess_charges) else stringResource(
                                    id = R.string.custom_charges
                                ),
                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                fontSize = 16.sp,
                                color = dark_blue, // Replace with your desired color
                            )

                            Image(
                                painter = painterResource(id = R.drawable.down_arrow),
                                contentDescription = null, // provide content description if needed
                                modifier = Modifier
                                    .height(dimensionResource(id = R.dimen._25sdp))
                                    .width(dimensionResource(id = R.dimen._25sdp))
                                    .clickable {
                                        isExpanded = !isExpanded
                                    }
//                                .pointerInput(Unit) {
//                                    detectTapGestures(
//                                        onTap = {
//                                            Log.wtf("TAg", "TAG")
//                                            isExpanded = !isExpanded
//                                        },
//                                    )
//                                }
                                , // make the image background transparent
                                contentScale = ContentScale.Fit, // scale the image to fill the Box
                                colorFilter = ColorFilter.tint(Color.Black)// scale the image to fill the Box

                            )
                        }
                    }


                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

    }


}


@Composable
fun ExtraChargesBox(isDarkTheme: Boolean, bookingDetails: BookingDetailsSingleton) {
    var isExpanded by remember { mutableStateOf(false) } // Track expanded/collapsed state
    val boxHeight by animateDpAsState(targetValue = if (isExpanded) 240.dp else 50.dp) // Animate height
    val boxColor by animateColorAsState(
        targetValue = if (isExpanded) returnFlagsBackGround(
            isDarkTheme
        ) else light_red
    ) // Animate color


    bookingDetails.bookingJourneyDetail?.extraBagPayment?.let { exc ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(boxHeight)
                    .background(
                        boxColor,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .animateContentSize(), // Animate content changes
                contentAlignment = Alignment.Center
            ) {
                this@Column.AnimatedVisibility(visible = isExpanded) {

                    Column {
                        Box(
                            modifier = Modifier
                                // .offset(x = 4.dp) // Equivalent to layout_marginStart
                                .background(
                                    color = light_red, // Replace with your badge background color
                                    shape = RoundedCornerShape(4.dp) // Adjust radius as needed
                                )
                                .fillMaxWidth()
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(dimensionResource(id = R.dimen._50sdp))
                                    .padding(
                                        horizontal = 10.dp,
                                        vertical = 10.dp,
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically

                            ) {
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Start,
                                    text = stringResource(
                                        id = R.string.extra_bags_label
                                    ),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 16.sp,
                                    color = dark_blue, // Replace with your desired color
                                )

                                Image(
                                    painter = painterResource(id = R.drawable.dropup),
                                    contentDescription = null, // provide content description if needed
                                    modifier = Modifier
                                        .height(dimensionResource(id = R.dimen._20sdp))
                                        .width(dimensionResource(id = R.dimen._20sdp))
                                        .clickable {
                                            isExpanded = !isExpanded

                                        }, // make the image background transparent
                                    contentScale = ContentScale.Fit,
                                    colorFilter = ColorFilter.tint(red)// scale the image to fill the Box
                                )
                            }
                        }
                        Column(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
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
                                    text = stringResource(id = R.string.time_label),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 12.sp,
                                    color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                )
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Start,
                                    text = exc.paymentDateTime?.convertDateForHHMM() ?: "",
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
                                    text = stringResource(id = R.string.date_label),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 12.sp,
                                    color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                )
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Start,
                                    text = exc.paymentDateTime?.convertDateInto_dd_MMM_yyyy() ?: "",
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
                                    text = stringResource(id = R.string.paid_label),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 12.sp,
                                    color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                )
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Start,
                                    text = if (exc.isExtraBagPaymentSettled == true) stringResource(
                                        R.string.yes
                                    ) else stringResource(
                                        R.string.no
                                    ),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 12.sp,
                                    color = if (exc.isExtraBagPaymentSettled == true) green else red, // Replace with your desired color
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
                                    text = stringResource(id = R.string.payment_method),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 12.sp,
                                    color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                )
                                if (!exc.totalCharge.toString().isNullOrEmpty()) {
                                    // Display payment method based on `paymentMode`
                                    val paymentMethodText = when (exc.paymentMode) {
                                        1 -> stringResource(id = R.string.automatic)
                                        2 -> stringResource(id = R.string.manual)
                                        else -> stringResource(id = R.string.dash)
                                    }

                                    Text(
                                        modifier = Modifier,
                                        textAlign = TextAlign.Start,
                                        text = paymentMethodText,
                                        style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                        fontSize = 12.sp,
                                        color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                    )
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
                                    text = stringResource(id = R.string.amount_label),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 12.sp,
                                    color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                )
                                if (!exc.totalCharge.toString().isNullOrEmpty()) {
                                    Text(
                                        modifier = Modifier,
                                        textAlign = TextAlign.Start,
                                        text = "${exc.currencySymbol} " + exc.totalCharge.toString(),
                                        style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                        fontSize = 12.sp,
                                        color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                    )
                                }

                            }

                        }
                    }
                }
                if (!isExpanded) {
                    Box(
                        modifier = Modifier
                            // .offset(x = 4.dp) // Equivalent to layout_marginStart
                            .background(
                                color = light_red, // Replace with your badge background color
                                shape = RoundedCornerShape(4.dp) // Adjust radius as needed
                            )
                            .fillMaxWidth()
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimensionResource(id = R.dimen._50sdp))
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 10.dp,
                                )
                                .clickable {
                                    isExpanded = !isExpanded
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Text(
                                modifier = Modifier,
                                textAlign = TextAlign.Start,
                                text = stringResource(
                                    id = R.string.extra_bags_label
                                ),
                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                fontSize = 16.sp,
                                color = red, // Replace with your desired color
                            )

                            Image(
                                painter = painterResource(id = R.drawable.down_arrow),
                                contentDescription = null, // provide content description if needed
                                modifier = Modifier
                                    .height(dimensionResource(id = R.dimen._25sdp))
                                    .width(dimensionResource(id = R.dimen._25sdp))
                                    .clickable {
                                        isExpanded = !isExpanded
                                    }
//                                .pointerInput(Unit) {
//                                    detectTapGestures(
//                                        onTap = {
//                                            Log.wtf("TAg", "TAG")
//                                            isExpanded = !isExpanded
//                                        },
//                                    )
//                                }
                                , // make the image background transparent
                                contentScale = ContentScale.Fit, // scale the image to fill the Box
                                colorFilter = ColorFilter.tint(red)// scale the image to fill the Box

                            )
                        }
                    }


                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

    }


}

@Composable
fun ConditionAcceptanceBox(isDarkTheme: Boolean, bookingDetails: BookingDetailsSingleton) {
    var isExpanded by remember { mutableStateOf(false) } // Track expanded/collapsed state
    val boxHeight by animateDpAsState(targetValue = if (isExpanded) 150.dp else 50.dp) // Animate height
    val boxColor by animateColorAsState(
        targetValue = if (isExpanded) returnFlagsBackGround(
            isDarkTheme
        ) else returnConditionalAcceptanceBackGround(isDarkTheme)
    ) // Animate color


    if (bookingDetails.passengersList!!.size > 0 && bookingDetails.bookingJourneyDetail?.acceptanceMode == 1) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(boxHeight)
                    .background(
                        boxColor,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .animateContentSize(), // Animate content changes
                contentAlignment = Alignment.Center
            ) {
                this@Column.AnimatedVisibility(visible = isExpanded) {

                    Column {
                        Box(
                            modifier = Modifier
                                // .offset(x = 4.dp) // Equivalent to layout_marginStart
                                .background(
                                    color = returnConditionalAcceptanceBackGround(isDarkTheme), // Replace with your badge background color
                                    shape = RoundedCornerShape(4.dp) // Adjust radius as needed
                                )
                                .fillMaxWidth()
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(dimensionResource(id = R.dimen._50sdp))
                                    .padding(
                                        horizontal = 10.dp,
                                        vertical = 10.dp,
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically

                            ) {
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Start,
                                    text = stringResource(
                                        id = R.string.conditional_acceptance
                                    ),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 16.sp,
                                    color = air_orange, // Replace with your desired color
                                )

                                Image(
                                    painter = painterResource(id = R.drawable.dropup),
                                    contentDescription = null, // provide content description if needed
                                    modifier = Modifier
                                        .height(dimensionResource(id = R.dimen._20sdp))
                                        .width(dimensionResource(id = R.dimen._20sdp))
                                        .clickable {
                                            isExpanded = !isExpanded

                                        }, // make the image background transparent
                                    contentScale = ContentScale.Fit,
                                    colorFilter = ColorFilter.tint(returnLabelDarkBlueColor(isDarkTheme))// scale the image to fill the Box
                                )
                            }
                        }
                        Column(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
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
                                    text = stringResource(id = R.string.tv_name),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 12.sp,
                                    color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                )
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Start,
                                    text = stringResource(id = R.string.checked_in),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 12.sp,
                                    color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                )

                            }


// Nested list in footer
                            if (bookingDetails.passengersList!!.size > 0 && bookingDetails.bookingJourneyDetail?.acceptanceMode == 1) {
                                val passengerList =
                                    remember { mutableStateOf(ArrayList<Passenger>()) }
                                passengerList.value.clear()
                                passengerList.value.addAll(bookingDetails.passengersList!!)
                                LazyColumn {
                                    items(passengerList.value) { footerItem ->
                                        ConditionalAcceptanceItem(
                                            itemAtPos = footerItem,
                                            0,
                                            onClick = {

                                            }
                                        )
                                    }
                                }
                            }


                        }
                    }
                }
                if (!isExpanded) {
                    Box(
                        modifier = Modifier
                            // .offset(x = 4.dp) // Equivalent to layout_marginStart
                            .background(
                                color = returnConditionalAcceptanceBackGround(isDarkTheme), // Replace with your badge background color
                                shape = RoundedCornerShape(4.dp) // Adjust radius as needed
                            )
                            .fillMaxWidth()
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimensionResource(id = R.dimen._50sdp))
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 10.dp,
                                )
                                .clickable {
                                    isExpanded = !isExpanded
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Text(
                                modifier = Modifier,
                                textAlign = TextAlign.Start,
                                text = stringResource(
                                    id = R.string.conditional_acceptance
                                ),
                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                fontSize = 16.sp,
                                color = air_orange, // Replace with your desired color
                            )

                            Image(
                                painter = painterResource(id = R.drawable.down_arrow),
                                contentDescription = null, // provide content description if needed
                                modifier = Modifier
                                    .height(dimensionResource(id = R.dimen._25sdp))
                                    .width(dimensionResource(id = R.dimen._25sdp))
                                    .clickable {
                                        isExpanded = !isExpanded
                                    }
//                                .pointerInput(Unit) {
//                                    detectTapGestures(
//                                        onTap = {
//                                            Log.wtf("TAg", "TAG")
//                                            isExpanded = !isExpanded
//                                        },
//                                    )
//                                }
                                , // make the image background transparent
                                contentScale = ContentScale.Fit, // scale the image to fill the Box
                                colorFilter = ColorFilter.tint(returnLabelDarkBlueColor(isDarkTheme))// scale the image to fill the Box

                            )
                        }
                    }


                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

    }


}

