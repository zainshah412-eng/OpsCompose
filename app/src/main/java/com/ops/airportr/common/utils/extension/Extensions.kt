package com.ops.airportr.common.utils.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.app.LocaleManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.CountDownTimer
import android.os.LocaleList
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.Html
import android.text.Spanned
import android.text.format.DateFormat
import android.text.format.DateUtils
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavController
import com.google.android.material.snackbar.Snackbar
import com.ops.airportr.AppApplication
import com.ops.airportr.R
import com.ops.airportr.common.AppConstants
import com.ops.airportr.common.utils.LoggerUtils
import com.ops.airportr.domain.model.language.LanguageListItemModel
import org.jsoup.Jsoup
import java.text.ParseException
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

@SuppressLint("MissingPermission")
fun getNetworkType(context: Context): String {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork
    val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)

    if (networkCapabilities != null) {
        when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                return "WiFi"
            }

            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                val telephonyManager =
                    context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                return when (telephonyManager.dataNetworkType) {
                    TelephonyManager.NETWORK_TYPE_LTE -> "MobileData4G"
                    TelephonyManager.NETWORK_TYPE_NR -> "MobileData5G"
                    TelephonyManager.NETWORK_TYPE_HSPAP,
                    TelephonyManager.NETWORK_TYPE_HSDPA,
                    TelephonyManager.NETWORK_TYPE_HSUPA,
                    TelephonyManager.NETWORK_TYPE_UMTS -> "MobileData3G"
                    TelephonyManager.NETWORK_TYPE_EDGE,
                    TelephonyManager.NETWORK_TYPE_GPRS -> "MobileData2G"

                    else -> "Unknown Cellular Network"
                }
            }
        }
    }
    return "No Network"
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
    ) == PackageManager.PERMISSION_GRANTED && (ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED))

}


