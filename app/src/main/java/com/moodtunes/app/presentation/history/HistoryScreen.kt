package com.moodtunes.app.presentation.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moodtunes.app.data.local.MoodSessionEntity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.moodtunes.app.R
import com.moodtunes.app.presentation.components.AppTopBar
import com.moodtunes.app.presentation.preview.previewHistorySession
import com.moodtunes.app.presentation.components.EmptyState
import com.moodtunes.app.presentation.components.MoodTunesCard
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HistoryScreen(
    onBack: () -> Unit,
    onSessionClick: (String) -> Unit,
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val sessions by viewModel.sessions.collectAsState()

    val grouped = remember(sessions) {
        sessions.groupBy { session ->
            val cal = Calendar.getInstance().apply { timeInMillis = session.timestamp }
            val today = Calendar.getInstance()
            val yesterday = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -1) }
            when {
                isSameDay(cal, today)     -> "Today"
                isSameDay(cal, yesterday) -> "Yesterday"
                else -> SimpleDateFormat("MMMM d", Locale.getDefault()).format(Date(session.timestamp))
            }
        }
    }

    Scaffold(
        containerColor = MoodTunesColors.Background,
        topBar = {
            AppTopBar(
                onBack   = onBack,
                title    = stringResource(R.string.history_title),
                subtitle = stringResource(R.string.history_subtitle),
                action   = {
                    IconButton(onClick = { viewModel.clearHistory() }) {
                        Icon(Icons.Filled.DeleteOutline, contentDescription = stringResource(R.string.cd_clear_history),
                            tint = MoodTunesColors.Error)
                    }
                }
            )
        }
    ) { padding ->
        if (sessions.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                EmptyState(emoji = "🕐", message = stringResource(R.string.history_empty))
            }
            return@Scaffold
        }
        LazyColumn(
            modifier       = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(bottom = 24.dp),
        ) {
            grouped.forEach { (dateLabel, daySessions) ->
                item {
                    Text(
                        text     = dateLabel.uppercase(),
                        style    = MaterialTheme.typography.labelSmall,
                        color    = MoodTunesColors.TextTertiary,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                    )
                }
                items(daySessions) { session ->
                    HistoryItemCard(
                        session  = session,
                        isFirst  = daySessions.first() == session,
                        onClick  = { onSessionClick(session.sessionId) },
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 3.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun HistoryItemCard(
    session: MoodSessionEntity,
    isFirst: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val timeStr = remember(session.timestamp) {
        SimpleDateFormat("h:mm a", Locale.getDefault()).format(Date(session.timestamp))
    }
    MoodTunesCard(modifier = modifier.fillMaxWidth(), onClick = onClick) {
        Row(
            modifier              = Modifier.fillMaxWidth(),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                modifier         = Modifier.size(42.dp).clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF1A1A2E)),
                contentAlignment = Alignment.Center,
            ) { Text(session.moodEmoji, fontSize = 20.sp) }
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(session.moodLabel, style = MaterialTheme.typography.titleSmall,
                        color = MoodTunesColors.TextPrimary)
                    if (isFirst) {
                        Box(
                            modifier = Modifier.clip(RoundedCornerShape(20.dp))
                                .background(Color(0xFF12102E))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(stringResource(R.string.history_badge_new), style = MaterialTheme.typography.labelSmall,
                                color = MoodTunesColors.Primary)
                        }
                    }
                }
                Text(stringResource(R.string.session_time_songs, timeStr, session.songs.size),
                    style = MaterialTheme.typography.bodySmall,
                    color = MoodTunesColors.TextTertiary)
            }
            Icon(Icons.Filled.ChevronRight, contentDescription = null,
                tint = MoodTunesColors.TextTertiary, modifier = Modifier.size(18.dp))
        }
    }
}

private fun isSameDay(a: Calendar, b: Calendar) =
    a.get(Calendar.YEAR) == b.get(Calendar.YEAR) &&
            a.get(Calendar.DAY_OF_YEAR) == b.get(Calendar.DAY_OF_YEAR)

// ── Previews ──────────────────────────────────────────────────────────────
@Preview(name = "HistoryItemCard", showBackground = true, backgroundColor = 0xFF08080F)
@Composable
private fun HistoryItemCardPreview() {
    MoodTunesTheme {
        HistoryItemCard(
            session  = previewHistorySession,
            isFirst  = true,
            onClick  = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
