package com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.ops.airportr.common.AppActionValues
import com.ops.airportr.common.AppActionValues.Companion.DISTANCE_GREATER_THAN_75
import com.ops.airportr.common.AppActionValues.Companion.DISTANCE_LESS_THAN_75
import com.ops.airportr.common.AppConstants
import com.ops.airportr.common.AppConstants.UPDATE_ACCEPTANCE_LOCK
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.theme.air_red
import com.ops.airportr.common.theme.air_yellow
import com.ops.airportr.common.theme.badgeTextColor
import com.ops.airportr.common.theme.customEditTextColorDarkTheme
import com.ops.airportr.common.theme.customTextLabelSmallStyle
import com.ops.airportr.common.theme.editTextBorderStrockColor
import com.ops.airportr.common.theme.light_white
import com.ops.airportr.common.theme.white
import com.ops.airportr.common.utils.BookingDetailsSingleton
import com.ops.airportr.common.utils.abcTagBackGroundColor
import com.ops.airportr.common.utils.extension.checkForTimeInMillisToCheckTimeSlot
import com.ops.airportr.common.utils.extension.convertIntoDateTimeFormat
import com.ops.airportr.common.utils.extension.convertIntoRelativeDateForAcceptanceDetail
import com.ops.airportr.common.utils.extension.getAirlineLogo
import com.ops.airportr.common.utils.extension.getCurrentTimeStampIntoFormat
import com.ops.airportr.common.utils.extension.maskPhoneNumber
import com.ops.airportr.common.utils.extension.text2Html
import com.ops.airportr.common.utils.extension.toast
import com.ops.airportr.common.utils.extension.urlForAcceptance
import com.ops.airportr.common.utils.mapbox.GetTimeAndDistanceBtwTwoPoints
import com.ops.airportr.common.utils.overSizeBagTagBackGroundColor
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.common.utils.returnLabelAirPurpleColor
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.common.utils.translations.AppMessagesTranslation
import com.ops.airportr.domain.model.acceptance.UpdateAcceptanceParam
import com.ops.airportr.domain.model.bookingdetails.BookingJourneyDetail
import com.ops.airportr.domain.model.bookingdetails.Job
import com.ops.airportr.domain.model.updatejob.UpdateJobParam
import com.ops.airportr.ui.componts.LoaderDialog
import com.ops.airportr.ui.componts.SlideToStart
import com.ops.airportr.ui.componts.SnackbarDemo
import com.ops.airportr.ui.componts.Space
import com.ops.airportr.ui.componts.TickAnimation
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.helper.JobDetailApiHelper
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.helper.TimeLockAlert
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.helper.allPassengerBottomSheet
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.helper.calculateRemainingTimeAndDis
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.helper.conditionalAcceptanceBottomSheet
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.helper.getTimeAndDistanceFromDestination
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.helper.returnETABox
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.helper.timeLockInSideGeoLocationBottomSheet
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.helper.timeLockOutsideGeoLocationBottomSheet
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


