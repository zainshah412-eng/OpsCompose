package com.ops.airportr.ui.screens.resetpassword

import android.app.Activity
import android.app.LocaleManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.os.LocaleListCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ops.airportr.AppApplication
import com.ops.airportr.R
import com.ops.airportr.common.theme.air_awesome_purple_200
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.theme.air_purple_awesome_light
import com.ops.airportr.common.theme.customTextDescriptionStyle
import com.ops.airportr.common.theme.customTextHeadingStyle
import com.ops.airportr.common.theme.customTextLabelStyle
import com.ops.airportr.common.theme.dark_blue
import com.ops.airportr.common.theme.fonts
import com.ops.airportr.common.theme.grey
import com.ops.airportr.common.theme.purple_100
import com.ops.airportr.common.theme.white
import com.ops.airportr.common.utils.changeLanguage
import com.ops.airportr.domain.model.language.LanguageListItemModel
import com.ops.airportr.route.Screen
import com.ops.airportr.ui.componts.BackPressHandler
import com.ops.airportr.ui.componts.CustomButton
import com.ops.airportr.ui.componts.Space

@Composable
fun ResetPasswordScreen(
    navHostController: NavHostController,
//    viewModel: LoginViewModel = hiltViewModel()
) {
//    LaunchedEffect(Unit) {
//        viewModel.resetError()
//    }
    val activity = LocalContext.current as? Activity
    BackPressHandler(activity) {
        if (!navHostController.popBackStack()) {
            // Finish the activity to close the app
            activity?.finish()
        }
    }

    var emailId by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        val (backImage, titleDescBox, centerBox) = createRefs()
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
                fontFamily = fonts,
                style = customTextHeadingStyle,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Space(height = 10, width = 0)
            Text(
                text = stringResource(id = R.string.reset_password_msg),
                fontFamily = fonts,
                style = customTextDescriptionStyle,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp)
            )
        }
        Column(
            modifier = Modifier
                .constrainAs(centerBox) {
                    top.linkTo(titleDescBox.bottom)
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
                fontFamily = fonts,
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
                textStyle = androidx.compose.ui.text.TextStyle(fontFamily = fonts),
                colors = outlinedTextFieldColors
            )
            Space(height = 16, width = 0)
            CustomButton(
                name = stringResource(id = R.string.password_reset),
                onButtonClick = {
                    if (emailId.text.isNullOrEmpty()) {
                        Toast.makeText(
                            context,
                            "Please enter correct detail.",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
//                                    viewModel.getLogin(
//                                        USER_AUTHENTICATE + emailId.text
//                                                + "&Password=" + password.text + "&IMEI=865753025921006"
//                                    )
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

}


@Composable
@Preview
fun ResetPasswordPreview() {
    val navController = rememberNavController()
    ResetPasswordScreen(navController)
}
