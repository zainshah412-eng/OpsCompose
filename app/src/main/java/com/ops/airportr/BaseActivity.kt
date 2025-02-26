package com.ops.airportr

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.ops.airportr.common.utils.extension.getCurrentTimeDifferenceForSessionOut
import com.ops.airportr.common.utils.extension.getCurrentTimeStampIntoFormat
import com.ops.airportr.common.utils.extension.showSnackBarJobCancelledActionMultilines
import com.ops.airportr.domain.model.user.GeoCoord
import com.ops.airportr.domain.model.user.User
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity
import com.zeugmasolutions.localehelper.LocaleHelper
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegate
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegateImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import java.util.Locale

@AndroidEntryPoint
abstract class BaseActivity : LocaleAwareCompatActivity() {
    private var user: User? = null
    open lateinit var loaderDialog: Dialog
    private var lastUserLocation = GeoCoord(0.0, 0.0)
    lateinit var local: Locale
    lateinit var localeUpdatedContext: ContextWrapper
    var isNightModeOn: Boolean = false
    var isNightThemeModeOn: Boolean = false
    private val localeDelegate: LocaleHelperActivityDelegate = LocaleHelperActivityDelegateImpl()
    private var coroutineScope: Job? = null
    private val MY_PERMISSIONS_REQUEST_LOCATION = 123
    var snackBarCancel: Snackbar? = null
    private val PERMISSION_CODE = 1000
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    private val locationRequest = LocationRequest.create().apply {
        interval = 10000 // 10 seconds
        fastestInterval = 5000 // 5 seconds
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localeDelegate.onCreate(this)
//        isNightModeOn = AppApplication.sessionManager.appTheme
//        if (!AppApplication.sessionManager.systemThemeEnable) {
//            if (isNightModeOn) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                isNightThemeModeOn = true
//
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                isNightThemeModeOn = false
//
//            }
//        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult?.lastLocation?.let { location ->
                    // Handle updated location
                    AppApplication.sessionManager.saveLastLocation(
                        GeoCoord(
                            location.latitude,
                            location.longitude
                        )
                    )
                        Log.wtf("LAT",location.latitude.toString())
                        Log.wtf("LON",location.longitude.toString())
                    if (AppApplication.sessionManager.jobCanceledNotification) {
                        if (AppApplication.sessionManager.isJobCanceledSnackBarNull) {
                            AppApplication.sessionManager.notificationCancelledBookingReference?.let {
                                if (!it.isNullOrEmpty()) {
                                    var bookingDetailFromDb =
                                        AppApplication.sessionManager.activeBookingDetails
                                    if (it == bookingDetailFromDb?.bookingReference) {
                                        AppApplication.sessionManager.removeActiveBookingDetail()
                                        AppApplication.sessionManager.saveToastTimer(true)
                                        AppApplication.sessionManager.saveDurationAndDistance(
                                            0L,
                                            0L
                                        )
                                      //  viewModelBase.updateTimeAndDistanceMapbox(0.0)
                                    }

                                    var languageType =
                                        AppApplication.sessionManager.jobCanceledNotificationLanguage
                                    var msg = ""
                                    if (languageType == "0") {
                                        msg =
                                            "Your assigned booking $it has been cancelled. Please contact your supervisor if you have already started this job."
                                    } else if (languageType == "1") {
                                        msg = "La réservation qui vous a été attribuée " + it +
                                                " a été annulée. Veuillez contacter votre superviseur si vous avez déjà commencé ce travail."

                                    } else {
                                        msg = "Ihre zugewiesene Buchung " + it +
                                                " wurde storniert. Bitte wenden Sie sich an Ihren Vorgesetzten, wenn Sie diese Stelle bereits begonnen haben."
                                    }

                                    if (AppApplication.sessionManager.isLoggedIn) {
                                        snackBarCancel = showSnackBarJobCancelledActionMultilines(
                                            msg,
                                            it,
                                            window.decorView.rootView,
                                            this@BaseActivity
                                        )!!
                                    }
                                }
                            }
                        }
                    }
//                    MapBoxCallbacks().getTimeAndDistanceFromDestination(
//                        applicationContext,
//                        viewModelBase
//                    )
                }
            }

            override fun onLocationAvailability(p0: LocationAvailability) {
                super.onLocationAvailability(p0)
            }

        }




        updateDataFromDb()
        try {
            lastUserLocation = AppApplication.sessionManager.getLastUserLocation
        } catch (e: Exception) {
            // handler
        } finally {
            // optional finally block
        }
        if (getCurrentTimeDifferenceForSessionOut(AppApplication.sessionManager.lastAppUseTime!!)) {
            AppApplication.sessionManager.logoutUser()
            Log.d("LastTimeAccessStatus", "YES")
        } else {
            Log.d("LastTimeAccessStatus", "NO")
            AppApplication.sessionManager.saveLastAppUseTime(getCurrentTimeStampIntoFormat())
        }

        verifyPermissions(this)
    }


    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun verifyPermissions(activity: Activity): Boolean? {
        var bSomethingFound = false
        val permissons = java.util.ArrayList<String>()

        //Storage
        if (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissons.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            permissons.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            bSomethingFound = true
        }
        if (activity.checkSelfPermission(Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.BLUETOOTH_PRIVILEGED) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.BLUETOOTH_ADVERTISE) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.ACCESS_NOTIFICATION_POLICY) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.ACCESS_NOTIFICATION_POLICY) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.FOREGROUND_SERVICE) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.FOREGROUND_SERVICE_LOCATION) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.WAKE_LOCK) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.MODIFY_AUDIO_SETTINGS) != PackageManager.PERMISSION_GRANTED
            || activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            permissons.add(Manifest.permission.BLUETOOTH)
            permissons.add(Manifest.permission.BLUETOOTH_ADMIN)
            permissons.add(Manifest.permission.USE_BIOMETRIC)
            permissons.add(Manifest.permission.USE_FINGERPRINT)
            permissons.add(Manifest.permission.POST_NOTIFICATIONS)
            permissons.add(Manifest.permission.BLUETOOTH_PRIVILEGED)
            permissons.add(Manifest.permission.BLUETOOTH_SCAN)
            permissons.add(Manifest.permission.BLUETOOTH_CONNECT)
            permissons.add(Manifest.permission.BLUETOOTH_ADVERTISE)
            permissons.add(Manifest.permission.POST_NOTIFICATIONS)
            permissons.add(Manifest.permission.CAMERA)
            permissons.add(Manifest.permission.CALL_PHONE)
            permissons.add(Manifest.permission.SEND_SMS)
            permissons.add(Manifest.permission.READ_PHONE_STATE)
            permissons.add(Manifest.permission.ACCESS_COARSE_LOCATION)
            permissons.add(Manifest.permission.ACCESS_NOTIFICATION_POLICY)
            permissons.add(Manifest.permission.FOREGROUND_SERVICE)
            permissons.add(Manifest.permission.FOREGROUND_SERVICE_LOCATION)
            permissons.add(Manifest.permission.WAKE_LOCK)
            permissons.add(Manifest.permission.RECORD_AUDIO)
            permissons.add(Manifest.permission.MODIFY_AUDIO_SETTINGS)
            permissons.add(Manifest.permission.ACCESS_FINE_LOCATION)
            bSomethingFound = true
        } else {
            bSomethingFound = false
        }
        if (bSomethingFound) {
            //Ask permissions
            val items = permissons.toTypedArray()
            activity.requestPermissions(items, 1)
            return false
        }
        return true
    }

    private fun updateDataFromDb() {
        try {
            // some code
            user = AppApplication.sessionManager.userDetails
        } catch (e: Exception) {
            // handler
        } finally {
            // optional finally block
        }
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, start location updates
                    startLocationUpdates()
                } else {
                    // Permission denied
                    // Handle the case where the user denies the permission
                }
                return
            }
        }
    }
    override fun onResume() {
        super.onResume()
        AppApplication.sessionManager.saveIsJobCanceledSnackBarNull(true)
        localeDelegate.onResumed(this)
     //   checkLocationPermission()
        startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
        localeDelegate.onPaused()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun createConfigurationContext(overrideConfiguration: Configuration): Context {
        val context = super.createConfigurationContext(overrideConfiguration)
        return LocaleHelper.onAttach(context)
    }

    override fun getApplicationContext(): Context =
        localeDelegate.getApplicationContext(super.getApplicationContext())

    override fun updateLocale(locale: Locale) {
        localeDelegate.setLocale(this, locale)
    }

    fun verifyStoragePermissions() {
        // check runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this
                    .checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_DENIED
            ) {
                //permission not granted, request it
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                this
                    .requestPermissions(permissions, PERMISSION_CODE)
            } else {
                //permission already granted
                //   pickFileFromStorage()
            }
        } else {
            //system os is less then marshmellow
            //  pickFileFromStorage()
        }
    }
}