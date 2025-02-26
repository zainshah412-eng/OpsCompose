package com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.profile

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.ops.airportr.AppApplication
import com.ops.airportr.BuildConfig
import com.ops.airportr.R
import com.ops.airportr.common.theme.air_awesome_purple_100
import com.ops.airportr.common.theme.customTextDescriptionStyle
import com.ops.airportr.common.theme.customTextLabelStyle
import com.ops.airportr.common.theme.editTextBorderStrockColor
import com.ops.airportr.common.theme.light_white
import com.ops.airportr.common.utils.extension.changeLanguage
import com.ops.airportr.common.utils.extension.moveOnNewScreen
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.domain.model.language.LanguageListItemModel
import com.ops.airportr.domain.model.user.User
import com.ops.airportr.route.Screen
import com.ops.airportr.ui.screens.loginscreen.CustomDropdownMenuForLanguageChange

private lateinit var user: User

@Composable
fun ProfileScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val activity = LocalContext.current as? Activity
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()

    try {
        // some code
        user = AppApplication.sessionManager.userDetails
    } catch (e: Exception) {
        // handler
    } finally {
        // optional finally block
    }
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
    var selectedTheme by remember {
        mutableStateOf(
            LanguageListItemModel(
                languageName = when (AppApplication.sessionManager.appTheme) {
                    true -> context.getString(R.string.night_text)
                    false -> context.getString(R.string.night_text)
                },
                flag = when (AppApplication.sessionManager.appTheme) {
                    true -> R.drawable.night_mode
                    false -> R.drawable.sun
                }
            )
        )
    }

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
    LaunchedEffect(AppApplication.sessionManager.appTheme) {
        val appLanguage = AppApplication.sessionManager.appTheme
        when (appLanguage) {
            true -> {
                selectedTheme = LanguageListItemModel(
                    context.getString(R.string.night_text),
                    R.drawable.night_mode
                )
            }

            false -> {
                selectedTheme =
                    LanguageListItemModel(context.getString(R.string.day_text), R.drawable.sun)
            }
        }
    }
    val listLanguageListItemModel = listOf(
        LanguageListItemModel("English", R.drawable.england),
        LanguageListItemModel("Deutsch", R.drawable.germany),
        LanguageListItemModel("Français", R.drawable.france)
    )

    val listThemeListItemModel = listOf(
        LanguageListItemModel(context.getString(R.string.day_text), R.drawable.sun),
        LanguageListItemModel(
            context.getString(R.string.night_text),
            R.drawable.night_mode
        )
    )

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(returnBackGroundColor(isDarkTheme))
    ) {
        val (title, languageSpinner, logoutImg, profileImg, userName, email, version,
            bottomBox) = createRefs()
        Text(
            text = stringResource(id = R.string.profile),
            style = MaterialTheme.typography.labelLarge,
            color = returnLabelDarkBlueColor(isDarkTheme),
            fontSize = 35.sp,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(14.dp),
        )

        Image(
            painter = painterResource(id = R.drawable.logout),
            contentDescription = null, // provide content description if needed
            modifier = Modifier
                .constrainAs(logoutImg) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(top = 24.dp, end = 14.dp)
                .height(30.dp)
                .width(30.dp)
                .clickable {
                    AppApplication.sessionManager.logoutUser()
                }, // make the image background transparent
            contentScale = ContentScale.Inside // scale the image to fill the Box
        )

        CustomDropdownMenuForLanguageChange(
            selectedLanguage,
            listLanguageListItemModel,
            returnLabelDarkBlueColor(isDarkTheme),
            modifier = Modifier
                .constrainAs(languageSpinner) {
                    top.linkTo(parent.top)
                    end.linkTo(logoutImg.start)
                }
                .fillMaxWidth() // Ensure the item takes full width if needed
                .wrapContentWidth(Alignment.End)
                .padding(end = 20.dp, top = 20.dp),
            onSelected = { selectedIndex ->
                selectedLanguage = listLanguageListItemModel[selectedIndex]
            },
            context,
            isDarkTheme
        )

        Image(
            painter = painterResource(id = R.drawable.profile_dumy),
            contentDescription = null, // provide content description if needed
            modifier = Modifier
                .constrainAs(profileImg) {
                    top.linkTo(title.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .padding(top = 5.dp, end = 5.dp)
                .height(dimensionResource(id = R.dimen._80sdp))
                .width(dimensionResource(id = R.dimen._80sdp))
                .clickable {

                }, // make the image background transparent
            contentScale = ContentScale.Crop // scale the image to fill the Box
        )

        Text(
            modifier = Modifier
                .constrainAs(userName) {
                    top.linkTo(profileImg.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints

                }
                .padding(top = 15.dp),
            textAlign = TextAlign.Center,
            text = stringResource(
                id = R.string.pName,
                user.firstName ?: "",
                user.lastName ?: ""
            ),
            style = MaterialTheme.typography.labelMedium,
            color = returnLabelDarkBlueColor(isDarkTheme), // Use your custom text style here
            fontSize = 18.sp,
        )
        Text(
            modifier = Modifier
                .constrainAs(email) {
                    top.linkTo(userName.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints

                }
                .padding(top = 5.dp),
            textAlign = TextAlign.Center,
            text = user.emailAddress ?: "",
            fontSize = 14.sp,
            style = MaterialTheme.typography.labelMedium,
            color = returnLabelDarkBlueColor(isDarkTheme),
        )
        Text(
            modifier = Modifier
                .constrainAs(version) {
                    top.linkTo(email.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints

                }
                .padding(top = 5.dp),
            textAlign = TextAlign.Center,
            text = stringResource(
                id = R.string.current_version,
                BuildConfig.VERSION_NAME,
                BuildConfig.VERSION_CODE.toString()
            ),
            style = customTextDescriptionStyle, // Use your custom text style here
            fontSize = 16.sp,
            color = air_awesome_purple_100, // Replace with your desired color
        )

        Box(
            modifier = Modifier
                .constrainAs(bottomBox) {
                    top.linkTo(version.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                ProfileTab(stringResource(id = R.string.PortrCode),
                    modifier,
                    isDarkTheme,
                    onClick = {
                        navHostController.moveOnNewScreen(Screen.PortrCode.route, false)

                    })
                ProfileTab(stringResource(id = R.string.profileDetail),
                    modifier,
                    isDarkTheme,
                    onClick = {
                        navHostController.moveOnNewScreen(Screen.ProfileDetail.route, false)
                    })
                ProfileTab(stringResource(id = R.string.whats_new),
                    modifier,
                    isDarkTheme,
                    onClick = {
                        navHostController.moveOnNewScreen(Screen.WhatsNewDetail.route, false)
                    })
                ProfileTab(stringResource(id = R.string.night_mode),
                    modifier,
                    isDarkTheme,
                    onClick = {

                    })
                ProfileTab(stringResource(id = R.string.switchProfile),
                    modifier,
                    isDarkTheme,
                    onClick = {
                        navHostController.navigate(Screen.WelcomeScreen.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    })
                ProfileTab(stringResource(id = R.string.give_feedback),
                    modifier,
                    isDarkTheme,
                    onClick = {

                    })
            }
        }
    }


}

@Composable
fun ProfileTab(
    tabName: String,
    modifier: Modifier = Modifier,
    isDarkTheme :Boolean,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .padding(top = dimensionResource(id = R.dimen._10sdp))
            .clickable { onClick() } // Optional if you want the whole container clickable
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Custom Text View Equivalent
            Text(
                text = tabName,
                fontSize = 14.sp,
                style = MaterialTheme.typography.labelMedium,
                color = returnLabelDarkBlueColor(isDarkTheme),
                modifier = Modifier
            )

            // Image Icon
            Icon(
                painter = painterResource(id = R.drawable.forward),
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(id = R.dimen._12sdp)),
                tint = air_awesome_purple_100 // Optional for tint
            )
        }

        // Bottom Divider
        Divider(
            thickness = 0.5.dp, // Adjust this value for thickness
            modifier = Modifier.fillMaxWidth(),
            color = if(isDarkTheme) editTextBorderStrockColor  else light_white
        )
    }
}

@Composable
fun CustomDropdownMenuForThemeChange(
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