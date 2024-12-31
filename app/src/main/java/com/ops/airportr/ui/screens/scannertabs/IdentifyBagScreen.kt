package com.ops.airportr.ui.screens.scannertabs

import android.app.Activity
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.ops.airportr.AppApplication
import com.ops.airportr.R
import com.ops.airportr.common.theme.air_purple
import com.ops.airportr.common.utils.getCurrentTimeStampIntoFormat
import com.ops.airportr.common.utils.returnBackGroundColor
import com.ops.airportr.common.utils.returnLabelAirPurple100Color
import com.ops.airportr.common.utils.returnLabelDarkBlueColor
import com.ops.airportr.domain.model.user.User


private lateinit var user: User

@Composable
fun IdentityBagScreen(navController: NavController) {
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

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(returnBackGroundColor(isDarkTheme))
    ) {
        val (topBar, userName, userType, qrCode, refresh) = createRefs()
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
                        navController.popBackStack()
                    }, // make the image background transparent
                contentScale = ContentScale.Inside, // scale the image to fill the Box
                colorFilter = ColorFilter.tint(returnLabelDarkBlueColor(isDarkTheme))
            )

            Text(
                text = stringResource(id = R.string.PortrCode),
                style = MaterialTheme.typography.labelSmall,
                color = returnLabelDarkBlueColor(isDarkTheme),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(14.dp),
            )
        }

        Text(
            text = stringResource(
                id = R.string.pName,
                user.firstName ?: "",
                user.lastName ?: ""
            ),
            style = MaterialTheme.typography.labelLarge,
            color = returnLabelDarkBlueColor(isDarkTheme),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            modifier = Modifier
                .constrainAs(userName) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                }
                .padding(top = 50.dp, start = 14.dp, bottom = 14.dp, end = 14.dp)
                .fillMaxWidth(),
        )
        Text(
            text = stringResource(
                id = R.string.pName,
                user.userRoles[0].description ?: "",
                ""
            ),
            style = MaterialTheme.typography.labelLarge,
            color = returnLabelAirPurple100Color(isDarkTheme),
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(userType) {
                    top.linkTo(userName.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(14.dp),
        )
        Box(
            modifier = Modifier
                .constrainAs(qrCode) {
                    top.linkTo(userType.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(), // Replace with dimension resource if needed
            contentAlignment = Alignment.Center
        ) {
            QRCodeGenerator(
                stringResource(
                    id = R.string.portr_code_generator,
                    user.userId ?: "",
                    getCurrentTimeStampIntoFormat()
                ), 300
            )
        }

        Image(
            painter = painterResource(id = R.drawable.refresh),
            contentDescription = null, // provide content description if needed
            modifier = Modifier
                .constrainAs(refresh) {
                    top.linkTo(qrCode.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 14.dp, end = 14.dp)
                .height(30.dp)
                .width(30.dp)
                .clickable {

                }, // make the image background transparent
            contentScale = ContentScale.Inside, // scale the image to fill the Box
            colorFilter = ColorFilter.tint(air_purple)
        )
    }
}

@Composable
fun QRCodeGenerator(content: String, size: Int = 200) {
    val qrCodeBitmap = generateQRCodeBitmap(content, size)

    Box(
        modifier = Modifier.size(size.dp),
        contentAlignment = Alignment.Center
    ) {
        qrCodeBitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Generated QR Code",
            )
        }
    }
}

private fun generateQRCodeBitmap(content: String, size: Int): Bitmap? {
    return try {
        val qrCodeWriter = QRCodeWriter()
        val bitMatrix: BitMatrix =
            qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, size, size)

        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(
                    x,
                    y,
                    if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE
                )
            }
        }
        bitmap
    } catch (e: WriterException) {
        e.printStackTrace()
        null
    }
}