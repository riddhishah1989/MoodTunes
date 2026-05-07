package com.moodtunes.app.presentation.moodpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.tooling.preview.Preview
import com.moodtunes.app.presentation.components.AppTopBar
import com.moodtunes.app.presentation.components.LoadingOverlay
import com.moodtunes.app.presentation.components.MoodChip
import com.moodtunes.app.presentation.components.PrimaryButton
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme

@Composable
fun MoodPickerScreen(
    onBack: () -> Unit,
    onRecommendationsReady: (String) -> Unit,
    viewModel: MoodPickerViewModel = hiltViewModel(),
) {
    val uiState     by viewModel.uiState.collectAsState()
    val selectedMood by viewModel.selectedMood.collectAsState()
    var customText   by remember { mutableStateOf("") }

    LaunchedEffect(uiState) {
        if (uiState is MoodPickerUiState.Success) {
            onRecommendationsReady((uiState as MoodPickerUiState.Success).sessionId)
            viewModel.resetState()
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(MoodTunesColors.Background)) {
        Column(modifier = Modifier.fillMaxSize()) {
            // ── Top Bar ───────────────────────────────────────────────────
            AppTopBar(
                onBack      = onBack,
                title       = "Choose your mood",
                subtitle    = "or describe it below",
                centerTitle = true,
            )

            // ── Mood Grid ─────────────────────────────────────────────────
            LazyVerticalGrid(
                columns             = GridCells.Fixed(2),
                contentPadding      = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement   = Arrangement.spacedBy(8.dp),
                modifier            = Modifier.weight(1f),
            ) {
                items(viewModel.moods) { mood ->
                    MoodChip(
                        mood            = mood,
                        selected        = selectedMood?.id == mood.id,
                        onClick         = { viewModel.selectMood(mood) },
                        showDescription = true,
                        modifier        = Modifier.fillMaxWidth().height(90.dp),
                    )
                }
            }

            // ── Free Text Input ───────────────────────────────────────────
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(0xFF0C0C1E))
                        .border(0.5.dp, Color(0xFF2A2A48), RoundedCornerShape(14.dp))
                ) {
                    TextField(
                        value         = customText,
                        onValueChange = { customText = it },
                        placeholder   = { Text("Describe your mood... e.g. \"just got a promotion!\"",
                            style = MaterialTheme.typography.bodySmall,
                            color = MoodTunesColors.TextHint) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor   = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor   = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor        = MoodTunesColors.TextPrimary,
                            unfocusedTextColor      = MoodTunesColors.TextPrimary,
                        ),
                        modifier      = Modifier.fillMaxWidth(),
                        maxLines      = 3,
                    )
                }

                // Error message
                if (uiState is MoodPickerUiState.Error) {
                    Text(
                        text      = (uiState as MoodPickerUiState.Error).message,
                        style     = MaterialTheme.typography.bodySmall,
                        color     = MoodTunesColors.Error,
                        textAlign = TextAlign.Center,
                        modifier  = Modifier.fillMaxWidth(),
                    )
                }

                // CTA Button
                PrimaryButton(
                    text    = "Get my playlist  →",
                    onClick = { viewModel.getRecommendations(customText) },
                    enabled = selectedMood != null || customText.isNotBlank(),
                )

                Text(
                    text      = "Powered by Claude AI · Anthropic",
                    style     = MaterialTheme.typography.labelSmall,
                    color     = MoodTunesColors.TextHint,
                    textAlign = TextAlign.Center,
                    modifier  = Modifier.fillMaxWidth(),
                )
            }
        }

        // Loading overlay
        if (uiState is MoodPickerUiState.Loading) {
            LoadingOverlay()
        }
    }
}

// ── Preview ───────────────────────────────────────────────────────────────
@Preview(name = "MoodPickerScreen", showBackground = true)
@Composable
private fun MoodPickerScreenPreview() {
    MoodTunesTheme {
        MoodPickerScreen(onBack = {}, onRecommendationsReady = {})
    }
}
