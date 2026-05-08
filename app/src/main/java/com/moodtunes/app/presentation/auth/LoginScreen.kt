package com.moodtunes.app.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.moodtunes.app.R
import com.moodtunes.app.presentation.components.AuthTextField
import com.moodtunes.app.presentation.components.PrimaryButton
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme

@Composable
fun SignInScreen(
    onSignIn: () -> Unit,
    onNavigateToSignUp: () -> Unit,
) {
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

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = false, onCheckedChange = {},
                    colors = CheckboxDefaults.colors(checkedColor = MoodTunesColors.Primary)
                )
                Text(
                    stringResource(R.string.check_remember_me), style = MaterialTheme.typography.bodySmall,
                    color = MoodTunesColors.TextSecondary
                )
            }
            TextButton(onClick = {}) {
                Text(
                    stringResource(R.string.link_forgot_password), style = MaterialTheme.typography.bodySmall,
                    color = MoodTunesColors.Primary
                )
            }
        }
        Spacer(Modifier.height(8.dp))

        PrimaryButton(text = stringResource(R.string.btn_login), onClick = onSignIn)
        Spacer(Modifier.height(12.dp))

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                stringResource(R.string.prompt_no_account),
                style = MaterialTheme.typography.bodyMedium,
                color = MoodTunesColors.TextSecondary,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            TextButton(onClick = onNavigateToSignUp, contentPadding = PaddingValues(0.dp)) {
                Text(
                    stringResource(R.string.btn_sign_up), style = MaterialTheme.typography.bodyMedium,
                    color = MoodTunesColors.Primary
                )
            }
        }
    }
}

// ── Preview ───────────────────────────────────────────────────────────────
@Preview(name = "Login Screen", showBackground = true)
@Composable
private fun SignInScreenPreview() {
    MoodTunesTheme {
        SignInScreen(onSignIn = {}, onNavigateToSignUp = {})
    }
}
