package com.moodtunes.app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.moodtunes.app.domain.model.Mood
import com.moodtunes.app.domain.model.Song
import androidx.compose.ui.tooling.preview.Preview
import com.moodtunes.app.navigation.Screen
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme

// ── Bottom Navigation Bar ─────────────────────────────────────────────────
data class BottomNavItem(
    val label: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

val bottomNavItems = listOf(
    BottomNavItem("Home", Screen.Home.route, Icons.Filled.Home, Icons.Outlined.Home),
    BottomNavItem("History", Screen.History.route, Icons.Filled.History, Icons.Outlined.History),
    BottomNavItem(
        "Favourites",
        Screen.Favourites.route,
        Icons.Filled.Favorite,
        Icons.Outlined.FavoriteBorder
    ),
    BottomNavItem("Settings", "settings", Icons.Filled.Settings, Icons.Outlined.Settings),
)

@Composable
fun MoodTunesBottomNav(
    currentRoute: String?,
    onNavigate: (String) -> Unit,
) {
    NavigationBar(
        containerColor = MoodTunesColors.Surface,
        contentColor = MoodTunesColors.TextSecondary,
        tonalElevation = 0.dp,
        modifier = Modifier.border(
            width = 0.5.dp,
            color = MoodTunesColors.Divider,
            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
        )
    ) {
        bottomNavItems.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigate(item.route) },
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label,
                        modifier = Modifier.size(22.dp),
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.labelSmall,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MoodTunesColors.Primary,
                    selectedTextColor = MoodTunesColors.Primary,
                    unselectedIconColor = MoodTunesColors.TextTertiary,
                    unselectedTextColor = MoodTunesColors.TextTertiary,
                    indicatorColor = Color.Transparent,
                ),
            )
        }
    }
}

// ── Mood Chip ─────────────────────────────────────────────────────────────
@Composable
fun MoodChip(
    mood: Mood,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showDescription: Boolean = false,
) {
    val accentColor = Color(mood.color)
    val bgColor = Color(mood.bgColor)
    val borderColor = if (selected) accentColor else Color(0xFF1E1E36)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (selected) bgColor.copy(alpha = 0.8f) else bgColor)
            .border(
                width = if (selected) 1.dp else 0.5.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 10.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = mood.emoji, fontSize = 24.sp)
            Text(
                text = mood.label,
                style = MaterialTheme.typography.labelLarge,
                color = accentColor,
                fontWeight = FontWeight.Medium,
            )
            if (showDescription) {
                Text(
                    text = mood.description,
                    style = MaterialTheme.typography.labelSmall,
                    color = accentColor.copy(alpha = 0.5f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

// ── Song Card ─────────────────────────────────────────────────────────────
@Composable
fun SongCard(
    song: Song,
    isFavourite: Boolean = false,
    onClick: () -> Unit,
    onSpotifyClick: () -> Unit,
    onYouTubeClick: () -> Unit,
    onFavouriteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MoodTunesColors.Surface)
            .border(0.5.dp, MoodTunesColors.CardBorder, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Album Art
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF1A1A2E)),
                contentAlignment = Alignment.Center,
            ) {
                if (song.albumArt != null) {
                    AsyncImage(
                        model = song.albumArt,
                        contentDescription = "${song.title} album art",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                } else {
                    Text(text = "🎵", fontSize = 20.sp)
                }
            }

            // Song Info
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = song.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color(0xFFE0E0FF),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = song.artist,
                    style = MaterialTheme.typography.bodySmall,
                    color = MoodTunesColors.TextTertiary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                // Genre Pill
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(MoodTunesColors.PrimaryContainer)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = song.genre,
                        style = MaterialTheme.typography.labelSmall,
                        color = MoodTunesColors.PrimaryVariant,
                    )
                }
            }

            // Action Buttons
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                // Spotify
                IconButton(
                    onClick = onSpotifyClick,
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(
                            if (song.spotifyUrl != null) MoodTunesColors.Spotify else Color(
                                0xFF1E1E36
                            )
                        )
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "Play on Spotify",
                        tint = Color.White,
                        modifier = Modifier.size(14.dp),
                    )
                }
                // YouTube
                IconButton(
                    onClick = onYouTubeClick,
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(
                            if (song.youtubeUrl != null) MoodTunesColors.YouTube else Color(
                                0xFF1E1E36
                            )
                        )
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayCircle,
                        contentDescription = "Watch on YouTube",
                        tint = Color.White,
                        modifier = Modifier.size(14.dp),
                    )
                }
                // Favourite
                IconButton(
                    onClick = onFavouriteClick,
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF1E1E36))
                        .border(0.5.dp, MoodTunesColors.CardBorder, CircleShape)
                ) {
                    Icon(
                        imageVector = if (isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favourite",
                        tint = if (isFavourite) MoodTunesColors.MoodRomantic else MoodTunesColors.TextTertiary,
                        modifier = Modifier.size(14.dp),
                    )
                }
            }
        }
    }
}

