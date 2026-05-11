package com.moodtunes.app.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import com.moodtunes.app.R
import com.moodtunes.app.presentation.components.AuthTextField
import com.moodtunes.app.presentation.components.PrimaryButton
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme

@Composable
fun SignUpScreen(
    onSignUp: () -> Unit,
    onNavigateToSignIn: () -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    AuthScaffold {
        Icon(
            painter = painterResource(R.drawable.ic_music_note),
            contentDescription = null,
            modifier = Modifier.size(56.dp).align(Alignment.CenterHorizontally),
            tint = MoodTunesColors.Primary,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            stringResource(R.string.app_name), style = MaterialTheme.typography.headlineLarge,
            color = MoodTunesColors.TextPrimary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(24.dp))

        AuthTextField(
            value = name,
            onValueChange = { name = it },
            label = stringResource(R.string.label_full_name),
            leadingIcon = {
                Icon(
                    Icons.Filled.Person, null,
                    tint = MoodTunesColors.TextTertiary, modifier = Modifier.size(18.dp)
                )
            },
        )
        Spacer(Modifier.height(12.dp))
        AuthTextField(
            value = email,
            onValueChange = { email = it },
            label = stringResource(R.string.label_email),
            leadingIcon = {
                Icon(
                    Icons.Filled.Email, null,
                    tint = MoodTunesColors.TextTertiary, modifier = Modifier.size(18.dp)
                )
            },
            keyboardType = KeyboardType.Email,
        )
        Spacer(Modifier.height(12.dp))
        AuthTextField(
            value = password,
            onValueChange = { password = it },
            label = stringResource(R.string.label_password),
            leadingIcon = {
                Icon(
                    Icons.Filled.Lock, null,
                    tint = MoodTunesColors.TextTertiary, modifier = Modifier.size(18.dp)
                )
            },
            keyboardType = KeyboardType.Password,
            isPassword = true,
        )




        Spacer(Modifier.height(12.dp))
        AuthTextField(
            value = password,
            onValueChange = { password = it },
            label = stringResource(R.string.label_confirm_password),
            leadingIcon = {
                Icon(
                    Icons.Filled.Lock, null,
                    tint = MoodTunesColors.TextTertiary, modifier = Modifier.size(18.dp)
                )
            },
            keyboardType = KeyboardType.Password,
            isPassword = true,
        )
        Spacer(Modifier.height(20.dp))
        PrimaryButton(text = stringResource(R.string.btn_sign_up), onClick = onSignUp)
        Spacer(Modifier.height(12.dp))

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                stringResource(R.string.prompt_have_account), style = MaterialTheme.typography.bodyMedium,
                color = MoodTunesColors.TextSecondary,  modifier = Modifier.align(Alignment.CenterVertically)
            )
            TextButton(onClick = onNavigateToSignIn, contentPadding = PaddingValues(0.dp)) {
                Text(
                    stringResource(R.string.btn_sign_in), style = MaterialTheme.typography.bodyMedium,
                    color = MoodTunesColors.Primary
                )
            }
        }
    }
}

// ── Preview ───────────────────────────────────────────────────────────────
@Preview(name = "Sign Up Screen", showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    MoodTunesTheme {
        SignUpScreen(onSignUp = {}, onNavigateToSignIn = {})
    }
}
