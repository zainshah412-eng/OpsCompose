package com.ops.airportr.common.utils

import android.app.LocaleManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.CountDownTimer
import android.os.LocaleList
import android.provider.Settings
import android.text.Html
import android.text.Spanned
import android.text.format.DateFormat
import android.text.format.DateUtils
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavController
import com.ops.airportr.AppApplication
import com.ops.airportr.R
import com.ops.airportr.common.AppConstants
import com.ops.airportr.domain.model.language.LanguageListItemModel
import org.jsoup.Jsoup
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

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


fun String.convertIntoRelativeDateTakeCustody(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    //inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    val dateTimeFormatString = "d/MM, HH:mm"
    val timeFormatString = "HH:mm"

    val date = inputFormat.parse(this)
    val timeInMilies = date?.time
    var relativeString = ""
    when {
        DateUtils.isToday(date?.time!!) -> {
            LoggerUtils.info("Extensions", "Today")
            relativeString = "Today ${DateFormat.format(timeFormatString, timeInMilies!!)}"
        }

        isYesterday(date) -> {
            LoggerUtils.info("Extensions", "Yesterday")
            relativeString = "Yesterday ${DateFormat.format(timeFormatString, timeInMilies!!)}"
        }

        isTomorrow(date) -> {
            LoggerUtils.info("Extensions", "Tomorrow")
            relativeString = "Tomorrow ${DateFormat.format(timeFormatString, timeInMilies!!)}"
        }

        else -> {
            relativeString = DateFormat.format(dateTimeFormatString, timeInMilies!!).toString()

        }
    }
    return relativeString
}

fun String.convertIntoRelativeDateWithTimeSlot(context: Context, slot: Int): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    Log.d("Slot", TimeUnit.MINUTES.toMillis(slot.toLong()).toString())
    val dateTimeFormatString1 = "d/MM, HH:mm"
    val dateTimeFormatString = "HH:mm"
    val timeFormatString = "HH:mm"

    val date = inputFormat.parse(this)
    val timeInMilies = date?.time
    val timeInMiliesAfterAddition = date?.time?.plus(TimeUnit.MINUTES.toMillis(slot.toLong()))
    var relativeString = ""
    when {
        DateUtils.isToday(date?.time!!) -> {
            LoggerUtils.info("Extensions", "Today")
            relativeString = context.getString(
                R.string.today
            ) + " ${DateFormat.format(timeFormatString, timeInMilies!!)}-${
                DateFormat.format(
                    timeFormatString,
                    timeInMiliesAfterAddition!!
                )
            }"
//            relativeString = "Today ${DateFormat.format(timeFormatString, timeInMilies!!)}-${
//                DateFormat.format(
//                    timeFormatString,
//                    timeInMiliesAfterAddition!!
//                )
//            }"
        }

        isYesterday(date) -> {
            LoggerUtils.info("Extensions", "Yesterday")
            relativeString = context.getString(
                R.string.yesterday
            ) + " ${
                DateFormat.format(
                    timeFormatString,
                    timeInMilies!!
                )
            }-${DateFormat.format(timeFormatString, timeInMiliesAfterAddition!!)}"
        }

        isTomorrow(date) -> {
            LoggerUtils.info("Extensions", "Tomorrow")
            relativeString = context.getString(
                R.string.tomorrow
            ) + " ${
                DateFormat.format(
                    timeFormatString,
                    timeInMilies!!
                )
            }-${DateFormat.format(timeFormatString, timeInMiliesAfterAddition!!)}"
        }

        else -> {
            relativeString = "${
                DateFormat.format(dateTimeFormatString1, timeInMilies!!).toString()
            }-${DateFormat.format(dateTimeFormatString, timeInMiliesAfterAddition!!)}"

        }
    }
    return relativeString
}


