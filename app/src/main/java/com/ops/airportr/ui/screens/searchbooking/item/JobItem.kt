package com.ops.airportr.ui.screens.searchbooking.item

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ops.airportr.R
import com.ops.airportr.common.theme.customTextLabelSmallStyle
import com.ops.airportr.common.theme.editTextBorderStrockColor
import com.ops.airportr.common.theme.light_white
import com.ops.airportr.common.utils.convertIntoDateTimeFormat
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnJobsNumberCircleBackground
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.domain.model.bookingdetails.Job

@Composable
fun JobItem(
    context: Context,
    onClick: (item: Job) -> kotlin.Unit,
    itemAtPos: MutableState<ArrayList<Job>>
) {
    val isDarkTheme = isSystemInDarkTheme()
    LazyColumn {
        itemsIndexed(itemAtPos.value) { index, itemAtPos ->
            Box(modifier = Modifier.fillMaxWidth()) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(returnBackGroundColor(isDarkTheme))
                        .padding(10.dp)
                        .clickable {
                            onClick(itemAtPos)
                        }
                ) {
                    val (roundedNumber, jobTitle, driverName, dateAndTime, jobStatus, dividerTag) = createRefs()

                    if (itemAtPos.jobType == 1 || itemAtPos.jobType == 6 || itemAtPos.jobType == 7) {
                        Box(
                            modifier = Modifier
                                .constrainAs(roundedNumber) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    bottom.linkTo(parent.bottom)
                                }
                                .offset(x = 4.dp) // Equivalent to layout_marginStart
                                .background(
                                    color = if (isDarkTheme) returnJobsNumberCircleBackground(
                                        isDarkTheme
                                    )
                                    else returnLabelAirPurple100Color(isDarkTheme), // Replace with your badge background color
                                    shape = RoundedCornerShape(15.dp) // Adjust radius as needed
                                )
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 4.dp
                                ) // Equivalent to paddingHorizontal, paddingTop, paddingBottom
                            //.visible(false) // Replace with your visibility logic
                        ) {
                            Text(
                                text = index.toString(),
                                color = returnLabelDarkBlueColor(isDarkTheme), // Text color
                                style = customTextLabelSmallStyle, // Use your custom text style
                                fontSize = 10.sp,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }


                        Box(
                            modifier = Modifier
                                .constrainAs(jobStatus) {
                                    top.linkTo(parent.top)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                }
                                .offset(x = 4.dp) // Equivalent to layout_marginStart
                                .background(
                                    color = if (isDarkTheme) returnJobsNumberCircleBackground(
                                        isDarkTheme
                                    )
                                    else returnLabelAirPurple100Color(isDarkTheme), // Replace with your badge background color
                                    shape = RoundedCornerShape(4.dp) // Adjust radius as needed
                                )
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 4.dp
                                ) // Equivalent to paddingHorizontal, paddingTop, paddingBottom
                            //.visible(false) // Replace with your visibility logic
                        ) {

                            Text(
                                text = if (itemAtPos.jobActivityStatus == 5)
                                    stringResource(id = R.string.done) else stringResource(id = R.string.to_do),
                                color = returnLabelDarkBlueColor(isDarkTheme), // Text color
                                style = customTextLabelSmallStyle, // Use your custom text style
                                fontSize = 10.sp,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        var jobName = ""
                        when (itemAtPos.jobType) {
                            1 -> {
                                jobName = stringResource(id = R.string.job_acceptance)
                            }

                            6 -> {
                                jobName = stringResource(id = R.string.job_repatriation)
                            }

                            7 -> {
                                jobName = stringResource(id = R.string.job_inject)
                            }

                        }

                        Text(
                            modifier = Modifier
                                .constrainAs(jobTitle) {
                                    top.linkTo(parent.top)
                                    start.linkTo(roundedNumber.end)
                                }
                                .padding(top = 5.dp, start = 10.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Start,
                            text = jobName,
                            style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                            fontSize = 18.sp,
                            color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                        )

                        Text(
                            modifier = Modifier
                                .constrainAs(driverName) {
                                    top.linkTo(jobTitle.bottom)
                                    start.linkTo(roundedNumber.end)
                                    end.linkTo(jobStatus.start)
                                    width = Dimension.fillToConstraints

                                }
                                .padding(top = 5.dp, start = 10.dp),
                            textAlign = TextAlign.Start,
                            text = stringResource(
                                id =
                                R.string.champ_data,
                                itemAtPos.currentChampion?.firstName ?: "",
                                itemAtPos.currentChampion?.lastName ?: ""
                            ),
                            style = MaterialTheme.typography.labelSmall, // Use your custom text style here
                            fontSize = 16.sp,
                            color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                        )

                        Text(
                            modifier = Modifier
                                .constrainAs(dateAndTime) {
                                    top.linkTo(driverName.bottom)
                                    start.linkTo(roundedNumber.end)
                                    end.linkTo(jobStatus.start)
                                    width = Dimension.fillToConstraints

                                }
                                .padding(top = 5.dp, start = 10.dp),
                            textAlign = TextAlign.Start,
                            text = stringResource(
                                id =
                                R.string.date_data,
                                itemAtPos.startDueDateTimeUTC?.convertIntoDateTimeFormat() ?: "",
                                itemAtPos.endDueDateTimeUTC?.convertIntoDateTimeFormat() ?: ""
                            ),
                            style = MaterialTheme.typography.labelSmall, // Use your custom text style here
                            fontSize = 16.sp,
                            color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                        )
                        Divider(
                            modifier = Modifier
                                .constrainAs(dividerTag) {
                                    top.linkTo(dateAndTime.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    width = Dimension.fillToConstraints

                                }
                                .padding(top = 10.dp),
                            thickness = 0.5.dp, // Adjust this value for thickness
                            color = if (isDarkTheme) editTextBorderStrockColor else light_white
                        )
                    }
                }

            }

        }
    }
}