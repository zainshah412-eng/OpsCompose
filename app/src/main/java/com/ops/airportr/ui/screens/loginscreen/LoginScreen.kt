package com.ops.airportr.ui.screens.loginscreen

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ops.airportr.AppApplication
import com.ops.airportr.BuildConfig
import com.ops.airportr.R
import com.ops.airportr.common.AppConstants
import com.ops.airportr.common.AppConstants.GET_CURRENT_USER_API
import com.ops.airportr.common.theme.air_awesome_purple_200
import com.ops.airportr.common.theme.air_orange_dark
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.theme.air_purple_awesome_light
import com.ops.airportr.common.theme.customTextLabelBoldStyle
import com.ops.airportr.common.theme.customTextLabelStyle
import com.ops.airportr.common.theme.dark_blue
import com.ops.airportr.common.theme.fonts
import com.ops.airportr.common.theme.grey
import com.ops.airportr.common.theme.light_orange_new
import com.ops.airportr.common.theme.purple_100
import com.ops.airportr.common.theme.white
import com.ops.airportr.common.utils.changeLanguage
import com.ops.airportr.common.utils.getDeviceUUID
import com.ops.airportr.common.utils.getNetworkType
import com.ops.airportr.common.utils.isCameraPermissionAllowed
import com.ops.airportr.common.utils.isLocationPermissionAllowed
import com.ops.airportr.common.utils.isValidEmail
import com.ops.airportr.common.utils.moveOnNewScreen
import com.ops.airportr.domain.model.BaseUrl
import com.ops.airportr.domain.model.language.LanguageListItemModel
import com.ops.airportr.domain.model.login.logincred.LoginCred
import com.ops.airportr.route.Screen
import com.ops.airportr.ui.componts.BackPressHandler
import com.ops.airportr.ui.componts.CustomButton
import com.ops.airportr.ui.componts.LoaderDialog
import com.ops.airportr.ui.componts.SnackbarDemo
import com.ops.airportr.ui.componts.Space
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navHostController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.resetAuthError()
    }
    val activity = LocalContext.current as? Activity
    BackPressHandler(activity) {
        if (!navHostController.popBackStack()) {
            // Finish the activity to close the app
            activity?.finish()
        }
    }
    var loginCredEmail by remember { mutableStateOf("") }
    var loginCredPassword by remember { mutableStateOf("") }
    var emailId by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var emailError by rememberSaveable { mutableStateOf(false) }
    var passwordError by rememberSaveable { mutableStateOf(false) }
    var snackBarShowFlag by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }


    val context = LocalContext.current
    val outlinedTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = purple_100,
        unfocusedBorderColor = Color.Gray,
        cursorColor = dark_blue,
        textColor = dark_blue,
        focusedLabelColor = dark_blue,
        unfocusedLabelColor = Color.Gray
    )

    var selectedLanguage by remember {
        mutableStateOf(
            LanguageListItemModel(
                languageName = when (AppApplication.sessionManager.appLanguage ?: "en") {
                    "en" -> "English"
                    "de" -> "Deutsch"
                    else -> "Français"
                },
                flag = when (AppApplication.sessionManager.appLanguage ?: "en") {
                    "en" -> R.drawable.england
                    "de" -> R.drawable.germany
                    else -> R.drawable.france
                }
            )
        )
    }

    val listLanguageListItemModel = listOf(
        LanguageListItemModel("English", R.drawable.england),
        LanguageListItemModel("Deutsch", R.drawable.germany),
        LanguageListItemModel("Français", R.drawable.france)
    )

    LaunchedEffect(AppApplication.sessionManager.appLanguage) {
        val appLanguage = AppApplication.sessionManager.appLanguage ?: "en"
        when (appLanguage) {
            "en" -> {
                selectedLanguage = LanguageListItemModel("English", R.drawable.england)
            }

            "de" -> {
                selectedLanguage = LanguageListItemModel("Deutsch", R.drawable.germany)
            }

            else -> {
                selectedLanguage = LanguageListItemModel("Français", R.drawable.france)
            }
        }
    }
    var showLoader by remember { mutableStateOf(false) }
    val state = viewModel.state.value
    val stateUserDetail = viewModel.stateUserDetail.value
  //  val stateRegisterDevice = viewModel.stateRegisterDevice.value

    if (state.loginResponse?.accessToken != "" && state.loginResponse?.accessToken != null) {
        if (!AppApplication.sessionManager.getBiometricStatus) {
            var loginCredModal = LoginCred(loginCredEmail, loginCredPassword)
            AppApplication.sessionManager.saveLoginCred(loginCredModal)
        }
        AppApplication.sessionManager.createLoginSession(state.loginResponse!!)
        state.loginResponse = null
        state.error = null
        state.isLoading = false
        showLoader = false
        viewModel.getUserDetail(
            AppApplication.sessionManager.baseUrl?.url + GET_CURRENT_USER_API
        )
    }

    if (stateUserDetail.userDetailResponse != null) {
        stateUserDetail.userDetailResponse?.user?.let { it1 ->
            AppApplication.sessionManager.saveUserDetails(
                it1
            )
        }
        AppApplication.sessionManager.saveIsLogIn(true)
        stateUserDetail.userDetailResponse = null
        stateUserDetail.error = null
        stateUserDetail.isLoading = false
        showLoader = false
        navHostController.moveOnNewScreen(Screen.WelcomeScreen.route, true)
//        viewModel.registerDevice(
//            AppApplication.sessionManager.baseUrl?.url + REGISTER_DEVICE,
//            RegisterDeviceParams(
//                fcmToken,
//                AppActionValues.FCM_V1,
//                AppApplication.sessionManager.userDetails.userId
//            )
//        )
    }

