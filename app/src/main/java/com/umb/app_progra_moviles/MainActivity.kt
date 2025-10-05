package com.umb.app_progra_moviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.umb.app_progra_moviles.ui.menu.MenuScreen
import com.umb.app_progra_moviles.ui.splash.SplashScreen
import com.umb.app_progra_moviles.ui.theme.AppprogramovilesIITheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppprogramovilesIITheme {
                var showSplash by remember { mutableStateOf(true) }

                LaunchedEffect(Unit) {
                    delay(5000)
                    showSplash = false
                }

                if (showSplash) {
                    SplashScreen()
                } else {
                    MenuScreen()
                }
            }
        }
    }
}
