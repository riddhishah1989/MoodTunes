package com.moodtunes.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.hilt.navigation.compose.hiltViewModel
import com.moodtunes.app.presentation.auth.AuthViewModel
import com.moodtunes.app.presentation.home.HomeScreen
import com.moodtunes.app.presentation.moodpicker.MoodPickerScreen
import com.moodtunes.app.presentation.recommendations.RecommendationsScreen
import com.moodtunes.app.presentation.history.HistoryScreen
import com.moodtunes.app.presentation.player.NowPlayingScreen
import com.moodtunes.app.presentation.onboarding.OnboardingScreen
import com.moodtunes.app.presentation.auth.LoginScreen
import com.moodtunes.app.presentation.auth.SignUpScreen
import com.moodtunes.app.presentation.favourites.FavouritesScreen
import com.moodtunes.app.presentation.splash.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object SignIn : Screen("sign_in")
    object SignUp : Screen("sign_up")
    object Home : Screen("home")
    object MoodPicker : Screen("mood_picker")
    object Recommendations : Screen("recommendations/{sessionId}") {
        fun createRoute(sessionId: String) = "recommendations/$sessionId"
    }

    object History : Screen("history")
    object Favourites : Screen("favourites")
    object NowPlaying : Screen("now_playing/{songIndex}/{sessionId}") {
        fun createRoute(songIndex: Int, sessionId: String) = "now_playing/$songIndex/$sessionId"
    }
}

@Composable
fun MoodTunesNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToSignIn = {
                    navController.navigate(Screen.SignIn.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToOnboarding = {
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
            )
        }

        composable(Screen.Onboarding.route) {
            val authViewModel: AuthViewModel = hiltViewModel()
            OnboardingScreen(
                onFinished = {
                    authViewModel.markOnboardingComplete()
                    navController.navigate(Screen.SignIn.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.SignIn.route) {
            val authViewModel: AuthViewModel = hiltViewModel()
            val state by authViewModel.state.collectAsState()

            LaunchedEffect(state.isAuthenticated) {
                if (state.isAuthenticated) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.SignIn.route) { inclusive = true }
                    }
                }
            }

            LoginScreen(
                state = state,
                onSignIn = authViewModel::signIn,
                onNavigateToSignUp = { navController.navigate(Screen.SignUp.route) },
            )
        }

        composable(Screen.SignUp.route) {
            val authViewModel: AuthViewModel = hiltViewModel()
            val state by authViewModel.state.collectAsState()

            LaunchedEffect(state.isAuthenticated) {
                if (state.isAuthenticated) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.SignUp.route) { inclusive = true }
                    }
                }
            }

            SignUpScreen(
                state = state,
                onSignUp = authViewModel::signUp,
                onNavigateToSignIn = { navController.popBackStack() }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onMoodClick = { navController.navigate(Screen.MoodPicker.route) },
                onSessionClick = { sessionId ->
                    navController.navigate(Screen.Recommendations.createRoute(sessionId))
                }
            )
        }

        composable(Screen.MoodPicker.route) {
            MoodPickerScreen(
                onBack = { navController.popBackStack() },
                onRecommendationsReady = { sessionId ->
                    navController.navigate(Screen.Recommendations.createRoute(sessionId)) {
                        popUpTo(Screen.MoodPicker.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Screen.Recommendations.route,
            arguments = listOf(navArgument("sessionId") { type = NavType.StringType })
        ) { backStackEntry ->
            val sessionId = backStackEntry.arguments?.getString("sessionId") ?: ""
            RecommendationsScreen(
                sessionId = sessionId,
                onBack = { navController.popBackStack() },
                onSongClick = { songIndex ->
                    navController.navigate(Screen.NowPlaying.createRoute(songIndex, sessionId))
                },
                onHistoryClick = { navController.navigate(Screen.History.route) }
            )
        }

        composable(Screen.History.route) {
            HistoryScreen(
                onBack = { navController.popBackStack() },
                onSessionClick = { sessionId ->
                    navController.navigate(Screen.Recommendations.createRoute(sessionId))
                }
            )
        }

        composable(Screen.Favourites.route) {
            FavouritesScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.NowPlaying.route,
            arguments = listOf(
                navArgument("songIndex") { type = NavType.IntType },
                navArgument("sessionId") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val songIndex = backStackEntry.arguments?.getInt("songIndex") ?: 0
            val sessionId = backStackEntry.arguments?.getString("sessionId") ?: ""
            NowPlayingScreen(
                songIndex = songIndex,
                sessionId = sessionId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
