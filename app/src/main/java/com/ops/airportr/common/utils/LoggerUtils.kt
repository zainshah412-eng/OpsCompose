package com.ops.airportr.common.utils

import android.annotation.SuppressLint
import android.util.Log
import com.ops.airportr.common.AppConstants.SHOW_CONSOLE_LOGS

@SuppressLint("LogNotTimber")
object LoggerUtils {

    fun debug(tag: String?, message: String) {
        if (SHOW_CONSOLE_LOGS) {
            Log.d(tag, message)
        }
    }

    fun error(tag: String?, message: String) {
        error(tag, message, null)
    }

    fun error(tag: String?, message: String, th: Throwable?) {
        if (SHOW_CONSOLE_LOGS) {
            Log.e(tag, message, th)
        }
    }

    fun verbose(tag: String?, message: String) {
        if (SHOW_CONSOLE_LOGS) {
            Log.v(tag, message)
        }
    }
    fun info(tag: String?, message: String) {
        if (SHOW_CONSOLE_LOGS) {
            Log.i(tag, message)
        }
    }
}