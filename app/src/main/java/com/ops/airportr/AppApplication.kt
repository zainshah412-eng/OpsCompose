package com.ops.airportr

import android.app.Application
import com.ops.airportr.common.utils.SessionManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppApplication : Application() {

    companion object {
        lateinit var instance: Application
        lateinit var sessionManager: SessionManager
    }

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        instance = this
        sessionManager = SessionManager(applicationContext)
    }
}