package com.ops.airportr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.ops.airportr.common.theme.OpsAirportrTheme
import com.ops.airportr.route.Navigation
import com.ops.airportr.ui.componts.LocalBackPressedDispatcher
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpsAirportrTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    //Navigation(navController)
                    CompositionLocalProvider(
                        LocalBackPressedDispatcher provides onBackPressedDispatcher
                    ) {
                        Navigation(navController)
                    }
                }
            }
        }
    }
}