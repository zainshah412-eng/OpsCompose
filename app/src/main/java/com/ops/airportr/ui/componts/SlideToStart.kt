package com.ops.airportr.ui.componts

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeProgress
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.ops.airportr.R
import com.ops.airportr.common.theme.air_green_low_light_100
import com.ops.airportr.common.theme.air_purple_medium_light
import com.ops.airportr.common.theme.green
import com.ops.airportr.common.theme.white
import com.ops.airportr.common.utils.returnJobsNumberCircleBackgroundOnJobComplete
import com.ops.airportr.common.utils.returnLabelAirPurpleColor
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SlideToStart(
    isDarkTheme: Boolean,
    onUnlockRequested: () -> Unit,
    msg: String,
    modifier: Modifier = Modifier,
    isAcceptance: Boolean
) {
    val density = LocalDensity.current // Get the density context
    val swipeState = rememberSwipeableState(initialValue = AnchorSlideStart.Start)

    val swipeFraction by remember {
        derivedStateOf { calculateSwipeStartFraction(swipeState.progress) }
    }

    val trackWidth = remember { mutableStateOf(0) }
    val horizontalPadding = 16.dp

    val hapticFeedback = LocalHapticFeedback.current

    LaunchedEffect(swipeState.currentValue) {
        if (swipeState.currentValue == AnchorSlideStart.End) {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
            onUnlockRequested()
            swipeState.animateTo(AnchorSlideStart.Start)
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .onSizeChanged { trackWidth.value = it.width }
            .height(60.dp)
            .background(
                color = if (isAcceptance) air_green_low_light_100 else air_purple_medium_light,
                shape = RoundedCornerShape(30.dp)
            )
            .padding(
                PaddingValues(
                    horizontal = horizontalPadding,
                    vertical = 8.dp,
                )
            )
            .swipeable(
                state = swipeState,
                anchors = mapOf(
                    0f to AnchorSlideStart.Start,
                    with(density) {
                        // Calculate offset in pixels using density
                        (trackWidth.value - 50.dp.toPx() - horizontalPadding.toPx() * 2)
                    } to AnchorSlideStart.End
                ),
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
    ) {
        Text(
            text = msg,
            color = if (isAcceptance) green else white,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(Alignment.Center)
                .alpha(1f - swipeFraction)
        )

        Box(
            modifier = Modifier
                .offset { IntOffset(swipeState.offset.value.roundToInt(), 0) }
                .size(48.dp)
                .background(
                    color = if (isAcceptance) returnJobsNumberCircleBackgroundOnJobComplete(
                        isDarkTheme
                    ) else returnLabelAirPurpleColor(isDarkTheme),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_chevrons_right_fill_24),
                contentDescription = null,
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
private fun calculateSwipeStartFraction(progress: SwipeProgress<AnchorSlideStart>): Float {
    val atAnchor = progress.from == progress.to
    val fromStart = progress.from == AnchorSlideStart.Start
    return if (atAnchor) {
        if (fromStart) 0f else 1f
    } else {
        if (fromStart) progress.fraction else 1f - progress.fraction
    }
}

enum class AnchorSlideStart { Start, End }

@Composable
fun TickAnimation(isDarkTheme: Boolean, modifier: Modifier = Modifier) {
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 500)
    )

    val alpha by animateFloatAsState(
        targetValue = 0f,
        animationSpec = tween(durationMillis = 500, delayMillis = 1000)
    )

    Box(
        modifier = modifier
            .background(color = returnLabelAirPurpleColor(isDarkTheme), shape = CircleShape)
            .size(Thumb.Size)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.tick),
            contentDescription = null,
            colorFilter = ColorFilter.tint(white)
        )
    }
}

private object Thumb {
    val Size = 40.dp
}