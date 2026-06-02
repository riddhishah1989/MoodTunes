package com.moodtunes.app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.moodtunes.app.R
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme
import com.moodtunes.app.ui.theme.MoodTunesTypography

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
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.cd_back),
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
    hint: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
) {
    TextField(
        label = null,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        leadingIcon = leadingIcon,
        visualTransformation = if (isPassword) PasswordVisualTransformation()
        else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        placeholder = {
            Text(
                text = hint,
                style = MoodTunesTypography.bodyMedium,
                color = MoodTunesColors.TextTertiary, // ← #999999 hint text
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MoodTunesColors.SurfaceVariant,  // #F0E5F8
            unfocusedContainerColor = MoodTunesColors.SurfaceVariant,  // #F0E5F8
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = MoodTunesColors.TextPrimary,     // #1A1A1A
            unfocusedTextColor = MoodTunesColors.TextPrimary,     // #1A1A1A
            cursorColor = MoodTunesColors.Primary,         // #6F259C
            focusedLeadingIconColor = MoodTunesColors.Primary,         // #6F259C
            unfocusedLeadingIconColor = MoodTunesColors.TextTertiary,    // #999999
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(0.5.dp, MoodTunesColors.CardBorder, RoundedCornerShape(12.dp)),
        textStyle = MoodTunesTypography.bodyMedium,
    )
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
            containerColor = MoodTunesColors.Primary,           // #6F259C
            contentColor = Color.White,
            disabledContainerColor = MoodTunesColors.SurfaceVariant,    // ✅ light grey not dark
            disabledContentColor = MoodTunesColors.TextTertiary,      // ✅ #999999
        ),
    ) {
        Text(
            text = text,
            style = MoodTunesTypography.titleMedium,
            color = Color.White,
        )
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
            .background(MoodTunesColors.Surface)              // ✅ #F8F3FD light
            .border(0.5.dp, MoodTunesColors.CardBorder, RoundedCornerShape(16.dp))
            .let { if (onClick != null) it.clickable(onClick = onClick) else it }
            .padding(12.dp),
        content = content,
    )
}

// ── Preview ───────────────────────────────────────────────────────────
@Preview(showBackground = true)
@Composable
private fun CommonUIPreview() {
    MoodTunesTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            AuthTextField(value = "riddhi@gmail.com", onValueChange = {}, hint = "Enter Email")
            Spacer(modifier = Modifier.size(12.dp))
            AuthTextField(
                value = "riddhi@123",
                onValueChange = {},
                hint = "Enter Password",
                isPassword = true
            )
            Spacer(modifier = Modifier.size(12.dp))
            MoodTunesCard(
                onClick = {},
            ) {
                Text(
                    "Card content goes here",
                    color = MoodTunesColors.TextPrimary, modifier = Modifier.padding(4.dp),
                )
            }

            Spacer(modifier = Modifier.size(12.dp))
            PrimaryButton(text = "Login", onClick = {})
        }
    }
}