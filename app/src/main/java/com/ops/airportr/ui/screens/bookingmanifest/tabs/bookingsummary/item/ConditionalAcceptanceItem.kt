package com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingsummary.item

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ops.airportr.R
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.domain.model.bookingdetails.Passenger

@Composable
fun ConditionalAcceptanceItem(
    itemAtPos: Passenger,
    index: Int,
    onClick: (Passenger) -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp)
            .height(dimensionResource(id = R.dimen._25sdp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier,
            textAlign = TextAlign.Start,
            text = stringResource(
                id = R.string.pName,
                itemAtPos.firstName ?: "",
                itemAtPos.lastName ?: ""
            ),
            style = MaterialTheme.typography.labelMedium, // Use your custom text style here
            fontSize = 12.sp,
            color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
        )
        Text(
            modifier = Modifier,
            textAlign = TextAlign.Start,
            text = if (itemAtPos.checkedInStatus == 0)
                stringResource(id = R.string.yes) else stringResource(
                id = R.string.no
            ),
            style = MaterialTheme.typography.labelMedium, // Use your custom text style here
            fontSize = 12.sp,
            color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
        )

    }
}