// ── Loading Overlay ───────────────────────────────────────────────────────
@Composable
fun LoadingOverlay(message: String = "Finding your perfect songs...") {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MoodTunesColors.Background.copy(alpha = 0.9f)),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            CircularProgressIndicator(color = MoodTunesColors.Primary)
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MoodTunesColors.TextSecondary,
            )
            Text(
                text = "Powered by Claude AI",
                style = MaterialTheme.typography.labelSmall,
                color = MoodTunesColors.TextTertiary,
            )
        }
    }
}

// ── Error State ───────────────────────────────────────────────────────────
@Composable
fun ErrorState(
    message: String,
    onRetry: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(text = "😕", fontSize = 48.sp)
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MoodTunesColors.TextSecondary,
        )
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(containerColor = MoodTunesColors.Primary),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text("Try again")
        }
    }
}

// ── Mood Interpretation Banner ────────────────────────────────────────────
@Composable
fun MoodInterpretationBanner(interpretation: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
            .background(MoodTunesColors.PrimaryContainer)
            .border(
                width = 0.5.dp,
                color = MoodTunesColors.CardBorder,
                shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
            )
            .padding(start = 0.dp, top = 10.dp, end = 12.dp, bottom = 10.dp),
    ) {
        Spacer(
            modifier = Modifier
                .width(3.dp)
                .height(40.dp)
                .background(MoodTunesColors.Primary)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = interpretation,
            style = MaterialTheme.typography.bodySmall,
            color = MoodTunesColors.PrimaryVariant,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

// ── Preview Data ──────────────────────────────────────────────────────────
private val previewMood = Mood(
    id = "happy", label = "Happy", emoji = "😊", description = "Upbeat & joyful",
    color = 0xFFFFD93D, bgColor = 0xFF1A1500,
)
private val previewSong = Song(
    id = "1", title = "Blinding Lights", artist = "The Weeknd", album = "After Hours",
    genre = "Pop", year = 2020, reason = "Energetic beat matching your mood",
    energyLevel = "High", tempo = "Fast",
    spotifyQuery = "Blinding Lights The Weeknd", youtubeQuery = "Blinding Lights The Weeknd",
    spotifyUrl = null, previewUrl = null, albumArt = null, albumArtThumb = null,
    youtubeUrl = null, youtubeThumbnail = null, youtubeVideoId = null,
)

// ── Previews ──────────────────────────────────────────────────────────────
@Preview(name = "BottomNav", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun MoodTunesBottomNavPreview() {
    MoodTunesTheme {
        MoodTunesBottomNav(currentRoute = "home", onNavigate = {})
    }
}

@Preview(name = "MoodChip - Unselected", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun MoodChipUnselectedPreview() {
    MoodTunesTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            MoodChip(mood = previewMood, selected = false, onClick = {})
        }
    }
}

@Preview(name = "MoodChip - Selected", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun MoodChipSelectedPreview() {
    MoodTunesTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            MoodChip(mood = previewMood, selected = true, onClick = {}, showDescription = true)
        }
    }
}

@Preview(name = "SongCard", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun SongCardPreview() {
    MoodTunesTheme {
        SongCard(
            song = previewSong,
            isFavourite = false,
            onClick = {},
            onSpotifyClick = {},
            onYouTubeClick = {},
            onFavouriteClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview(name = "SongCard - Favourited", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun SongCardFavouritedPreview() {
    MoodTunesTheme {
        SongCard(
            song = previewSong,
            isFavourite = true,
            onClick = {},
            onSpotifyClick = {},
            onYouTubeClick = {},
            onFavouriteClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview(name = "LoadingOverlay", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun LoadingOverlayPreview() {
    MoodTunesTheme {
        LoadingOverlay()
    }
}

@Preview(name = "ErrorState", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun ErrorStatePreview() {
    MoodTunesTheme {
        ErrorState(message = "Something went wrong. Please try again.", onRetry = {})
    }
}

@Preview(name = "MoodInterpretationBanner", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun MoodInterpretationBannerPreview() {
    MoodTunesTheme {
        MoodInterpretationBanner(
            interpretation = "You're feeling happy and energetic. Perfect for upbeat pop and dance tracks.",
            modifier = Modifier.padding(16.dp),
        )
    }
}
