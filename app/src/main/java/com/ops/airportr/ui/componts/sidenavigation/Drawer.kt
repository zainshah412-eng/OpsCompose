package com.ops.airportr.ui.componts.sidenavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ops.airportr.R
import com.ops.airportr.common.theme.air_awesome_light_white

import com.ops.airportr.common.theme.textColorPrimary
import com.ops.airportr.route.sidenavigationscreens.screensFromDrawer

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Box(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier
                .fillMaxSize()
        ) {

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        textColorPrimary
//                        brush = Brush.horizontalGradient(
//                            listOf(
//                                Color(0xFF966DE7),
//                                Color(0xFF755CD4),
//                                Color(0xFF4C48C1)
//                            )
//                        )
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = modifier.padding(15.dp)) {
                    Text(
                        text = "Zain Ali",
                        style = MaterialTheme.typography.bodyMedium,
                        color = air_awesome_light_white
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "zainshah412.za@gmail.com",
                        style = MaterialTheme.typography.bodyMedium,
                        color = air_awesome_light_white
                    )
                }

                Image(
                    painter = painterResource(R.drawable.airportr_logo),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )

            }

            screensFromDrawer.forEach { screen ->
                Spacer(Modifier.height(14.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { onDestinationClicked(screen.route) })
                        .height(40.dp)
                        .background(color = Color.Transparent)

                ) {
                    Image(
                        painter = painterResource(id = screen.icon),
                        contentDescription = screen.title,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp)
                            .padding(start = 10.dp)
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(
                        text = screen.title,
                        fontSize = 16.sp,
                        color = air_awesome_light_white,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
        }
    }

}
