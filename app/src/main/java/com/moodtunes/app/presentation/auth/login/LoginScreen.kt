package com.moodtunes.app.presentation.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.size.Size
import com.moodtunes.app.R
import com.moodtunes.app.presentation.components.AuthTextField
import com.moodtunes.app.presentation.components.PrimaryButton
import com.moodtunes.app.ui.theme.MoodTunesTypography

@Composable
fun LoginScreen(
    onNavigationSignUpScreen: () -> Unit,
    onLoginSuccess: () -> Unit,
    onForgotPasswordScreen: () -> Unit
) {
    val loginViewModel: LoginViewModel = hiltViewModel()


}

@Composable
fun LoginContent() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_music_note),
                contentDescription = "MoodTunes Logo",
                modifier = Modifier
                    .size(50.dp)


            )
            Text(
                text = stringResource(id = R.string.app_name),
                style = MoodTunesTypography.titleMedium
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AuthTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                keyboardType = KeyboardType.Email,
                hint = stringResource(id = R.string.hint_email)
            )
            Spacer(modifier = Modifier.size(15.dp))
            AuthTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                keyboardType = KeyboardType.Password,
                hint = stringResource(id = R.string.hint_password),
                isPassword = true
            )
            Spacer(modifier = Modifier.size(15.dp))
            PrimaryButton(text = stringResource(R.string.btn_login), onClick = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginContent() {
    LoginContent()
}
