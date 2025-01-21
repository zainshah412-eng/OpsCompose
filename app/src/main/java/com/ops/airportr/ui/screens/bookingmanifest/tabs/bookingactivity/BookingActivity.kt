package com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingactivity

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ops.airportr.R
import com.ops.airportr.common.AppConstants.GET_ACTION_UPDATE_LOG
import com.ops.airportr.common.AppConstants.GET_BOOKING_NOTES_BY_BOOKING_REFERENCE
import com.ops.airportr.common.AppConstants.GET_COMMUNICATION_LOG_CCD
import com.ops.airportr.common.theme.fontsRegular
import com.ops.airportr.common.utils.BookingDetailsSingleton
import com.ops.airportr.common.utils.convertDateFormatForNotes
import com.ops.airportr.common.utils.getActionType
import com.ops.airportr.common.utils.getAgentActionName
import com.ops.airportr.common.utils.getAgentActionTypeImage1
import com.ops.airportr.common.utils.getCommunicationEmailActionType
import com.ops.airportr.common.utils.getCommunicationSMSActionType
import com.ops.airportr.common.utils.getScanType
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnLabelAirPurpleColor
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.common.utils.urlForAcceptance
import com.ops.airportr.domain.model.bookingdetails.BookingJourneyDetail
import com.ops.airportr.domain.model.bookingnotes.GetBookingNotesByReference
import com.ops.airportr.domain.model.getcommunicationlog.CCDGetCommunicationLogResponse
import com.ops.airportr.domain.model.getcommunicationlog.CustomActionUpdateModel
import com.ops.airportr.domain.model.getcommunicationlog.params.CCDGetCommunicationLogParam
import com.ops.airportr.domain.model.updatelogs.GetActionUpdateLogsResponse
import com.ops.airportr.domain.model.updatelogs.params.GetActionUpdateLogsParams
import com.ops.airportr.ui.componts.LoaderDialog
import com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingactivity.item.ActivityFeedItem
import com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingactivity.viewmodel.BookingActivityViewModel


