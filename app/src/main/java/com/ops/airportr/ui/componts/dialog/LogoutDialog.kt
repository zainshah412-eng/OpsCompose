package com.ops.airportr.ui.componts.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.ops.airportr.AppApplication
import com.ops.airportr.R

import com.ops.airportr.common.theme.grey_trans
import com.ops.airportr.route.Screen

@Composable
fun LogoutDialog(
    navHostController: NavHostController
) {
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = stringResource(id = R.string.logout), color = grey_trans) },
            text = { Text(stringResource(id = R.string.login_text), color = grey_trans) },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        AppApplication.sessionManager.saveIsLogIn(false)
                        navHostController.navigate(Screen.LoginScreen.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                ) {
                    Text(stringResource(id = R.string.login_text), color = grey_trans)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false

                    }
                ) {
                    Text(stringResource(id = R.string.cancel), color = grey_trans)
                }
            },
            containerColor = grey_trans,
            iconContentColor = grey_trans
        )
    }
}