fun changeLanguage(languageName: LanguageListItemModel, context: Context) {
    when (languageName.languageName) {
        "English" -> {
            AppApplication.sessionManager.saveAppLanguage("en")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.getSystemService(LocaleManager::class.java)
                    .applicationLocales = LocaleList.forLanguageTags("en")
            } else {
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("en"))
            }
        }

        "Deutsch" -> {
            AppApplication.sessionManager.saveAppLanguage("de")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.getSystemService(LocaleManager::class.java)
                    .applicationLocales = LocaleList.forLanguageTags("de")
            } else {
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("de"))
            }
        }

        "Français" -> {
            AppApplication.sessionManager.saveAppLanguage("fr")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.getSystemService(LocaleManager::class.java)
                    .applicationLocales = LocaleList.forLanguageTags("fr")
            } else {
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

fun Context.urlForCCD(): String {
    val env = AppApplication.sessionManager.baseUrl?.env
    val url = when {
        env.equals("-uat") -> {
            AppConstants.PRODUCTION_URL_CCD_UAT
        }

        env.equals("-dev") -> {
            AppConstants.PRODUCTION_URL_CCD_DEV
        }

        else -> {
            AppConstants.PRODUCTION_URL_CCD
        }
    }
    return url
}






fun String.html2Text(): String {
    return Jsoup.parse(this).text();
}

@RequiresApi(Build.VERSION_CODES.N)
fun String.text2Html(): Spanned? {
    return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
}


fun removeLastChar(s: String?): String {
    return if (s == null || s.length == 0) "" else s.substring(0, s.length - 1)
}

fun sealReturn(array: ArrayList<String>): String {
    val sealsList = java.lang.StringBuilder(10000)
    if (array.size > 2) {
        for (obj in 0..1) {
            sealsList.append(array[obj] + ",")
        }
    } else {
        for (obj in array) {
            sealsList.append(obj + ",")
        }
    }
    return sealsList.toString()
}

fun getActionType(context: Context, value: String): String {
    return when (value) {
        "1" -> {
            context.resources.getString(R.string.accept_label)
        }

        "2" -> {
            context.resources.getString(R.string.check_id)
        }

        "3" -> {
            context.resources.getString(R.string.print_tags)
        }

        "4" -> {
            context.resources.getString(R.string.add_seals)
        }

        "5" -> {
            context.resources.getString(R.string.update_seal)
        }

        "6" -> {
            context.resources.getString(R.string.add_iata_tag)
        }

        "7" -> {
            context.resources.getString(R.string.take_picture)
        }

        "8" -> {
            context.resources.getString(R.string.take_declaration)
        }

        "9" -> {
            context.resources.getString(R.string.take_signature)
        }

        "10" -> {
            context.resources.getString(R.string.check_boarding_pass)
        }

        "11" -> {
            context.resources.getString(R.string.transport_pick_up)
        }

        "12" -> {
            context.resources.getString(R.string.transport_drop_off)
        }

        "13" -> {
            context.resources.getString(R.string.add_storage_location)
        }

        "14" -> {
            context.resources.getString(R.string.repatriate)
        }

        "15" -> {
            context.resources.getString(R.string.injected)
        }

        "16" -> {
            context.resources.getString(R.string.record_weight)
        }

        "17" -> {
            context.resources.getString(R.string.generate_boarding_pass)
        }

        "18" -> {
            context.resources.getString(R.string.screening)
        }

        "19" -> {
            context.resources.getString(R.string.record_iata_tag)
        }

        "20" -> {
            context.resources.getString(R.string.remove_seal)
        }

        "21" -> {
            context.resources.getString(R.string.add_luggage_image)
        }

        "22" -> {
            context.resources.getString(R.string.add_acceptance_signature_image)
        }

        "23" -> {
            context.resources.getString(R.string.add_repatriate_signature_image)
        }

        "24" -> {
            context.resources.getString(R.string.record_max_weight)
        }

        "25" -> {
            context.resources.getString(R.string.take_custody)
        }

        "26" -> {
            context.resources.getString(R.string.add_passport_image)
        }

        "27" -> {
            context.resources.getString(R.string.downgrade_passenger_luggage)
        }

        "28" -> {
            context.resources.getString(R.string.commented_in_fly)
        }

        "29" -> {
            context.resources.getString(R.string.place_in_storage)
        }

        "30" -> {
            context.resources.getString(R.string.remove_passenger_luggage)
        }

        "31" -> {
            context.resources.getString(R.string.update_job_activity_status)
        }

        "32" -> {
            context.resources.getString(R.string.add_recipient_name)
        }

        "33" -> {
            context.resources.getString(R.string.report_damage)
        }

        "34" -> {
            context.resources.getString(R.string.pre_validated)
        }

        "35" -> {
            context.resources.getString(R.string.upgrade_passenger_luggage)
        }

        "36" -> {
            context.resources.getString(R.string.booking_validated)
        }

        "37" -> {
            context.resources.getString(R.string.updated_champion)
        }

        "38" -> {
            context.resources.getString(R.string.add_boarding_pass_image)
        }

        "39" -> {
            context.resources.getString(R.string.add_photo_id_image)
        }

        "40" -> {
            context.resources.getString(R.string.excess_baggage_payment)
        }

        "41" -> {
            context.resources.getString(R.string.add_excess_baggage_payment_receipt_image)
        }

        "42" -> {
            context.resources.getString(R.string.agree_excess_baggage_charges)
        }

        "43" -> {
            context.resources.getString(R.string.override_enabled)
        }

        "44" -> {
            context.resources.getString(R.string.add_passenger_luggage)
        }

        "45" -> {
            context.resources.getString(R.string.add_luggage_xray)
        }

        "46" -> {
            context.resources.getString(R.string.conditional_acceptance)
        }

        "47" -> {
            context.resources.getString(R.string.conditional_acceptance_checked_in)
        }

        "48" -> {
            context.resources.getString(R.string.conditional_acceptance_reassigned)
        }

        "49" -> {
            context.resources.getString(R.string.add_dcs_iata_tag)
        }

        "50" -> {
            context.resources.getString(R.string.get_default_baggage_allowance)
        }

        "51" -> {
            context.resources.getString(R.string.injected_using_iata_tag)
        }

        "52" -> {
            context.resources.getString(R.string.delete_dcs_iata_tag)
        }

        "53" -> {
            context.resources.getString(R.string.report_missing_bag)
        }

        "54" -> {
            context.resources.getString(R.string.scan_bags)
        }

        "55" -> {
            context.resources.getString(R.string.bag_collected)
        }

        "56" -> {
            context.resources.getString(R.string.bags_delivered)
        }

        "57" -> {
            context.resources.getString(R.string.processed_customs_charge)
        }

        "58" -> {
            context.resources.getString(R.string.bag_with_customs)
        }

        "59" -> {
            context.resources.getString(R.string.add_customs_payment_receipt_image)
        }

        "60" -> {
            context.resources.getString(R.string.bulk_injection_validated)
        }

        "61" -> {
            context.resources.getString(R.string.bags_delivered_by_aks)
        }

        "62" -> {
            context.resources.getString(R.string.hand_over_by_aks)
        }

        "63" -> {
            context.resources.getString(R.string.add_boarding_pass_scanned)
        }

        "64" -> {
            context.resources.getString(R.string.add_boarding_pass_manual)
        }

        "65" -> {
            context.resources.getString(R.string.add_bag)
        }

        "66" -> {
            context.resources.getString(R.string.add_child_equipment)
        }

        "67" -> {
            context.resources.getString(R.string.add_oversize_item)
        }

        "68" -> {
            context.resources.getString(R.string.validated_using_iata_tag)
        }

        "69" -> {
            context.resources.getString(R.string.bag_not_damaged)
        }

        "70" -> {
            context.resources.getString(R.string.driver_job_has_started)
        }

        "71" -> {
            context.resources.getString(R.string.driver_arrived_at_doorstep)
        }

        "72" -> {
            context.resources.getString(R.string.driver_has_left_the_geolocation_fence)
        }

        "73" -> {
            context.resources.getString(R.string.bags_placed_in_storage)
        }

        "74" -> {
            context.resources.getString(R.string.bags_collected_from_storage)
        }

        "75" -> {
            context.resources.getString(R.string.identify_bag_scan)
        }

        "76" -> {
            context.resources.getString(R.string.custody_bag_scan)
        }

        "77" -> {
            context.resources.getString(R.string.take_custody_scan)
        }

        "78" -> {
            context.resources.getString(R.string.fast_inject_scan)
        }

        "79" -> {
            context.resources.getString(R.string.place_in_storage_scan)
        }

        "80" -> {
            context.resources.getString(R.string.accept_bag_scan)
        }


        else -> {
            value
        }
    }


}

fun getAgentActionType(
    context: Context,
    value: String,
    bookingReference: String = "",
    actionValue: String = ""
): String {
    var amountPaid = 0.0
    return when (value) {
        "1" -> {
            context.resources.getString(R.string.address_is_amended)
        }

        "2" -> {
            context.resources.getString(R.string.bag_type_is_amended)
        }

        "3" -> {
            context.resources.getString(R.string.time_slot_is_amended)
        }

        "4" -> {
            context.resources.getString(R.string.bag_count_is_amended)
        }

        "5" -> {
            context.resources.getString(R.string.email_address_is_amended)
        }

        "6" -> {
            context.resources.getString(R.string.passenger_name_is_amended)
        }

        "7" -> {
            context.resources.getString(R.string.passenger_phone_number_is_amended)
        }

        "8" -> {
            context.resources.getString(R.string.customer_declaration_signed_is_amended)
        }

        "9" -> {
            context.resources.getString(R.string.iata_tag_is_uploaded)
        }

        "10" -> {
            context.resources.getString(R.string.customer_checked_in)
        }

        "11" -> {
            context.resources.getString(R.string.booking_is_cancelled)
        }

        "12" -> {
            context.resources.getString(
                R.string.booking_cancel_push_msg_eng,
                bookingReference
            )
        }

        "13" -> {
            if (actionValue.isNotEmpty()) {
                amountPaid = actionValue.toDouble() / 100
            }
            context.resources.getString(
                R.string.new_booking_payment, amountPaid.toString(),
            )
        }

        "14" -> {
            if (actionValue.isNotEmpty()) {
                amountPaid = actionValue.toDouble() / 100
            }
            context.resources.getString(
                R.string.excess_charge_payment, amountPaid.toString()
            )
        }

        "15" -> {
            if (actionValue.isNotEmpty()) {
                amountPaid = actionValue.toDouble() / 100
            }
            context.resources.getString(
                R.string.amend_booking_payment, amountPaid.toString()
            )
        }

        "16" -> {
            if (actionValue.isNotEmpty()) {
                amountPaid = actionValue.toDouble() / 100
            }
            context.resources.getString(
                R.string.driver_tip_payment, amountPaid.toString()
            )
        }

        "17" -> {
            if (actionValue.isNotEmpty()) {
                amountPaid = actionValue.toDouble() / 100
            }
            context.resources.getString(
                R.string.extra_bags_payment, amountPaid.toString()
            )
        }

        "18" -> {
            if (actionValue.isNotEmpty()) {
                amountPaid = actionValue.toDouble() / 100
            }
            context.resources.getString(
                R.string.custom_charge_payment, amountPaid.toString()
            )
        }

        "19" -> {
            if (actionValue.isNotEmpty()) {
                amountPaid = actionValue.toDouble() / 100
            }
            context.resources.getString(
                R.string.oversize_charge_payment, amountPaid.toString()
            )
        }

        else -> {
            value
        }
    }
}

fun getScanType(context: Context, value: Int?): String {
    return when (value) {
        1 -> "${context.resources.getString(R.string.iata)}"
        2 -> "${context.resources.getString(R.string.seal)}"
        else -> {
            "$value"
        }
    }
}

fun Context.getCommunicationEmailActionType(value: String): String {
    return when (value) {
        "1" -> {
            getString(R.string.acceptance_email)
        }

        "2" -> {
            getString(R.string.injection_email)
        }

        "3" -> {
            getString(R.string.complete_booking_email)
        }

        "4" -> {
            getString(R.string.luggage_downgrade_email)
        }

        "5" -> {
            getString(R.string.repatriation_email)
        }

        "6" -> {
            getString(R.string.reset_password_email)
        }

        "7" -> {
            getString(R.string.cancelled_within_eligible_window)
        }

        "8" -> {
            getString(R.string.cancelled_outside_eligible_window)
        }

        "9" -> {
            getString(R.string.account_confirmation_email)
        }

        "10" -> {
            getString(R.string.set_password_email)
        }

        "11" -> {
            getString(R.string.hour_before_collection_email)
        }

        "12" -> {
            getString(R.string.day_before_collection_email)
        }

        "13" -> {
            getString(R.string.downgrade_enroute)
        }

        "14" -> {
            getString(R.string.amend_booking_email)
        }

        "15" -> {
            getString(R.string.collect_repatriate_bag_email)
        }

        "16" -> {
            getString(R.string.account_confirmation_after_booking)
        }

        "17" -> {
            getString(R.string.return_journey_booking)
        }

        "18" -> {
            getString(R.string.excess_payment_email)
        }

        "19" -> {
            getString(R.string.refund_failure_email)
        }

        "20" -> {
            getString(R.string.excess_payment_manual_email)
        }

        "21" -> {
            getString(R.string.driver_prepare_for_arrival)
        }

        "22" -> {
            getString(R.string.user_login_email)
        }

        "23" -> {
            getString(R.string.welcome_home_email)
        }

        "24" -> {
            getString(R.string.upsell_return)
        }

        "25" -> {
            getString(R.string.promo_code_eligible_user)
        }

        "26" -> {
            getString(R.string.promo_code_in_eligible_user)
        }

        "27" -> {
            getString(R.string.excess_payment_fail_email)
        }

        "28" -> {
            getString(R.string.arrival_after_closing_time)
        }

        "29" -> {
            getString(R.string.upsell_payment_failure)
        }

        "30" -> {
            getString(R.string.conditional_acceptance_reminder)
        }

        "31" -> {
            getString(R.string.conditional_acceptance_final_reminder)
        }

        "32" -> {
            getString(R.string.downgrade_not_checked_in)
        }

        "33" -> {
            getString(R.string.downgrade_not_checked_in_system)
        }

        "34" -> {
            getString(R.string.conditional_acceptance_checked_in)
        }

        "35" -> {
            getString(R.string.exception)
        }

        "36" -> {
            getString(R.string.driver_tip_payment_receipt)
        }

        "37" -> {
            getString(R.string.extra_bag_doorstep_payment_failure)
        }

        "38" -> {
            getString(R.string.extra_bag_doorstep_payment)
        }

        "39" -> {
            getString(R.string.onfleet_short_notice_booking)
        }

        "40" -> {
            getString(R.string.declaration_confirmation_email)
        }

        "41" -> {
            getString(R.string.delivery_confirmation_email)
        }

        "42" -> {
            getString(R.string.mandatory_steps_reminder_email)
        }

        "43" -> {
            getString(R.string.customs_charges_receipt_email)
        }

        "44" -> {
            getString(R.string.customs_requisition_notification_email)
        }

        "45" -> {
            getString(R.string.cancellation_notice_bags_no_show_email)
        }

        "46" -> {
            getString(R.string.cancellation_notice_with_no_refund_email)
        }

        "47" -> {
            getString(R.string.unsigned_cancellation)
        }

        "48" -> {
            getString(R.string.missing_bags)
        }

        "49" -> {
            getString(R.string.customs_charge_failure)
        }

        "50" -> {
            getString(R.string.upload_iata_reminder)
        }

        "51" -> {
            getString(R.string.first_payment_link_email)
        }

        "52" -> {
            getString(R.string.urgent_payment_link_email)
        }

        else -> {
            value
        }
    }

}

fun Context.getCommunicationSMSActionType(value: String): String {
    return when (value) {
        "1" -> {
            getString(R.string.injection_sms)
        }

        "2" -> {
            getString(R.string.repatriation_sms)
        }

        "3" -> {
            getString(R.string.booking_confirmation)
        }

        "4" -> {
            getString(R.string.driver_prepare_for_arrival)
        }

        "5" -> {
            getString(R.string.driver_arrived)
        }

        "6" -> {
            getString(R.string.driver_early)
        }

        "7" -> {
            getString(R.string.driver_early_acceptance_confirmation)
        }

        "8" -> {
            getString(R.string.driver_late)
        }

        "9" -> {
            getString(R.string.driver_late_without_eta)
        }

        "10" -> {
            getString(R.string.bag_reassigned_late_sms)
        }

        "11" -> {
            getString(R.string.verify_user)
        }

        "12" -> {
            getString(R.string.return_journey_sms)
        }

        "13" -> {
            getString(R.string.inbound_arrival_sms)
        }

        "14" -> {
            getString(R.string.airport_info_sms)
        }

        "15" -> {
            getString(R.string.return_journey_ba_data_link_sms)
        }

        "16" -> {
            getString(R.string.up_sell_return_unknown_flight)
        }

        "17" -> {
            getString(R.string.up_sell_return_reconsider)
        }

        "18" -> {
            getString(R.string.inbound_arrival_after_closing_time)
        }

        "19" -> {
            getString(R.string.conditional_acceptance_reminder)
        }

        "20" -> {
            getString(R.string.conditional_acceptance_final_reminder)
        }

        "21" -> {
            getString(R.string.downgrade_not_checked_in)
        }

        "22" -> {
            getString(R.string.conditional_acceptance_checked_in)
        }

        "23" -> {
            getString(R.string.driver_rating)
        }

        "24" -> {
            getString(R.string.driver_tip)
        }

        "25" -> {
            getString(R.string.driver_tip_payment_receipt)
        }

        "26" -> {
            getString(R.string.driver_tip_payment_failure_receipt)
        }

        "27" -> {
            getString(R.string.custom_sms_text)
        }

        "28" -> {
            getString(R.string.day_before_flight_checkin_reminder)
        }

        "29" -> {
            getString(R.string.covid_document_upload_reminder)
        }

        "30" -> {
            getString(R.string.customs_declaration_verification_code)
        }

        "31" -> {
            getString(R.string.airport_arrival)
        }

        "32" -> {
            getString(R.string.delivery)
        }

        "33" -> {
            getString(R.string.mandatory_steps_reminder)
        }

        "34" -> {
            getString(R.string.handover)
        }

        "35" -> {
            getString(R.string.collection)
        }

        "36" -> {
            getString(R.string.missing_bag)
        }

        "37" -> {
            getString(R.string.driver_eta)
        }

        "38" -> {
            getString(R.string.upload_iata_reminder)
        }

        "39" -> {
            getString(R.string.notify_driver_for_canceled_booking)
        }

        "41" -> {
            getString(R.string.first_payment_link_sms)
        }

        "42" -> {
            getString(R.string.urgent_payment_link_sms)
        }

        else -> {
            value
        }
    }
}

fun Context.getAgentActionTypeImage1(value: String, actorType: Int): Int {
    return when (value) {
        "1", "2", "3", "4", "5", "6", "7", "8" -> {
            when (actorType) {
                1, 2 -> {
                    R.drawable.ic_amended_booking
                }

                3 -> {
                    R.drawable.ic_booking_amend_customer
                }

                else -> {
                    R.drawable.ic_booking_amend_customer
                }
            }
        }

        "9" -> {
            R.drawable.ic_iata_tag_upload
        }

        "10" -> {
            R.drawable.ic_customer_check_in
        }

        "11" -> {
            when (actorType) {
                1, 2 -> {
                    R.drawable.ic_booking_cancelled_agent
                }

                3 -> {
                    R.drawable.ic_booking_cancel_customer
                }

                else -> {
                    R.drawable.ic_booking_cancelled_agent
                }
            }
        }

        "12" -> {
            R.drawable.ic_push_notification
        }

        "13", "14", "15", "16", "17", "18", "19" -> {
            when (actorType) {
                1, 2 -> {
                    R.drawable.ic_green_credit_card
                }

                3 -> {
                    R.drawable.ic_pink_credit_card
                }

                else -> {
                    R.drawable.ic_purple_credit_card
                }
            }
        }

        else -> {
            R.drawable.ic_amended_booking
        }
    }
}

fun Context.getAgentActionName(value: String, actorType: Int, name: String): String {
    return when (value) {
        "13", "14", "15", "16", "17", "18", "19", "20" -> {
            when (actorType) {
                1 -> {
                    getString(
                        R.string.auto_payment
                    )
                }

                2 -> {
                    getString(
                        R.string.assisted_payment, name
                    )
                }

                3 -> {
                    getString(
                        R.string.customer_payment
                    )
                }

                else -> {
                    getString(
                        R.string.auto_payment
                    )
                }
            }
        }

        else -> {
            name
        }
    }
}


fun String.getJobTypeInjectDetails(context: Context): String {
    return when {
        this == "1" -> {
            context.getString(
                R.string.no_collected
            )
        }

        this == "2" -> {
            context.getString(
                R.string.waiting_for_pickup
            )
        }

        this == "3" -> {
            context.getString(
                R.string.en_route
            )
        }

        this == "4" -> {
            context.getString(
                R.string.secure_storage
            )
        }

        this == "5" -> {
            context.getString(
                R.string.delivered
            )
        }

        else -> {
            context.getString(
                R.string.complete
            )
        }
    }
}

fun Context?.isDoomed(): Boolean = when (this) {
    null -> true
    is Activity -> (this.isDestroyed or this.isFinishing)
    else -> false
}

fun maskPhoneNumber(phoneNumber: String): String {
    return if (phoneNumber.length > 4) {
        phoneNumber.replaceRange(2, phoneNumber.length - 1, "*".repeat(phoneNumber.length - 3))
    } else {
        return phoneNumber // If the phone number is too short to mask
    }

}

fun Context.getAirlineLogo(operatingAirlineCode: String): Int {
    when (operatingAirlineCode) {
        resources.getString(R.string.american_airline) -> {
            return R.drawable.american
        }

        resources.getString(R.string.virgin_atlantic) -> {
            return R.drawable.virgin
        }

        resources.getString(R.string.qantas) -> {
            return R.drawable.qantas
        }

        resources.getString(R.string.singapor_airline) -> {
            return R.drawable.singapore_airline
        }

        resources.getString(R.string.cathay_pacific) -> {
            return R.drawable.cathay
        }

        resources.getString(R.string.british_airways) -> {
            return R.drawable.british
        }

        resources.getString(R.string.lufthansa_airways) -> {
            return R.drawable.ic_lufthansa_logo_2018__1_
        }

        resources.getString(R.string.easyjet) -> {
            return R.drawable.easyjet
        }

        resources.getString(R.string.swiss_airline) -> {
            return R.drawable.swiss_logo
        }

        resources.getString(R.string.austrian_airline) -> {
            return R.drawable.ic_austrian_2018
        }

        resources.getString(R.string.finnair) -> {
            return R.drawable.finnair_bg
        }

        resources.getString(R.string.edelweiss) -> {
            return R.drawable.logo_edelweiss
        }

        resources.getString(R.string.helvetic) -> {
            return R.drawable.logo_helvetic
        }

        resources.getString(R.string.air_france) -> {
            return R.drawable.air_france
        }

        resources.getString(R.string.klm_airline) -> {
            return R.drawable.klm_airline
        }

        resources.getString(R.string.emirates_airline) -> {
            return R.drawable.emirates_logo
        }

        resources.getString(R.string.aegean) -> {
            return R.drawable.aegean
        }

        resources.getString(R.string.air_canada) -> {
            return R.drawable.aircanada
        }

        resources.getString(R.string.air_europa) -> {
            return R.drawable.aireuropa
        }

        resources.getString(R.string.delta) -> {
            return R.drawable.delta
        }

        resources.getString(R.string.ita_airways) -> {
            return R.drawable.ita
        }

        resources.getString(R.string.tap_portugal) -> {
            return R.drawable.tap
        }

        resources.getString(R.string.turkish_airlines) -> {
            return R.drawable.turkish
        }

        resources.getString(R.string.united_airlines) -> {
            return R.drawable.united
        }

        resources.getString(R.string.thai_airways_international) -> {
            return R.drawable.thai
        }

        resources.getString(R.string.brussels_airlines) -> {
            return R.drawable.brussels
        }

        else -> {
            return R.drawable.placeholder
        }
    }
}





fun Context.checkLocationEnabled(): Boolean {
    val locationManager: LocationManager =
        getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
        LocationManager.NETWORK_PROVIDER
    )
}

