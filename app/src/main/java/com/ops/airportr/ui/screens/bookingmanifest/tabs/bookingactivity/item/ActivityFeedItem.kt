package com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingactivity.item

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
import com.ops.airportr.common.utils.removeLastChar
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.common.utils.returnLeadPassengerBackGround
import com.ops.airportr.common.utils.returnLineColorForAuditTrailItem
import com.ops.airportr.common.utils.returnSealAndIataTagsColor
import com.ops.airportr.common.utils.sealReturn
import com.ops.airportr.domain.model.bookingdetails.BookingLuggageTamperAwareLiteSeal
import com.ops.airportr.domain.model.bookingdetails.PassengerLuggage
import com.ops.airportr.domain.model.getcommunicationlog.CustomActionUpdateModel

@Composable
fun ActivityFeedItem(
    tvSystemEmail :String,
    tvBookingStatus :String,
    image :Int,
    tvActionValues :String,
    date:String

) {
    val isDarkTheme = isSystemInDarkTheme()
    Box(modifier = Modifier.fillMaxWidth()) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(returnBackGroundColor(isDarkTheme))
                .padding(10.dp)
                .clickable {
                    // onClick(itemAtPos)
                }
        ) {
            val (bagImage,edtView, detailBox) = createRefs()

            Image(
                painter = painterResource(id = image),
                contentDescription = null, // provide content description if needed
                modifier = Modifier
                    .constrainAs(bagImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .height(dimensionResource(id = R.dimen._15sdp))
                    .width(dimensionResource(id = R.dimen._15sdp))
                    .clip(RoundedCornerShape(4.dp)), // make the image background transparent
                contentScale = ContentScale.FillWidth // scale the image to fill the Box
            )
            Box(
                modifier = Modifier
                    .constrainAs(edtView) {
                        top.linkTo(bagImage.bottom)
                        start.linkTo(bagImage.start)
                        end.linkTo(bagImage.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .width(dimensionResource(id = R.dimen._1sdp))
                    .background(
                        color = returnLineColorForAuditTrailItem(isDarkTheme)
                    )
            )

            Column(
                modifier = Modifier
                    .constrainAs(detailBox) {
                        top.linkTo(parent.top)
                        start.linkTo(bagImage.end)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                      //  height = Dimension.fillToConstraints
                    }
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start

            ) {

                Text(
                    textAlign = TextAlign.Start,
                    text = date,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    color = returnLabelAirPurple100Color(isDarkTheme),
                )

                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    textAlign = TextAlign.Start,
                    text = tvSystemEmail,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    color = returnLabelDarkBlueColor(isDarkTheme),
                )
                Text(
                    textAlign = TextAlign.Start,
                    text = tvBookingStatus,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    color = returnLabelAirPurple100Color(isDarkTheme),
                )
                if(tvActionValues != "") {
                    Text(
                        textAlign = TextAlign.Start,
                        text = tvActionValues,
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 10.sp,
                        color = returnLabelAirPurple100Color(isDarkTheme),
                    )
                }

            }
        }
    }
}