var bookingDetails: BookingDetailsSingleton = BookingDetailsSingleton()

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun JobDetailsScreen(
    navController: NavController,
    jobObj: Job?,
    passengerName: String,
    viewModel: JobDetailsScreenViewModel = hiltViewModel(),
) {
    val activity = LocalContext.current as? Activity
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()
    val scrollState = rememberScrollState()
    var getTimeAndDistanceBtwTwoPoints: GetTimeAndDistanceBtwTwoPoints? = null

    var snackBarShowFlag by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showLoader by remember { mutableStateOf(false) }


    var detailModel by remember { mutableStateOf(BookingJourneyDetail()) }
    var timeDate by remember { mutableStateOf("") }
    var etaStart by remember { mutableStateOf(0L) }
    var phoneNumberFromBookingDetail by remember { mutableStateOf("") }

    var showTickStartJob by remember { mutableStateOf(false) }
    var showSliderStartJob by remember { mutableStateOf(true) }

    var showTickStartAcceptance by remember { mutableStateOf(false) }
    var showSliderStartAcceptance by remember { mutableStateOf(true) }
    var isAcceptanceStarted by remember { mutableStateOf(false) }
    var isConditionalFlag by remember { mutableStateOf(false) }
    var showSnackBarCheckForZeroBagCount by remember { mutableStateOf(false) }
    var showSnackBarCheckForJobStarted by remember { mutableStateOf(false) }
    var snackBarMessage by remember { mutableStateOf("") }
    var bottomSheetShowCases by remember { mutableStateOf(-1) }

    //ETA
    var etaText by remember { mutableStateOf("") } // Initial text
    var etaBackGroundColor by remember { mutableStateOf(Color.Black) } // Initial text
    var etaBackTextColor by remember { mutableStateOf(Color.Black) } // Initial text
    var timeDuration by remember { mutableStateOf(0.0) } // Initial text
    var etaLoader by remember { mutableStateOf(false) } // Initial text
    var etaBoxColorFlag by remember { mutableStateOf(false) } // Initial text
    var etaCounterFlag by remember { mutableStateOf(false) } // Initial text
    var isInCollectionTimeKey by remember { mutableStateOf(false) } // Initial text
    var timeLockInSideGeoLocation by remember { mutableStateOf(false) } // Initial text
    var timeLockOutsideGeoLocation by remember { mutableStateOf(false) } // Initial text
    var callTrackingApi by remember { mutableStateOf(true) } // Initial text
    var callCount by remember { mutableStateOf(0) } // Initial text

    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)


    // Trigger reappearance logic
    LaunchedEffect(showTickStartJob) {
        if (showTickStartJob) {
            delay(1000) // Duration of tick animation
            showTickStartJob = false
            bookingDetails.init()
            if (bagCount(bookingDetails) == 0) {
                showSnackBarCheckForZeroBagCount = true
            } else {
                if (AppApplication.sessionManager.userDetails.userId ==
                    AppApplication.sessionManager.currentChampId
                ) {
                    etaStart = AppApplication.sessionManager.duration

                    JobDetailApiHelper().smsDeviceData(
                        context,
                        viewModel,
                        bookingDetails
                    )
                } else {
                    bottomSheetShowCases = 1
                    sheetState.show()

                }
            }
        }
    }

    // Trigger reappearance logic
    LaunchedEffect(showTickStartAcceptance) {
        if (showTickStartAcceptance) {
            delay(1500) // Duration of tick animation
            showTickStartAcceptance = false

            AppApplication.sessionManager.saveTrackingApi(false)
            var endTime = ""
            for (obj in bookingDetails.bookingJourneyDetail?.jobs!!) {
                if (obj.jobType!! == 1) {
                    endTime = obj.endDueDateTimeUTC.toString()
                }
            }
            isInCollectionTimeKey =
                bookingDetails.bookingJourneyDetail?.collectionDateTimeUTC?.let {
                    checkForTimeInMillisToCheckTimeSlot(
                        it, endTime
                    )
                } == true

            if (!isInCollectionTimeKey) {
                if (AppApplication.sessionManager.distance <= 75) {
                    timeLockInSideGeoLocation = true
                    timeLockOutsideGeoLocation = false
                } else {
                    timeLockOutsideGeoLocation = true
                    timeLockInSideGeoLocation = false
                }
            }

            delay(1000) // Delay before showing the slider again
            showSliderStartAcceptance = true
        }
    }

    LaunchedEffect(isConditionalFlag) {
        if (isConditionalFlag) {
            bottomSheetShowCases = 2
            sheetState.show()
        }
    }


    LaunchedEffect(isInCollectionTimeKey) {
        if (isInCollectionTimeKey == true) {
            bottomSheetShowCases = 3
            sheetState.show()
        }
    }

    LaunchedEffect(timeLockInSideGeoLocation) {
        if (timeLockInSideGeoLocation) {
            bottomSheetShowCases = 4
            sheetState.show()
        }
    }


    LaunchedEffect(timeLockOutsideGeoLocation) {
        if (timeLockOutsideGeoLocation) {
            bottomSheetShowCases = 5
            sheetState.show()
        }
    }


    val acceptanceLockState = viewModel.stateAcceptance.value
    val smsDeviceDataState = viewModel.smsDeviceData.value
    val updateJobDataState = viewModel.updateJob.value
    val actionUpdateLogsState = viewModel.stateActonUpdateLogs.value
    val storeTrackingDataState = viewModel.storeTrackingData.value
    val applyActionUpdateNewState = viewModel.applyActionUpdateNew.value
    val trackingUserState = viewModel.trackingUser.value

    //TODO: On Resume
    //TODO:========================================================
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    LaunchedEffect(currentBackStackEntry.value?.lifecycle) {
        currentBackStackEntry.value?.lifecycle?.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                bookingDetails = BookingDetailsSingleton()
                bookingDetails.init()
                detailModel = bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)!!
                JobDetailApiHelper().updateAcceptanceLock(
                    context,
                    viewModel,
                    bookingDetails
                )
            }
        })
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(
                        when (bottomSheetShowCases) {
                            4, 5 -> 400.dp
                            3 -> 250.dp // Assuming you meant to have a height for case 3
                            else -> 200.dp
                        }
                    )
                    .padding(6.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                if (bottomSheetShowCases == 1) {
                    TimeLockAlert(
                        onCancelClick = {
                            // Handle the "Cancel" button click
                            coroutineScope.launch {
                                sheetState.hide()
                            }
                        },
                        onAssignClick = {
                            // Handle the "Assign" button click
                            println("Assign button clicked")
                            coroutineScope.launch {
                                sheetState.hide()
                                if (returnChampionExistOrNot()) {
                                    JobDetailApiHelper().updateJob(
                                        context, viewModel, bookingDetails
                                    )

                                } else {
                                    JobDetailApiHelper().getActionUpdateLog(
                                        context, viewModel, bookingDetails
                                    )
                                }
                            }

                        },
                        isDarkTheme
                    )

                } else if (bottomSheetShowCases == 2) {
                    conditionalAcceptanceBottomSheet(onAssignClick = {
                        // Handle the "Assign" button click
                        bottomSheetShowCases = -1
                        coroutineScope.launch {
                            sheetState.hide()
                        }
                    }, isDarkTheme)
                } else if (bottomSheetShowCases == 3) {
                    allPassengerBottomSheet(
                        onAssignClick = {
                            coroutineScope.launch {
                                sheetState.hide()
                            }
                            isInCollectionTimeKey = false
                            getTimeAndDistanceBtwTwoPoints?.onDestroy()
                            callTrackingApi = false
                            JobDetailApiHelper().applyActionUpdateNewData(
                                context,
                                viewModel,
                                bookingDetails
                            )

                        },
                        isDarkTheme
                    )
                } else if (bottomSheetShowCases == 4) {
                    timeLockInSideGeoLocationBottomSheet(
                        onAssignClick = {
                            coroutineScope.launch {
                                sheetState.hide()
                            }
                            isInCollectionTimeKey = true
                            timeLockInSideGeoLocation = false
                        },
                        onCancelClick = {
                            coroutineScope.launch {
                                sheetState.hide()
                            }
                            timeLockInSideGeoLocation = false
                        },
                        isDarkTheme
                    )
                } else if (bottomSheetShowCases == 5) {
                    timeLockOutsideGeoLocationBottomSheet(
                        onAssignClick = {
                            coroutineScope.launch {
                                sheetState.hide()
                            }
                            timeLockOutsideGeoLocation = false
                        },
                        onCancelClick = {
                            coroutineScope.launch {
                                sheetState.hide()
                            }
                            timeLockOutsideGeoLocation = false
                        },
                        isDarkTheme
                    )
                }

            }
        },
        sheetShape = MaterialTheme.shapes.large.copy(
            topEnd = CornerSize(20.dp),
            topStart = CornerSize(20.dp)
        ), // Rounded top corners only
        sheetBackgroundColor = returnBackGroundColor(isDarkTheme) // Optional: custom background color
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(returnBackGroundColor(isDarkTheme))
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 12.dp, end = 12.dp, top = 12.dp)
            ) {
                val (scrollScreen, slideToStartJobBoxRef) = createRefs()
                if (acceptanceLockState.response != null && acceptanceLockState.response?.acceptanceLockData != null) {
                    Box(
                        modifier = Modifier
                            .constrainAs(scrollScreen) {
                                top.linkTo(parent.top)
                                bottom.linkTo(slideToStartJobBoxRef.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                                height = Dimension.fillToConstraints
                            }
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                            .background(returnBackGroundColor(isDarkTheme))
                    ) {

                        bookingDetails = BookingDetailsSingleton()
                        bookingDetails.init()
                        detailModel =
                            bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)!!

                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            val (userName, bookingReference, tagsBox, bottomBoxRef, sildeToStartJobBoxRef) = createRefs()
                            if (detailModel.passengers.isNullOrEmpty()) {
                                Text(
                                    modifier = Modifier
                                        .constrainAs(userName) {
                                            top.linkTo(parent.top)
                                            start.linkTo(parent.start)
                                        }
                                        .padding(top = 5.dp)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Start,
                                    text = stringResource(
                                        id = R.string.pName,
                                        detailModel.bookingContact?.firstName
                                            ?: "",
                                        detailModel.bookingContact?.lastName
                                            ?: ""
                                    ),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 18.sp,
                                    color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                                )
                            } else {
                                Text(
                                    modifier = Modifier
                                        .constrainAs(userName) {
                                            top.linkTo(parent.top)
                                            start.linkTo(parent.start)
                                        }
                                        .padding(top = 5.dp)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Start,
                                    text = stringResource(
                                        id = R.string.pName,
                                        detailModel.passengers[0].firstName
                                            ?: "",
                                        detailModel.passengers[0].lastName
                                            ?: ""
                                    ),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 18.sp,
                                    color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                                )
                            }

                            Text(
                                modifier = Modifier
                                    .constrainAs(bookingReference) {
                                        top.linkTo(userName.bottom)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                        width = Dimension.fillToConstraints

                                    }
                                    .padding(top = 5.dp),
                                textAlign = TextAlign.Start,
                                text = detailModel.bookingReference ?: "",
                                style = MaterialTheme.typography.labelSmall, // Use your custom text style here
                                fontSize = 14.sp,
                                color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                            )
                            Row(
                                modifier = Modifier
                                    .constrainAs(tagsBox) {
                                        top.linkTo(bookingReference.bottom)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                        width = Dimension.fillToConstraints
                                    }
                                    .padding(top = 10.dp),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(5.dp)) // Apply rounded corners
                                        .background(abcTagBackGroundColor(isDarkTheme)) // Set background color
                                        .border(
                                            0.dp,
                                            abcTagBackGroundColor(isDarkTheme),
                                            RoundedCornerShape(5.dp)
                                        )
                                        .padding(
                                            top = 6.dp,
                                            bottom = 6.dp,
                                            start = 10.dp,
                                            end = 10.dp
                                        )

                                ) {
                                    val isAbcBooking =
                                        detailModel.addOnProductsOverview?.isEmpty() != true &&
                                                detailModel.addOnProductsOverview.get(
                                                    0
                                                ).addOnProductType.equals(
                                                    1
                                                )
                                    Text(
                                        text = if (isAbcBooking) "ABC" else "CCD",
                                        color = if (isDarkTheme) badgeTextColor else customEditTextColorDarkTheme, // Text color
                                        style = MaterialTheme.typography.labelSmall, // Use your custom text style
                                        fontSize = 10.sp,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                                Space(height = 0, width = 5)

                                if (detailModel.isCancelled == true) {

                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp)) // Apply rounded corners
                                            .background(air_red) // Set background color
                                            .border(0.dp, air_red, RoundedCornerShape(5.dp))
                                            .padding(6.dp)

                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.cancelled),
                                            color = white, // Text color
                                            style = customTextLabelSmallStyle, // Use your custom text style
                                            fontSize = 10.sp,
                                            modifier = Modifier.align(Alignment.Center)
                                        )
                                    }
                                    Space(height = 0, width = 5)
                                }
                                if (detailModel.jobs?.get(0)?.jobType == AppActionValues.REPATRAITION_ACTION_VALUE) {

                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp)) // Apply rounded corners
                                            .background(air_purple) // Set background color
                                            .border(0.dp, air_purple, RoundedCornerShape(5.dp))
                                            .padding(6.dp)

                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.repatriated),
                                            color = white, // Text color
                                            style = customTextLabelSmallStyle, // Use your custom text style
                                            fontSize = 10.sp,
                                            modifier = Modifier.align(Alignment.Center)
                                        )
                                    }
                                    Space(height = 0, width = 5)
                                }

                                if (detailModel.hasOversizedBag) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp)) // Apply rounded corners
                                            .background(overSizeBagTagBackGroundColor(isDarkTheme)) // Set background color
                                            .border(
                                                0.dp,
                                                overSizeBagTagBackGroundColor(isDarkTheme),
                                                RoundedCornerShape(5.dp)
                                            )
                                            .padding(6.dp)

                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.oversize),
                                            color = if (isDarkTheme) badgeTextColor else customEditTextColorDarkTheme, // Text color
                                            style = MaterialTheme.typography.labelSmall, // Use your custom text style
                                            fontSize = 10.sp,
                                            modifier = Modifier.align(Alignment.Center)
                                        )
                                    }
                                    Space(height = 0, width = 5)
                                }


                                if (detailModel.isConditionalAcceptance == true) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp)) // Apply rounded corners
                                            .background(air_yellow) // Set background color
                                            .border(0.dp, air_yellow, RoundedCornerShape(5.dp))
                                            .height(dimensionResource(id = R.dimen._9sdp))
                                            .width(dimensionResource(id = R.dimen._9sdp))
                                    )
                                }

                            }

                            Column(modifier = Modifier.constrainAs(bottomBoxRef) {
                                top.linkTo(tagsBox.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(sildeToStartJobBoxRef.top)
                                width = Dimension.fillToConstraints
                            })
                            {

                                //ETA
                                Column {
                                    Divider(
                                        modifier = Modifier
                                            .padding(top = 10.dp),
                                        thickness = 0.5.dp, // Adjust this value for thickness
                                        color = if (isDarkTheme) editTextBorderStrockColor else light_white
                                    )
                                    Text(
                                        modifier = Modifier
                                            .padding(top = 5.dp)
                                            .fillMaxWidth(),
                                        textAlign = TextAlign.Start,
                                        text = stringResource(id = R.string.expected_time),
                                        style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                        fontSize = 16.sp,
                                        color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                                    )
                                    timeDate =
                                        acceptanceLockState.response?.acceptanceLockData?.customerMessage?.let { it1 ->
                                            AppMessagesTranslation(context).translateErrorMessagesString(
                                                it1
                                            ).text2Html().toString()
                                        }.toString()


                                    for (obj in bookingDetails.bookingJourneyDetail?.jobs!!) {
                                        if (obj.jobType == 1) {
                                            timeDate =
                                                stringResource(
                                                    id = R.string.date_data,
                                                    obj.startDueDateTimeUTC?.convertIntoDateTimeFormat()
                                                        ?: "",
                                                    obj.endDueDateTimeUTC?.convertIntoDateTimeFormat()
                                                        ?: ""
                                                )

                                        }
                                    }


                                    Text(
                                        modifier = Modifier
                                            .padding(top = 5.dp)
                                            .fillMaxWidth(),
                                        textAlign = TextAlign.Start,
                                        text = timeDate,
                                        style = MaterialTheme.typography.labelSmall, // Use your custom text style here
                                        fontSize = 12.sp,
                                        color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                                    )

                                    Space(height = 5, width = 5)
                                    returnETABox(
                                        eta = if (etaText == "") stringResource(id = R.string.eta) else etaText,
                                        isDarkTheme = isDarkTheme,
                                        etaBackGroundColor,
                                        etaBackTextColor
                                    )
                                    if (etaCounterFlag) {
                                        Log.wtf("ETA", etaText.toString())
                                    }

                                    Divider(
                                        modifier = Modifier
                                            .padding(top = 10.dp),
                                        thickness = 0.5.dp, // Adjust this value for thickness
                                        color = if (isDarkTheme) editTextBorderStrockColor else light_white
                                    )
                                }

                                //Location
                                Column {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(
                                                modifier = Modifier
                                                    .padding(top = 5.dp, end = 10.dp),
                                                textAlign = TextAlign.Start,
                                                text = stringResource(id = R.string.location),
                                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                                fontSize = 16.sp,
                                                color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                                            )
                                            Text(
                                                modifier = Modifier
                                                    .padding(top = 5.dp),
                                                textAlign = TextAlign.Start,
                                                text = bookingDetails.bookingJourneyDetail?.collectionLocation?.addressLine1
                                                    ?: "",
                                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                                fontSize = 12.sp,
                                                color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                            )
                                            Text(
                                                modifier = Modifier
                                                    .padding(top = 5.dp),
                                                textAlign = TextAlign.Start,
                                                text = bookingDetails.bookingJourneyDetail?.collectionLocation?.addressTown + ", " +
                                                        bookingDetails.bookingJourneyDetail?.collectionLocation?.addressLine2,
                                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                                fontSize = 12.sp,
                                                color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                            )
                                            Text(
                                                modifier = Modifier
                                                    .padding(top = 5.dp),
                                                textAlign = TextAlign.Start,
                                                text = bookingDetails.bookingJourneyDetail?.collectionLocation?.addressPostCode
                                                    ?: "",
                                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                                fontSize = 12.sp,
                                                color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                            )
                                        }

                                        Image(
                                            painter = painterResource(id = R.drawable.location),
                                            contentDescription = null, // provide content description if needed
                                            modifier = Modifier
                                                .padding(start = 14.dp)
                                                .height(30.dp)
                                                .width(30.dp)
                                                .clickable {
                                                    //   navHostController.popBackStack()
                                                }, // make the image background transparent
                                            contentScale = ContentScale.Inside,
                                            colorFilter = ColorFilter.tint(
                                                returnLabelDarkBlueColor(
                                                    isDarkTheme
                                                )
                                            )// scale the image to fill the Box
                                        )
                                    }

                                    Divider(
                                        modifier = Modifier
                                            .padding(top = 10.dp),
                                        thickness = 0.5.dp, // Adjust this value for thickness
                                        color = if (isDarkTheme) editTextBorderStrockColor else light_white
                                    )
                                }

                                //Contact Driver
                                Column {

                                    val zeroIndexValueCheck =
                                        bookingDetails.bookingJourneyDetail?.bookingContact?.phoneNumber?.get(
                                            0
                                        )
                                    if (zeroIndexValueCheck == '0') {
                                        val number =
                                            bookingDetails.bookingJourneyDetail?.bookingContact?.phoneNumber?.substring(
                                                1
                                            )
                                        phoneNumberFromBookingDetail = stringResource(
                                            id = R.string.phonenumber,
                                            "+" + bookingDetails.bookingJourneyDetail?.bookingContact?.countryCode,
                                            number ?: ""
                                        ).toString()
                                    } else {
                                        val number =
                                            bookingDetails.bookingJourneyDetail?.bookingContact?.phoneNumber
                                                ?: ""
                                        phoneNumberFromBookingDetail = stringResource(
                                            id = R.string.phonenumber,
                                            "+" + bookingDetails.bookingJourneyDetail?.bookingContact?.countryCode,
                                            number
                                        ).toString()
                                    }

                                    Row(
                                        modifier = Modifier
                                            .height(dimensionResource(id = R.dimen._60sdp))
                                            .fillMaxSize(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(
                                                modifier = Modifier
                                                    .padding(top = 5.dp, end = 10.dp),
                                                textAlign = TextAlign.Start,
                                                text = stringResource(id = R.string.contact_customer),
                                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                                fontSize = 16.sp,
                                                color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                                            )
                                            Text(
                                                modifier = Modifier
                                                    .padding(top = 5.dp),
                                                textAlign = TextAlign.Start,
                                                text = maskPhoneNumber(phoneNumberFromBookingDetail),
                                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                                fontSize = 18.sp,
                                                color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                            )
                                        }

                                        Image(
                                            painter = painterResource(id = R.drawable.call),
                                            contentDescription = null, // provide content description if needed
                                            modifier = Modifier
                                                .padding(start = 14.dp)
                                                .height(30.dp)
                                                .width(30.dp)
                                                .clickable {
                                                    //   navHostController.popBackStack()
                                                }, // make the image background transparent
                                            contentScale = ContentScale.Inside,
                                            colorFilter = ColorFilter.tint(
                                                returnLabelDarkBlueColor(
                                                    isDarkTheme
                                                )
                                            )
                                            // scale the image to fill the Box
                                        )
                                        Image(
                                            painter = painterResource(id = R.drawable.message),
                                            contentDescription = null, // provide content description if needed
                                            modifier = Modifier
                                                .padding(start = 14.dp)
                                                .height(30.dp)
                                                .width(30.dp)
                                                .clickable {
                                                    //   navHostController.popBackStack()
                                                }, // make the image background transparent
                                            contentScale = ContentScale.Inside,
                                            colorFilter = ColorFilter.tint(
                                                returnLabelDarkBlueColor(
                                                    isDarkTheme
                                                )
                                            )// scale the image to fill the Box
                                        )
                                    }

                                    Divider(
                                        modifier = Modifier
                                            .padding(top = 10.dp),
                                        thickness = 0.5.dp, // Adjust this value for thickness
                                        color = if (isDarkTheme) editTextBorderStrockColor else light_white
                                    )
                                }

                                //BagTotal
                                Column {
                                    Text(
                                        modifier = Modifier
                                            .padding(top = 5.dp, end = 10.dp),
                                        textAlign = TextAlign.Start,
                                        text = stringResource(id = R.string.bags_count),
                                        style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                        fontSize = 16.sp,
                                        color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                                    )
                                    Text(
                                        modifier = Modifier
                                            .padding(top = 5.dp)
                                            .fillMaxWidth(),
                                        textAlign = TextAlign.Start,
                                        text = bagCount(bookingDetails).toString(),
                                        style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                        fontSize = 12.sp,
                                        color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                    )


                                    Divider(
                                        modifier = Modifier
                                            .padding(top = 10.dp),
                                        thickness = 0.5.dp, // Adjust this value for thickness
                                        color = if (isDarkTheme) editTextBorderStrockColor else light_white
                                    )
                                }

                                //FlightDetail
                                Column {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(
                                                modifier = Modifier
                                                    .padding(top = 5.dp, end = 10.dp),
                                                textAlign = TextAlign.Start,
                                                text = stringResource(id = R.string.flight_detail),
                                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                                fontSize = 16.sp,
                                                color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                                            )
                                            Text(
                                                modifier = Modifier
                                                    .padding(top = 5.dp)
                                                    .fillMaxWidth(),
                                                textAlign = TextAlign.Start,
                                                text = bookingDetails.bookingJourneyDetail?.flightStatus?.flightInfo?.requestFlightNumber
                                                    ?: "",
                                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                                fontSize = 12.sp,
                                                color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                            )


                                            Text(
                                                modifier = Modifier
                                                    .padding(top = 5.dp)
                                                    .fillMaxWidth(),
                                                textAlign = TextAlign.Start,
                                                text = stringResource(
                                                    id = R.string.departing_,
                                                    bookingDetails.bookingJourneyDetail?.flightStatus?.flightStatusDetails
                                                        ?.get(0)?.departureDateTimeDetails?.scheduledDateTimeLocal?.convertIntoRelativeDateForAcceptanceDetail(
                                                            context
                                                        ).toString(),
                                                    bookingDetails.bookingJourneyDetail?.flightStatus?.flightStatusDetails
                                                        ?.get(0)?.departureAirport?.airportCityName
                                                        ?: ""
                                                ),
                                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                                fontSize = 12.sp,
                                                color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                            )

                                        }
                                        val logo =
                                            bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(
                                                0
                                            )?.flightStatus?.flightStatusDetails?.get(
                                                0
                                            )?.operatingAirlineCode

                                        Image(
                                            painter = painterResource(
                                                id = context.getAirlineLogo(
                                                    logo ?: ""
                                                )
                                            ),
                                            contentDescription = null, // provide content description if needed
                                            modifier = Modifier
                                                .padding(start = 14.dp)
                                                .height(30.dp)
                                                .width(100.dp)
                                                .clickable {
                                                    //   navHostController.popBackStack()
                                                }, // make the image background transparent
                                            contentScale = ContentScale.Inside,
                                            colorFilter = ColorFilter.tint(
                                                returnLabelDarkBlueColor(
                                                    isDarkTheme
                                                )
                                            )
                                            // scale the image to fill the Box
                                        )
                                    }
                                    Divider(
                                        modifier = Modifier
                                            .padding(top = 10.dp),
                                        thickness = 0.5.dp, // Adjust this value for thickness
                                        color = if (isDarkTheme) editTextBorderStrockColor else light_white
                                    )
                                }

                                //Assign Driver
                                Column {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(
                                                modifier = Modifier
                                                    .padding(top = 5.dp, end = 10.dp),
                                                textAlign = TextAlign.Start,
                                                text = stringResource(id = R.string.assigned_driver),
                                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                                fontSize = 16.sp,
                                                color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                            )
                                            Text(
                                                modifier = Modifier
                                                    .padding(top = 5.dp),
                                                textAlign = TextAlign.Start,
                                                text = stringResource(id = R.string.tap_to_assign),
                                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                                fontSize = 14.sp,
                                                color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                                            )
                                            Text(
                                                modifier = Modifier
                                                    .padding(top = 5.dp),
                                                textAlign = TextAlign.Start,
                                                text = stringResource(id = R.string.tap_to_assign),
                                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                                fontSize = 12.sp,
                                                color = returnLabelAirPurpleColor(isDarkTheme), // Replace with your desired color
                                            )
                                            Text(
                                                modifier = Modifier
                                                    .padding(top = 5.dp),
                                                textAlign = TextAlign.Start,
                                                text = stringResource(id = R.string.cancel_booking),
                                                style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                                fontSize = 16.sp,
                                                color = returnLabelAirPurple100Color(isDarkTheme), // Replace with your desired color
                                            )
                                        }

                                        Image(
                                            painter = painterResource(id = R.drawable.ic_userpic),
                                            contentDescription = null, // provide content description if needed
                                            modifier = Modifier
                                                .padding(start = 14.dp)
                                                .height(50.dp)
                                                .width(50.dp)
                                                .clickable {
                                                    //   navHostController.popBackStack()
                                                }, // make the image background transparent
                                            contentScale = ContentScale.Inside,
                                            // scale the image to fill the Box
                                        )
                                    }

                                    Divider(
                                        modifier = Modifier
                                            .padding(top = 10.dp),
                                        thickness = 0.5.dp, // Adjust this value for thickness
                                        color = if (isDarkTheme) editTextBorderStrockColor else light_white
                                    )
                                }

                                Text(
                                    modifier = Modifier
                                        .padding(top = 5.dp)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    text = stringResource(id = R.string.view_booking_detail),
                                    style = MaterialTheme.typography.labelMedium, // Use your custom text style here
                                    fontSize = 16.sp,
                                    color = returnLabelAirPurpleColor(isDarkTheme), // Replace with your desired color
                                )
                            }
                        }

                    }
                }

                Box(modifier = Modifier
                    .constrainAs(slideToStartJobBoxRef) {
                        bottom.linkTo(parent.bottom)
                        top.linkTo(scrollScreen.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .height(100.dp)) {
                    if (!isAcceptanceStarted) {
                        if (showSliderStartJob) {
                            SlideToStart(
                                isDarkTheme,
                                onUnlockRequested = {
                                    showSliderStartJob = false
                                    showTickStartJob = true

                                },
                                stringResource(id = R.string.slide_to_start_job),
                                modifier = Modifier.align(Alignment.Center),
                                isAcceptanceStarted
                            )
                        }
                        if (showTickStartJob) {
                            TickAnimation(
                                isDarkTheme = isDarkTheme,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(8.dp)
                            )
                        }
                    } else {
                        if (showSliderStartAcceptance) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.slide_warning),
                                    contentDescription = null, // provide content description if needed
                                    modifier = Modifier
                                        .padding(start = 14.dp)
                                        .height(45.dp)
                                        .width(45.dp)
                                        .clickable {
                                            //   navHostController.popBackStack()
                                        }, // make the image background transparent
                                    contentScale = ContentScale.Inside,
                                )
                                Space(height = 10, width = 10)
                                SlideToStart(
                                    isDarkTheme,
                                    onUnlockRequested = {
                                        showSliderStartAcceptance = false
                                        showTickStartAcceptance = true
                                    },
                                    stringResource(id = R.string.start_acceptance),
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    isAcceptanceStarted
                                )
                            }

                        }
                        if (showTickStartAcceptance) {
                            TickAnimation(
                                isDarkTheme = isDarkTheme,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(8.dp)
                            )
                        }
                    }

                }


                if (showSnackBarCheckForZeroBagCount) {
                    snackBarMessage = stringResource(id = R.string.zero_bag)
                    SnackbarDemo(snackBarMessage)
                }

                if (showSnackBarCheckForJobStarted) {
                    snackBarMessage = stringResource(id = R.string.job_started)
                    SnackbarDemo(snackBarMessage)
                }

                if (etaBoxColorFlag) {
                    calculateRemainingTimeAndDis(
                        context,
                        timeDuration,
                        time = { time ->
                            etaText = time
                            if(callTrackingApi) {
                                JobDetailApiHelper().callTrackData(
                                    context,
                                    viewModel,
                                    timeDuration
                                )
                            }
                        },
                        textColorCode = { color ->
                            etaBackTextColor = color
                        },
                        backgroundColorCode = { color ->
                            etaBackGroundColor = color
                        })
                }
            }


            if (smsDeviceDataState.response != null && smsDeviceDataState.response?.responseStatus != null) {
                smsDeviceDataState.response = null
                smsDeviceDataState.error = null
                smsDeviceDataState.isLoading = false

                AppApplication.sessionManager.saveJobStartDateAndTime(
                    getCurrentTimeStampIntoFormat()
                )

                if (bookingDetails.bookingJourneyDetail?.isConditionalAcceptance == true
                ) {
                    isConditionalFlag = true

                } else {
                    showSnackBarCheckForJobStarted = true
                    AppApplication.sessionManager.saveActiveBookingDetails(bookingDetails.bookingDetailFromDb)
                    AppApplication.sessionManager.saveTrackingApi(true)
                    getTimeAndDistanceBtwTwoPoints = GetTimeAndDistanceBtwTwoPoints(activity)
                    if (getTimeAndDistanceBtwTwoPoints != null) {
                        getTimeAndDistanceFromDestination(
                            getTimeAndDistanceBtwTwoPoints!!,
                            onEtaDistanceDuration = { distance, duration ->
                                showSliderStartJob = true
                                isAcceptanceStarted = true
                                etaBoxColorFlag = true
                                timeDuration = duration


                            },
                            etaLoader = { flag ->
                                etaLoader = flag
                            }
                        )
                    }
                }

            }
            if (updateJobDataState.response != null && updateJobDataState.response?.responseStatus != null) {
                updateJobDataState.response = null
                updateJobDataState.error = null
                updateJobDataState.isLoading = false

                AppApplication.sessionManager.userDetails.userId?.let { it1 ->
                    AppApplication.sessionManager.saveCurrentChampionId(
                        it1
                    )
                }
                acceptanceLockState.isLoading = false
                acceptanceLockState.error = null
                acceptanceLockState.response = null
                viewModel.updateAcceptanceLock(
                    context.urlForAcceptance() + UPDATE_ACCEPTANCE_LOCK,
                    UpdateAcceptanceParam(
                        bookingDetails.bookingJourneyDetail?.bookingReference ?: ""
                    )
                )
            }
            if (actionUpdateLogsState.response != null && actionUpdateLogsState.response?.responseStatus != null) {
                actionUpdateLogsState.isLoading = false
                actionUpdateLogsState.error = null
                actionUpdateLogsState.response = null
                jobObj?.jobId?.let { it1 ->
                    AppApplication.sessionManager.userDetails.userId?.let { it2 ->
                        viewModel.updateJob(
                            context.urlForAcceptance() + AppConstants.UPDATE_JOB,
                            UpdateJobParam(
                                it2,
                                it1
                            )
                        )
                    }
                }

            }
            if (acceptanceLockState.error != null || updateJobDataState.error != null || smsDeviceDataState.error != null) {
                errorMessage = acceptanceLockState.error ?: context.getString(R.string.no_internet)
                snackBarShowFlag = true
            }
            if (storeTrackingDataState.error != null || storeTrackingDataState.error != null || storeTrackingDataState.error != null) {
                storeTrackingDataState.response = null
                storeTrackingDataState.error = null
                storeTrackingDataState.isLoading = false
            }
            if (applyActionUpdateNewState.error != null || applyActionUpdateNewState.response?.responseStatus != null) {
                applyActionUpdateNewState.response = null
                applyActionUpdateNewState.error = null
                applyActionUpdateNewState.isLoading = false
                callCount += 1
                JobDetailApiHelper().updateJobForTracking(
                    context,
                    viewModel,
                    bookingDetails,
                    70
                )
            }


            if (trackingUserState.error != null || trackingUserState.response?.responseStatus != null) {
                trackingUserState.response = null
                trackingUserState.error = null
                trackingUserState.isLoading = false

                callCount += 1
                if (callCount == 3) {
                    val distance = AppApplication.sessionManager.distance
//                    AmplitudeWork().driverTrackingEnd(
//                        bookingDetailFromDb!!,
//                        distance < 10,
//                        timeSlot
//                    )
                    AppApplication.sessionManager.removeActiveBookingDetail()
                    AppApplication.sessionManager.saveToastTimer(true)
                    AppApplication.sessionManager.saveDurationAndDistance(0L, 0L)
                    context.toast("Ready to GO to Next Screen")
                    Log.wtf("TOAST", "Ready to GO to Next Screen")
                    //   viewModel.updateTimeAndDistanceMapbox(0.0)

//                    val intent = Intent(requireContext(), AddPassengersAct::class.java)
//                    intent.putExtra("jobObj", jobObj)
//                    startActivityForResult(intent, 2)
                } else {
                    if (AppApplication.sessionManager.distance <= 75) {
                        JobDetailApiHelper().updateJobForTracking(
                            context,
                            viewModel,
                            bookingDetails,
                            DISTANCE_LESS_THAN_75
                        )
                    } else {
                        JobDetailApiHelper().updateJobForTracking(
                            context,
                            viewModel,
                            bookingDetails,
                            DISTANCE_GREATER_THAN_75
                        )
                    }
                }
            }


            if (acceptanceLockState.isLoading || smsDeviceDataState.isLoading || updateJobDataState.isLoading || etaLoader) {
                showLoader = true
                LoaderDialog(showDialog = showLoader)
            }


        }
    }
}


