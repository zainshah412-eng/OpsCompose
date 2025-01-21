package com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingmanifest.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ops.airportr.R
import com.ops.airportr.common.theme.customTextLabelSmallStyle
import com.ops.airportr.common.utils.callbacks.ImageLoadCallbacks
import com.ops.airportr.common.utils.callbacks.LoadImageUsingCallback
import com.ops.airportr.common.utils.removeLastChar
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.common.utils.returnLeadPassengerBackGround
import com.ops.airportr.common.utils.returnSealAndIataTagsColor
import com.ops.airportr.common.utils.sealReturn
import com.ops.airportr.domain.model.bookingdetails.BookingLuggageTamperAwareLiteSeal
import com.ops.airportr.domain.model.bookingdetails.PassengerLuggage

@Composable
fun BagsItem(
    itemAtPos: PassengerLuggage,
    index: Int,
    previousBags: Int,
    totalBagCount: Int,
    passengerId: String,
    passengerName: String,
    imageLoadCallbacks: ImageLoadCallbacks,
    onClick: (PassengerLuggage, String, String) -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()
    Box(modifier = Modifier.fillMaxWidth()) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen._120sdp))
                .background(returnBackGroundColor(isDarkTheme))
                .padding(10.dp)
                .clickable {
                    onClick(itemAtPos, passengerId, passengerName)
                }
        ) {
            val (bagImage, bagCounter, detailBox) = createRefs()


            if (itemAtPos.passengerLuggageImageId?.isEmpty() == true) {
                Image(
                    painter = painterResource(id = R.drawable.empty_image),
                    contentDescription = null, // provide content description if needed
                    modifier = Modifier
                        .constrainAs(bagImage) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                        .height(dimensionResource(id = R.dimen._110sdp))
                        .width(dimensionResource(id = R.dimen._100sdp))
                        .clip(RoundedCornerShape(4.dp)), // make the image background transparent
                    contentScale = ContentScale.FillWidth // scale the image to fill the Box
                )
            } else {
                Box(modifier = Modifier
                    .constrainAs(bagImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .clip(RoundedCornerShape(4.dp))) {
                    LoadImageUsingCallback(
                        itemAtPos.passengerLuggageImageId?.get(0) ?: "",
                        imageLoadCallbacks,
                    )
                }

            }


            Box(
                modifier = Modifier
                    .constrainAs(bagCounter) {
                        top.linkTo(bagImage.top)
                        end.linkTo(bagImage.end)
                    }
                    .padding(10.dp)
                    .offset(x = 4.dp) // Equivalent to layout_marginStart
                    .background(
                        color = returnLeadPassengerBackGround(isDarkTheme), // Replace with your badge background color
                        shape = RoundedCornerShape(4.dp) // Adjust radius as needed
                    )
                    .padding(
                        horizontal = 10.dp,
                        vertical = 4.dp
                    ) // Equivalent to paddingHorizontal, paddingTop, paddingBottom
                //.visible(false) // Replace with your visibility logic
            ) {
                val newCount: Int = previousBags + index

                Text(
                    text = stringResource(
                        id = R.string.bag_pics_count,
                        newCount.toString(),
                        totalBagCount.toString()
                    ),
                    color = returnLabelDarkBlueColor(isDarkTheme), // Text color
                    style = customTextLabelSmallStyle, // Use your custom text style
                    fontSize = 10.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Column(
                modifier = Modifier
                    .constrainAs(detailBox) {
                        top.linkTo(parent.top)
                        start.linkTo(bagImage.end)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start

            ) {
                Text(
                    textAlign = TextAlign.Start,
                    text = stringResource(id = R.string.iata),
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    color = returnSealAndIataTagsColor(isDarkTheme),
                )
                Text(
                    textAlign = TextAlign.Start,
                    text = if (itemAtPos.iataCode?.isEmpty() == true) {
                        ""
                    } else {
                        itemAtPos.iataCode ?: ""
                    },
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    color = returnLabelAirPurple100Color(isDarkTheme),
                )


                val sealObjectList: ArrayList<BookingLuggageTamperAwareLiteSeal> =
                    itemAtPos.bookingLuggageTamperAwareLiteSeals
                        ?: arrayListOf<BookingLuggageTamperAwareLiteSeal>()
                val sealList = arrayListOf<String>()
                sealObjectList.forEach {
                    it.tamperAwareSealCode?.let { it1 -> sealList.add(it1) }
                }
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    textAlign = TextAlign.Start,
                    text = stringResource(id = R.string.seal_title_text),
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,

                    color = returnSealAndIataTagsColor(isDarkTheme),
                )
                Text(
                    textAlign = TextAlign.Start,
                    text = if (sealList.isEmpty()) "-" else removeLastChar(sealReturn(sealList)),
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    color = returnLabelAirPurple100Color(isDarkTheme),
                )

            }
        }
    }
}