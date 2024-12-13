package com.ops.airportr.common.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.google.gson.Gson
import com.ops.airportr.domain.model.BaseUrl
import com.ops.airportr.domain.model.login.AuthTokenResp
import com.ops.airportr.domain.model.login.logincred.LoginCred
import com.ops.airportr.domain.model.user.User

class SessionManager(
    // Context
    var _context: Context,
)
{
    var pref: SharedPreferences

    // Editor for Shared preferences
    var editor: SharedPreferences.Editor

    // Shared pref mode
    var PRIVATE_MODE = 0

    /**
     * Create login session
     *
     * @param id is identification number of record data in database
     * @param name of user
     * @param work is title or role in company
     * @param about is simple description of user
     * @param username is a account id for credential
     * @param token is a secret key which generated from id and username
     */

    fun createLoginSession(
        authTokenResp: AuthTokenResp,
    ) {
//        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_ACCESS_TOKEN, authTokenResp.accessToken)
        editor.putString(KEY_TOKEN_TYPE, authTokenResp.tokenType)
        authTokenResp.expiresIn?.let { editor.putInt(KEY_EXPIRES_IN, it) }

        editor.commit()
    }

    fun saveIsLogIn(isLogIn: Boolean) {
        editor.remove(IS_LOGIN)
        editor.putBoolean(IS_LOGIN, isLogIn)
        editor.commit()
    }

    fun saveSessionFor2FA(key: String) {
        editor.remove(KEY_2FA_SESSION)
        editor.putString(KEY_2FA_SESSION, key)
        editor.commit()
    }

    fun saveEmail(key: String) {
        editor.remove(KEY_EMAIL)
        editor.putString(KEY_EMAIL, key)
        editor.commit()
    }

    fun saveUserDetails(user: User) {
        editor.remove(KEY_USER_DETAILS)
        val json = Gson().toJson(user)
        editor.putString(KEY_USER_DETAILS, json)
        editor.commit()
    }
//
//    fun saveAppVersions(appVersions: WhatsNewResponse) {
//        editor.remove(KEY_APP_VERSIONS)
//        val json = Gson().toJson(appVersions)
//        editor.putString(KEY_APP_VERSIONS, json)
//        editor.commit()
//    }
//
    fun saveBaseUrl(url: BaseUrl) {
        editor.remove(KEY_BASE_URL)
        val json = Gson().toJson(url)
        editor.putString(KEY_BASE_URL, json)
        editor.commit()
    }

    fun saveLoginCred(loginCred: LoginCred) {
        editor.remove(KEY_LOGIN_CRED)
        val json = Gson().toJson(loginCred)
        editor.putString(KEY_LOGIN_CRED, json)
        editor.commit()
    }

    fun saveSubscriptionKey(key: String) {
        editor.remove(KEY_SUBSCRIPTION)
        editor.putString(KEY_SUBSCRIPTION, key)
        editor.commit()
    }

    fun saveAirPortIdAndName(id: String, terminal: String, name: String) {
        editor.remove(KEY_AIRPORT_ID)
        editor.remove(KEY_AIRPORT_NAME)
        editor.remove(KEY_AIRPORT_TERMINAL)
        editor.putString(KEY_AIRPORT_ID, id)
        editor.putString(KEY_AIRPORT_NAME, terminal)
        editor.putString(KEY_AIRPORT_TERMINAL, name)
        editor.commit()
    }

//    fun saveViewJobDetails(viewJobDetails: ViewJobResponse) {
//        editor.remove(KEY_VIEW_JOB_DETAILS)
//        val json = Gson().toJson(viewJobDetails)
//        editor.putString(KEY_VIEW_JOB_DETAILS, json)
//        editor.commit()
//    }
//
//    fun saveBookingDetails(bookingDetails: BookingDetails?) {
//        editor.remove(KEY_BOOKING_DETAILS)
//        editor.commit()
//        val json = Gson().toJson(bookingDetails)
//        editor.putString(KEY_BOOKING_DETAILS, json)
//        editor.commit()
//    }
//
//    fun saveActiveBookingDetails(bookingDetails: BookingDetails?) {
//        editor.remove(KEY_ACTIVE_BOOKING_DETAILS)
//        editor.commit()
//        val json = Gson().toJson(bookingDetails)
//        editor.putString(KEY_ACTIVE_BOOKING_DETAILS, json)
//        editor.commit()
//    }

    fun removeActiveBookingDetail() {
        editor.remove(KEY_ACTIVE_BOOKING_DETAILS)
        editor.commit()
    }

    fun saveDurationAndDistance(dis: Long, dur: Long) {
        editor.remove(KEY_DURATION)
        editor.remove(KEY_DISTANCE)
        editor.commit()
        editor.putLong(KEY_DURATION, dur)
        editor.putLong(KEY_DISTANCE, dis)
        editor.commit()
    }

    fun saveTimer(time: Int) {
        editor.remove(KEY_TIMER)
        editor.commit()
        editor.putInt(KEY_TIMER, time)
        editor.commit()
    }


    fun saveTrackingApi(flag: Boolean) {
        editor.remove(KEY_CALL_TRACKING_API)
        editor.commit()
        editor.putBoolean(KEY_CALL_TRACKING_API, flag)
        editor.commit()
    }

    fun jobCanceledNotification(flag: Boolean) {
        editor.remove(KEY_JOB_NOTIFICATION)
        editor.commit()
        editor.putBoolean(KEY_JOB_NOTIFICATION, flag)
        editor.commit()
    }

    fun biometricEnabled(flag: Boolean) {
        editor.remove(KEY_BIOMETRIC_STATUS)
        editor.commit()
        editor.putBoolean(KEY_BIOMETRIC_STATUS, flag)
        editor.commit()
    }

    fun jobCanceledNotificationLanguage(flag: String) {
        editor.remove(KEY_JOB_NOTIFICATION_LANGUAGE)
        editor.commit()
        editor.putString(KEY_JOB_NOTIFICATION_LANGUAGE, flag)
        editor.commit()
    }

    fun saveIsJobCanceledSnackBarNull(flag: Boolean) {
        editor.remove(KEY_JOB_CANCEL_NOTIFICATION_SNACKBAR)
        editor.commit()
        editor.putBoolean(KEY_JOB_CANCEL_NOTIFICATION_SNACKBAR, flag)
        editor.commit()
    }

    fun saveNotificationJobCancelBookingReference(bookingRef: String) {
        editor.remove(KEY_JOB_CANCEL_NOTIF_BOOKING_REF)
        editor.commit()
        editor.putString(KEY_JOB_CANCEL_NOTIF_BOOKING_REF, bookingRef)
        editor.commit()
    }

    fun saveJobCancelBookingReference(bookingRef: String) {
        editor.remove(KEY_CANCELED_BOOKING_REF)
        editor.commit()
        editor.putString(KEY_CANCELED_BOOKING_REF, bookingRef)
        editor.commit()
    }

    fun saveJobStartDateAndTime(bookingRef: String) {
        editor.remove(KEY_JOB_START_DATE_AND_TIME)
        editor.commit()
        editor.putString(KEY_JOB_START_DATE_AND_TIME, bookingRef)
        editor.commit()
    }

//    fun saveCCDBookingDetails(bookingDetails: CCDBagDetailsModel) {
//        editor.remove(KEY_CCD_BOOKING_DETAILS)
//        editor.commit()
//        val json = Gson().toJson(ccdBookingDetails)
//        editor.putString(KEY_CCD_BOOKING_DETAILS, json)
//        editor.commit()
//    }

    fun saveCurrentChampionId(currentChampId: String) {
        editor.remove(KEY_CURRENT_CHAMPION_ID)
        editor.putString(KEY_CURRENT_CHAMPION_ID, currentChampId)
        editor.commit()
    }

//    fun saveAddPassengerModelData(addPassengerModelData: AddPassengerModelData) {
//        editor.remove(KEY_ADD_PASSENGER_MODEL_VALUE)
//        editor.commit()
//        val json = Gson().toJson(addPassengerModelData)
//        editor.putString(KEY_ADD_PASSENGER_MODEL_VALUE, json)
//        editor.commit()
//    }

    fun saveCurrentFlow(isAcceptance: Boolean) {
        editor.putBoolean(IS_FLOW, true)
        editor.putBoolean(IS_ACCEPTANCE, isAcceptance)
        editor.commit()
    }

    fun saveEnvironmentType(environment: Int) {
        editor.putInt(ENVIRONMENT_TYPE, environment)
        editor.commit()
    }

    fun saveLastAppUseTime(time: String) {
        editor.remove(KEY_LAST_APP_USED_TIME)
        editor.putString(KEY_LAST_APP_USED_TIME, time)
        editor.commit()
    }
//
//    fun saveLastLocation(coords: GeoCoord) {
//        editor.remove(KEY_LATITUDE)
//        editor.remove(KEY_LONGITUDE)
//        editor.putString(KEY_LATITUDE, coords.latitude.toString())
//        editor.putString(KEY_LONGITUDE, coords.longitude.toString())
//        editor.commit()
//    }
//
//    fun saveTagInjectHeaderCustomModel(listTagInjectHeaderCustomModel: ArrayList<TagInjectHeaderCustomModel>) {
//        editor.remove(KEY_TAG_INJECT_HEADER_CUSTOM_MODEL)
//        val json = Gson().toJson(listTagInjectHeaderCustomModel)
//        editor.putString(KEY_TAG_INJECT_HEADER_CUSTOM_MODEL, json)
//        editor.commit()
//    }

    fun clearTagInjectHeaderCustomModel() {
        editor.remove(KEY_TAG_INJECT_HEADER_CUSTOM_MODEL)
        editor.commit()
    }

    fun saveAppLanguage(lang: String) {
        editor.remove(KEY_APP_LANGUAGE)
        editor.putString(KEY_APP_LANGUAGE, lang)
        editor.commit()
    }

    fun saveToastTimer(time: Boolean) {
        editor.remove(KEY_TIMER_TOAST)
        editor.commit()
        editor.putBoolean(KEY_TIMER_TOAST, time)
        editor.commit()
    }

    fun saveSystemLanguage(systemLanguage: String) {
        editor.remove(KEY_SYSTEM_LANGUAGE)
        editor.putString(KEY_SYSTEM_LANGUAGE, systemLanguage)
        editor.commit()
    }

    fun saveAppTheme(isNightModeOn: Boolean) {
        editor.remove(KEY_APP_THEME)
        editor.putBoolean(KEY_APP_THEME, isNightModeOn)
        editor.commit()
    }

    fun saveSystemTheme(isNightModeOn: Boolean) {
        editor.remove(KEY_SYSTEM_THEME)
        editor.putBoolean(KEY_SYSTEM_THEME, isNightModeOn)
        editor.commit()
    }

    fun saveSystemThemeEnable(systemThemeEnable: Boolean) {
        editor.remove(KEY_SYSTEM_THEME_ENABLE)
        editor.putBoolean(KEY_SYSTEM_THEME_ENABLE, systemThemeEnable)
        editor.commit()
    }

    fun saveAppLanguageSpinnerPosition(position: Int) {
        editor.remove(KEY_APP_LANGUAGE_SPINNER_POSITION)
        editor.putInt(KEY_APP_LANGUAGE_SPINNER_POSITION, position)
        editor.commit()
    }

    fun saveAppThemeSpinnerPosition(position: Int) {
        editor.remove(KEY_APP_THEME_SPINNER_POSITION)
        editor.putInt(KEY_APP_THEME_SPINNER_POSITION, position)
        editor.commit()
    }

//    fun saveCustodyHeaderCustomModel(listTagInjectHeaderCustomModel: ArrayList<TagInjectHeaderCustomModel>) {
//        editor.remove(KEY_CUSTODY_HEADER_CUSTOM_MODEL)
//        val json = Gson().toJson(listTagInjectHeaderCustomModel)
//        editor.putString(KEY_CUSTODY_HEADER_CUSTOM_MODEL, json)
//        editor.commit()
//    }
//
//    fun saveCustodyABCCCDModel(listCustodyHeaderCustomModel: ArrayList<MyHeaderCustomModel>) {
//        editor.remove(KEY_CUSTODY_ABCCCD_CUSTOM_MODEL)
//        val json = Gson().toJson(listCustodyHeaderCustomModel)
//        editor.putString(KEY_CUSTODY_ABCCCD_CUSTOM_MODEL, json)
//        editor.commit()
//    }

    fun saveOnFleetDriverEmail(email: String) {
        editor.remove(KEY_ON_FLEET_DRIVER_EMAIL)
        editor.putString(KEY_ON_FLEET_DRIVER_EMAIL, email)
        editor.commit()
    }

    fun getOnFleetDriverEmail(): String {
        return pref.getString(KEY_ON_FLEET_DRIVER_EMAIL, "") ?: ""
    }

    fun saveOnFleetDriverPass(pass: String) {
        editor.remove(KEY_ON_FLEET_DRIVER_PASS)
        editor.putString(KEY_ON_FLEET_DRIVER_PASS, pass)
        editor.commit()
    }

    fun getOnFleetDriverPass(): String {
        return pref.getString(KEY_ON_FLEET_DRIVER_PASS, "") ?: ""
    }

    fun getOnFleetBookingKey(): String {
        return pref.getString(KEY_ON_FLEET_BOOKING_REF, "") ?: ""
    }

    fun getPnrForLxKey(): String {
        return pref.getString(KEY_PNR_LX, "") ?: ""
    }

    fun savePnrForLxKey(flag: String) {
        editor.remove(KEY_PNR_LX)
        editor.putString(KEY_PNR_LX, flag)
        editor.commit()
    }

    fun saveOnFleetBookingKey(flag: String) {
        editor.remove(KEY_ON_FLEET_BOOKING_REF)
        editor.putString(KEY_ON_FLEET_BOOKING_REF, "")
        editor.commit()
    }

    fun saveCheckInValue(checkInStatus: String) {
        editor.remove(KEY_CHECKIN_VALUE)
        editor.putString(KEY_CHECKIN_VALUE, checkInStatus)
        editor.commit()
    }


    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     *
     * @return boolean
     */
//    fun checkLogin(): Boolean {
//        // Check login status
//        if (!isLoggedIn) {
//            // user is not logged in redirect him to Login Activity
//            val i = Intent(_context, MainActivity::class.java)
//            // Closing all the Activities
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//            // Add new Flag to start new Activity
//            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//
//            // Staring Login Activity
//            _context.startActivity(i)
//            return false
//        }
//        return true
//    }

    val authDetails: AuthTokenResp
        get() {
            // return user
            return AuthTokenResp(
                pref.getString(KEY_ACCESS_TOKEN, null),
                pref.getInt(KEY_EXPIRES_IN, 0),
                pref.getString(
                    KEY_TOKEN_TYPE, null
                )
            )
        }

    val userDetails: User
        get() {
            try {
                // some code
                val json: String? = pref.getString(KEY_USER_DETAILS, "")
                return Gson().fromJson(json, User::class.java)
            } catch (e: Exception) {
                // handler
                return return Gson().fromJson("", User::class.java)
            } finally {
                // optional finally block
            }

        }

    val loginCred: LoginCred
        get() {
            return try {
                // Retrieve the JSON string from shared preferences
                val json: String? = pref.getString(KEY_LOGIN_CRED, "")

                // Check if the JSON string is null or empty
                if (json.isNullOrEmpty()) {
                    // Return a default instance of LoginCred if JSON is invalid
                    LoginCred()
                } else {
                    // Parse the JSON string into a LoginCred object
                    Gson().fromJson(json, LoginCred::class.java) ?: LoginCred()
                }
            } catch (e: Exception) {
                // Return a default instance of LoginCred in case of an exception
                LoginCred()
            }
        }

//    val appVersions: WhatsNewResponse
//        get() {
//            try {
//                // some code
//                val json: String? = pref.getString(KEY_APP_VERSIONS, "")
//                return Gson().fromJson(json, WhatsNewResponse::class.java)
//            } catch (e: Exception) {
//                // handler
//                return return Gson().fromJson("", WhatsNewResponse::class.java)
//            } finally {
//                // optional finally block
//            }
//
//        }
//
    val baseUrl: BaseUrl?
        get() {
            val json: String? = pref.getString(KEY_BASE_URL, "")
            return Gson().fromJson(json, BaseUrl::class.java)
        }

    val subscriptionKey: String?
        get() {
            return pref.getString(KEY_SUBSCRIPTION, "")
        }

    val getSession2FA: String?
        get() {
            return pref.getString(KEY_2FA_SESSION, "")
        }

    val getEmail: String?
        get() {
            return pref.getString(KEY_EMAIL, "")
        }

    val airportId: String?
        get() {
            return pref.getString(KEY_AIRPORT_ID, "")
        }

    val airportName: String?
        get() {
            return pref.getString(KEY_AIRPORT_NAME, "")
        }

    val airportTerminal: String?
        get() {
            return pref.getString(KEY_AIRPORT_TERMINAL, "")
        }


//    val viewJobDetails: ViewJobResponse
//        get() {
//            val json: String? = pref.getString(KEY_VIEW_JOB_DETAILS, "")
//            return Gson().fromJson(json, ViewJobResponse::class.java)
//        }
//
//    val getLastUserLocation: GeoCoord
//        get() {
//            var lat = pref.getString(KEY_LATITUDE, "")
//            var lon = pref.getString(KEY_LONGITUDE, "")
//            if (lat == "" || lon == "") {
//                return GeoCoord(
//                    0.0,
//                    0.0
//                )
//            } else {
//                return GeoCoord(
//                    pref.getString(KEY_LATITUDE, "")!!.toDouble(),
//                    pref.getString(KEY_LONGITUDE, "")!!.toDouble()
//                )
//            }
//        }
//
//    val tagInjectHeaderCustomModel: ArrayList<TagInjectHeaderCustomModel>
//        get() {
//            val emptyList = Gson().toJson(ArrayList<TagInjectHeaderCustomModel>())
//            val json: String? = pref.getString(KEY_TAG_INJECT_HEADER_CUSTOM_MODEL, emptyList)
//            val type: Type = object : TypeToken<ArrayList<TagInjectHeaderCustomModel?>?>() {}.type
//            return Gson().fromJson(json, type)
//        }

    val appLanguage: String?
        get() {
            var defaultSystemLang = Resources.getSystem().configuration.locale.language
            return if (defaultSystemLang.equals("fr")) {
                pref.getString(KEY_APP_LANGUAGE, defaultSystemLang)
            } else if (defaultSystemLang.equals("de")) {
                pref.getString(KEY_APP_LANGUAGE, defaultSystemLang)
            } else {
                pref.getString(KEY_APP_LANGUAGE, "en")
            }
        }

    val systemLanguage: String?
        get() {
            return pref.getString(KEY_SYSTEM_LANGUAGE, "")
        }

    val appLanguageSpinnerPosition: Int
        get() {
            return pref.getInt(KEY_APP_LANGUAGE_SPINNER_POSITION, 0)
        }

    val appThemeSpinnerPosition: Int
        get() {
            return pref.getInt(KEY_APP_THEME_SPINNER_POSITION, 0)
        }

    val appTheme: Boolean
        get() {
            return pref.getBoolean(KEY_APP_THEME, false)
        }

    val systemTheme: Boolean
        get() {
            return pref.getBoolean(KEY_SYSTEM_THEME, false)
        }

    val systemThemeEnable: Boolean
        get() {
            return pref.getBoolean(KEY_SYSTEM_THEME_ENABLE, false)
        }

//    val custodyHeaderCustomModel: ArrayList<TagInjectHeaderCustomModel>
//        get() {
//            val emptyList = Gson().toJson(ArrayList<TagInjectHeaderCustomModel>())
//            val json: String? = pref.getString(KEY_CUSTODY_HEADER_CUSTOM_MODEL, emptyList)
//            val type: Type = object : TypeToken<ArrayList<TagInjectHeaderCustomModel?>?>() {}.type
//            return Gson().fromJson(json, type)
//        }
//
//    val custodyHeaderABCCCDCustomModel: ArrayList<MyHeaderCustomModel>
//        get() {
//            val emptyList = Gson().toJson(ArrayList<MyHeaderCustomModel>())
//            val json: String? = pref.getString(KEY_CUSTODY_ABCCCD_CUSTOM_MODEL, emptyList)
//            val type: Type = object : TypeToken<ArrayList<MyHeaderCustomModel?>?>() {}.type
//            return Gson().fromJson(json, type)
//        }

//    val bookingDetails: BookingDetails
//        get() {
//            if (pref.getString(KEY_BOOKING_DETAILS, "") != null) {
//                val json: String? = pref.getString(KEY_BOOKING_DETAILS, "")
//                return Gson().fromJson(json, BookingDetails::class.java)
//            } else {
//                return Gson().fromJson("", BookingDetails::class.java)
//            }
//        }

//    val bookingDetails: BookingDetails?
//        get() {
//            val json: String? = pref.getString(KEY_BOOKING_DETAILS, "")
//            return if (!json.isNullOrEmpty()) {
//                Gson().fromJson(json, BookingDetails::class.java)
//            } else {
//                null
//            }
//        }
//
//
//    val activeBookingDetails: BookingDetails?
//        get() {
//            if (!pref.getString(KEY_ACTIVE_BOOKING_DETAILS, "").isNullOrEmpty()) {
//                val json: String? = pref.getString(KEY_ACTIVE_BOOKING_DETAILS, "")
//                return Gson().fromJson(json, BookingDetails::class.java)
//            } else {
//             return null
//            }
//        }

    val duration: Long
        get() {
            return pref.getLong(KEY_DURATION, 0)
        }
    val trackingApi: Boolean
        get() {
            return pref.getBoolean(KEY_CALL_TRACKING_API, false)
        }

    val jobCanceledNotification: Boolean
        get() {
            return pref.getBoolean(KEY_JOB_NOTIFICATION, false)
        }

    val getBiometricStatus: Boolean
        get() {
            return pref.getBoolean(KEY_BIOMETRIC_STATUS, false)
        }

    val jobCanceledNotificationLanguage: String?
        get() {
            return pref.getString(KEY_JOB_NOTIFICATION_LANGUAGE, "")
        }

    val isJobCanceledSnackBarNull: Boolean
        get() {
            return pref.getBoolean(KEY_JOB_CANCEL_NOTIFICATION_SNACKBAR, true)
        }

    val notificationCancelledBookingReference: String?
        get() {
            return pref.getString(KEY_JOB_CANCEL_NOTIF_BOOKING_REF, "")
        }

    val cancelledBookingReference: String?
        get() {
            return pref.getString(KEY_CANCELED_BOOKING_REF, "")
        }
    val jobStartDateAndTime: String?
        get() {
            return pref.getString(KEY_JOB_START_DATE_AND_TIME, "")
        }

    val distance: Long
        get() {
            return pref.getLong(KEY_DISTANCE, 0)
        }


    val timer: Int
        get() {
            return pref.getInt(KEY_TIMER, 0)
        }

    val toastTimer: Boolean
        get() {
            return pref.getBoolean(KEY_TIMER_TOAST, true)
        }


//    val addPassengerModelData: AddPassengerModelData
//        get() {
//            if (pref.getString(KEY_ADD_PASSENGER_MODEL_VALUE, "") != null) {
//                val json: String? = pref.getString(KEY_ADD_PASSENGER_MODEL_VALUE, "")
//                return Gson().fromJson(json, AddPassengerModelData::class.java)
//            } else {
//                return Gson().fromJson("", AddPassengerModelData::class.java)
//            }
//        }
//
//    val ccdBookingDetails: CCDBagDetailsModel
//        get() {
//            val json: String? = pref.getString(KEY_CCD_BOOKING_DETAILS, "")
//            return Gson().fromJson(json, CCDBagDetailsModel::class.java)
//        }

    val currentChampId: String?
        get() {
            return pref.getString(KEY_CURRENT_CHAMPION_ID, "")
        }
    var environmentType: Int = 0
        get() {
            return pref.getInt(ENVIRONMENT_TYPE, 0)
        }

    val lastAppUseTime: String?
        get() {
            return pref.getString(KEY_LAST_APP_USED_TIME, "")
        }

    val getCheckInValue: String?
        get() {
            return pref.getString(KEY_CHECKIN_VALUE, "")
        }


    /**
     * Clear session details
     */
    fun clearPrefs() {
        editor.clear()
        editor.commit()
    }

    fun logoutUser() {
        var language = appLanguage
        var languagePosition = appLanguageSpinnerPosition

        var appTheme = appTheme
        var themePosition = appThemeSpinnerPosition
        var systemThemeEnable = systemThemeEnable

        // Clearing all data from Shared Preferences
        editor.remove(IS_LOGIN)
        editor.remove(IS_UPADTED)
        editor.remove(IS_ACCEPTANCE)
        editor.remove(IS_FLOW)
        editor.remove(ENVIRONMENT_TYPE)
        editor.remove(IS_APP_USER_SAVED)
        editor.remove(KEY_ID)
        editor.remove(KEY_USER_ID)
        editor.remove(KEY_TOKEN)
        editor.remove(KEY_ACCESS_TOKEN)
        editor.remove(KEY_TOKEN_TYPE)
        editor.remove(KEY_EXPIRES_IN)
        editor.remove(KEY_USER_DETAILS)
        editor.remove(KEY_APP_VERSIONS)
        editor.remove(KEY_VIEW_JOB_DETAILS)
        editor.remove(KEY_TAG_INJECT_HEADER_CUSTOM_MODEL)
        editor.remove(KEY_CUSTODY_HEADER_CUSTOM_MODEL)
        editor.remove(KEY_CUSTODY_ABCCCD_CUSTOM_MODEL)
        editor.remove(KEY_BOOKING_DETAILS)
        editor.remove(KEY_ACTIVE_BOOKING_DETAILS)
        editor.remove(KEY_DURATION)
        editor.remove(KEY_DISTANCE)
        editor.remove(KEY_TIMER)
        editor.remove(KEY_TIMER_TOAST)
        editor.remove(KEY_CALL_TRACKING_API)
        editor.remove(KEY_JOB_NOTIFICATION)
        editor.remove(KEY_JOB_NOTIFICATION_LANGUAGE)
        editor.remove(KEY_JOB_CANCEL_NOTIFICATION_SNACKBAR)
        editor.remove(KEY_JOB_CANCEL_NOTIF_BOOKING_REF)
        editor.remove(KEY_CCD_BOOKING_DETAILS)
        editor.remove(KEY_CURRENT_CHAMPION_ID)
        editor.remove(KEY_CURRENT_FLOW_NAME)
        //  editor.remove(KEY_BASE_URL)
        // editor.remove(KEY_LOGIN_CRED)
        editor.remove(KEY_BASE_URL_ENV)
        editor.remove(KEY_SUBSCRIPTION)
        editor.remove(KEY_LONGITUDE)
        editor.remove(KEY_AIRPORT_ID)
        editor.remove(KEY_AIRPORT_TERMINAL)
        editor.remove(KEY_AIRPORT_NAME)
        editor.remove(KEY_CHECKIN_VALUE)
        editor.remove(KEY_ADD_PASSENGER_MODEL_VALUE)
        editor.remove(KEY_ON_FLEET_DRIVER_EMAIL)
        editor.remove(KEY_ON_FLEET_DRIVER_PASS)
        editor.remove(KEY_ON_FLEET_BOOKING_REF)
        editor.remove(KEY_PNR_LX)
        editor.remove(KEY_CANCELED_BOOKING_REF)
        editor.remove(KEY_JOB_START_DATE_AND_TIME)
        editor.remove(KEY_LAST_APP_USED_TIME)

//        editor.clear()
        editor.commit()

        if (language != null) {
            saveAppLanguage(language)
        }
        saveAppLanguageSpinnerPosition(languagePosition)

        saveAppTheme(appTheme)
        saveAppThemeSpinnerPosition(themePosition)
        saveSystemThemeEnable(systemThemeEnable)

        // After logout redirect user to Login Activity
//        val i = Intent(_context, LoginAct::class.java)
//        // Closing all the Activities
//        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        // Staring Login Activity
//        _context.startActivity(i)
    }

    /**
     * Quick check for login
     */
    val isLoggedIn: Boolean
        get() = pref.getBoolean(IS_LOGIN, false)
    val isAcceptance: Boolean
        get() = pref.getBoolean(IS_ACCEPTANCE, false)
    val isFlowSelected: Boolean
        get() = pref.getBoolean(IS_FLOW, false)

    companion object {
        // Shared pref file name
        private const val PREF_NAME = "SPF_PREF"

        // All Shared Preferences Keys
        private const val IS_LOGIN = "IsLoggedIn"
        private const val IS_UPADTED = "IsUpdated"
        private const val IS_ACCEPTANCE = "IsAcceptance"
        private const val IS_FLOW = "IsFlow"
        private const val ENVIRONMENT_TYPE = "EnviromentType"

        private const val IS_APP_USER_SAVED = "IsAppUserSaved"

        // User id
        const val KEY_ID = "id"

        const val KEY_USER_ID = "userId"

        // User token
        const val KEY_TOKEN = "userToken"
        const val KEY_ACCESS_TOKEN = "accessToken"
        const val KEY_TOKEN_TYPE = "token_type"
        const val KEY_EXPIRES_IN = "expires_in"

        const val KEY_USER_DETAILS = "userDetails"
        const val KEY_APP_VERSIONS = "appVersions"
        const val KEY_VIEW_JOB_DETAILS = "viewJobDetails"
        const val KEY_TAG_INJECT_HEADER_CUSTOM_MODEL = "tagInjectHeaderCustomModel"
        const val KEY_APP_LANGUAGE = "appLanguage"
        const val KEY_SYSTEM_LANGUAGE = "systemLanguage"
        const val KEY_APP_THEME = "keyAppTheme"
        const val KEY_SYSTEM_THEME = "keySystemTheme"
        const val KEY_SYSTEM_THEME_ENABLE = "keySystemThemeEnable"
        const val KEY_APP_LANGUAGE_SPINNER_POSITION = "appLanguageSpinnerPosition"
        const val KEY_APP_THEME_SPINNER_POSITION = "appThemeSpinnerPosition"
        const val KEY_CUSTODY_HEADER_CUSTOM_MODEL = "custodyHeaderCustomModel"
        const val KEY_CUSTODY_ABCCCD_CUSTOM_MODEL = "custodyHeaderABCCCDCustomModel"
        const val KEY_BOOKING_DETAILS = "bookingDetails"
        const val KEY_ACTIVE_BOOKING_DETAILS = "activeBookingDetails"
        const val KEY_DURATION = "duration"
        const val KEY_DISTANCE = "distance"
        const val KEY_TIMER = "timer"
        const val KEY_TIMER_TOAST = "toastTimer"
        const val KEY_CALL_TRACKING_API = "trackingApi"
        const val KEY_JOB_NOTIFICATION = "jobNotification"
        const val KEY_BIOMETRIC_STATUS = "biometricStatus"
        const val KEY_JOB_NOTIFICATION_LANGUAGE = "jobNotificationLanguage"
        const val KEY_JOB_CANCEL_NOTIFICATION_SNACKBAR = "jobNotificationCancelSnackbar"
        const val KEY_JOB_CANCEL_NOTIF_BOOKING_REF = "jobNotificationCancelBookingRef"
        const val KEY_CCD_BOOKING_DETAILS = "CcdBookingDetails"
        const val KEY_CURRENT_CHAMPION_ID = "currentChampionId"
        const val KEY_CURRENT_FLOW_NAME = "currentFlowName"
        const val KEY_LAST_APP_USED_TIME = "lastAppUseTime"
        const val KEY_BASE_URL = "BaseUrl"
        const val KEY_LOGIN_CRED = "LoginCred"
        const val KEY_BASE_URL_ENV = "BaseUrlEnv"
        const val KEY_SUBSCRIPTION = "SubscriptionKey"
        const val KEY_2FA_SESSION = "Session2FA"
        const val KEY_EMAIL = "Email"
        const val KEY_LATITUDE = "LatitudeKey"
        const val KEY_LONGITUDE = "LongitudeKey"
        const val KEY_AIRPORT_ID = "AirportId"
        const val KEY_AIRPORT_TERMINAL = "AirportTerminal"
        const val KEY_AIRPORT_NAME = "AirportName"
        const val KEY_CHECKIN_VALUE = "checkInValue"
        const val KEY_ADD_PASSENGER_MODEL_VALUE = "AddPassengerModelData"
        const val KEY_ON_FLEET_DRIVER_EMAIL = "OnFleetDriverEmail"
        const val KEY_ON_FLEET_DRIVER_PASS = "OnFleetDriverPass"
        const val KEY_ON_FLEET_BOOKING_REF = "onFleetBookingRef"
        const val KEY_PNR_LX = "pnrLx"
        const val KEY_CANCELED_BOOKING_REF = "canceledBookingRef"
        const val KEY_JOB_START_DATE_AND_TIME = "jobStartDateAndTime"

    }

    // Constructor
    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
        editor.apply()
    }
}