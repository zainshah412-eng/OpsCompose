package com.ops.airportr.ui.screens.loginscreen

import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ops.airportr.AppApplication
import com.ops.airportr.R
import com.ops.airportr.common.theme.air_awesome_purple_200
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.theme.air_purple_awesome_light
import com.ops.airportr.common.theme.customTextLabelStyle
import com.ops.airportr.common.theme.dark_blue
import com.ops.airportr.common.theme.fonts
import com.ops.airportr.common.theme.grey
import com.ops.airportr.common.theme.purple_100
import com.ops.airportr.common.theme.white
import com.ops.airportr.common.utils.changeLanguage
import com.ops.airportr.domain.model.language.LanguageListItemModel
import com.ops.airportr.ui.componts.CustomButton
import com.ops.airportr.ui.componts.Space

@Composable
fun LoginScreen(
    navHostController: NavHostController,
//    viewModel: LoginViewModel = hiltViewModel()
) {
//    LaunchedEffect(Unit) {
//        viewModel.resetError()
//    }
//    val activity = LocalContext.current as? Activity
//    BackPressHandler(activity) {
//        if (!navHostController.popBackStack()) {
//            // Finish the activity to close the app
//            activity?.finish()
//        }
//    }

    var emailId by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val outlinedTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = purple_100,
        unfocusedBorderColor = Color.Gray,
        cursorColor = dark_blue,
        textColor = dark_blue,
        focusedLabelColor = dark_blue,
        unfocusedLabelColor = Color.Gray
    )
    var isChecked by remember { mutableStateOf(true) }
    val appLanguage = AppApplication.sessionManager.appLanguage ?: "en"
    val listLanguageListItemModel = remember {
        ArrayList<LanguageListItemModel>().apply {
            if (appLanguage == "en") {
                add(LanguageListItemModel("English", R.drawable.england))
                add(LanguageListItemModel("Deutsch", R.drawable.germany))
                add(LanguageListItemModel("Français", R.drawable.france))
            } else if (appLanguage == "de") {
                add(LanguageListItemModel("Deutsch", R.drawable.germany))
                add(LanguageListItemModel("English", R.drawable.england))
                add(LanguageListItemModel("Français", R.drawable.france))
            } else {
                add(LanguageListItemModel("Français", R.drawable.france))
                add(LanguageListItemModel("English", R.drawable.england))
                add(LanguageListItemModel("Deutsch", R.drawable.germany))
            }
        }
    }
    var selectedItem by remember { mutableStateOf<LanguageListItemModel?>(null) }
    //   val state = viewModel.state.value

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(white)
        ) {

            OverlappingImagesTop()

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Space(height = 50, width = 0)
                Image(
                    painter = painterResource(id = R.drawable.airportr_logo),
                    contentDescription = null, // provide content description if needed
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth(), // make the image background transparent
                    contentScale = ContentScale.Inside // scale the image to fill the Box
                )
                Space(height = 50, width = 0)
                CustomDropdownMenuForLanguageChange(
                    listLanguageListItemModel,
                    dark_blue,
                    modifier = Modifier
                        .fillMaxWidth() // Ensure the item takes full width if needed
                        .wrapContentWidth(Alignment.End)
                        .padding(end = 20.dp),
                    onSelected = { selectedIndex ->
                        selectedItem = listLanguageListItemModel[selectedIndex]
                    },
                    context
                )

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
                                imageVector = Icons.Filled.Email, "Email",
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

                    Text(
                        text = stringResource(id = R.string.password),
                        fontFamily = fonts,
                        style = customTextLabelStyle,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Lock, "Email",
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
                    Space(height = 10, width = 0)

                    CustomButton(
                        name = stringResource(id = R.string.login_text),
                        onButtonClick = {
                            if (emailId.text.isNullOrEmpty() && password.text.isNullOrEmpty()) {
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
                        containerColor = if (emailId.text.isNotEmpty() && password.text.isNotEmpty()) air_purple else air_purple_awesome_light,
                        textColor = if (emailId.text.isNotEmpty() && password.text.isNotEmpty()) white else air_awesome_purple_200

                    )
                    Space(height = 20, width = 0)
                    Text(
                        text = stringResource(id = R.string.login_with_biometrics_text),
                        fontFamily = fonts,
                        style = customTextLabelStyle,
                        color = air_purple,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }


            }
            Text(
                text = stringResource(id = R.string.login_trouble_text),
                fontFamily = fonts,
                style = customTextLabelStyle,
                color = air_purple,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .align(Alignment.BottomCenter)
                    .padding(0.dp, 0.dp, 0.dp, 40.dp)
            )
            Box(modifier = Modifier.align(Alignment.BottomEnd)) {
                OverlappingImagesBottom()
            }

        }

//        state.loginResponse.let {
//
//        }
//
//
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
                .padding(0.dp, 0.dp, 0.dp, 10.dp), // Align to the end
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun CustomDropdownMenuForLanguageChange(
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
                painter = painterResource(id = list[0].flag), // Replace with your image resource
                contentDescription = "language",
                modifier = Modifier
                    .size(width = 41.dp, height = 41.dp)
                    .padding(vertical = 10.dp),
            )
            androidx.compose.material3.Text(
                text = list[0].languageName,
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
                        changeLanguage(list[selectedIndex], context)
//                        val timeToSleepModel = TimeToSleepModel(list[selectedIndex],"Off","")
//                        AppApplication.sessionManager.saveTimeToSleepDetail(timeToSleepModel)
                    }
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = item.flag), // Replace with your image resource
                            contentDescription = "language",
                            modifier = Modifier
                                .size(width = 21.dp, height = 21.dp)
                                .padding(end = 10.dp),
                        )
                        Text(
                            text = item.languageName,
                            fontFamily = fonts,
                            style = customTextLabelStyle,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }

    }
}

@Composable
@Preview
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController)
}
