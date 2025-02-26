package com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobnotes

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ops.airportr.AppApplication
import com.ops.airportr.R
import com.ops.airportr.common.AppConstants
import com.ops.airportr.common.theme.air_awesome_purple_100
import com.ops.airportr.common.theme.customTextHeadingStyle
import com.ops.airportr.common.theme.customTextLabelSmallStyle
import com.ops.airportr.common.theme.fontsRegular
import com.ops.airportr.common.utils.BookingDetailsSingleton
import com.ops.airportr.common.utils.extension.toast
import com.ops.airportr.common.utils.extension.urlForAcceptance
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnLabelAirPurpleColor
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.domain.model.bookingdetails.Job
import com.ops.airportr.domain.model.bookingnotes.BookingNote
import com.ops.airportr.domain.model.note.BookingNoteParams
import com.ops.airportr.ui.componts.LoaderDialog
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.bookingDetails
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobnotes.item.NotesItem

var bookingDetails: BookingDetailsSingleton = BookingDetailsSingleton()

@Composable
fun JobNotesScreen(
    navController: NavController,
    jobObj: Job?,
    viewModel: JobNotesViewModel = hiltViewModel(),
) {
    val activity = LocalContext.current as? Activity
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()

    var snackBarShowFlag by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showLoader by remember { mutableStateOf(false) }
    var messageEdt by remember { mutableStateOf(("")) }
    var bookingRef by remember { mutableStateOf(("")) }


    val bookingNotesState = viewModel.stateBookingNotes.value
    val addNotesState = viewModel.stateAddNotes.value

    //TODO: On Resume
    //TODO:========================================================
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    LaunchedEffect(currentBackStackEntry.value?.lifecycle) {
        currentBackStackEntry.value?.lifecycle?.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                bookingDetails = BookingDetailsSingleton()
                bookingDetails.init()
                bookingRef =
                    bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.bookingReference
                        ?: ""
                viewModel.getBookingNotes(
                    context.urlForAcceptance() + AppConstants.GET_BOOKING_NOTES_BY_BOOKING_REFERENCE,
                    bookingRef
                )
            }
        })
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(returnBackGroundColor(isDarkTheme))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 2.dp, end = 2.dp, top = 12.dp)
        ) {
            val (messageBar, bookingInfoBox, messageBox) = createRefs()
            val bookingNote = remember { mutableStateOf(ArrayList<BookingNote>()) }
            Box(
                modifier = Modifier
                    .constrainAs(messageBar) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp, top = 4.dp)
                    .drawBehind {
                        drawLine(
                            color = Color.Gray, // Change to desired color
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = 2.dp.toPx() // Change thickness as needed
                        )
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    TextField(
                        value = messageEdt,
                        onValueChange = { messageEdt = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.enter_your_note),
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 14.sp,
                                color = returnLabelDarkBlueColor(isDarkTheme),
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        textStyle = TextStyle(fontFamily = fontsRegular),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = returnLabelAirPurpleColor(isDarkTheme),
                            disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            backgroundColor = returnBackGroundColor(isDarkTheme),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            cursorColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_send__msg),
                        contentDescription = null, // provide content description if needed
                        modifier = Modifier
                            .padding(start = 14.dp)
                            .height(30.dp)
                            .width(30.dp)
                            .clickable {
                                if (messageEdt.length > 0) {
                                    var model =
                                        AppApplication.sessionManager.userDetails.userId?.let {
                                            BookingNoteParams(
                                                1,
                                                jobObj?.bookingJourneyReference.toString(),
                                                jobObj?.bookingReference.toString(),
                                                messageEdt,
                                                it
                                            )
                                        }
                                    if (model != null) {
                                        viewModel.stateAddNotes(
                                            context.urlForAcceptance() + AppConstants.ADD_BOOKING_NOTE,
                                            model
                                        )
                                    }
                                } else {
                                    context.toast("Enter the Message First")
                                }
                            }, // make the image background transparent
                        contentScale = ContentScale.Inside, // scale the image to fill the Box
                        colorFilter = ColorFilter.tint(returnLabelDarkBlueColor(isDarkTheme))
                    )
                }
            }
            if (bookingNotesState.response != null && bookingNotesState.response?.bookingNote?.size!! > 0) {
                Box(modifier = Modifier.constrainAs(bookingInfoBox) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(messageBar.top)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }) {
                    bookingNote.value.clear()
                    bookingNotesState.response?.bookingNote?.let {
                        bookingNote.value.addAll(it)
                    }
                    NotesItem(context, bookingNote)
                    bookingNotesState.response = null
                    bookingNotesState.error = null
                    bookingNotesState.isLoading = false
                }
            } else {
                Column(
                    modifier = Modifier
                        .constrainAs(bookingInfoBox) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(messageBar.top)
                            height = Dimension.fillToConstraints
                        }
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.oops),
                        contentDescription = stringResource(id = R.string.no_bookings_here), // provide content description if needed
                        modifier = Modifier
                            .padding(top = 14.dp)
                            .height(120.dp)
                            .width(120.dp), // make the image background transparent
                        contentScale = ContentScale.Crop // scale the image to fill the Box
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.no_notes),
                        style = customTextHeadingStyle, // Use your custom text style here
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = returnLabelDarkBlueColor(isDarkTheme)// Replace with your desired color
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 5.dp, start = 10.dp, end = 10.dp)
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.no_notes_desc),
                        style = customTextLabelSmallStyle, // Use your custom text style here
                        fontSize = 12.sp,
                        color = air_awesome_purple_100, // Replace with your desired color
                    )


                }
            }

            if (addNotesState.response != null && addNotesState.response?.responseStatus != null) {
                addNotesState.response = null
                addNotesState.error = null
                addNotesState.isLoading = false
                viewModel.getBookingNotes(
                    context.urlForAcceptance() + AppConstants.GET_BOOKING_NOTES_BY_BOOKING_REFERENCE,
                    bookingRef
                )
            }

            if (addNotesState.isLoading) {
                showLoader = true
                LoaderDialog(showDialog = showLoader)
            }

        }


    }
}
