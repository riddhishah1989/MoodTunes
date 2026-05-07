package com.moodtunes.app.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme

@Composable
internal fun AuthScaffold(content: @Composable ColumnScope.() -> Unit) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MoodTunesColors.Background)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp, vertical = 48.dp),
            content = content,
        )
    }
}

@Composable
internal fun GoogleSignInButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(14.dp),
        border = androidx.compose.foundation.BorderStroke(0.5.dp, MoodTunesColors.CardBorder),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFF0C0C1E)),
    ) {
        Text("G", style = MaterialTheme.typography.titleSmall, color = MoodTunesColors.Error)
        Spacer(Modifier.width(8.dp))
        Text(
            "Sign up with Google", style = MaterialTheme.typography.bodyMedium,
            color = MoodTunesColors.TextSecondary
        )
    }
}

// ── Previews ──────────────────────────────────────────────────────────────
@Preview(name = "AuthScaffold", showBackground = true)
@Composable
private fun AuthScaffoldPreview() {
    MoodTunesTheme {
        AuthScaffold {
            Text("Auth content goes here", color = MoodTunesColors.TextPrimary)
        }
    }
}

@Preview(name = "GoogleSignInButton", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun GoogleSignInButtonPreview() {
    MoodTunesTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            GoogleSignInButton(onClick = {})
        }
    }
}
