package com.moodtunes.app.presentation.auth.forgotpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moodtunes.app.presentation.components.AppTopBar
import com.moodtunes.app.presentation.components.CircularAppIconWithText
import com.moodtunes.app.presentation.components.CustomTextField
import com.moodtunes.app.presentation.components.MoodTunesLoadingOverlay
import com.moodtunes.app.presentation.components.MoodTunesSnackbarHost
import com.moodtunes.app.presentation.components.PrimaryButton
import com.moodtunes.app.presentation.components.rememberMoodTunesSnackbar
import com.moodtunes.app.presentation.state.UiState
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme
import com.moodtunes.app.ui.theme.MoodTunesTypography

@Composable
fun ForgotPasswordScreen(onBack: () -> Unit, onForgotPasswordSuccess: () -> Unit) {
    val forgotPwdViewModel: ForgotPasswordViewModel = hiltViewModel()
    val uiState by forgotPwdViewModel.uiStateForgotPwd.collectAsState()
    val snackbarHostState = rememberMoodTunesSnackbar()

    LaunchedEffect(uiState) {
        when (val state = uiState) {
            is UiState.Success -> onForgotPasswordSuccess()
            is UiState.Error -> {
                snackbarHostState.showSnackbar(state.message)
                forgotPwdViewModel.resetState()
            }

            else -> Unit
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MoodTunesColors.Background,
        snackbarHost = { MoodTunesSnackbarHost(snackbarHostState) }) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MoodTunesColors.Background)
                .padding(padding)
        ) {
            ForgotPasswordContent(onBack = onBack, onForgotPasswordClick = { email ->
                forgotPwdViewModel.forgotPassword(email)
            })
            // ── Loading overlay ───────────────────────────
            MoodTunesLoadingOverlay(isLoading = uiState is UiState.Loading)
        }
    }

}

@Composable
fun ForgotPasswordContent(onBack: () -> Unit, onForgotPasswordClick: (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MoodTunesColors.Background)
    ) {
        AppTopBar(onBack = onBack)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo Section
            Spacer(modifier = Modifier.size(20.dp))
            CircularAppIconWithText(
                outerCircleSize = 120,
                imageSize = 60,
            )
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "Forgot your password", style = MoodTunesTypography.labelMedium)
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "No worries , we will send you reset instructions",
                style = MoodTunesTypography.labelMedium
            )
            Spacer(modifier = Modifier.size(20.dp))
            CustomTextField(value = email, onValueChange = { email = it }, hint = "Enter Email")
            Spacer(modifier = Modifier.size(20.dp))
            PrimaryButton(
                text = "Submit",
                onClick = {
                    onForgotPasswordClick(email)
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ForgotPasswordPreview() {
    MoodTunesTheme {
        ForgotPasswordContent(onBack = {}, onForgotPasswordClick = {})
    }
}
