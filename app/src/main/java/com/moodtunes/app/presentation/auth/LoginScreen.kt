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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.moodtunes.app.presentation.components.AuthTextField
import com.moodtunes.app.presentation.components.PrimaryButton
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme

@Composable
fun SignInScreen(
    onSignIn: () -> Unit,
    onNavigateToSignUp: () -> Unit,
) {
    var email    by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    AuthScaffold {
        Text("🎵", fontSize = 48.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(8.dp))
        Text("MoodTunes", style = MaterialTheme.typography.headlineLarge,
            color = MoodTunesColors.TextPrimary,
            modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(32.dp))
        Text("Sign in to your account", style = MaterialTheme.typography.headlineMedium,
            color = MoodTunesColors.TextPrimary)
        Spacer(Modifier.height(24.dp))

        AuthTextField(
            value         = email,
            onValueChange = { email = it },
            label         = "Email address",
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

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = false, onCheckedChange = {},
                    colors = CheckboxDefaults.colors(checkedColor = MoodTunesColors.Primary))
                Text("Remember me", style = MaterialTheme.typography.bodySmall,
                    color = MoodTunesColors.TextSecondary)
            }
            TextButton(onClick = {}) {
                Text("Forgot password?", style = MaterialTheme.typography.bodySmall,
                    color = MoodTunesColors.Primary)
            }
        }
        Spacer(Modifier.height(8.dp))

        PrimaryButton(text = "Sign in", onClick = onSignIn)
        Spacer(Modifier.height(12.dp))
        GoogleSignInButton(onClick = onSignIn)
        Spacer(Modifier.height(24.dp))

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Don't have an account? ", style = MaterialTheme.typography.bodySmall,
                color = MoodTunesColors.TextSecondary)
            TextButton(onClick = onNavigateToSignUp, contentPadding = PaddingValues(0.dp)) {
                Text("Sign up", style = MaterialTheme.typography.bodySmall,
                    color = MoodTunesColors.Primary)
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
