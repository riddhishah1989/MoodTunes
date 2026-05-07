package com.moodtunes.app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moodtunes.app.data.local.MoodSessionEntity
import com.moodtunes.app.domain.model.PresetMoods
import androidx.compose.ui.tooling.preview.Preview
import com.moodtunes.app.presentation.components.EmptyState
import com.moodtunes.app.presentation.components.MoodChip
import com.moodtunes.app.presentation.components.MoodTunesCard
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(
    onMoodClick: () -> Unit,
    onSessionClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val sessions by viewModel.recentSessions.collectAsState()
    val greeting = remember { getGreeting() }

    Scaffold(
        containerColor = MoodTunesColors.Background,
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp),
        ) {
            // ── Top Bar ───────────────────────────────────────────────────
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column {
                        Text(
                            text = "MoodTunes",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MoodTunesColors.TextPrimary,
                        )
                        Text(
                            text = greeting,
                            style = MaterialTheme.typography.bodySmall,
                            color = MoodTunesColors.TextSecondary,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF15152A))
                            .border(0.5.dp, MoodTunesColors.CardBorder, CircleShape),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            Icons.Filled.Person, contentDescription = "Profile",
                            tint = MoodTunesColors.Primary, modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // ── Hero Banner ───────────────────────────────────────────────
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF12103A))
                        .border(0.5.dp, Color(0xFF2D2B5E), RoundedCornerShape(20.dp))
                        .clickable(onClick = onMoodClick)
                        .padding(20.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "HOW ARE YOU FEELING?",
                            style = MaterialTheme.typography.labelSmall,
                            color = MoodTunesColors.Primary,
                            fontWeight = FontWeight.Medium,
                        )
                        Text(
                            text = "Let AI find music\nthat matches your mood",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MoodTunesColors.TextPrimary,
                        )
                        Text(
                            text = "Powered by Claude AI",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF4A4870),
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(MoodTunesColors.Primary)
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "Pick my mood  →",
                                style = MaterialTheme.typography.labelLarge,
                                color = Color.White,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // ── Quick Mood Scroll ─────────────────────────────────────────
            item {
                Text(
                    text = "QUICK PICK",
                    style = MaterialTheme.typography.labelSmall,
                    color = MoodTunesColors.TextTertiary,
                    modifier = Modifier.padding(horizontal = 20.dp),
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(PresetMoods.all.take(6)) { mood ->
                        MoodChip(
                            mood = mood,
                            selected = false,
                            onClick = onMoodClick,
                            modifier = Modifier.width(72.dp),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // ── Recent Sessions ───────────────────────────────────────────
            if (sessions.isNotEmpty()) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "RECENT SESSIONS",
                            style = MaterialTheme.typography.labelSmall,
                            color = MoodTunesColors.TextTertiary,
                        )
                        TextButton(onClick = {}) {
                            Text(
                                "See all", color = MoodTunesColors.Primary,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(sessions.take(3)) { session ->
                    SessionCard(
                        session = session,
                        onClick = { onSessionClick(session.sessionId) },
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
                    )
                }
            } else {
                item {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        EmptyState(emoji = "🎵", message = "Your mood sessions will appear here")
                    }
                }
            }
        }
    }
}

@Composable
private fun SessionCard(
    session: MoodSessionEntity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dateStr = remember(session.timestamp) {
        SimpleDateFormat("MMM d, h:mm a", Locale.getDefault()).format(Date(session.timestamp))
    }
    MoodTunesCard(modifier = modifier.fillMaxWidth(), onClick = onClick) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF1A1A2E)),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = session.moodEmoji, fontSize = 20.sp)
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = session.moodLabel + if (!session.customText.isNullOrBlank()) " — \"${session.customText}\"" else "",
                    style = MaterialTheme.typography.titleSmall,
                    color = MoodTunesColors.TextPrimary,
                    maxLines = 1,
                )
                Text(
                    text = "$dateStr · ${session.songs.size} songs",
                    style = MaterialTheme.typography.bodySmall,
                    color = MoodTunesColors.TextTertiary,
                )
            }
            Icon(
                Icons.Filled.ChevronRight, contentDescription = null,
                tint = MoodTunesColors.TextTertiary, modifier = Modifier.size(18.dp)
            )
        }
    }
}

private fun getGreeting(): String {
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return when {
        hour < 12 -> "Good morning — what's your vibe today?"
        hour < 17 -> "Good afternoon — how are you feeling?"
        else -> "Good evening — how was your day?"
    }
}

// ── Previews ──────────────────────────────────────────────────────────────
private val previewSession = MoodSessionEntity(
    sessionId = "1", moodId = "happy", moodLabel = "Happy", moodEmoji = "😊",
    customText = null, moodInterpretation = "Feeling joyful today",
    songs = emptyList(), timestamp = System.currentTimeMillis(),
)

@Preview(name = "SessionCard", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun SessionCardPreview() {
    MoodTunesTheme {
        SessionCard(
            session = previewSession,
            onClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
