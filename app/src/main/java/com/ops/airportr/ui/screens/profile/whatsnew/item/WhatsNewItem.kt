package com.ops.airportr.ui.screens.profile.whatsnew.item

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.theme.customTextLabelSmallStyle
import com.ops.airportr.common.theme.dark_blue
import com.ops.airportr.common.theme.white
import com.ops.airportr.domain.model.whatsnew.updatenew.UpdateAvailableItem

@Composable
fun WhatsNewItem(
    context: Context,
    onClick: (item: UpdateAvailableItem) -> kotlin.Unit,
    itemAtPos: MutableState<ArrayList<UpdateAvailableItem>>
) {
    LazyColumn {
        items(itemAtPos.value) { itemAtPos ->
            Box(
                modifier = Modifier
                    .fillMaxSize() // Set content scale to crop
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(white)
                        .padding(10.dp)
                        .clickable {
                            onClick(itemAtPos)
                        }
                ) {
                    val (tvFeatureName, tvVersion, tvFeatureDetail) = createRefs()
                    Text(
                        modifier = Modifier
                            .constrainAs(tvFeatureName) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            }
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        text = itemAtPos.featureName,
                        style = customTextLabelSmallStyle, // Use your custom text style here
                        fontSize = 14.sp,
                        color = dark_blue, // Replace with your desired color
                    )
                    Text(
                        modifier = Modifier
                            .constrainAs(tvVersion) {
                                top.linkTo(tvVersion.bottom)
                                start.linkTo(parent.start)
                            }
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        text = itemAtPos.version ?: "",
                        style = customTextLabelSmallStyle, // Use your custom text style here
                        fontSize = 14.sp,
                        color = dark_blue, // Replace with your desired color
                    )
                    Text(
                        modifier = Modifier
                            .constrainAs(tvFeatureDetail) {
                                top.linkTo(tvVersion.bottom)
                                start.linkTo(parent.start)
                            }
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        text = itemAtPos.featureDetail,
                        style = customTextLabelSmallStyle, // Use your custom text style here
                        fontSize = 14.sp,
                        color = dark_blue, // Replace with your desired color
                    )
                    if (!itemAtPos.list.isNullOrEmpty())
                        LazyColumn {
                            items(itemAtPos.list) { item ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize() // Set content scale to crop
                                ) {
                                    ConstraintLayout(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(white)
                                            .padding(10.dp)
                                            .clickable {

                                            }
                                    ) {
                                        val (dot, tvFeature) = createRefs()
                                        Box(
                                            modifier = Modifier
                                                .constrainAs(dot) {
                                                    top.linkTo(parent.top)
                                                    start.linkTo(parent.start)
                                                }
                                                .offset(x = 4.dp) // Equivalent to layout_marginStart
                                                .background(
                                                    color = air_purple, // Replace with your badge background color
                                                    shape = RoundedCornerShape(8.dp) // Adjust radius as needed
                                                )
                                                .padding(
                                                    horizontal = 4.dp,
                                                    vertical = 2.dp
                                                ) // Equivalent to paddingHorizontal, paddingTop, paddingBottom
                                            //.visible(false) // Replace with your visibility logic
                                        )
                                        Text(
                                            modifier = Modifier
                                                .constrainAs(tvFeature) {
                                                    top.linkTo(parent.top)
                                                    start.linkTo(dot.end)
                                                    end.linkTo(parent.end)
                                                    width = Dimension.fillToConstraints
                                                }
                                                .padding(top = 5.dp)
                                                .fillMaxWidth(),
                                            textAlign = TextAlign.Start,
                                            text = itemAtPos.featureName,
                                            style = customTextLabelSmallStyle, // Use your custom text style here
                                            fontSize = 14.sp,
                                            color = dark_blue, // Replace with your desired color
                                        )
                                    }
                                }
                            }
                        }
                }
            }
        }
    }
}