fun Context.showSnackBarJobCancelledActionMultilines(
    text: String,
    bookingRef: String,
    view: View,
    context: Context
): Snackbar? {
    val parentLayout = view.findViewById<View>(android.R.id.content)
    val snackBar: Snackbar = Snackbar
        .make(parentLayout, text, Snackbar.LENGTH_INDEFINITE)
        .setAction(getString(R.string.cross)) {
            AppApplication.sessionManager.jobCanceledNotification(false)
            AppApplication.sessionManager.saveIsJobCanceledSnackBarNull(true)
        }
    val sbView = snackBar.view
    val textView = sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    val themeColor = if (AppApplication.sessionManager.appTheme) {
        ContextCompat.getColor(this, R.color.air_orange100)
    } else {
        ContextCompat.getColor(this, R.color.light_orange)
    }
    val textColor = if (AppApplication.sessionManager.appTheme) {
        ContextCompat.getColor(this, R.color.air_orange200)
    } else {
        ContextCompat.getColor(this, R.color.air_orange_dark)
    }
    snackBar.view.setBackgroundColor(themeColor)
    textView.setTextColor(textColor)
    textView.maxLines = 5
    snackBar.setActionTextColor(Color.parseColor("#EA6B43")) // Adjust color as needed

    val params = sbView.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.TOP
    sbView.layoutParams = params
    snackBar.view.setOnClickListener {
        // Start your desired activity or fragment here
        snackBar.dismiss()
    }
    snackBar.show()
    AppApplication.sessionManager.saveIsJobCanceledSnackBarNull(false)

    return snackBar
}


