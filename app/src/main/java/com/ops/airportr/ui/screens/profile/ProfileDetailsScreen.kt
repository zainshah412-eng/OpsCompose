package com.ops.airportr.ui.screens.profile

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.ops.airportr.AppApplication
import com.ops.airportr.R
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.theme.fontsRegular
import com.ops.airportr.common.theme.white
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.common.utils.returnLabelAirPurpleColor
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.domain.model.user.User
import com.ops.airportr.ui.componts.CustomButton
import com.ops.airportr.ui.componts.Space

private lateinit var user: User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDetailScreen(
    navHostController: NavHostController
) {
    val activity = LocalContext.current as? Activity
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()

    var firstName by remember { mutableStateOf(("")) }
    var lastName by remember { mutableStateOf(("")) }
    var phoneNo by remember { mutableStateOf(("")) }
    val outlinedTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = returnLabelAirPurpleColor(isDarkTheme),
        unfocusedBorderColor = returnLabelAirPurple100Color(isDarkTheme),
        cursorColor = returnLabelDarkBlueColor(isDarkTheme),
        textColor = returnLabelDarkBlueColor(isDarkTheme),
        focusedLabelColor = returnLabelDarkBlueColor(isDarkTheme),
        unfocusedLabelColor = returnLabelAirPurple100Color(isDarkTheme)
    )

    try {
        // some code
        user = AppApplication.sessionManager.userDetails
        firstName = user.firstName ?: ""
        lastName = user.lastName ?: ""
        phoneNo = user.contactNumber ?: ""
    } catch (e: Exception) {
        // handler
    } finally {
        // optional finally block
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(returnBackGroundColor(isDarkTheme))
    ) {
        val (topBar, authBox, userType, qrCode, refresh) = createRefs()
        Row(modifier = Modifier
            .constrainAs(topBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .height(dimensionResource(id = R.dimen._60sdp))
            .fillMaxSize()
            .shadow(elevation = 12.dp)
            .background(returnBackGroundColor(isDarkTheme)),
            verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = null, // provide content description if needed
                modifier = Modifier
                    .padding(start = 14.dp, end = 14.dp)
                    .height(30.dp)
                    .width(30.dp)
                    .clickable {
                        navHostController.popBackStack()
                    }, // make the image background transparent
                contentScale = ContentScale.Inside, // scale the image to fill the Box
                colorFilter = ColorFilter.tint(returnLabelDarkBlueColor(isDarkTheme))
            )

            Text(
                text = stringResource(id = R.string.profileDetail),
                style = MaterialTheme.typography.labelSmall,
                color = returnLabelDarkBlueColor(isDarkTheme),
                fontSize = 18.sp,
                modifier = Modifier.padding(14.dp),
            )
        }

        Column(modifier = Modifier
            .constrainAs(authBox) {
                top.linkTo(
                    topBar.bottom
                )
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = stringResource(id = R.string.first_name_text),
                style = MaterialTheme.typography.labelMedium,
                color = returnLabelDarkBlueColor(isDarkTheme),
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            OutlinedTextField(value = firstName,
                onValueChange = { firstName = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person, "Email",
                        tint = returnLabelDarkBlueColor(isDarkTheme)

                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.first_name_text),
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 14.sp,
                        color = returnLabelDarkBlueColor(isDarkTheme),

                        )
                },

                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.first_name_text),
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 14.sp,
                        color = returnLabelDarkBlueColor(isDarkTheme),
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(fontFamily = fontsRegular),
                colors = outlinedTextFieldColors
            )

            Space(height = 16, width = 0)

            Text(
                text = stringResource(id = R.string.last_name_text),
                style = MaterialTheme.typography.labelMedium,
                color = returnLabelDarkBlueColor(isDarkTheme),

                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            OutlinedTextField(value = lastName,
                onValueChange = { lastName = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person, "Email",
                        tint = returnLabelDarkBlueColor(isDarkTheme)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.last_name_text),
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 14.sp,
                        color = returnLabelDarkBlueColor(isDarkTheme),

                        )
                },

                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.last_name_text),
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 14.sp,
                        color = returnLabelDarkBlueColor(isDarkTheme),

                        )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(fontFamily = fontsRegular),
                colors = outlinedTextFieldColors
            )

            Space(height = 20, width = 0)
            Text(
                text = stringResource(id = R.string.phone_number),
                style = MaterialTheme.typography.labelMedium,
                color = returnLabelDarkBlueColor(isDarkTheme),

                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            OutlinedTextField(value = phoneNo,
                onValueChange = { phoneNo = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.PhoneAndroid, "Email",
                        tint = returnLabelDarkBlueColor(isDarkTheme)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.phone_number),
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 14.sp,
                        color = returnLabelDarkBlueColor(isDarkTheme),

                        )
                },

                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.passport_number),
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 14.sp,
                        color = returnLabelDarkBlueColor(isDarkTheme),

                        )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(fontFamily = fontsRegular),
                colors = outlinedTextFieldColors
            )
            Space(height = 20, width = 0)
            CustomButton(
                name = stringResource(id = R.string.done),
                onButtonClick = {
//                    var email = emailId.text
//                    if (email.isValidEmail()) {
//                        println("Valid Email")
//                    } else {
//                        println("Invalid Email")
//                    }

                },
                paddingTop = 10,
                paddingHorizontal = 0,
                modifier = Modifier.height(70.dp),
                containerColor = air_purple,
                textColor = white,
                isEnabled = true
            )
            Space(height = 20, width = 0)


        }
    }
}