fun String.convertIntoRelativeDateWithTimeSlotInDays(context: Context): String {
    var minusCarry = ""
    var returnValue = ""
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val date = inputFormat.parse(this)
    val convertDateInMillies = date?.time
    val currentTimeInMillies = System.currentTimeMillis()
    val minusValues = convertDateInMillies?.minus(currentTimeInMillies)
    if (minusValues != null) {
        if (minusValues > 0) {
            LoggerUtils.info("Ext", "Positive value: $minusValues")
            minusCarry = ""
            returnValue =
                context.resources.getString(R.string.in_text) + " " + getDurationBreakdownWithContext(
                    context, minusValues
                )
        } else {
            LoggerUtils.info("Ext", "Negative value: $minusValues")
            minusCarry = ""
            returnValue =
                getDurationBreakdownWithContext(
                    context,
                    minusValues * -1
                ) + " " + context.resources.getString(R.string.ago_text)
        }
    }
    return minusCarry + returnValue
}

fun String.convertDateWithTimeSlotInDaysForNewPassengerDetail(context: Context): String {
    var minusCarry = ""
    var returnValue = ""
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val date = inputFormat.parse(this)
    val convertDateInMillies = date?.time
    val currentTimeInMillies = System.currentTimeMillis()
    val minusValues = convertDateInMillies?.minus(currentTimeInMillies)
    if (minusValues != null) {
        if (minusValues > 0) {
            LoggerUtils.info("Ext", "Positive value: $minusValues")
            minusCarry = ""
            returnValue =
                getDurationBreakdownWithContext(
                    context, minusValues
                )
        } else {
            LoggerUtils.info("Ext", "Negative value: $minusValues")
            minusCarry = ""
            returnValue =
                getDurationBreakdownWithContext(
                    context,
                    minusValues * -1
                )
        }
    }
    return minusCarry + returnValue
}

fun String.convertIntoDateTimeFormat(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    //inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    val dateTimeFormatString = "HH:mm, d MMM"

    val date = inputFormat.parse(this)
    val timeInMilies = date?.time
    var relativeString = ""
    relativeString = DateFormat.format(dateTimeFormatString, timeInMilies!!).toString()
    return relativeString
}

fun String.convertTimeIntoTimer(txtTime: TextView): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    val dateTimeFormatString = "MMM d, h:mm aa"
    val timeFormatString = "h:mm aa"

    val date = inputFormat.parse(this)
    val timeInMilies = date?.time
    LoggerUtils.info("EXT", "time in Millies: $timeInMilies")
    var relativeString = ""
    when {
        DateUtils.isToday(date?.time!!) -> {
            LoggerUtils.info("Extensions", "Today")
            relativeString = timeInMilies?.let { countDownTimer(it, txtTime) }.toString()
        }

        isYesterday(date) -> {
            LoggerUtils.info("Extensions", "Yesterday")
            relativeString = timeInMilies?.let { countDownTimer(it, txtTime) }.toString()
        }

        isTomorrow(date) -> {
            LoggerUtils.info("Extensions", "Tomorrow")
            relativeString = timeInMilies?.let { countDownTimer(it, txtTime) }.toString()
        }

        else -> {
            relativeString = DateFormat.format(dateTimeFormatString, timeInMilies!!).toString()
            txtTime.text = "Next Session $relativeString"
        }
    }
    return relativeString
}


fun countDownTimer(millies: Long, txtTime: TextView): Boolean {
    var currentTime = millies - System.currentTimeMillis()
    var callTheApi = false
    val countDownTimer = object : CountDownTimer(currentTime, 1000) {
        override fun onTick(p0: Long) {
            val millis: Long = p0
            val hms = String.format(
                "%02d:%02d:%02d",
                (TimeUnit.MILLISECONDS.toHours(millis) - TimeUnit.DAYS.toHours(
                    TimeUnit.MILLISECONDS.toDays(
                        millis
                    )
                )),
                (TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))),
                (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                ))
            )
            txtTime.text = "Time remaining: $hms"
            callTheApi = false
        }

        override fun onFinish() {
            txtTime.text = "Session is Live now"
            System.out.println("Time up")
            callTheApi = true
        }
    }
    countDownTimer.start()
    return callTheApi
}

