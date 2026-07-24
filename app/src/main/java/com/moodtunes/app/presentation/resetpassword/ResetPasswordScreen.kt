package com.moodtunes.app.presentation.resetpassword

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moodtunes.app.R
import com.moodtunes.app.presentation.components.AppTopBar
import com.moodtunes.app.presentation.components.CircularAppIconWithText
import com.moodtunes.app.presentation.components.CustomTextField
import com.moodtunes.app.presentation.components.PrimaryButton
import com.moodtunes.app.presentation.components.rememberMoodTunesSnackbar
import com.moodtunes.app.presentation.state.UiState
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTypography

@Composable
fun ResetPasswordScreen(email: String, onResetPasswordSuccess: () -> Unit, onBack: () -> Unit) {

    val resetPasswordViewModel: ResetPasswordViewModel = hiltViewModel()
    val uiState by resetPasswordViewModel.uiStateResetPassword.collectAsState()
    val snackbarHostState = rememberMoodTunesSnackbar()

    LaunchedEffect(uiState) {
        when (val state = uiState) {
            is UiState.Success -> onResetPasswordSuccess()
            is UiState.Error -> {
                snackbarHostState.showSnackbar(state.message)
                resetPasswordViewModel.resetState()
            }

            else -> {}
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MoodTunesColors.Background,
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MoodTunesColors.Background)
                .padding(padding)
        ) {
            ResetPasswordContent(
                onResetPwdClick = { newPassword, confirmPassword ->
                    resetPasswordViewModel.resetPassword(email, newPassword, confirmPassword)
                },
                onBack = onBack
            )
        }
    }
}

@Composable
fun ResetPasswordContent(
    onResetPwdClick: (String, String) -> Unit,
    onBack: () -> Unit
) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
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
            Spacer(modifier = Modifier.size(20.dp))
            CircularAppIconWithText(
                outerCircleSize = 120,
                imageSize = 60,
            )
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                text = "Your new password must be different from previous used passwords.",
                style = MoodTunesTypography.labelMedium
            )
            Spacer(modifier = Modifier.size(30.dp))
            CustomTextField(
                value = password,
                onValueChange = { password = it },
                keyboardType = KeyboardType.Password,
                hint = stringResource(R.string.hint_password),
                isPassword = true,
            )
            Spacer(modifier = Modifier.size(15.dp))
            CustomTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                keyboardType = KeyboardType.Password,
                hint = stringResource(R.string.hint_confirm_password),
                isPassword = true,
            )
            Spacer(modifier = Modifier.size(15.dp))
            PrimaryButton(
                text = "Reset Password",
                onClick = { onResetPwdClick(password, confirmPassword) },
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun ResetPasswordPreview() {
    ResetPasswordContent(onResetPwdClick = { _, _ -> }, onBack = {})
}