package com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobnotes.item

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ops.airportr.common.theme.editTextBorderStrockColor
import com.ops.airportr.common.theme.light_white
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.domain.model.bookingnotes.BookingNote

@Composable
fun NotesItem(
    context: Context,
    itemAtPos: MutableState<ArrayList<BookingNote>>
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

                ) {
                    val (driverName, noteRef, dividerTag) = createRefs()
                    Row(
                        modifier = Modifier
                            .constrainAs(driverName) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(top = 5.dp, start = 10.dp),
                            textAlign = TextAlign.Start,
                            text = itemAtPos.user.userRoles[0].description,
                            style = MaterialTheme.typography.labelLarge, // Use your custom text style here
                            fontSize = 14.sp,
                            color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 5.dp, start = 10.dp),
                            textAlign = TextAlign.Start,
                            text = itemAtPos.dateTimeCreatedUTC,
                            style = MaterialTheme.typography.labelSmall, // Use your custom text style here
                            fontSize = 12.sp,
                            color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                        )
                    }

                    Text(
                        modifier = Modifier
                            .constrainAs(noteRef) {
                                top.linkTo(driverName.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                            }
                            .padding(top = 5.dp, start = 10.dp),
                        textAlign = TextAlign.Start,
                        text = itemAtPos.text,
                        style = MaterialTheme.typography.labelSmall, // Use your custom text style here
                        fontSize = 12.sp,
                        color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                    )

                    Divider(
                        modifier = Modifier
                            .constrainAs(dividerTag) {
                                top.linkTo(noteRef.bottom)
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