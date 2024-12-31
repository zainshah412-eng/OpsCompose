package com.ops.airportr.ui.componts

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.PopupProperties
import com.bumptech.glide.Glide
import com.ops.airportr.R
import com.ops.airportr.common.theme.red
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ops.airportr.common.theme.air_awesome_purple_200
import com.ops.airportr.common.theme.air_purple_awesome_light
import com.ops.airportr.common.theme.dark_blue
import com.ops.airportr.common.theme.white
import com.ops.airportr.common.utils.changeLanguage
import com.ops.airportr.common.utils.returnBackGroundColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CustomButton(
    name: String,
    onButtonClick: () -> Unit,
    paddingTop: Int = 0,
    paddingHorizontal: Int = 0,
    modifier: Modifier,
    fontSize: TextUnit = 16.sp,
    containerColor: Color = air_purple_awesome_light,
    textColor:Color = air_awesome_purple_200,
    isEnabled: Boolean = false, // Default to true
    defaultElevation:Int = 8,
    pressedElevation :Int = 20,
    focusedElevation :Int = 20
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
            defaultElevation = defaultElevation.dp,
            pressedElevation = pressedElevation.dp,
            focusedElevation = focusedElevation.dp
        ),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        onClick = onButtonClick,
        enabled = true
    ) {
        Text(
            text = name,
            style = TextStyle(
                fontSize = fontSize,
                color = textColor,
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
        containerColor = Color.Black,
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

@Composable
fun LoaderDialog(showDialog: Boolean, onDismiss: () -> Unit = {}) {
    val isDarkTheme = isSystemInDarkTheme()
    if (showDialog) {
      //  Dialog(onDismissRequest = onDismiss) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(returnBackGroundColor(isDarkTheme))
                    .wrapContentSize(Alignment.Center)
            ) {
                AndroidView(
                    modifier = Modifier.size(100.dp),
                    factory = { context ->
                        ImageView(context).apply {
                            Glide.with(context)
                                .asGif()
                                .load(R.drawable.ic_logo_loader)
                                .into(this)
                        }
                    }
                )
            }
        }
//    }
}

@Composable
fun SnackbarDemo(message:String) {
    // Create a SnackbarHostState
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Show the Snackbar immediately
    LaunchedEffect(Unit) {
        snackbarHostState.showSnackbar(
            message = message,
            actionLabel = "Dismiss"
        )
    }

    // SnackbarHost to display the Snackbar
    SnackbarHost(
        hostState = snackbarHostState
    )
}