@Composable
fun BookingActivityScreen(
    navController: NavController,
    bookingActivityViewModel: BookingActivityViewModel = hiltViewModel()
) {

    val activity = LocalContext.current as? Activity
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()
    var bookingRef by remember { mutableStateOf(("")) }
    var snackBarShowFlag by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showLoader by remember { mutableStateOf(false) }

    var bookingDetails: BookingDetailsSingleton
    var detailModel by remember { mutableStateOf(BookingJourneyDetail()) }

    val bookingNotesState = bookingActivityViewModel.state.value
    val communicationLogState = bookingActivityViewModel.stateCommunicationLogs.value
    val actionUpdateLogsState = bookingActivityViewModel.stateActonUpdateLogs.value

    var actionUpdate = remember { mutableStateOf(ArrayList<CustomActionUpdateModel>()) }

    //TODO: On Resume
    //TODO:========================================================
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    LaunchedEffect(currentBackStackEntry.value?.lifecycle) {
        currentBackStackEntry.value?.lifecycle?.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                bookingDetails = BookingDetailsSingleton()
                bookingDetails.init()
                detailModel = bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)!!
                bookingDetails.bookingDetailFromDb?.bookingReference?.let { bookRef ->
                    bookingDetails.bookingDetailFromDb!!.bookingJourneyDetails[0].bookingJourneyReference?.let { bookJourRef ->
                        // viewModel.loadData() // Call your ViewModel function
                        bookingActivityViewModel.getCommunicationLog(
                            context.urlForAcceptance() + GET_COMMUNICATION_LOG_CCD,
                            CCDGetCommunicationLogParam(
                                bookJourRef,
                                bookRef
                            )
                        )
                    }
                }


            }
        })
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(returnBackGroundColor(isDarkTheme))
    ) {
        bookingDetails = BookingDetailsSingleton()
        bookingDetails.init()
        detailModel = bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)!!

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(returnBackGroundColor(isDarkTheme))
                .padding(
                    top = 10.dp,
                    start = 10.dp,
                    end = 10.dp
                )
        ) {
            val (title, auditTrailList, dividerLineRef, notesBox) = createRefs()
            Text(
                text = stringResource(id = R.string.audit_),
                style = MaterialTheme.typography.labelLarge,
                color = returnLabelDarkBlueColor(isDarkTheme),
                fontSize = 22.sp,
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .padding(5.dp),
            )
            if (communicationLogState.response != null && communicationLogState.response?.communicationLog != null) {
                actionUpdate.value.clear()
                setDataForCommunicationLogs(communicationLogState.response, actionUpdate)
                communicationLogState.response = null
                communicationLogState.error = null
                communicationLogState.isLoading = false
                bookingDetails.bookingDetailFromDb?.bookingReference?.let { it1 ->
                    bookingActivityViewModel.getBookingNotes(
                        context.urlForAcceptance() + GET_BOOKING_NOTES_BY_BOOKING_REFERENCE,
                        it1
                    )
                }
            }

            if (bookingNotesState.response != null && bookingNotesState.response?.bookingNote != null) {
                setDataForNotes(
                    bookingNotesState.response,
                    actionUpdate
                )


                bookingDetails.bookingDetailFromDb?.bookingReference?.let { bookRef ->
                    bookingDetails.bookingDetailFromDb!!.bookingJourneyDetails[0].bookingJourneyReference?.let { bookJourRef ->
                        // viewModel.loadData() // Call your ViewModel function
                        bookingActivityViewModel.getActionUpdateLog(
                            context.urlForAcceptance() + GET_ACTION_UPDATE_LOG,
                            GetActionUpdateLogsParams(
                                bookRef,
                                bookJourRef
                            )
                        )
                    }
                }



                bookingNotesState.response = null
                bookingNotesState.error = null
                bookingNotesState.isLoading = false

            }
            if (actionUpdateLogsState.response != null && actionUpdateLogsState.response?.actionUpdates != null) {

                setDataForActionLogs(
                    actionUpdateLogsState.response,
                    bookingDetails, actionUpdate
                )
                if (actionUpdate.value.size > 0) {

                    LazyColumn(
                        modifier = Modifier.constrainAs(auditTrailList) {
                            top.linkTo(title.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(dividerLineRef.top)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        }
                    ) {
                        itemsIndexed(actionUpdate.value) { innerindex, model ->
                            var tvSystemEmail = ""
                            var tvBookingStatus = ""
                            var image = -1
                            var tvActionValues = ""
                            var tvDate = model.dateTimeUTC.convertDateFormatForNotes()

                            when (model.from) {
                                "NOTES" -> {
                                    tvSystemEmail = model.name.toString()
                                    tvBookingStatus = model.name.toString()
                                    image = R.drawable.ic_notes
                                    tvActionValues = ""
                                }

                                "ACTION_LOGS" -> {
                                    tvSystemEmail = model.name.toString()
                                    tvBookingStatus =
                                        getActionType(context, model.actionType.toString())
                                    image = R.drawable.ic_action_update
                                    tvActionValues = ""


                                    if (model.actionType == 43 && !model.message.toString()
                                            .isNullOrEmpty()
                                    ) {
                                        val str =
                                            if (model.message.toString().contains("ADMINCOPY")) {
                                                model.message?.split("ADMINCOPY")?.get(1)
                                            } else {
                                                model.message
                                            }

                                        tvActionValues = str ?: ""

                                    } else if (model.scanType != null && model.scanType != -1) {
                                        tvActionValues = "${
                                            getScanType(
                                                context,
                                                model.scanType
                                            )
                                        } : ${model.message}"
                                    } else {
                                        tvActionValues = ""
                                    }
                                }

                                "SYSTEM_EMAIL" -> {
                                    tvSystemEmail = model.name.toString()
                                    tvBookingStatus =
                                        context.getCommunicationEmailActionType(model.actionType.toString())
                                    image = R.drawable.ic_email
                                    tvActionValues = ""

                                }

                                "SYSTEM_SMS" -> {
                                    tvSystemEmail = model.name.toString()
                                    tvBookingStatus =
                                        context.getCommunicationSMSActionType(model.actionType.toString())
                                    image = R.drawable.ic_sms
                                    tvActionValues = ""

                                }

                                "AGENT_ACTION_TYPE" -> {
                                    val icon = context.getAgentActionTypeImage1(
                                        model.actionType.toString(),
                                        model.actionCreatorRole
                                    )
                                    tvSystemEmail = context.getAgentActionName(
                                        model.actionType.toString(),
                                        model.actionCreatorRole, model.name ?: ""
                                    )
                                    tvBookingStatus =
                                        context.getCommunicationSMSActionType(model.actionType.toString())
                                    image = icon
                                    tvActionValues = ""
                                }
                            }

                            ActivityFeedItem(
                                tvSystemEmail,
                                tvBookingStatus,
                                image,
                                tvActionValues,
                                tvDate
                            )
                        }
                    }
                }

                actionUpdateLogsState.response = null
                actionUpdateLogsState.error = null
                actionUpdateLogsState.isLoading = false
            }

            Divider(
                thickness = 0.5.dp, // Adjust this value for thickness
                modifier = Modifier
                    .constrainAs(dividerLineRef) {
                        bottom.linkTo(notesBox.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth(),
                color = returnLabelAirPurpleColor(isDarkTheme)
            )

            Box(
                modifier = Modifier
                    .constrainAs(notesBox) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(60.dp)
                    .fillMaxWidth()
                    .clickable {
                        //  onStartDateClick()
                    }
                    .padding(start = 4.dp, end = 4.dp, top = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.width(8.dp))

                    TextField(
                        value = bookingRef,
                        onValueChange = { bookingRef = it },
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

                            }, // make the image background transparent
                        contentScale = ContentScale.Inside, // scale the image to fill the Box
                        //    colorFilter = ColorFilter.tint(returnLabelDarkBlueColor(isDarkTheme))
                    )

                }
            }
        }
    }

    if (bookingNotesState.error != null) {
        errorMessage = communicationLogState.error ?: context.getString(R.string.no_internet)
        snackBarShowFlag = true
    }
    if (bookingNotesState.isLoading) {
        showLoader = true
        LoaderDialog(showDialog = showLoader)
    }

}

