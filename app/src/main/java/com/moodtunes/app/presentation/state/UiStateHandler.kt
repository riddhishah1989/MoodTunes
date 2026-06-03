package com.moodtunes.app.presentation.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moodtunes.app.ui.theme.MoodTunesColors

/**
 * Generic UI state handler composable.
 *
 * Every screen uses this instead of writing
 * the same when(uiState) block repeatedly.
 *
 * Usage:
 *   val uiState by viewModel.uiState.collectAsState()
 *
 *   UiStateHandler(
 *       uiState  = uiState,
 *       onRetry  = { viewModel.load() },
 *   ) { data ->
 *       // your screen content with data
 *       RecommendationsList(songs = data.songs)
 *   }
 */
@Composable
fun <T> UiStateHandler(
    uiState: UiState<T>,
    onRetry: () -> Unit,
    snackbarHostState: SnackbarHostState? = null,
    idleContent: @Composable (() -> Unit)? = null,
    content: @Composable (data: T) -> Unit,
) {
    // Show snackbar when error occurs — only if snackbarHostState provided
    LaunchedEffect(uiState) {
        if (uiState is UiState.Error && snackbarHostState != null) {
            snackbarHostState.showSnackbar(uiState.message)
        }
    }
    when (uiState) {
        is UiState.Loading -> {
            MoodTunesLoadingIndicator()
        }

        is UiState.Error -> {
            if (snackbarHostState != null) {
                // Snackbar handles the error — show form again
                idleContent?.invoke()
            } else {
                // Full screen error with retry button
                MoodTunesErrorState(
                    message = uiState.message,
                    onRetry = onRetry,
                )
            }
        }

        is UiState.Success -> {
            content(uiState.data)
        }
    }
}

// ── Shared Loading Indicator ──────────────────────────────────
@Composable
fun MoodTunesLoadingIndicator(
    message: String = "Loading...",
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            CircularProgressIndicator(
                color = MoodTunesColors.Primary,
            )
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MoodTunesColors.TextSecondary,
            )
        }
    }
}

// ── Shared Error State ────────────────────────────────────────
@Composable
fun MoodTunesErrorState(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(32.dp),
        ) {
            Text(text = "😕", style = MaterialTheme.typography.displayLarge)
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MoodTunesColors.TextSecondary,
            )
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MoodTunesColors.Primary,
                ),
            ) {
                Text("Try again")
            }
        }
    }
}
