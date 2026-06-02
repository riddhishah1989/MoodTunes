package com.moodtunes.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
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
    // ── Backgrounds (from Figma) ──────────────
    val Background = Color(0xFFFFFFFF)  // Figma: #FFFFFF — page color
    val Surface = Color(0xFFF8F3FD)  // soft lavender white — cards bg
    val SurfaceVariant = Color(0xFFF0E5F8)  // input fields
    val CardBorder = Color(0xFFE0D0EE)  // card outlines

    // ── Primary (from Figma) ─────────────────────
    val Primary = Color(0xFF6F259C)  // Figma: #6F259C — button color
    val PrimaryVariant = Color(0xFF9B5CC8)  // lighter purple
    val PrimaryContainer = Color(0xFFF5EEFF)  // hero card bg
    val OnPrimary = Color(0xFFFFFFFF)  // white text on button

    // ── Text (from Figma) ────────────────────────
    val TextPrimary = Color(0xFF1A1A1A)  // Figma: #1A1A1A — main text
    val TextSecondary = Color(0xFF555555)  // secondary text
    val TextTertiary = Color(0xFF999999)  // Figma: #999999 — unselected/hint
    val TextHint = Color(0xFFBBBBBB)  // placeholder text

    // ── Player (from Figma) ──────────────────────
    val PlayButton = Color(0xFF6B32AB)  // Figma: #6B32AB — play button
    val PlayerIcon = Color(0xFF1A1C20)  // Figma: #1A1C20 — fwd/back icons

    // ── Mood accent colors ────────────────────────
    val MoodHappy = Color(0xFFB89000)
    val MoodSad = Color(0xFF3878C8)
    val MoodEnergetic = Color(0xFFD03828)
    val MoodCalm = Color(0xFF1A8050)
    val MoodRomantic = Color(0xFFD01858)
    val MoodFocused = Color(0xFF6F259C)
    val MoodAngry = Color(0xFFE02818)
    val MoodAnxious = Color(0xFF9828D0)

    // ── Mood background tints ─────────────────────
    val MoodHappyBg = Color(0xFFFFF8E0)
    val MoodSadBg = Color(0xFFE8EEFF)
    val MoodEnergeticBg = Color(0xFFFFE8E8)
    val MoodCalmBg = Color(0xFFE8F8EE)
    val MoodRomanticBg = Color(0xFFFFE8F5)
    val MoodFocusedBg = Color(0xFFEEE8FF)
    val MoodAngryBg = Color(0xFFFFEAEA)
    val MoodAnxiousBg = Color(0xFFF5E8FF)

    // ── Functional ───────────────────────────────
    val Spotify = Color(0xFF1DB954)
    val YouTube = Color(0xFFC4302B)
    val Error = Color(0xFFD82828)
    val Success = Color(0xFF1A9858)
    val Warning = Color(0xFFD89000)
    val Divider = Color(0xFFEEE5F5)
}

private val LightColorScheme = lightColorScheme(

    // ── Background ───────────────────────────────
    background = MoodTunesColors.Background,    // #FFFFFF — screen background
    onBackground = MoodTunesColors.TextPrimary,   // #1A1A1A — text ON background

    // ── Surface (cards, sheets) ──────────────────
    surface = MoodTunesColors.Surface,        // #F8F3FD — card background
    onSurface = MoodTunesColors.TextPrimary,    // #1A1A1A — text on cards
    surfaceVariant = MoodTunesColors.SurfaceVariant, // #F0E5F8 — input fields
    onSurfaceVariant = MoodTunesColors.TextSecondary,  // #555555 — hint text on inputs

    // ── Primary (buttons, active states) ────────
    primary = MoodTunesColors.Primary,          // #6F259C — button bg
    onPrimary = MoodTunesColors.OnPrimary,        // #FFFFFF — white text ON button
    primaryContainer = MoodTunesColors.PrimaryContainer, // #F5EEFF — hero card / chip bg
    onPrimaryContainer = MoodTunesColors.Primary,        // #6F259C — purple text on container

    // ── Secondary ────────────────────────────────
    secondary = MoodTunesColors.PrimaryVariant, // #9B5CC8
    onSecondary = Color.White,
    secondaryContainer = MoodTunesColors.PrimaryContainer,
    onSecondaryContainer = MoodTunesColors.Primary,

    // ── Other ────────────────────────────────────
    outline = MoodTunesColors.CardBorder,  // #E0D0EE — borders
    error = MoodTunesColors.Error,       // #D82828 — error states
    onError = Color.White,
)

