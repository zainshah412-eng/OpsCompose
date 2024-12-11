package com.ops.airportr.common.utils

import android.app.LocaleManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavController
import com.ops.airportr.AppApplication
import com.ops.airportr.domain.model.language.LanguageListItemModel
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

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

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

