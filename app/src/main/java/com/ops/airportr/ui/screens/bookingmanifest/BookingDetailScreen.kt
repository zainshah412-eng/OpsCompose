package com.ops.airportr.ui.screens.bookingmanifest

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.ops.airportr.R
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnLabelAirPurpleColor
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.ui.screens.bookingmanifest.tabs.BookingActivityScreen
import com.ops.airportr.ui.screens.bookingmanifest.tabs.BookingJobsScreen
import com.ops.airportr.ui.screens.bookingmanifest.tabs.BookingSummaryScreen
import com.ops.airportr.ui.screens.scannertabs.IdentityBagScreen

@Composable
fun BookingDetailScreen(
    navHostController: NavController,
) {
    val activity = LocalContext.current as? Activity
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()

    var selectedIndex by remember { mutableIntStateOf(0) }

    val tabTitles = listOf(
        stringResource(id = R.string.summary),
        stringResource(id = R.string.top_nav_manifest),
        stringResource(id = R.string.activity),
        stringResource(id = R.string.jobs),
    )

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(returnBackGroundColor(isDarkTheme))
    ) {
        val (topBar, tabsRow, content) = createRefs()

        Row(
            modifier = Modifier
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(dimensionResource(id = R.dimen._60sdp))
                .fillMaxWidth()
                .shadow(elevation = 12.dp)
                .background(returnBackGroundColor(isDarkTheme)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 14.dp, end = 14.dp)
                    .size(30.dp)
                    .clickable {
                        navHostController.popBackStack()
                    },
                contentScale = ContentScale.Inside,
                colorFilter = ColorFilter.tint(returnLabelDarkBlueColor(isDarkTheme))
            )

            Text(
                text = stringResource(id = R.string.booking_details),
                style = MaterialTheme.typography.labelSmall,
                color = returnLabelDarkBlueColor(isDarkTheme),
                fontSize = 18.sp,
                modifier = Modifier.padding(14.dp),
            )
        }

        ScrollableTabRow(
            selectedTabIndex = selectedIndex,
            modifier = Modifier
                .constrainAs(tabsRow) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(vertical = 8.dp),
            containerColor = returnBackGroundColor(isDarkTheme),
            contentColor = returnLabelAirPurpleColor(isDarkTheme),
            edgePadding = 16.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedIndex]),
                    color = returnLabelAirPurpleColor(isDarkTheme),
                    height = 2.dp
                )
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    text = {
                        Text(
                            title,
                            style = MaterialTheme.typography.labelSmall,
                            color = returnLabelAirPurpleColor(isDarkTheme),
                            fontSize = 16.sp,
                        )
                    }
                )
            }
        }

        // Display content for the selected tab
        Box(
            modifier = Modifier
                .constrainAs(content) {
                    top.linkTo(tabsRow.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }

        ) {
            when (selectedIndex) {
                0 -> BookingSummaryScreen(navHostController)
                1 -> IdentityBagScreen(navHostController)
                2 -> BookingActivityScreen(navHostController)
                3 -> BookingJobsScreen(navHostController)
            }
        }
    }
}

