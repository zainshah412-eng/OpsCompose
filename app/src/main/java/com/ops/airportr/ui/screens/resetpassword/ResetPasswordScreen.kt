package com.ops.airportr.ui.screens.resetpassword

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ops.airportr.R
import com.ops.airportr.common.AppConstants
import com.ops.airportr.common.theme.air_awesome_purple_200
import com.ops.airportr.common.theme.air_orange_dark
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.theme.air_purple_awesome_light
import com.ops.airportr.common.theme.customTextDescriptionStyle
import com.ops.airportr.common.theme.customTextHeadingStyle
import com.ops.airportr.common.theme.customTextLabelStyle
import com.ops.airportr.common.theme.dark_blue
import com.ops.airportr.common.theme.fontsRegular
import com.ops.airportr.common.theme.light_orange_new
import com.ops.airportr.common.theme.purple_100
import com.ops.airportr.common.theme.white
import com.ops.airportr.common.utils.extension.isValidEmail
import com.ops.airportr.ui.componts.BackPressHandler
import com.ops.airportr.ui.componts.CustomButton
import com.ops.airportr.ui.componts.LoaderDialog
import com.ops.airportr.ui.componts.Space

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(
    navHostController: NavHostController,
    viewModel: ResetPasswordViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.resetPasswordError()
    }
    val activity = LocalContext.current as? Activity
    BackPressHandler(activity) {
        if (!navHostController.popBackStack()) {
            // Finish the activity to close the app
            activity?.finish()
        }
    }
    var emailError by rememberSaveable { mutableStateOf(false) }
    var snackBarShowFlag by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showLoader by remember { mutableStateOf(false) }
    var emailId by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    val state = viewModel.state.value
    if (state.resetPasswordResponse?.responseStatus == 1){
        state.resetPasswordResponse = null
        state.error = null
        state.isLoading = false
        showLoader = false
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        val (backImage, titleDescBox, errorBox, centerBox) = createRefs()
        val outlinedTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = purple_100,
            unfocusedBorderColor = Color.Gray,
            cursorColor = dark_blue,
            textColor = dark_blue,
            focusedLabelColor = dark_blue,
            unfocusedLabelColor = Color.Gray
        )

        Image(
            painter = painterResource(id = R.drawable.ic_back_arrow_blue),
            contentDescription = "back arrow",
            modifier = Modifier
                .constrainAs(backImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(start = 20.dp, top = 20.dp)
                .size(width = 25.dp, height = 25.dp)
                .clickable {
                    navHostController.popBackStack()
                }
        )
        Column(
            modifier = Modifier
                .constrainAs(titleDescBox) {
                    top.linkTo(backImage.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Space(height = 70, width = 0)
            Text(
                text = stringResource(id = R.string.password_reset),
                style = customTextHeadingStyle,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Space(height = 10, width = 0)
            Text(
                text = stringResource(id = R.string.reset_password_msg),
                style = customTextDescriptionStyle,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp)
            )
        }
        if (state.error != null) {
            Box(
                modifier = Modifier
                    .constrainAs(errorBox) {
                        top.linkTo(titleDescBox.bottom)
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
        val errorBarrier = createBottomBarrier(titleDescBox, errorBox)
        Column(
            modifier = Modifier
                .constrainAs(centerBox) {
                    top.linkTo(errorBarrier)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Space(height = 50, width = 0)
            Text(
                text = stringResource(id = R.string.email),
                style = customTextLabelStyle,
                fontSize = 16.sp,
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
                    )
                },

                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.enter_email),
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = androidx.compose.ui.text.TextStyle(fontFamily = fontsRegular),
                colors = outlinedTextFieldColors
            )
            Space(height = 16, width = 0)
            CustomButton(
                name = stringResource(id = R.string.password_reset),
                onButtonClick = {
                    var email = emailId.text
                    if (email.isValidEmail()) {
                        println("Valid Email")
                    } else {
                        println("Invalid Email")
                    }
                    if (emailId.text.trim().isNotEmpty()){
                        if (!emailId.text.isValidEmail()){
                            emailError = true
                        }else{
                            emailError = false
                            var baseUrl = ""
                            var subscriptionKey = ""
                            var baseUrlEnv = ""
                            var flag = false
                            callForApiResetPassword(
                                baseUrl + AppConstants.RESET_PASSWORD,
                                email,
                                viewModel,
                                context
                            )
                        }
                    }
                },
                paddingTop = 10,
                paddingHorizontal = 0,
                modifier = Modifier.height(70.dp),
                containerColor = if (emailId.text.isNotEmpty()) air_purple else air_purple_awesome_light,
                textColor = if (emailId.text.isNotEmpty()) white else air_awesome_purple_200

            )
            Space(height = 20, width = 0)
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

}

private fun callForApiResetPassword(
    url: String,
    email: String,
    viewModel: ResetPasswordViewModel,
    context: Context
) {
    viewModel.resetPassword(
        url,
        email,
    )
}


@Composable
@Preview
fun ResetPasswordPreview() {
    val navController = rememberNavController()
    ResetPasswordScreen(navController)
}
