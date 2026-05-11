package com.moodtunes.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.moodtunes.app.navigation.MoodTunesNavGraph
import com.moodtunes.app.navigation.Screen
import com.moodtunes.app.presentation.components.MoodTunesBottomNav
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoodTunesTheme {
                val navController = rememberNavController()
                val currentEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentEntry?.destination?.route

                val showBottomNav = currentRoute in listOf(
                    Screen.Home.route,
                    Screen.History.route,
                    Screen.Favourites.route,
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MoodTunesColors.Background,
                    bottomBar = {
                        if (showBottomNav) {
                            MoodTunesBottomNav(
                                currentRoute = currentRoute,
                                onNavigate = { route ->
                                    navController.navigate(route) {
                                        popUpTo(Screen.Home.route) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                ) { _ ->
                    MoodTunesNavGraph(navController = navController)
                }
            }
        }
    }
}
