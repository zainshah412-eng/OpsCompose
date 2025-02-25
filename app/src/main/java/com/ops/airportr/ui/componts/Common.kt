package com.ops.airportr.ui.componts

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.ops.airportr.R
import com.ops.airportr.common.theme.fonts
import com.ops.airportr.common.theme.red
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ops.airportr.common.theme.air_awesome_purple_200
import com.ops.airportr.common.utils.changeLanguage
import kotlinx.coroutines.delay

@Composable
fun CustomButton(
    name: String,
    onButtonClick: () -> Unit,
    paddingTop: Int = 0,
    paddingHorizontal: Int = 0,
    modifier: Modifier,
    fontSize: TextUnit = 16.sp,
    containerColor: Color = air_awesome_purple_200,
    textColor:Color = air_awesome_purple_200
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = paddingHorizontal.dp,
                end = paddingHorizontal.dp,
                top = paddingTop.dp
            ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 8.dp, pressedElevation = 20.dp, focusedElevation = 20.dp
        ),
        shape = RoundedCornerShape(50),
        // shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        onClick = onButtonClick
    ) {
        Text(
            text = name,
            style = TextStyle(
                fontSize = fontSize,
                color = textColor,
                fontFamily = fonts,
                fontWeight = FontWeight.W500
            )
        )
    }
}

@Composable
fun ViewPagerInCompose() {
    val pagerState = rememberPagerState()
    HorizontalPager(
        count = 3,  // Number of pages
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(top = 30.dp)
    ) { page ->
        Image(
            painter = painterResource(id = R.drawable.all_set),
            contentDescription = null, // provide content description if needed
            modifier = Modifier
                .fillMaxWidth() // width fills the available space
                .height(300.dp)
                .background(Color.Transparent), // make the image background transparent
            contentScale = ContentScale.FillBounds // scale the image to fill the Box
        )
    }
}

@Composable
fun AutoSlidingPager() {
    val pagerState = rememberPagerState() // Pager state to manage page scrolling

    // Auto-slide effect
    LaunchedEffect(key1 = pagerState.currentPage) {
        // Delay for 2 seconds
        delay(2000)
        // Move to the next page, looping back to the first page when reaching the last
        val nextPage = (pagerState.currentPage + 1) % 3
        pagerState.animateScrollToPage(nextPage)
    }

    HorizontalPager(
        count = 3, // Number of pages in the pager
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(top = 30.dp)
    ) { page ->
        Image(
            painter = painterResource(id = R.drawable.all_set),
            contentDescription = null, // provide content description if needed
            modifier = Modifier
                .fillMaxWidth() // width fills the available space
                .height(300.dp)
                .background(Color.Transparent), // make the image background transparent
            contentScale = ContentScale.FillBounds // scale the image to fill the Box
        )
    }
}

@Composable
fun Space(height: Int, width: Int) {
    Spacer(
        modifier = Modifier
            .height(height.dp)
            .width(width.dp)
    )
}

@Composable
fun MyFloatingActionButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = Color.Black,
        contentColor = Color.White,
        modifier = Modifier.size(56.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_plus_circle_fill_24), // Replace with your drawable's ID
            contentDescription = "Example Icon",
            modifier = Modifier.size(56.dp) // Adjust the size as needed
        )
    }
}