fun Context.convertIntoRelativeDate(timeinMillies: Long): String {
    var timeMultiplies = timeinMillies * 1000
    var relativeString = ""
    val timeFormatString = "h:mm aa"
    val dateTimeFormatString = "EEE, MMM d yyyy, h:mm aa"

    when {
        DateUtils.isToday(timeMultiplies) -> {
            LoggerUtils.info("Extensions", "Today")
            relativeString = "Today ${DateFormat.format(timeFormatString, timeMultiplies)}"
        }

        DateUtils.isToday(timeMultiplies + DateUtils.DAY_IN_MILLIS) -> {
            LoggerUtils.info("Extensions", "Yesterday")
            relativeString = "Yesterday ${DateFormat.format(timeFormatString, timeMultiplies)}"
        }

        DateUtils.isToday(timeMultiplies - DateUtils.DAY_IN_MILLIS) -> {
            LoggerUtils.info("Extensions", "Tomorrow")
            relativeString = "Tomorrow ${DateFormat.format(timeFormatString, timeMultiplies)}"
        }

        else -> {
            relativeString = DateFormat.format(dateTimeFormatString, timeMultiplies).toString()

        }
    }
    return relativeString
}

fun isYesterday(d: Date): Boolean {
    return DateUtils.isToday(d.time + DateUtils.DAY_IN_MILLIS)
}

fun isTomorrow(d: Date): Boolean {
    return DateUtils.isToday(d.time - DateUtils.DAY_IN_MILLIS)
}

fun isGetDate(d: Date): Int {
    return (d.time - DateUtils.DAY_IN_MILLIS).toInt()
}

fun getFormattedDate(smsTimeInMilis: Long, relativeString: String): String {
    val smsTime = Calendar.getInstance()
    smsTime.timeInMillis = smsTimeInMilis
    val now = Calendar.getInstance()
    val timeFormatString = "h:mm aa"
    val dateTimeFormatString = "EEE, MMM d, h:mm aa"
    val HOURS = (60 * 60 * 60).toLong()
    return if (now[Calendar.DATE] == smsTime[Calendar.DATE]) {
        "Today " + DateFormat.format(timeFormatString, smsTime)
    } else if (now[Calendar.DATE] - smsTime[Calendar.DATE] == 1) {
        "Yesterday " + DateFormat.format(timeFormatString, smsTime)
    } else if (now[Calendar.YEAR] == smsTime[Calendar.YEAR]) {
        DateFormat.format(dateTimeFormatString, smsTime).toString()
    } else {
        DateFormat.format("MMMM dd yyyy, h:mm aa", smsTime).toString()
    }
}

fun String.html2Text(): String {
    return Jsoup.parse(this).text();
}

@RequiresApi(Build.VERSION_CODES.N)
fun String.text2Html(): Spanned? {
    return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
}

fun getDurationBreakdownWithContext(context: Context, millis: Long): String {
    var millis = millis
    require(millis >= 0) { "Duration must be greater than zero!" }
    val days = TimeUnit.MILLISECONDS.toDays(millis)
    millis -= TimeUnit.DAYS.toMillis(days)
    val hours = TimeUnit.MILLISECONDS.toHours(millis)
    millis -= TimeUnit.HOURS.toMillis(hours)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
    millis -= TimeUnit.MINUTES.toMillis(minutes)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(millis)
    val sb = StringBuilder(64)
    if (days > 0) {
        sb.append(days)
        sb.append(" " + context.resources.getString(R.string.days_text) + " ")

    }
    if (hours > 0) {
        sb.append(hours)
        sb.append(" " + context.resources.getString(R.string.hours_text) + " ")
    }
    sb.append(minutes)
    sb.append(" " + context.resources.getString(R.string.minutes_text) + " ")
    //sb.append(seconds)
    //sb.append(" Seconds")
    return sb.toString()
}

fun getJulianToSimpleDate(julianDate: String): String {
    val date = SimpleDateFormat("yyD", Locale.ENGLISH).parse(julianDate)
    return SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date!!)
}

fun String.changeDateFormat(): String {
    val date = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).parse(this)
    return SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date!!)
}
