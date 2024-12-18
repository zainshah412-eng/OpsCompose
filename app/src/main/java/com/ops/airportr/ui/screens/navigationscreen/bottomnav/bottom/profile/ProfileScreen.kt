package com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.profile

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.NavHostController
import com.ops.airportr.AppApplication
import com.ops.airportr.BuildConfig
import com.ops.airportr.R
import com.ops.airportr.common.theme.air_awesome_purple_100
import com.ops.airportr.common.theme.customTextDescriptionStyle
import com.ops.airportr.common.theme.customTextHeadingStyle
import com.ops.airportr.common.theme.customTextLabelStyle
import com.ops.airportr.common.theme.dark_blue
import com.ops.airportr.common.theme.light_white
import com.ops.airportr.common.theme.white
import com.ops.airportr.common.utils.moveOnNewScreen
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
    val context = LocalContext.current
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
    val listLanguageListItemModel = listOf(
        LanguageListItemModel("English", R.drawable.england),
        LanguageListItemModel("Deutsch", R.drawable.germany),
        LanguageListItemModel("Français", R.drawable.france)
    )

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        val (title, languageSpinner, logoutImg, profileImg, userName, email, version,
            bottomBox) = createRefs()
        Text(
            text = stringResource(id = R.string.profile),
            style = customTextHeadingStyle,
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

                }, // make the image background transparent
            contentScale = ContentScale.Inside // scale the image to fill the Box
        )

        CustomDropdownMenuForLanguageChange(
            selectedLanguage,
            listLanguageListItemModel,
            dark_blue,
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
            context
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
            style = customTextDescriptionStyle, // Use your custom text style here
            fontSize = 18.sp,
            color = dark_blue, // Replace with your desired color
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
            text = user.emailAddress?:"",
            style = customTextDescriptionStyle, // Use your custom text style here
            fontSize = 14.sp,
            color = dark_blue, // Replace with your desired color
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
                    onClick = {
                        navHostController.moveOnNewScreen(Screen.PortrCode.route, false)

                    })
                ProfileTab(stringResource(id = R.string.profileDetail),
                    modifier,
                    onClick = {

                    })
                ProfileTab(stringResource(id = R.string.whats_new),
                    modifier,
                    onClick = {

                    })
                ProfileTab(stringResource(id = R.string.night_mode),
                    modifier,
                    onClick = {

                    })
                ProfileTab(stringResource(id = R.string.switchProfile),
                    modifier,
                    onClick = {

                    })
                ProfileTab(stringResource(id = R.string.give_feedback),
                    modifier,
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
                style = customTextLabelStyle, // Use your custom text style here
                fontSize = 14.sp,
                color = dark_blue,
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
            color = light_white, // Replace with `viewBorderColor`
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
