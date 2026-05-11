package com.moodtunes.app.presentation.recommendations

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.tooling.preview.Preview
import com.moodtunes.app.domain.model.PresetMoods
import com.moodtunes.app.presentation.components.*
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme

@Composable
fun RecommendationsScreen(
    sessionId: String,
    onBack: () -> Unit,
    onSongClick: (Int) -> Unit,
    onHistoryClick: () -> Unit,
    viewModel: RecommendationsViewModel = hiltViewModel(),
) {
    val session by viewModel.session.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(sessionId) { viewModel.loadSession(sessionId) }

    Scaffold(containerColor = MoodTunesColors.Background) { padding ->
        if (session == null) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = MoodTunesColors.Primary)
            }
            return@Scaffold
        }
        val s = session!!
        LazyColumn(
            modifier            = Modifier.fillMaxSize().padding(padding),
            contentPadding      = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            // ── Top Bar ───────────────────────────────────────────────────
            item {
                AppTopBar(
                    onBack      = onBack,
                    title       = "${s.moodEmoji} ${s.moodLabel} · ${s.songs.size} songs",
                    centerTitle = true,
                    titleColor  = Color(PresetMoods.all.find { it.id == s.moodId }?.color ?: 0xFFFFFFFF),
                )
            }

            // ── Mood Interpretation ───────────────────────────────────────
            item {
                MoodInterpretationBanner(
                    interpretation = s.moodInterpretation,
                    modifier       = Modifier.padding(start = 20.dp, end = 20.dp),
                )
            }

            // ── Song Cards ────────────────────────────────────────────────
            itemsIndexed(s.songs) { index, song ->
                val isFav by viewModel.getFavouriteFlow(song.id).collectAsState(initial = false)
                SongCard(
                    song             = song,
                    isFavourite      = isFav,
                    onClick          = { onSongClick(index) },
                    onSpotifyClick   = {
                        song.spotifyUrl?.let { url ->
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        } ?: run {
                            val query = Uri.encode(song.spotifyQuery)
                            context.startActivity(Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://open.spotify.com/search/$query")))
                        }
                    },
                    onYouTubeClick   = {
                        val url = song.youtubeUrl
                            ?: "https://www.youtube.com/results?search_query=${Uri.encode(song.youtubeQuery)}"
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    },
                    onFavouriteClick = {
                        if (isFav) viewModel.getFavouriteFlow(song.id) // just a read
                        viewModel.toggleFavourite(song)
                    },
                    modifier = Modifier.padding(horizontal = 20.dp),
                )
            }
        }
    }
}

// ── Preview ───────────────────────────────────────────────────────────────
@Preview(name = "RecommendationsScreen - Loading", showBackground = true)
@Composable
private fun RecommendationsScreenPreview() {
    MoodTunesTheme {
        RecommendationsScreen(sessionId = "", onBack = {}, onSongClick = {}, onHistoryClick = {})
    }
}