val DmSansFontFamily = FontFamily(
    Font(R.font.dmsans_regular, FontWeight.Normal),
    Font(R.font.dmsans_medium, FontWeight.Medium),
    Font(R.font.dmsans_semibold, FontWeight.SemiBold),
    Font(R.font.dmsans_bold, FontWeight.Bold),
    Font(R.font.dmsans_black, FontWeight.Black),
)

val MoodTunesTypography = Typography(
    // ── Display — hero numbers, large splash text ──────────
    displayLarge = TextStyle(
        fontFamily = DmSansFontFamily,
        fontWeight = FontWeight.Black,      // dmsans_black — most impactful
        fontSize = 32.sp,
        letterSpacing = (-1).sp,
        color = MoodTunesColors.TextPrimary,
    ),
    displayMedium = TextStyle(
        fontFamily = DmSansFontFamily,
        fontWeight = FontWeight.Bold,       // dmsans_bold
        fontSize = 28.sp,                // ← fix: was 16sp, too small for display
        letterSpacing = (-0.5).sp,
        color = MoodTunesColors.TextPrimary,
    ),

    // ── Headline — page titles, screen headers ──────────────
    headlineLarge = TextStyle(
        fontFamily = DmSansFontFamily,
        fontWeight = FontWeight.Bold,       // dmsans_bold
        fontSize = 24.sp,
        letterSpacing = (-0.5).sp,
        color = MoodTunesColors.TextPrimary,
    ),
    headlineMedium = TextStyle(
        fontFamily = DmSansFontFamily,
        fontWeight = FontWeight.Bold,       // dmsans_bold
        fontSize = 20.sp,
        letterSpacing = (-0.5).sp,
        color = MoodTunesColors.TextPrimary,
    ),

    // ── Title — card titles, section headers ────────────────
    titleLarge = TextStyle(
        fontFamily = DmSansFontFamily,
        fontWeight = FontWeight.SemiBold,      // dmsans_semibold
        fontSize = 18.sp,
        color = MoodTunesColors.TextPrimary,
    ),
    titleMedium = TextStyle(
        fontFamily = DmSansFontFamily,
        fontWeight = FontWeight.SemiBold,      // dmsans_semibold
        fontSize = 15.sp,
        color = MoodTunesColors.TextPrimary,
    ),
    titleSmall = TextStyle(
        fontFamily = DmSansFontFamily,
        fontWeight = FontWeight.Medium,        // ← fix: Medium instead of SemiBold
        fontSize = 13.sp,                   //    feels lighter and cleaner
        color = MoodTunesColors.TextPrimary,
    ),

    // ── Body — descriptions, content text ───────────────────
    bodyLarge = TextStyle(
        fontFamily = DmSansFontFamily,
        fontWeight = FontWeight.Normal,        // dmsans_regular
        fontSize = 16.sp,
        lineHeight = 24.sp,
        color = MoodTunesColors.TextPrimary,
    ),
    bodyMedium = TextStyle(
        fontFamily = DmSansFontFamily,
        fontWeight = FontWeight.Normal,        // dmsans_regular
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = MoodTunesColors.TextSecondary,
    ),
    bodySmall = TextStyle(
        fontFamily = DmSansFontFamily,
        fontWeight = FontWeight.Normal,        // dmsans_regular
        fontSize = 12.sp,
        lineHeight = 18.sp,
        color = MoodTunesColors.TextSecondary,
    ),

    // ── Label — buttons, chips, badges ──────────────────────
    labelLarge = TextStyle(
        fontFamily = DmSansFontFamily,
        fontWeight = FontWeight.SemiBold,   // dmsans_semibold — buttons need weight
        fontSize = 12.sp,
        letterSpacing = 0.1.sp,
        color = MoodTunesColors.TextSecondary,
    ),
    labelSmall = TextStyle(
        fontFamily = DmSansFontFamily,
        fontWeight = FontWeight.Medium,     // ← fix: Medium not Normal — more readable
        fontSize = 10.sp,
        letterSpacing = 0.08.sp,
        color = MoodTunesColors.TextTertiary,
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
        colorScheme = LightColorScheme,
        typography = MoodTunesTypography,
        shapes = MoodTunesShapes,
        content = content
    )
}
