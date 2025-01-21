package com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingmanifest.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ops.airportr.R
import com.ops.airportr.common.theme.customTextLabelSmallStyle
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.common.utils.returnLeadPassengerBackGround
import com.ops.airportr.domain.model.bookingdetails.Passenger
import com.ops.airportr.domain.model.bookingdetails.PassengerLuggage

@Composable
fun PassengerItem(
    itemAtPos: Passenger,
    index: Int,
    pnr: String,
    onClick: (Passenger) -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()
    val passengerLuggageList = remember { mutableStateOf(ArrayList<PassengerLuggage>()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(returnBackGroundColor(isDarkTheme))
            .padding(10.dp)
            .clickable {
                onClick(itemAtPos)
            }
    ) {
        // Main Content Section
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
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
                text = itemAtPos.firstName?.let { fn ->
                    itemAtPos.lastName?.let { ln -> "$fn $ln" } ?: fn
                } ?: itemAtPos.lastName.orEmpty(),
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
                text = if (pnr.isEmpty()) {
                    stringResource(id = R.string.pName, stringResource(id = R.string.pnr), "-")
                } else {
                    stringResource(id = R.string.pName, stringResource(id = R.string.pnr), pnr)
                },
                style = MaterialTheme.typography.labelSmall,
                fontSize = 12.sp,
                color = returnLabelAirPurple100Color(isDarkTheme),
            )
        }

        // Luggage List
        passengerLuggageList.value.clear()
        itemAtPos.passengerLuggage?.let { passengerLuggageList.value.addAll(it) }

//        if (passengerLuggageList.value.isNotEmpty()) {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp)
//            ) {
//                items(passengerLuggageList.value) { childItem ->
//                    BagsItem(
//                        itemAtPos = childItem,
//                        index = index,
//                        onClick = {
//                            // Handle bag click
//                        }
//                    )
//                }
//            }
//        }
    }
}
