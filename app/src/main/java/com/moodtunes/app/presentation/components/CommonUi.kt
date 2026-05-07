package com.moodtunes.app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme

// ── Top Bar ───────────────────────────────────────────────────────────────
@Composable
fun AppTopBar(
    onBack: () -> Unit,
    title: String,
    subtitle: String? = null,
    centerTitle: Boolean = false,
    titleColor: Color = MoodTunesColors.TextPrimary,
    action: @Composable (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onBack) {
            Icon(
                Icons.Filled.ArrowBack, contentDescription = "Back",
                tint = MoodTunesColors.TextSecondary
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = if (centerTitle) Alignment.CenterHorizontally else Alignment.Start,
        ) {
            Text(
                text = title,
                style = if (centerTitle) MaterialTheme.typography.titleMedium
                else MaterialTheme.typography.headlineMedium,
                color = titleColor,
            )
            if (subtitle != null) {
                Text(
                    text = subtitle, style = MaterialTheme.typography.bodySmall,
                    color = MoodTunesColors.TextSecondary
                )
            }
        }
        if (action != null) action() else if (centerTitle) Spacer(Modifier.width(48.dp))
    }
}

// ── Text Field ────────────────────────────────────────────────────────────
@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
) {
    Column {
        Text(
            label, style = MaterialTheme.typography.labelLarge,
            color = MoodTunesColors.TextSecondary, modifier = Modifier.padding(bottom = 6.dp)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            leadingIcon = leadingIcon,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF0C0C1E),
                unfocusedContainerColor = Color(0xFF0C0C1E),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = MoodTunesColors.TextPrimary,
                unfocusedTextColor = MoodTunesColors.TextPrimary,
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    0.5.dp, MoodTunesColors.CardBorder, RoundedCornerShape(12.dp)
                ),
        )
    }
}

// ── Primary Button ────────────────────────────────────────────────────────
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MoodTunesColors.Primary,
            disabledContainerColor = Color(0xFF2A2A44),
        ),
    ) {
        Text(text, style = MaterialTheme.typography.titleSmall, color = Color.White)
    }
}

// ── Empty State ───────────────────────────────────────────────────────────
@Composable
fun EmptyState(
    emoji: String,
    message: String,
    subtitle: String? = null,
    emojiColor: Color = Color.Unspecified,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(emoji, fontSize = 36.sp, color = emojiColor)
        Text(
            message, style = MaterialTheme.typography.bodyMedium,
            color = MoodTunesColors.TextTertiary
        )
        if (subtitle != null) {
            Text(
                subtitle, style = MaterialTheme.typography.bodySmall,
                color = MoodTunesColors.TextHint
            )
        }
    }
}

// ── Card Container ────────────────────────────────────────────────────────
@Composable
fun MoodTunesCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MoodTunesColors.Surface)
            .border(0.5.dp, MoodTunesColors.CardBorder, RoundedCornerShape(16.dp))
            .let { if (onClick != null) it.clickable(onClick = onClick) else it }
            .padding(12.dp),
        content = content,
    )
}

// ── Previews ──────────────────────────────────────────────────────────────
@Preview(name = "AppTopBar - Left", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun AppTopBarPreview() {
    MoodTunesTheme {
        AppTopBar(onBack = {}, title = "Favourites", subtitle = "12 saved tracks")
    }
}

@Preview(name = "AppTopBar - Centered", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun AppTopBarCenteredPreview() {
    MoodTunesTheme {
        AppTopBar(onBack = {}, title = "Choose your mood", subtitle = "or describe it below", centerTitle = true)
    }
}

@Preview(name = "AuthTextField", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun AuthTextFieldPreview() {
    MoodTunesTheme {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            AuthTextField(value = "user@email.com", onValueChange = {}, label = "Email address")
            AuthTextField(value = "", onValueChange = {}, label = "Password", isPassword = true)
        }
    }
}

@Preview(name = "PrimaryButton", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun PrimaryButtonPreview() {
    MoodTunesTheme {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            PrimaryButton(text = "Sign in", onClick = {})
            PrimaryButton(text = "Disabled", onClick = {}, enabled = false)
        }
    }
}

@Preview(name = "EmptyState", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun EmptyStatePreview() {
    MoodTunesTheme {
        Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
            EmptyState(emoji = "🎵", message = "No songs yet", subtitle = "Pick a mood to get started")
        }
    }
}

@Preview(name = "MoodTunesCard", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun MoodTunesCardPreview() {
    MoodTunesTheme {
        MoodTunesCard(modifier = Modifier.fillMaxWidth().padding(16.dp), onClick = {}) {
            Text("Card content goes here", color = Color.White, modifier = Modifier.padding(4.dp))
        }
    }
}
