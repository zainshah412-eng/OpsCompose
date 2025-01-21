package com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails

import android.app.Activity
import android.os.Build
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
import androidx.compose.foundation.layout.Spacer
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
import com.ops.airportr.BuildConfig
import com.ops.airportr.R
import com.ops.airportr.common.AppActionValues
import com.ops.airportr.common.AppConstants
import com.ops.airportr.common.AppConstants.SEND_DEVICE_DATA
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
import com.ops.airportr.common.utils.convertIntoDateTimeFormat
import com.ops.airportr.common.utils.convertIntoRelativeDateForAcceptanceDetail
import com.ops.airportr.common.utils.getAirlineLogo
import com.ops.airportr.common.utils.getCurrentTimeStampIntoFormat
import com.ops.airportr.common.utils.getNetworkType
import com.ops.airportr.common.utils.maskPhoneNumber
import com.ops.airportr.common.utils.overSizeBagTagBackGroundColor
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnETABackGroundBox
import com.ops.airportr.common.utils.returnJobsNumberCircleBackgroundOnJobInComplete
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.common.utils.returnLabelAirPurpleColor
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.common.utils.text2Html
import com.ops.airportr.common.utils.translations.AppMessagesTranslation
import com.ops.airportr.common.utils.urlForAcceptance
import com.ops.airportr.domain.model.acceptance.UpdateAcceptanceParam
import com.ops.airportr.domain.model.bookingdetails.BookingJourneyDetail
import com.ops.airportr.domain.model.bookingdetails.Job
import com.ops.airportr.domain.model.senddevicedata.SendDeviceDataParam
import com.ops.airportr.domain.model.updatejob.UpdateJobParam
import com.ops.airportr.domain.model.updatelogs.params.GetActionUpdateLogsParams
import com.ops.airportr.ui.componts.CustomButton
import com.ops.airportr.ui.componts.LoaderDialog
import com.ops.airportr.ui.componts.SlideToStart
import com.ops.airportr.ui.componts.SnackbarDemo
import com.ops.airportr.ui.componts.Space
import com.ops.airportr.ui.componts.TickAnimation
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

    var snackBarShowFlag by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showLoader by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }


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
    var snackBarMessage by remember {
        mutableStateOf("")
    }
    var bottomSheetShowCases by remember {
        mutableStateOf(-1)
    }
    val coroutineScope = rememberCoroutineScope()


    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)


    // Trigger reappearance logic
    LaunchedEffect(showTickStartJob) {
        Log.wtf("showTickStartJob", showTickStartJob.toString())
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

                    val sendDeviceDataParam = SendDeviceDataParam(
                        BuildConfig.VERSION_NAME,
                        bookingDetails.bookingJourneyDetail?.bookingReference,
                        getCurrentTimeStampIntoFormat(),
                        Build.VERSION.RELEASE ?: "Unknown",
                        "Android",
                        getNetworkType(context),
                        "Acceptance"
                    )

                    viewModel.smsDeviceData(
                        context.urlForAcceptance() + SEND_DEVICE_DATA,
                        sendDeviceDataParam
                    )
                } else {
                    bottomSheetShowCases = 1
                    sheetState.show()

                }
            }

            //  delay(1000) // Delay before showing the slider again

        }
    }

    // Trigger reappearance logic
    LaunchedEffect(showTickStartAcceptance) {
        Log.wtf("showTickStartAcceptance", showTickStartAcceptance.toString())
        if (showTickStartAcceptance) {
            delay(1500) // Duration of tick animation
            showTickStartAcceptance = false
            delay(1000) // Delay before showing the slider again
            showSliderStartAcceptance = true
        }
    }

    LaunchedEffect(isConditionalFlag) {
        Log.wtf("isConditionalFlag", isConditionalFlag.toString())
        if (isConditionalFlag) {
            bottomSheetShowCases = 2
            sheetState.show()
        }
    }

    val acceptanceLockState = viewModel.stateAcceptance.value
    val smsDeviceDataState = viewModel.smsDeviceData.value
    val updateJobDataState = viewModel.updateJob.value
    val actionUpdateLogsState = viewModel.stateActonUpdateLogs.value

    //TODO: On Resume
    //TODO:========================================================
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    LaunchedEffect(currentBackStackEntry.value?.lifecycle) {
        currentBackStackEntry.value?.lifecycle?.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                // viewModel.loadData() // Call your ViewModel function
                bookingDetails = BookingDetailsSingleton()
                bookingDetails.init()
                detailModel = bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)!!
                viewModel.updateAcceptanceLock(
                    context.urlForAcceptance() + UPDATE_ACCEPTANCE_LOCK,
                    UpdateAcceptanceParam(
                        bookingDetails.bookingJourneyDetail?.bookingReference ?: ""
                    )
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
                    .height(200.dp)
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
                                    var jobIdAgainstAcceptance = ""
                                    for (obj in bookingDetails.bookingJourneyDetail?.jobs!!) {
                                        if (obj.jobType == 1)
                                            jobIdAgainstAcceptance = obj.jobId.toString()
                                    }
                                    AppApplication.sessionManager.userDetails.userId?.let { it1 ->
                                        viewModel.updateJob(
                                            context.urlForAcceptance() + AppConstants.UPDATE_JOB,
                                            UpdateJobParam(
                                                it1,
                                                jobIdAgainstAcceptance
                                            )
                                        )
                                    }
                                } else {
                                    bookingDetails.bookingDetailFromDb?.bookingReference?.let { bookRef ->
                                        bookingDetails.bookingDetailFromDb!!.bookingJourneyDetails[0].bookingJourneyReference?.let { bookJourRef ->
                                            // viewModel.loadData() // Call your ViewModel function
                                            viewModel.getActionUpdateLog(
                                                context.urlForAcceptance() + AppConstants.GET_ACTION_UPDATE_LOG,
                                                GetActionUpdateLogsParams(
                                                    bookRef,
                                                    bookJourRef
                                                )
                                            )
                                        }
                                    }
                                }
                            }

                        },
                        isDarkTheme,

                        )

                } else if (bottomSheetShowCases == 2) {
                    conditionalAcceptanceBottomSheet(onAssignClick = {
                        // Handle the "Assign" button click
                        bottomSheetShowCases = -1
                        coroutineScope.launch {
                            sheetState.hide()
                        }
                    }, isDarkTheme)
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
                                        text = timeDate.toString(),
                                        style = MaterialTheme.typography.labelSmall, // Use your custom text style here
                                        fontSize = 12.sp,
                                        color = returnLabelDarkBlueColor(isDarkTheme), // Replace with your desired color
                                    )

                                    Space(height = 5, width = 5)
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp)) // Apply rounded corners
                                            .background(returnETABackGroundBox(isDarkTheme)) // Set background color
                                            .border(
                                                0.dp,
                                                returnETABackGroundBox(isDarkTheme),
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
                                            detailModel.addOnProductsOverview?.isEmpty() != true
                                                    && detailModel.addOnProductsOverview.get(
                                                0
                                            ).addOnProductType == 1
                                        Text(
                                            text = stringResource(id = R.string.eta),
                                            color = if (isDarkTheme) badgeTextColor else customEditTextColorDarkTheme, // Text color
                                            style = MaterialTheme.typography.labelSmall, // Use your custom text style
                                            fontSize = 10.sp,
                                            modifier = Modifier.align(Alignment.Center)
                                        )
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
                                    stringResource(id = R.string.slide_to_start_job),
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
            }


            if (smsDeviceDataState.response != null && smsDeviceDataState.response?.responseStatus != null) {
                smsDeviceDataState.response = null
                smsDeviceDataState.error = null
                smsDeviceDataState.isLoading = false

                AppApplication.sessionManager.saveJobStartDateAndTime(
                    getCurrentTimeStampIntoFormat()
                )
                showSliderStartJob = true
                isAcceptanceStarted = true
                if (bookingDetails.bookingJourneyDetail?.isConditionalAcceptance == true
                ) {
                    isConditionalFlag = true

                } else {
                    showSnackBarCheckForJobStarted = true
                    AppApplication.sessionManager.saveActiveBookingDetails(bookingDetails.bookingDetailFromDb)
                    AppApplication.sessionManager.saveTrackingApi(true)
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
            if (acceptanceLockState.isLoading || smsDeviceDataState.isLoading || updateJobDataState.isLoading) {
                showLoader = true
                LoaderDialog(showDialog = showLoader)
            }
        }
    }
}


