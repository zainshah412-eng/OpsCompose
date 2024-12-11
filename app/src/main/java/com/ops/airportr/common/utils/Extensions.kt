package com.ops.airportr.common.utils

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.navigation.NavController
import com.ops.airportr.AppApplication
import com.ops.airportr.domain.model.language.LanguageListItemModel

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

            val packageManager: PackageManager = context.packageManager
            val intent: Intent = packageManager.getLaunchIntentForPackage(context.packageName)!!
            val componentName: ComponentName = intent.component!!
            val restartIntent: Intent = Intent.makeRestartActivityTask(componentName)
            context.startActivity(restartIntent)
            Runtime.getRuntime().exit(0)
            //  updateLocale(Locales.English)
//            MainScreenAct().updateLocale(Locales.English)

//                val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("eng")
//                AppCompatDelegate.setApplicationLocales(appLocale)

            //         AppCompatDelegate.lo
//                val myLocale = Locale("en")
//                val res: Resources = resources
//                val dm: DisplayMetrics = res.getDisplayMetrics()
//                val conf: Configuration = res.getConfiguration()
//                conf.locale = myLocale
//                res.updateConfiguration(conf, dm)
//                this.onRestart()
        }

        "Deutsch" -> {
            AppApplication.sessionManager.saveAppLanguage("de")
            val packageManager: PackageManager = context.packageManager
            val intent: Intent = packageManager.getLaunchIntentForPackage(context.packageName)!!
            val componentName: ComponentName = intent.component!!
            val restartIntent: Intent = Intent.makeRestartActivityTask(componentName)
            context.startActivity(restartIntent)
            Runtime.getRuntime().exit(0)
            // updateLocale(Locales.German)
//            MainScreenAct().updateLocale(Locales.German)

//                val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("de")
//            //    AppCompatDelegate.setApplicationLocales(appLocale)
//
//                val config = resources.configuration
//                val lang = "de" // your language code
//                val locale = Locale(lang)
//                Locale.setDefault(locale)
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
//                    config.setLocale(locale)
//                else
//                    config.locale = locale
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//                    createConfigurationContext(config)
//                resources.updateConfiguration(config, resources.displayMetrics)
        }

        "Français" -> {
            AppApplication.sessionManager.saveAppLanguage("fr")
            val packageManager: PackageManager = context.packageManager
            val intent: Intent = packageManager.getLaunchIntentForPackage(context.packageName)!!
            val componentName: ComponentName = intent.component!!
            val restartIntent: Intent = Intent.makeRestartActivityTask(componentName)
            context.startActivity(restartIntent)
            Runtime.getRuntime().exit(0)
            //  updateLocale(Locales.French)
//            MainScreenAct().updateLocale(Locales.French)

        }
    }
}