fun Context.formatDuration(seconds: Double): String {
    val minute = 60
    val hour = minute * 60
    val day = hour * 24
    val month = day * 30 // Approximate number of days in a month
    val year = month * 12 // Approximate number of months in a year

    return when {
        seconds < minute -> "$seconds ${getSecondsStringText(seconds.toInt())}"
        seconds < hour -> {
            val minutes = kotlin.math.ceil(seconds / minute).toInt()
            "$minutes ${getMinutesStringText(minutes)}"
        }

        seconds < day -> {
            val hours = (seconds / hour).toInt()
            val remainingMinutes = ((seconds % hour) / minute).toInt()
            "$hours ${getHoursStringText(hours)}, $remainingMinutes ${
                getMinutesStringText(
                    remainingMinutes
                )
            }"
        }

        seconds < month -> {
            val days = (seconds / day).toInt()
            val remainingHours = ((seconds % day) / hour).toInt()
            "$days ${getDaysStringText(days)}, $remainingHours ${
                getHoursStringText(
                    remainingHours
                )
            }"
        }

        seconds < year -> {
            val months = (seconds / month).toInt()
            val remainingDays = ((seconds % month) / day).toInt()
            "$months ${getMonthsStringText(months)}, $remainingDays ${
                getDaysStringText(
                    remainingDays
                )
            }"
        }

        else -> {
            val years = (seconds / year).toInt()
            val remainingMonths = ((seconds % year) / month).toInt()
            "$years ${getYearsStringText(years)}, $remainingMonths ${
                getMonthsStringText(
                    remainingMonths
                )
            }"
        }
    }
}
fun Context.getSecondsStringText(value: Int): String {
    return if (value > 1) getString(R.string.seconds_label) else getString(
        R.string.second_label
    )
}

fun Context.getDaysStringText(value: Int): String {
    return if (value > 1) getString(R.string.days_label) else getString(
        R.string.day_label
    )
}

fun Context.getHoursStringText(value: Int): String {
    return if (value > 1) getString(R.string.hours_label) else getString(
        R.string.hour_label
    )
}

fun Context.getMinutesStringText(value: Int): String {
    return if (value > 1) getString(R.string.minutes_label) else getString(
        R.string.minute_label
    )
}

fun Context.getMonthsStringText(value: Int): String {
    return if (value > 1) getString(R.string.months_label) else getString(
        R.string.month_label
    )
}

fun Context.getYearsStringText(value: Int): String {
    return if (value > 1) getString(R.string.years_label) else getString(
        R.string.year_label
    )
}