private fun bagCount(bookingDetails: BookingDetailsSingleton): Int {

    var noOfBags = 0
    var noOfChildItems = 0
    var noOfOverSizeItem = 0
    var totalBags = 0
    if (bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.passengers!!.size > 0) {
        for (obj in bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.passengers!!) {
            for (objLuggage in obj.passengerLuggage!!) {
                if (!objLuggage.isDowngraded) {
                    when (objLuggage.bagClassification) {
                        1 -> {
                            noOfBags++
                        }

                        2 -> {
                            noOfChildItems++
                        }

                        3 -> {
                            noOfOverSizeItem++
                        }
                    }
                }
            }
        }
        if (noOfBags == 0 && noOfChildItems == 0 && noOfOverSizeItem == 0) {
            for (obj in bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.virtualBagClassification!!) {
                when (obj.type) {
                    1 -> {
                        noOfBags = obj.count!!
                    }

                    2 -> {
                        noOfChildItems = obj.count!!
                    }

                    3 -> {
                        noOfOverSizeItem = obj.count!!
                    }
                }
            }
        }
    } else {
        for (obj in bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.virtualBagClassification!!) {
            when (obj.type) {
                1 -> {
                    noOfBags = obj.count!!
                }

                2 -> {
                    noOfChildItems = obj.count!!
                }

                3 -> {
                    noOfOverSizeItem = obj.count!!
                }
            }
        }
    }

    totalBags = noOfBags + noOfChildItems + noOfOverSizeItem

    return totalBags
}

private fun returnChampionExistOrNot(): Boolean {
    if (AppApplication.sessionManager.currentChampId.isNullOrEmpty()) {
        return true
    } else if (AppApplication.sessionManager.userDetails.userId !=
        AppApplication.sessionManager.currentChampId
    ) {
        return false
    } else {
        return true
    }
}