//    if (stateRegisterDevice.registerDeviceResponse != null) {
//        stateUserDetail.userDetailResponse?.user?.let { it1 ->
//            AppApplication.sessionManager.saveUserDetails(
//                it1
//            )
//        }
//        stateUserDetail.userDetailResponse = null
//        stateUserDetail.error = null
//        stateUserDetail.isLoading = false
//        showLoader = false
//        navHostController.moveOnNewScreen(Screen.WelcomeScreen.route, true)
//    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        val (topBox, logoImage, languageSpinner, errorBox, authBox, resetAccount, bottomBox) = createRefs()



        Box(modifier = Modifier.constrainAs(topBox) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            OverlappingImagesTop()
        }
        Image(
            painter = painterResource(id = R.drawable.airportr_logo),
            contentDescription = null, // provide content description if needed
            modifier = Modifier
                .constrainAs(logoImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 40.dp)
                .height(120.dp), // make the image background transparent
            contentScale = ContentScale.Inside // scale the image to fill the Box
        )
        CustomDropdownMenuForLanguageChange(
            selectedLanguage,
            listLanguageListItemModel,
            dark_blue,
            modifier = Modifier
                .constrainAs(languageSpinner) {
                    top.linkTo(logoImage.bottom)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth() // Ensure the item takes full width if needed
                .wrapContentWidth(Alignment.End)
                .padding(end = 20.dp),
            onSelected = { selectedIndex ->
                selectedLanguage = listLanguageListItemModel[selectedIndex]
            },
            context
        )
        if (state.error != null) {
            Box(
                modifier = Modifier
                    .constrainAs(errorBox) {
                        top.linkTo(languageSpinner.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(16.dp) // Apply padding first
                    .clip(RoundedCornerShape(10.dp)) // Then clip the corners
                    .background(light_orange_new) // Background should respect the clipping
            ) {
                Text(
                    text = state.error.toString(),
                    color = air_orange_dark,
                    style = customTextLabelStyle,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(14.dp) // Optional inner padding for Text
                )
            }
        }
        val errorBarrier = createBottomBarrier(languageSpinner, errorBox)
        Column(
            modifier = Modifier
                .constrainAs(authBox) {
                    top.linkTo(
                        errorBarrier
                        // if (state.error != null) errorBox.bottom else languageSpinner.bottom
                    )
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            state.loginResponse = null
            state.error = null
            stateUserDetail.userDetailResponse = null
            stateUserDetail.error = null
            Text(
                text = stringResource(id = R.string.email),
                fontFamily = fonts,
                style = customTextLabelStyle,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            OutlinedTextField(
                value = emailId,
                onValueChange = { emailId = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email, "Email",
                        tint = dark_blue
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.enter_email),
                        fontFamily = fonts
                    )
                },

                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.enter_email),
                        fontFamily = fonts
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(fontFamily = fonts),
                colors = outlinedTextFieldColors
            )
            if (emailError) {
                Text(
                    text = stringResource(id = R.string.incorrect_email),
                    fontFamily = fonts,
                    style = customTextLabelStyle,
                    fontSize = 12.sp,
                    color = air_orange_dark,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                )
            }
            Space(height = 16, width = 0)

            Text(
                text = stringResource(id = R.string.password),
                fontFamily = fonts,
                style = customTextLabelStyle,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock, "Email",
                        tint = dark_blue
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.password),
                        fontFamily = fonts
                    )
                },
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.enter_password),
                        fontFamily = fonts
                    )
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else
                    PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = androidx.compose.ui.text.TextStyle(fontFamily = fonts),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                    // Please provide localized description for accessibility services
                    val description =
                        if (passwordVisible) "Hide password" else "Show password"
                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Icon(
                            imageVector = image, description,
                            tint = grey
                        )
                    }
                },
                colors = outlinedTextFieldColors
            )
            if (passwordError) {
                Text(
                    text = stringResource(id = R.string.incorrect_password),
                    fontFamily = fonts,
                    style = customTextLabelStyle,
                    fontSize = 12.sp,
                    color = air_orange_dark,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                )
            }
            Space(height = 20, width = 0)
            CustomButton(
                name = stringResource(id = R.string.login_text),
                onButtonClick = {
                    var email = emailId.text
                    if (email.isValidEmail()) {
                        println("Valid Email")
                    } else {
                        println("Invalid Email")
                    }

                    if (emailId.text.trim().isNotEmpty() && password.text.trim().isNotEmpty()) {
                        if (!emailId.text.isValidEmail()) {
                            emailError = true
                        } else if (password.text.length < 6) {
                            passwordError = true
                        } else {
                            emailError = false
                            passwordError = false

                            var email = ""
                            var baseUrl = ""
                            var subscriptionKey = ""
                            var baseUrlEnv = ""
                            var flag = false
                            if (BuildConfig.ENVIRONMENT == "live") {
                                email = emailId.text
                                baseUrl = AppConstants.PRODUCTION_URL_LIVE
                                baseUrlEnv = "-live"
                                subscriptionKey = AppConstants.LIVE_SUBSCRIPTION_KEY
                                flag = true
                            } else {
                                when {
                                    (emailId.text.trim().contains("-uat")) -> {
                                        email = emailId.text.replace("-uat", "")
                                        baseUrl = AppConstants.PRODUCTION_URL_UAT
                                        baseUrlEnv = "-uat"
                                        subscriptionKey = AppConstants.UAT_SUBSCRIPTION_KEY
                                        flag = true
                                    }

                                    (emailId.text.trim().contains("-dev")) -> {
                                        email = emailId.text.replace("-dev", "")
                                        baseUrl = AppConstants.PRODUCTION_URL_DEV
                                        baseUrlEnv = "-dev"
                                        subscriptionKey = AppConstants.DEV_SUBSCRIPTION_KEY
                                        flag = true
                                    }

                                    else -> {
                                        flag = false
                                        snackBarShowFlag = true
                                        errorMessage =
                                            "You need to use -uat or -dev with email for Login"
                                    }
                                }
                            }
                            if (flag) {

                                AppApplication.sessionManager.biometricEnabled(false)
                                AppApplication.sessionManager.saveBaseUrl(
                                    BaseUrl(
                                        baseUrl,
                                        baseUrlEnv
                                    )
                                )
                                AppApplication.sessionManager.saveSubscriptionKey(subscriptionKey)
                                loginCredEmail = email
                                loginCredPassword = password.text
                                callForApiToken(
                                    baseUrl + AppConstants.TOKEN_ENDPOINT,
                                    email,
                                    password.text,
                                    viewModel,
                                    context
                                )
                            }
                        }
                    }
                },
                paddingTop = 10,
                paddingHorizontal = 0,
                modifier = Modifier.height(70.dp),
                containerColor = if (emailId.text.isNotEmpty() && password.text.isNotEmpty()) air_purple else air_purple_awesome_light,
                textColor = if (emailId.text.isNotEmpty() && password.text.isNotEmpty()) white else air_awesome_purple_200,
                isEnabled = emailId.text.isNotEmpty() && password.text.isNotEmpty()

            )
            Space(height = 20, width = 0)

            Text(
                text = stringResource(id = R.string.login_with_biometrics_text),
                fontFamily = fonts,
                style = customTextLabelBoldStyle,
                fontSize = 14.sp,
                color = air_purple,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )

        }

        Text(
            text = stringResource(id = R.string.login_trouble_text),
            fontFamily = fonts,
            style = customTextLabelBoldStyle,
            fontSize = 14.sp,
            color = air_purple,
            modifier = Modifier
                .constrainAs(resetAccount) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp)
                .clickable {
                    navHostController.moveOnNewScreen(Screen.ResetPassword.route,false)
                }

        )

        Box(modifier = Modifier.constrainAs(bottomBox) {
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }) {
            OverlappingImagesBottom()
        }

        if (snackBarShowFlag) {
            SnackbarDemo(errorMessage)
            LaunchedEffect(Unit) {
                delay(3000L)
                snackBarShowFlag = false
                errorMessage = ""
            }

        }
    }

    if (state.error != null ) {
        errorMessage = state.error ?: context.getString(R.string.no_internet)
        snackBarShowFlag = true
    }
    if (state.isLoading ) {
        Log.wtf("StateLoadingAuth", "Called")
        showLoader = true
        LoaderDialog(showDialog = showLoader)
    }

    if (stateUserDetail.error != null) {
        errorMessage = state.error ?: context.getString(R.string.no_internet)
        snackBarShowFlag = true
    }
    if (stateUserDetail.isLoading) {
        Log.wtf("StateLoadingDetail", "Called")
        showLoader = true
        LoaderDialog(showDialog = showLoader)
    }
