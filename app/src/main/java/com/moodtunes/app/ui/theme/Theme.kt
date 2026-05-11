package com.moodtunes.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moodtunes.app.R

// ── Color Palette (extracted from Figma design) ───────────────────────────
object MoodTunesColors {
    // Backgrounds
    val Background = Color(0xFF08080F)
    val Surface = Color(0xFF0F0F1E)
    val SurfaceVariant = Color(0xFF141428)
    val CardBorder = Color(0xFF1E1E36)

    // Primary accent — purple (our spec, replacing Figma's teal)
    val Primary = Color(0xFF6C63FF)
    val PrimaryVariant = Color(0xFF8B85FF)
    val PrimaryContainer = Color(0xFF12103A)
    val OnPrimary = Color(0xFFFFFFFF)

    // Secondary accent — teal (kept from Figma for Spotify references)
    val Secondary = Color(0xFF00D4CC)
    val OnSecondary = Color(0xFF003333)

    // Text
    val TextPrimary = Color(0xFFFFFFFF)
    val TextSecondary = Color(0xFF888888)
    val TextTertiary = Color(0xFF444466)
    val TextHint = Color(0xFF333355)

    // Mood accent colors
    val MoodHappy = Color(0xFFFFD93D)
    val MoodSad = Color(0xFF5B8FD4)
    val MoodEnergetic = Color(0xFFFF6B6B)
    val MoodCalm = Color(0xFF5DD68A)
    val MoodRomantic = Color(0xFFFF63A5)
    val MoodFocused = Color(0xFF4D96FF)
    val MoodAngry = Color(0xFFFF4444)
    val MoodAnxious = Color(0xFFC77DFF)

    // Mood background tints
    val MoodHappyBg = Color(0xFF1A1500)
    val MoodSadBg = Color(0xFF040810)
    val MoodEnergeticBg = Color(0xFF0F0404)
    val MoodCalmBg = Color(0xFF04100A)
    val MoodRomanticBg = Color(0xFF0F0408)
    val MoodFocusedBg = Color(0xFF04060F)
    val MoodAngryBg = Color(0xFF0F0404)
    val MoodAnxiousBg = Color(0xFF0A040F)

    // Functional
    val Spotify = Color(0xFF1DB954)
    val YouTube = Color(0xFFC4302B)
    val Error = Color(0xFFFF4444)
    val Success = Color(0xFF5DD68A)
    val Warning = Color(0xFFFFD93D)
    val Divider = Color(0xFF12122A)
}

// ── Dark Color Scheme ─────────────────────────────────────────────────────
private val DarkColorScheme = darkColorScheme(
    primary = MoodTunesColors.Primary,
    onPrimary = MoodTunesColors.OnPrimary,
    primaryContainer = MoodTunesColors.PrimaryContainer,
    onPrimaryContainer = MoodTunesColors.PrimaryVariant,
    secondary = MoodTunesColors.Secondary,
    onSecondary = MoodTunesColors.OnSecondary,
    background = MoodTunesColors.Background,
    onBackground = MoodTunesColors.TextPrimary,
    surface = MoodTunesColors.Surface,
    onSurface = MoodTunesColors.TextPrimary,
    surfaceVariant = MoodTunesColors.SurfaceVariant,
    onSurfaceVariant = MoodTunesColors.TextSecondary,
    error = MoodTunesColors.Error,
    outline = MoodTunesColors.CardBorder,
)

// ── Font Family ───────────────────────────────────────────────────────────
val PoppinsFontFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold),
)

// ── Typography ────────────────────────────────────────────────────────────
val MoodTunesTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        letterSpacing = (-1).sp,
        color = MoodTunesColors.TextPrimary
    ),
    headlineLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = (-0.5).sp,
        color = MoodTunesColors.TextPrimary
    ),
    headlineMedium = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        letterSpacing = (-0.5).sp,
        color = MoodTunesColors.TextPrimary
    ),
    titleLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = MoodTunesColors.TextPrimary
    ),
    titleMedium = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        color = MoodTunesColors.TextPrimary
    ),
    titleSmall = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        color = MoodTunesColors.TextPrimary
    ),
    bodyLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        color = MoodTunesColors.TextPrimary
    ),
    bodyMedium = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = MoodTunesColors.TextSecondary
    ),
    bodySmall = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = MoodTunesColors.TextSecondary
    ),
    labelLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        letterSpacing = 0.1.sp,
        color = MoodTunesColors.TextSecondary
    ),
    labelSmall = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 0.08.sp,
        color = MoodTunesColors.TextTertiary
    ),
)

// ── Shapes ────────────────────────────────────────────────────────────────
val MoodTunesShapes = Shapes(
    extraSmall = RoundedCornerShape(6.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp),
)

// ── Theme Composable ──────────────────────────────────────────────────────
@Composable
fun MoodTunesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = MoodTunesTypography,
        shapes = MoodTunesShapes,
        content = content
    )
}
