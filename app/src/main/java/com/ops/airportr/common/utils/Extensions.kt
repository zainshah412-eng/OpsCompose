package com.ops.airportr.common.utils

import android.app.LocaleManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.LocaleList
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavController
import com.ops.airportr.AppApplication
import com.ops.airportr.common.AppConstants
import com.ops.airportr.domain.model.language.LanguageListItemModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun NavController.moveOnNewScreen(id: String, isClear: Boolean = false) {
    navigate(id) {
        if (isClear) {
            popUpTo(this@moveOnNewScreen.currentDestination?.id!!) {
                inclusive = true
            }
        }
    }
}

fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
        AppConstants.EMAIL_ADDRESS.matcher(this.trim()).matches()
fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


fun getDeviceUUID(context: Context): String {
    return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
}

fun getNetworkType(context: Context): String {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val capabilities = connectivityManager.getNetworkCapabilities(network)

    return when {
        capabilities == null -> "No Internet"
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "Wi-Fi"
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "Cellular"
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> "Ethernet"
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> "Bluetooth"
        else -> "Unknown"
    }
}

fun isCameraPermissionAllowed(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED
}

fun isLocationPermissionAllowed(context: Context): Boolean {
    return (ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED &&(ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED))

}



fun changeLanguage(languageName: LanguageListItemModel, context: Context) {
    when (languageName.languageName) {
        "English" -> {
            AppApplication.sessionManager.saveAppLanguage("en")
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                context.getSystemService(LocaleManager::class.java)
                    .applicationLocales = LocaleList.forLanguageTags("en")
            }else {
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("en"))
            }
        }

        "Deutsch" -> {
            AppApplication.sessionManager.saveAppLanguage("de")
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                context.getSystemService(LocaleManager::class.java)
                    .applicationLocales = LocaleList.forLanguageTags("de")
            }else {
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("de"))
            }
        }

        "Français" -> {
            AppApplication.sessionManager.saveAppLanguage("fr")
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                context.getSystemService(LocaleManager::class.java)
                    .applicationLocales = LocaleList.forLanguageTags("fr")
            }else {
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("fr"))
            }
        }

    }
}
fun Context.urlForAcceptance(): String {
    val env = AppApplication.sessionManager.baseUrl?.env
    val url = when {
        env.equals("-uat") -> {
            AppConstants.PRODUCTION_URL_UAT
        }

        env.equals("-dev") -> {
            AppConstants.PRODUCTION_URL_DEV
        }

        else -> {
            AppConstants.PRODUCTION_URL_LIVE
        }
    }
    return url
}
fun getCurrentTimeStampIntoFormat(): String {
    val sdfDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val now = Date()
    return sdfDate.format(now)
}