//
//    if (stateRegisterDevice.error != null) {
//        errorMessage = state.error ?: context.getString(R.string.no_internet)
//        snackBarShowFlag = true
//    }
//    if (stateRegisterDevice.isLoading) {
//        Log.wtf("StateLoadingDetail", "Called")
//        showLoader = true
//        LoaderDialog(showDialog = showLoader)
//    }

}


@Composable
fun OverlappingImagesTop() {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Optional padding
    ) {
        // Second Image
        Image(
            painter = painterResource(id = R.drawable.ic_top_line),
            contentDescription = "Top Line",
            modifier = Modifier
                .size(width = 200.dp, height = 70.dp),
            // .align(Alignment.TopStart), // Align to the bottom-end
            contentScale = ContentScale.Crop
        )
        // First Image
        Image(
            painter = painterResource(id = R.drawable.ic_top_purple_line),
            contentDescription = "Top Purple Line",
            modifier = Modifier
                .size(width = 70.dp, height = 200.dp),
            //   .align(Alignment.TopStart), // Align to the top-start
            contentScale = ContentScale.Crop
        )


    }
}

@Composable
fun OverlappingImagesBottom() {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Optional padding
    ) {
        // First Image
        Image(
            painter = painterResource(id = R.drawable.ic_botton_line),
            contentDescription = "Bottom Line",
            modifier = Modifier
                .size(width = 60.dp, height = 180.dp)
                .align(Alignment.BottomEnd)
                .padding(0.dp, 0.dp, 0.dp, 0.dp), // Align to the start
            contentScale = ContentScale.Crop
        )

        // Second Image
        Image(
            painter = painterResource(id = R.drawable.ic_bottom_line_purple),
            contentDescription = "Bottom Purple Line",
            modifier = Modifier
                .size(width = 300.dp, height = 120.dp)
                .align(Alignment.BottomEnd)
                .padding(0.dp, 0.dp, 0.dp, 0.dp), // Align to the end
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun CustomDropdownMenuForLanguageChange(
    selectedLanguage: LanguageListItemModel,
    list: List<LanguageListItemModel>, // Menu Options
    color: Color, // Color
    modifier: Modifier, //
    onSelected: (Int) -> Unit, // Pass the Selected Option
    context: Context
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    var expand by remember { mutableStateOf(false) }
    var stroke by remember { mutableIntStateOf(1) }
    Box(
        modifier
            .clickable {
                expand = !expand
                stroke = if (expand) 2 else 1
            },
        contentAlignment = Alignment.Center
    ) {
        Row {
            Image(
                painter = painterResource(id = selectedLanguage.flag), // Replace with your image resource
                contentDescription = "language",
                modifier = Modifier
                    .size(width = 41.dp, height = 41.dp)
                    .padding(vertical = 10.dp),
            )
            androidx.compose.material3.Text(
                text = selectedLanguage.languageName,
                color = color,
                fontSize = 14.sp,
                fontFamily = fonts,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 10.dp)
            )

        }


        DropdownMenu(
            expanded = expand,
            onDismissRequest = {
                expand = false
                stroke = 1
            },
            properties = PopupProperties(
                focusable = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
            ),
            modifier = Modifier
                .background(Color.White)
                .padding(2.dp)
                .fillMaxWidth(.4f)
        ) {
            list.forEachIndexed { index, item ->
                DropdownMenuItem(
                    onClick = {
                        selectedIndex = index
                        expand = false
                        stroke = if (expand) 2 else 1 // Ensure `stroke` logic is meaningful
                        onSelected(selectedIndex) // Notify about the selected index
                        changeLanguage(item, context) // Update language
                    },
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Flag Image
                            Image(
                                painter = painterResource(id = item.flag),
                                contentDescription = "language",
                                modifier = Modifier
                                    .size(41.dp) // Use fixed size for consistency
                                    .padding(end = 8.dp) // Space between image and text
                            )

                            // Language Text
                            Text(
                                text = item.languageName,
                                fontFamily = fonts,
                                style = customTextLabelStyle,
                                fontSize = 16.sp
                            )
                        }
                    }
                )

            }
        }

    }
}

@Composable
fun BoxWithCustomBackground(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // Apply padding first
            .clip(RoundedCornerShape(10.dp)) // Then clip the corners
            .background(light_orange_new) // Background should respect the clipping
    ) {
        Text(
            text = message,
            color = air_orange_dark,
            modifier = Modifier.padding(16.dp) // Optional inner padding for Text
        )
    }
}


private fun callForApiToken(
    url: String,
    email: String,
    password: String,
    viewModel: LoginViewModel,
    context: Context
) {
    viewModel.authToken(
        url,
        "password",
        email,
        password, "1",
        "None",
        BuildConfig.VERSION_NAME,
        "Android",
        getDeviceUUID(context),
        android.os.Build.MANUFACTURER,
        android.os.Build.VERSION.RELEASE,
        getNetworkType(context),
        "N/A",
        if (isCameraPermissionAllowed(context) == true) "Authorised" else "Not Authorised",
        if (isLocationPermissionAllowed(context) == true) "Authorised" else "Not Authorised",
    )
}


@Composable
@Preview
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController)
}
