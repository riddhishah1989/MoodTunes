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
import androidx.compose.ui.tooling.preview.Preview
import com.moodtunes.app.presentation.components.AuthTextField
import com.moodtunes.app.presentation.components.PrimaryButton
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme

@Composable
fun SignUpScreen(
    onSignUp: () -> Unit,
    onNavigateToSignIn: () -> Unit,
) {
    var name     by remember { mutableStateOf("") }
    var email    by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    AuthScaffold {
        Text("Welcome to MoodTunes", style = MaterialTheme.typography.headlineMedium,
            color = MoodTunesColors.TextPrimary)
        Text("Create your account", style = MaterialTheme.typography.bodyMedium,
            color = MoodTunesColors.TextSecondary)
        Spacer(Modifier.height(24.dp))

        AuthTextField(
            value         = name,
            onValueChange = { name = it },
            label         = "Full name",
            leadingIcon   = { Icon(Icons.Filled.Person, null,
                tint = MoodTunesColors.TextTertiary, modifier = Modifier.size(18.dp)) },
        )
        Spacer(Modifier.height(12.dp))
        AuthTextField(
            value         = email,
            onValueChange = { email = it },
            label         = "Email",
            leadingIcon   = { Icon(Icons.Filled.Email, null,
                tint = MoodTunesColors.TextTertiary, modifier = Modifier.size(18.dp)) },
            keyboardType  = KeyboardType.Email,
        )
        Spacer(Modifier.height(12.dp))
        AuthTextField(
            value         = password,
            onValueChange = { password = it },
            label         = "Password",
            leadingIcon   = { Icon(Icons.Filled.Lock, null,
                tint = MoodTunesColors.TextTertiary, modifier = Modifier.size(18.dp)) },
            keyboardType  = KeyboardType.Password,
            isPassword    = true,
        )
        Spacer(Modifier.height(20.dp))

        PrimaryButton(text = "Sign up", onClick = onSignUp)
        Spacer(Modifier.height(12.dp))
        GoogleSignInButton(onClick = onSignUp)
        Spacer(Modifier.height(24.dp))

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Already have an account? ", style = MaterialTheme.typography.bodySmall,
                color = MoodTunesColors.TextSecondary)
            TextButton(onClick = onNavigateToSignIn, contentPadding = PaddingValues(0.dp)) {
                Text("Sign in", style = MaterialTheme.typography.bodySmall,
                    color = MoodTunesColors.Primary)
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
