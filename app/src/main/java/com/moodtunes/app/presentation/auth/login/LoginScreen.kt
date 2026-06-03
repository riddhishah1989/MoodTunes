package com.moodtunes.app.presentation.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moodtunes.app.R
import com.moodtunes.app.presentation.components.CustomTextField
import com.moodtunes.app.presentation.components.CircularAppIconWithText
import com.moodtunes.app.presentation.components.PrimaryButton
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTypography

@Composable
fun LoginScreen(
    onNavigationSignUpScreen: () -> Unit,
    onLoginSuccess: () -> Unit,
    onForgotPasswordScreen: () -> Unit
) {
    val loginViewModel: LoginViewModel = hiltViewModel()

    LoginContent(
        onSignUpClick = onNavigationSignUpScreen,
        onForgotPasswordClick = onForgotPasswordScreen,
        onLoginClick = {
            // handle login logic here if needed, then:
            onLoginSuccess()
        }
    )
}

@Composable
fun LoginContent(
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    
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
        
        // Welcome Text Section
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Welcome Back!", 
                style = MoodTunesTypography.headlineMedium,
                color = MoodTunesColors.TextPrimary
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Please fill the forms below to get started.",
                style = MoodTunesTypography.bodyMedium,
                color = MoodTunesColors.TextSecondary
            )
            Text(
                text = "If you are not member yet, Please click on Register below",
                style = MoodTunesTypography.bodySmall,
                color = MoodTunesColors.TextSecondary
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Input Section
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            keyboardType = KeyboardType.Email,
            hint = stringResource(id = R.string.hint_email)
        )
        Spacer(modifier = Modifier.size(15.dp))
        CustomTextField(
            value = password,
            onValueChange = { password = it },
            keyboardType = KeyboardType.Password,
            hint = stringResource(id = R.string.hint_password),
            isPassword = true
        )
        
        // Forgot Password link
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            TextButton(onClick = onForgotPasswordClick) {
                Text(
                    text = stringResource(R.string.link_forgot_password),
                    style = MoodTunesTypography.labelMedium,
                    color = MoodTunesColors.Primary,
                    textAlign = TextAlign.Right
                )
            }
        }
        
        Spacer(modifier = Modifier.size(20.dp))
        
        PrimaryButton(
            text = stringResource(R.string.btn_sign_in), 
            onClick = onLoginClick
        )
        
        Spacer(modifier = Modifier.weight(1.2f))

        // Footer Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account?",
                style = MoodTunesTypography.bodyMedium,
                color = MoodTunesColors.TextSecondary
            )
            TextButton(onClick = onSignUpClick) {
                Text(
                    text = stringResource(R.string.btn_sign_up),
                    style = MoodTunesTypography.labelMedium,
                    color = MoodTunesColors.Primary,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginContent() {
    LoginContent(
        onSignUpClick = {},
        onForgotPasswordClick = {},
        onLoginClick = {}
    )
}
