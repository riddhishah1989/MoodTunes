package com.moodtunes.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.moodtunes.app.presentation.auth.forgotpassword.ForgotPasswordScreen
import com.moodtunes.app.presentation.auth.login.LoginScreen
import com.moodtunes.app.presentation.auth.register.SignUpScreen
import com.moodtunes.app.presentation.changepassword.ChangePasswordScreen
import com.moodtunes.app.presentation.resetpassword.ResetPasswordScreen
import com.moodtunes.app.presentation.splash.MoodTunesSplashScreen
import com.moodtunes.app.presentation.verifyotp.VerifyOTPScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object ForgotPwd : Screen("forgot_pwd")

    object VerifyOTP : Screen("verify_otp/{email}")

    object ResetPassword : Screen("reset_pwd/{email}")

    object ChangePassword : Screen("change_pwd")
    object Home : Screen("home")
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
                onBack = { navController.popBackStack() }, // navigate to login screen on back press
                onForgotPasswordSuccess = {
                    navController.navigate(Screen.VerifyOTP.route) {
                        popUpTo(Screen.VerifyOTP.route)
                    }
                })
        }
        composable(
            Screen.VerifyOTP.route,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            VerifyOTPScreen(
                email = backStackEntry.arguments?.getString("email") ?: "",
                onVerifyOTPSuccess = {
                    navController.navigate(Screen.ResetPassword.route) {
                        popUpTo(Screen.ResetPassword.route)
                    }
                }, onBack = { navController.popBackStack() }
            )
        }
        composable(
            Screen.ResetPassword.route,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            ResetPasswordScreen(
                email = backStackEntry.arguments?.getString("email") ?: "",
                onResetPasswordSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route)
                    }
                },
                onBack = { navController.popBackStack() })
        }


        composable(Screen.ChangePassword.route) {
            ChangePasswordScreen(onChangePasswordSuccess = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            })
        }


    }
}

