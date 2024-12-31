package com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.scanner

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ops.airportr.R
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnLabelAirPurpleColor
import com.ops.airportr.data.viewmodel.MainViewModel
import com.ops.airportr.route.sidenavigationscreens.BottomScreens
import com.ops.airportr.ui.screens.scannertabs.IdentityBagScreen
import com.ops.airportr.ui.screens.scannertabs.OrganiseBagScreen
import com.ops.airportr.ui.screens.scannertabs.TagAndInjectBagScreen
import com.ops.airportr.ui.screens.scannertabs.TakeCustodyScreen

@Composable
fun ScannerScreen(
    navController: NavController,
) {
    val activity = LocalContext.current as? Activity
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()

    var selectedIndex by remember { mutableIntStateOf(0) }

    val tabTitles =
        listOf(
            stringResource(id = R.string.identify),
            stringResource(id = R.string.custody),
            stringResource(id = R.string.organise),
            stringResource(id = R.string.tag),
        )

    Column {
        TabRow(
            selectedTabIndex = selectedIndex,
            modifier = Modifier.fillMaxWidth(),
            containerColor = returnBackGroundColor(isDarkTheme),
            contentColor = returnBackGroundColor(isDarkTheme),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedIndex]),
                    color = returnLabelAirPurpleColor(isDarkTheme), // Custom indicator color
                    height = 2.dp      // Custom indicator height
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
                            color = returnLabelAirPurpleColor(isDarkTheme), // Use your custom text style here
                            fontSize = 10.sp,
                        )
                    }
                )
            }
        }

        // Content based on the selected tab
        when (selectedIndex) {
            0 -> IdentityBagScreen(navController)
            1 -> TakeCustodyScreen(navController)
            2 -> OrganiseBagScreen()
            3 -> TagAndInjectBagScreen()
        }
    }
}
