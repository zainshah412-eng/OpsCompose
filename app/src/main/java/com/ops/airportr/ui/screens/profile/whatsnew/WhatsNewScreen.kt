package com.ops.airportr.ui.screens.profile.whatsnew

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ops.airportr.AppApplication
import com.ops.airportr.R
import com.ops.airportr.common.theme.customTextHeadingStyle
import com.ops.airportr.common.theme.dark_blue
import com.ops.airportr.common.theme.white
import com.ops.airportr.domain.model.user.User
import com.ops.airportr.domain.model.whatsnew.updatenew.UpdateAvailableItem
import com.ops.airportr.ui.componts.LoaderDialog
import com.ops.airportr.ui.screens.profile.whatsnew.item.WhatsNewItem

private lateinit var user: User

@Composable
fun WhatsNewScreen(
    navHostController: NavHostController,
    viewModel: WhatsNewViewModel = hiltViewModel(),
) {
    try {
        // some code
        user = AppApplication.sessionManager.userDetails

    } catch (e: Exception) {
        // handler
    } finally {
        // optional finally block
    }
    val whatsNewList = remember { mutableStateOf(ArrayList<UpdateAvailableItem>()) }

    val state = viewModel.state.value
    val context = LocalContext.current

    var snackBarShowFlag by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showLoader by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        val (topBar, WhatsNewBox) = createRefs()
        Row(modifier = Modifier
            .constrainAs(topBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .height(dimensionResource(id = R.dimen._60sdp))
            .fillMaxSize()
            .shadow(elevation = 12.dp)
            .background(Color.White),
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
                colorFilter = ColorFilter.tint(dark_blue)
            )

            Text(
                text = stringResource(id = R.string.whats_new),
                style = customTextHeadingStyle,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(14.dp),
            )
        }

        if (state.response != null && state.response?.data!!.size > 0) {
            SetFeatureData(context, whatsNewList)
        }

        if (state.error != null) {
            errorMessage = state.error ?: context.getString(R.string.no_internet)
            snackBarShowFlag = true
        }
        if (state.isLoading) {
            Log.wtf("StateLoadingAuth", "Called")
            showLoader = true
            LoaderDialog(showDialog = showLoader)
        }

    }
}

@Composable
fun SetFeatureData(context: Context, whatsNewList: MutableState<ArrayList<UpdateAvailableItem>>) {
    var appVersions = AppApplication.sessionManager.appVersions
    val arrayList = ArrayList<String>()
    whatsNewList.value.clear()
    for (objOuter in appVersions.data!!) {
        for (obj in objOuter?.featureLists!!) {
            if (obj?.list != null) {
                whatsNewList.value.add(
                    UpdateAvailableItem(
                        obj.date!!,
                        objOuter.newVersion!!,
                        obj.featureDetail!!,
                        obj.list
                    )
                )
            } else {
                whatsNewList.value.add(
                    UpdateAvailableItem(
                        obj?.date!!,
                        objOuter.newVersion!!,
                        obj.featureDetail!!,
                        arrayList
                    )
                )
            }
        }
    }
    whatsNewList.value.reverse()
    WhatsNewItem(context, onClick = {
    }, itemAtPos = whatsNewList)
}