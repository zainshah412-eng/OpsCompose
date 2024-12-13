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
//    var password by remember { mutableStateOf(TextFieldValue("")) }
//    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val outlinedTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = purple_100,
        unfocusedBorderColor = Color.Gray,
        cursorColor = dark_blue,
        textColor = dark_blue,
        focusedLabelColor = dark_blue,
        unfocusedLabelColor = Color.Gray
    )

//    var selectedLanguage by remember {
//        mutableStateOf(
//            LanguageListItemModel(
//                languageName = when (AppApplication.sessionManager.appLanguage ?: "en") {
//                    "en" -> "English"
//                    "de" -> "Deutsch"
//                    else -> "Français"
//                },
//                flag = when (AppApplication.sessionManager.appLanguage ?: "en") {
//                    "en" -> R.drawable.england
//                    "de" -> R.drawable.germany
//                    else -> R.drawable.france
//                }
//            )
//        )
//    }

//    val listLanguageListItemModel = listOf(
//        LanguageListItemModel("English", R.drawable.england),
//        LanguageListItemModel("Deutsch", R.drawable.germany),
//        LanguageListItemModel("Français", R.drawable.france)
//    )

//    LaunchedEffect(AppApplication.sessionManager.appLanguage) {
//        val appLanguage = AppApplication.sessionManager.appLanguage ?: "en"
//        when (appLanguage) {
//            "en" -> {
//                selectedLanguage = LanguageListItemModel("English", R.drawable.england)
//            }
//            "de" -> {
//                selectedLanguage = LanguageListItemModel("Deutsch", R.drawable.germany)
//            }
//            else -> {
//                selectedLanguage = LanguageListItemModel("Français", R.drawable.france)
//            }
//        }
//    }



//    var selectedItem by remember { mutableStateOf<LanguageListItemModel?>(null) }
//    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {

//        Log.wtf("RESPONSE_DATA", state.loginResponse.toString())
//        if (state.loginResponse?.status == true) {
////                navHostController.moveOnNewScreen(Screen.HomeScreen.route, true)
//            state.loginResponse = null
////                navHostController.navigate(Screen.HomeScreen.route) {
////                    launchSingleTop =
////                        true  // Ensures that if you are already on the HomeScreen, it won't add it again to the stack
////                }
//            AppApplication.sessionManager.saveIsLogIn(true)
//            navHostController.navigate(Screen.HomeScreen.route) {
//                popUpTo(0) { inclusive = true }
//            }
//        }


//        state.loginResponse.let {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(white)
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Space(height = 10, width = 0)
                    Image(
                        painter = painterResource(id = R.drawable.ic_back_arrow_blue),
                        contentDescription = "back arrow",
                        modifier = Modifier
                            .size(width = 40.dp, height = 40.dp)
                            .align(Alignment.Start)
                            .padding(start = 10.dp)
                    )
                    Space(height = 70, width = 0)
                    Text(
                        text = stringResource(id = R.string.password_reset),
                        fontFamily = fonts,
                        style = customTextHeadingStyle,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    )
                    Space(height = 10, width = 0)
                    Text(
                        text = stringResource(id = R.string.reset_password_msg),
                        fontFamily = fonts,
                        style = customTextDescriptionStyle,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(start = 15.dp, end = 15.dp)
                    )
                    Space(height = 10, width = 0)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(start = 20.dp, end = 20.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Space(height = 20, width = 0)
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
                            name = stringResource(id = R.string.reset_password),
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
//        }


//        if (state.loginResponse?.status == false) {
//            Toast.makeText(
//                context,
//                state.loginResponse?.msg ?: "Incorrect Id or Password",
//                Toast.LENGTH_SHORT
//            )
//                .show()
//
//            state.loginResponse = null
////            navHostController.navigate(Screen.HomeScreen.route) {
////                launchSingleTop =
////                    true  // Ensures that if you are already on the HomeScreen, it won't add it again to the stack
////            }
////            navHostController.moveOnNewScreen(Screen.HomeScreen.route, true){
////            }
////            navHostController.navigate(Screen.HomeScreen.route) {
////                popUpTo(0) { inclusive = true }
////            }
//        }
//        if (state.isLoading) {
//            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//        }


    }

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
                .size(width = 200.dp, height = 100.dp)
                .align(Alignment.TopStart), // Align to the bottom-end
            contentScale = ContentScale.Crop
        )
        // First Image
        Image(
            painter = painterResource(id = R.drawable.ic_top_purple_line),
            contentDescription = "Top Purple Line",
            modifier = Modifier
                .size(width = 100.dp, height = 250.dp)
                .align(Alignment.TopStart), // Align to the top-start
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
                .size(width = 100.dp, height = 300.dp)
                .align(Alignment.BottomEnd)
                .padding(0.dp, 0.dp, 0.dp, 10.dp), // Align to the start
            contentScale = ContentScale.Crop
        )

        // Second Image
        Image(
            painter = painterResource(id = R.drawable.ic_bottom_line_purple),
            contentDescription = "Bottom Purple Line",
            modifier = Modifier
                .size(width = 300.dp, height = 150.dp)
                .align(Alignment.BottomEnd)
                .padding(0.dp, 0.dp, 0.dp, 0.dp), // Align to the end
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun CustomDropdownMenuForLanguageChange(
    selectedLanguage : LanguageListItemModel,
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
                        stroke = if (expand) 2 else 1
                        onSelected(selectedIndex)
                        changeLanguage(item, context)
                    }
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = item.flag), // Replace with your image resource
                            contentDescription = "language",
                            modifier = Modifier
                                .size(width = 41.dp, height = 41.dp)
                                .padding(vertical = 10.dp),
                        )
                        Text(
                            text = item.languageName,
                            fontFamily = fonts,
                            style = customTextLabelStyle,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }
                }
            }
        }

    }
}


@Composable
@Preview
fun ResetPasswordPreview() {
    val navController = rememberNavController()
    ResetPasswordScreen(navController)
}
