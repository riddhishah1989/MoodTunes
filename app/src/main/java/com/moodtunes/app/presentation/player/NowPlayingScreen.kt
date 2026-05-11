package com.moodtunes.app.presentation.player

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.moodtunes.app.R
import com.moodtunes.app.domain.model.Song
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme

@Composable
fun NowPlayingScreen(
    songIndex: Int,
    sessionId: String,
    onBack: () -> Unit,
    viewModel: NowPlayingViewModel = hiltViewModel(),
) {
    val song    by viewModel.song.collectAsState()
    val context = LocalContext.current
    var showLyrics by remember { mutableStateOf(false) }

    LaunchedEffect(songIndex, sessionId) { viewModel.load(songIndex, sessionId) }

    val isFav by viewModel.isFavourite(song?.id ?: "").collectAsState(initial = false)

    Scaffold(containerColor = MoodTunesColors.Background) { padding ->
        song?.let { s ->
            Column(
                modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(8.dp))
                // ── Top Bar ───────────────────────────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, stringResource(R.string.cd_back), tint = MoodTunesColors.TextSecondary)
                    }
                    Text(stringResource(R.string.now_playing_title), style = MaterialTheme.typography.titleMedium,
                        color = MoodTunesColors.TextPrimary)
                    IconButton(onClick = { showLyrics = !showLyrics }) {
                        Icon(if (showLyrics) Icons.Filled.MusicNote else Icons.Filled.Lyrics,
                            stringResource(R.string.cd_toggle_view), tint = MoodTunesColors.TextSecondary)
                    }
                }

                Spacer(Modifier.height(24.dp))

                if (!showLyrics) {
                    // ── Album Art ─────────────────────────────────────────
                    Box(
                        modifier = Modifier.size(240.dp).clip(RoundedCornerShape(24.dp))
                            .background(Color(0xFF1A1A2E)),
                        contentAlignment = Alignment.Center,
                    ) {
                        if (s.albumArt != null) {
                            AsyncImage(model = s.albumArt, contentDescription = null,
                                modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                        } else {
                            Icon(
                                painter = painterResource(R.drawable.ic_music_note),
                                contentDescription = null,
                                modifier = Modifier.size(80.dp),
                                tint = Color(0xFF4A4870),
                            )
                        }
                    }
                } else {
                    // ── Song Reason (Claude AI explanation) ───────────────
                    Box(
                        modifier = Modifier.height(240.dp).fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .background(MoodTunesColors.PrimaryContainer)
                            .padding(20.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = stringResource(R.string.now_playing_why_this_song, s.reason),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MoodTunesColors.PrimaryVariant,
                            textAlign = TextAlign.Center,
                            lineHeight = 24.sp,
                        )
                    }
                }

                Spacer(Modifier.height(32.dp))

                // ── Song Info ─────────────────────────────────────────────
                Text(s.title, style = MaterialTheme.typography.headlineMedium,
                    color = MoodTunesColors.TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.height(4.dp))
                Text("${s.artist} · ${s.album}", style = MaterialTheme.typography.bodyMedium,
                    color = MoodTunesColors.TextSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)

                Spacer(Modifier.height(24.dp))

                // ── Source Toggle Pills ───────────────────────────────────
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = {
                            val url = s.spotifyUrl ?: "https://open.spotify.com/search/${Uri.encode(s.spotifyQuery)}"
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        },
                        shape  = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MoodTunesColors.Spotify),
                        modifier = Modifier.height(36.dp),
                    ) {
                        Icon(Icons.Filled.PlayArrow, null, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(4.dp))
                        Text(stringResource(R.string.btn_spotify), style = MaterialTheme.typography.labelLarge)
                    }
                    Button(
                        onClick = {
                            val url = s.youtubeUrl ?: "https://www.youtube.com/results?search_query=${Uri.encode(s.youtubeQuery)}"
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        },
                        shape  = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MoodTunesColors.YouTube),
                        modifier = Modifier.height(36.dp),
                    ) {
                        Icon(Icons.Filled.PlayCircle, null, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(4.dp))
                        Text(stringResource(R.string.btn_youtube), style = MaterialTheme.typography.labelLarge)
                    }
                }

                Spacer(Modifier.weight(1f))

                // ── Playback Controls ─────────────────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(onClick = { viewModel.previous() }, modifier = Modifier.size(48.dp)) {
                        Icon(Icons.Filled.SkipPrevious, stringResource(R.string.cd_previous),
                            tint = MoodTunesColors.TextSecondary, modifier = Modifier.size(32.dp))
                    }
                    Box(
                        modifier = Modifier.size(64.dp).clip(CircleShape)
                            .background(MoodTunesColors.Primary),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(Icons.Filled.PlayArrow, stringResource(R.string.cd_play),
                            tint = Color.White, modifier = Modifier.size(36.dp))
                    }
                    IconButton(onClick = { viewModel.next() }, modifier = Modifier.size(48.dp)) {
                        Icon(Icons.Filled.SkipNext, stringResource(R.string.cd_next),
                            tint = MoodTunesColors.TextSecondary, modifier = Modifier.size(32.dp))
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    IconButton(onClick = { viewModel.toggleFavourite(s) }) {
                        Icon(
                            imageVector = if (isFav) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = stringResource(R.string.cd_favourite),
                            tint = if (isFav) MoodTunesColors.MoodRomantic else MoodTunesColors.TextTertiary,
                        )
                    }
                }
            }
        } ?: Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = MoodTunesColors.Primary)
        }
    }
}

// ── Preview ───────────────────────────────────────────────────────────────
@Preview(name = "NowPlayingScreen - Loading", showBackground = true)
@Composable
private fun NowPlayingScreenPreview() {
    MoodTunesTheme {
        NowPlayingScreen(songIndex = 0, sessionId = "", onBack = {})
    }
}
