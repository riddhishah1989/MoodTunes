package com.moodtunes.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.moodtunes.app.navigation.MoodTunesNavGraph
import com.moodtunes.app.navigation.Screen
import com.moodtunes.app.presentation.splash.SplashState
import com.moodtunes.app.presentation.splash.SplashViewModel
import com.moodtunes.app.ui.theme.MoodTunesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MoodTunesTheme {
                val navController = rememberNavController()
                MoodTunesNavGraph(
                    navController = navController,
                    startDestination = Screen.Splash.route
                )
            }
        }
    }
}