@Composable
fun TimeLockAlert(
    onCancelClick: () -> Unit,
    onAssignClick: () -> Unit,
    isDarkTheme: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen._14sdp))
    ) {
        // Alert Title
        Text(
            text = stringResource(id = R.string.assign_to_yourself),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen._28sdp),
                    bottom = dimensionResource(id = R.dimen._10sdp)
                ),
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            color = returnLabelDarkBlueColor(isDarkTheme)
        )


        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._20sdp)))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Reset Button

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 5.dp),

                ) {
                CustomButton(
                    name = stringResource(id = R.string.no_cancel),
                    onButtonClick = {
                        onCancelClick()
                    },
                    paddingTop = 10,
                    paddingHorizontal = 0,
                    modifier = Modifier.height(60.dp),
                    containerColor = returnJobsNumberCircleBackgroundOnJobInComplete(isDarkTheme),
                    textColor = returnLabelAirPurpleColor(isDarkTheme),
                    isEnabled = true,
                    focusedElevation = 0,
                    defaultElevation = 0,
                )
            }
            Space(height = 10, width = 10)
            // Apply Button
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 5.dp)
            ) {
                CustomButton(
                    name = stringResource(id = R.string.yes_assign),
                    onButtonClick = {
                        onAssignClick()
                    },
                    paddingTop = 10,
                    paddingHorizontal = 0,
                    modifier = Modifier.height(60.dp),
                    containerColor = returnLabelAirPurpleColor(isDarkTheme),
                    textColor = white,
                    isEnabled = true
                )
            }
        }

    }
}

@Composable
fun conditionalAcceptanceBottomSheet(
    onAssignClick: () -> Unit,
    isDarkTheme: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen._14sdp))
    ) {
        // Alert Title
        Text(
            text = stringResource(id = R.string.reminder),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen._5sdp),
                    bottom = dimensionResource(id = R.dimen._10sdp)
                ),
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            color = returnLabelDarkBlueColor(isDarkTheme)
        )


        Text(
            text = stringResource(id = R.string.this_is_a_conditional_acceptance_booking),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen._5sdp),
                    bottom = dimensionResource(id = R.dimen._10sdp)
                ),
            textAlign = TextAlign.Start,
            color = returnLabelDarkBlueColor(isDarkTheme)
        )


        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._20sdp)))


        Box(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 5.dp),

            ) {
            CustomButton(
                name = stringResource(id = R.string.alert_passenger_button),
                onButtonClick = {
                    onAssignClick()
                },
                paddingTop = 10,
                paddingHorizontal = 0,
                modifier = Modifier.height(60.dp),
                containerColor = returnLabelAirPurpleColor(isDarkTheme),
                textColor = white,
                isEnabled = true,
                focusedElevation = 0,
                defaultElevation = 0,
            )
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