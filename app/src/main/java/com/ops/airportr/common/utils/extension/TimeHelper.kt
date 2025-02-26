package com.ops.airportr.common.utils.extension

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.text.format.DateFormat
import android.text.format.DateUtils
import android.util.Log
import android.widget.TextView
import com.ops.airportr.R
import com.ops.airportr.common.utils.LoggerUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

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

fun String.convertDateWithoutZoneTime(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)

    val date = inputFormat.parse(this)
    val formattedDate = outputFormat.format(date!!)
    return formattedDate
}

fun String.convertDateForBagCheckIn(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)

    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)
}

fun String.convertDateForHHMM(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)

    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)
}

fun String.convertDateInto_dd_MMM_yyyy(): String? {
    if (this.isNullOrEmpty())
        return ""
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    val date = inputFormat.parse(this)
    return outputFormat.format(date)
}

fun String.convertDateFormatForNotes(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.ENGLISH)

    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)
}
fun String.convertDateForBagDetail(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("dd MMM yyyy 'at' HH:mm", Locale.ENGLISH)

    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)
}

fun String.convertIntoRelativeDateForAcceptanceDetail(context: Context): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    //inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    val dateTimeFormatString = "HH:mm, d/MM"
    val timeFormatString = "HH:mm"

    val date = inputFormat.parse(this)
    val timeInMilies = date?.time
    var relativeString = ""
    when {
        DateUtils.isToday(date?.time!!) -> {
            LoggerUtils.info("Extensions", "Today")
            relativeString = "${
                DateFormat.format(
                    timeFormatString,
                    timeInMilies!!
                )
            } " + context.getString(R.string.today)
        }

        isYesterday(date) -> {
            LoggerUtils.info("Extensions", "Yesterday")
            relativeString = "${DateFormat.format(timeFormatString, timeInMilies!!)} " +
                    context.getString(R.string.yesterday)
        }

        isTomorrow(date) -> {
            LoggerUtils.info("Extensions", "Tomorrow")
            relativeString = "${DateFormat.format(timeFormatString, timeInMilies!!)} " +
                    context.getString(R.string.tomorrow)
        }

        else -> {
            relativeString = DateFormat.format(dateTimeFormatString, timeInMilies!!).toString()

        }
    }
    return relativeString
}

fun getCurrentTimeDifferenceForSessionOut(oldDateAndTime: String): Boolean {
    val dateFormater = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    var status = false
    if (oldDateAndTime != "") {
        try {
            val old: Date = dateFormater.parse(oldDateAndTime)
            val now = Date()

            val diff: Long = now.getTime() - old.getTime()
            val seconds = diff / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            val days = hours / 24
            status = hours > 8

        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
    return status
}

@SuppressLint("SimpleDateFormat")
fun checkForTimeInMillisToCheckTimeSlot(date1: String, date2: String): Boolean {
    var isInSlot: Boolean = false
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val startDate: Date? = sdf.parse(date1)
    val endDate: Date? = sdf.parse(date2)
    val currentDate = Date()
    isInSlot = currentDate > startDate && currentDate < endDate
    return isInSlot
}

fun String.compareDates(): String {
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
            returnValue = getDurationBreakdown(minusValues)
        } else {
            LoggerUtils.info("Ext", "Negative value: $minusValues")
            minusCarry = "-"
            returnValue = getDurationBreakdown(minusValues * -1)
        }
    }
    return minusCarry + returnValue
}

fun getDurationBreakdown(millis: Long): String {
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
        sb.append(" Days ")

    }
    if (hours > 0) {
        sb.append(hours)
        sb.append(" Hours ")
    }
    sb.append(minutes)
    sb.append(" Minutes ")
    //sb.append(seconds)
    //sb.append(" Seconds")
    return sb.toString()
}