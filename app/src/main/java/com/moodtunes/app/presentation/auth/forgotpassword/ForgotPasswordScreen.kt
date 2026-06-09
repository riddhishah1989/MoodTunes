package com.moodtunes.app.presentation.auth.forgotpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moodtunes.app.presentation.components.AppTopBar
import com.moodtunes.app.presentation.components.CircularAppIconWithText
import com.moodtunes.app.ui.theme.MoodTunesTypography

@Composable
fun ForgotPasswordScreen(onBack: () -> Unit, onPasswordResetSent: () -> Unit) {

}

@Composable
fun ForgotPasswordContent() {
    var email by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppTopBar(onBack = {})
        // Logo Section
        Spacer(modifier = Modifier.size(20.dp))
        CircularAppIconWithText(
            outerCircleSize = 120,
            imageSize = 60,
        )
        Spacer(modifier = Modifier.size(30.dp))
        Text(text = "Forgot your password", style = MoodTunesTypography.labelLarge)
        Text(text = "and Continue", style = MoodTunesTypography.labelLarge)
    }
}

@Composable
@Preview
fun ForgotPasswordPreview() {
    ForgotPasswordContent()
}