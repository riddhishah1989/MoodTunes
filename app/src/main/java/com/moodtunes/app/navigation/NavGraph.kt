package com.moodtunes.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.moodtunes.app.presentation.auth.forgotpassword.ForgotPasswordScreen
import com.moodtunes.app.presentation.auth.login.LoginScreen
import com.moodtunes.app.presentation.auth.register.SignUpScreen
import com.moodtunes.app.presentation.splash.MoodTunesSplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object ForgotPwd : Screen("forgot_pwd")
    object Home : Screen("home")
    object History : Screen("history")
    object Favourites : Screen("favourites")
    object Journal : Screen("journal")
    object Profile : Screen("profile")
    object Insights : Screen("insights")
    object MoodPicker : Screen("mood_picker")
    object Recommendations : Screen("recommendations/{sessionId}") {
        fun createRoute(sessionId: String) = "recommendations/{$sessionId}"
    }

    object NowPlaying : Screen("now_playing/{sessionId}/{songIndex}") {
        fun createRoute(sessionId: String, songIndex: Int) =
            "now_playing/$sessionId/$songIndex"
    }
}

@Composable
fun MoodTunesNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Splash.route) {
            MoodTunesSplashScreen(
                onNavigateToSignIn = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigationSignUpScreen = {
                    navController.navigate(Screen.SignUp.route)
                },
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                },
                onForgotPasswordScreen = { navController.navigate(Screen.ForgotPwd.route) })
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(
                onSignUpSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigationLoginScreen = {
                    navController.popBackStack()
                })
        }
        composable(Screen.ForgotPwd.route) {
            ForgotPasswordScreen(
                onBack = { navController.popBackStack() },
                onPasswordResetSent = { navController.popBackStack() }) // Go back to SignIn after sending reset email
        }


    }
}

