package com.moodtunes.app.presentation.favourites

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.moodtunes.app.data.local.FavouriteEntity
import com.moodtunes.app.presentation.components.AppTopBar
import com.moodtunes.app.presentation.components.EmptyState
import com.moodtunes.app.presentation.components.MoodTunesCard
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme
import androidx.core.net.toUri

@Composable
fun FavouritesScreen(
    onBack: () -> Unit,
    viewModel: FavouritesViewModel = hiltViewModel(),
) {
    val favs by viewModel.favourites.collectAsState()
    val context = LocalContext.current

    Scaffold(
        containerColor = MoodTunesColors.Background,
        topBar = {
            AppTopBar(
                onBack = onBack,
                title = "Favourites",
                subtitle = "${favs.size} saved tracks",
            )
        }
    ) { padding ->
        if (favs.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center,
            ) {
                EmptyState(
                    emoji = "♥",
                    message = "No favourites yet",
                    subtitle = "Heart a song from your recommendations",
                    emojiColor = MoodTunesColors.MoodRomantic,
                )
            }
            return@Scaffold
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(favs, key = { it.songId }) { fav ->
                FavouriteCard(
                    fav = fav,
                    onSpotify = {
                        fav.spotifyUrl?.let {
                            context.startActivity(
                                Intent(Intent.ACTION_VIEW, it.toUri())
                            )
                        }
                    },
                    onYouTube = {
                        fav.youtubeUrl?.let {
                            context.startActivity(
                                Intent(Intent.ACTION_VIEW, it.toUri())
                            )
                        }
                    },
                    onRemove = { viewModel.remove(fav.songId) },
                )
            }
        }
    }
}

@Composable
private fun FavouriteCard(
    fav: FavouriteEntity,
    onSpotify: () -> Unit,
    onYouTube: () -> Unit,
    onRemove: () -> Unit,
) {
    MoodTunesCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(11.dp))
                    .background(Color(0xFF1A1A2E)),
                contentAlignment = Alignment.Center,
            ) {
                if (fav.albumArt != null) {
                    AsyncImage(
                        model = fav.albumArt,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                } else {
                    Text("🎵", fontSize = 18.sp)
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = fav.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color(0xFFD0D0FF),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "${fav.artist} · ${fav.genre}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MoodTunesColors.TextTertiary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            IconButton(onClick = onSpotify, modifier = Modifier.size(32.dp)) {
                Icon(
                    Icons.Filled.PlayArrow,
                    contentDescription = "Play on Spotify",
                    tint = MoodTunesColors.Spotify,
                    modifier = Modifier.size(18.dp),
                )
            }
            IconButton(onClick = onYouTube, modifier = Modifier.size(32.dp)) {
                Icon(
                    Icons.Filled.PlayCircle,
                    contentDescription = "Play on YouTube",
                    tint = MoodTunesColors.YouTube,
                    modifier = Modifier.size(18.dp),
                )
            }
            IconButton(onClick = onRemove, modifier = Modifier.size(32.dp)) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Remove favourite",
                    tint = MoodTunesColors.MoodRomantic,
                    modifier = Modifier.size(18.dp),
                )
            }
        }
    }
}

// ── Previews ──────────────────────────────────────────────────────────────

private val previewFavourite = FavouriteEntity(
    songId = "1",
    title = "Blinding Lights",
    artist = "The Weeknd",
    album = "After Hours",
    genre = "Pop",
    albumArt = null,
    spotifyUrl = null,
    youtubeUrl = null,
)

@Preview(name = "FavouriteCard", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun FavouriteCardPreview() {
    MoodTunesTheme {
        FavouriteCard(
            fav = previewFavourite,
            onSpotify = {},
            onYouTube = {},
            onRemove = {},
        )
    }
}