@Composable
private fun setDataForCommunicationLogs(
    response: CCDGetCommunicationLogResponse?,
    actionUpdate: MutableState<ArrayList<CustomActionUpdateModel>>
) {
    response?.let {
        for (obj in it.communicationLog) {
            if (obj.communicationType == 1) {
                actionUpdate.value.add(
                    CustomActionUpdateModel(
                        name = stringResource(R.string.system_email),
                        message = "",
                        dateTimeUTC = obj.dateTimeUtcEmailSent,
                        actionType = obj.emailType,
                        from = "SYSTEM_EMAIL"
                    )
                )
            } else {
                actionUpdate.value.add(
                    CustomActionUpdateModel(
                        name = stringResource(R.string.system_sms),
                        message = "",
                        dateTimeUTC = obj.dateTimeUtcEmailSent,
                        actionType = obj.smsType,
                        from = "SYSTEM_SMS"
                    )
                )
            }
        }
    }

    actionUpdate.value.sortByDescending { it.dateTimeUTC }

}

@Composable
private fun setDataForNotes(
    getBookingNotesByReference: GetBookingNotesByReference?,
    actionUpdate: MutableState<ArrayList<CustomActionUpdateModel>>
) {
    getBookingNotesByReference?.let {
        for (obj in getBookingNotesByReference.bookingNote) {
            actionUpdate.value.add(
                CustomActionUpdateModel(
                    name = stringResource(
                        R.string.pName,
                        obj.user.firstName ?: "",
                        obj.user.lastName ?: ""
                    ),
                    message = obj.text,
                    dateTimeUTC = obj.dateTimeCreatedUTC,
                    actionType = 0,
                    from = "NOTES"
                )
            )
        }
    }
}

@Composable
private fun setDataForActionLogs(
    getActionUpdateLogsResponse: GetActionUpdateLogsResponse?,
    bookingDetails: BookingDetailsSingleton,
    actionUpdate: MutableState<ArrayList<CustomActionUpdateModel>>
) {
    var amountPaid = ""
    for (obj in bookingDetails.currentChampions!!) {
        getActionUpdateLogsResponse?.let {
            for (objAction in getActionUpdateLogsResponse.actionUpdates) {
                if (obj.championId == objAction.userId) {
                    var model = CustomActionUpdateModel(
                        name = stringResource(
                            id = R.string.pName,
                            obj.firstName ?: "",
                            obj.lastName ?: ""
                        ),
                        message = objAction.actionValues,
                        dateTimeUTC = objAction.dateTimeUTC,
                        actionType = objAction.actionType,
                        actionCreatorRole = -1,
                        scanType = objAction.scanType,
                        from = "ACTION_LOGS"
                    )
                    if (!actionUpdate.value.contains(model)) {
                        actionUpdate.value.add(
                            model
                        )
                    }
                }
            }
        }

    }

    for (objAction in getActionUpdateLogsResponse!!.bookingActionUpdates) {
        if (objAction.actionType == 13 ||
            objAction.actionType == 14 ||
            objAction.actionType == 15 ||
            objAction.actionType == 16 ||
            objAction.actionType == 17 ||
            objAction.actionType == 18 ||
            objAction.actionType == 19 ||
            objAction.actionType == 20
        ) {
            amountPaid = objAction.actionValue.toString()
        }
        var model = CustomActionUpdateModel(
            name = objAction.userNameuserName,
            message = objAction.actionValue,
            dateTimeUTC = objAction.dateTimeUTC,
            actionType = objAction.actionType,
            actionCreatorRole = objAction.actionCreatorRole,
            scanType = objAction.scanType,
            bookingReference = objAction.bookingReference,
            amountPaid = amountPaid,
            from = "AGENT_ACTION_TYPE",
        )
        actionUpdate.value.add(
            model
        )
    }
    actionUpdate.value.sortByDescending { it.dateTimeUTC }

}

