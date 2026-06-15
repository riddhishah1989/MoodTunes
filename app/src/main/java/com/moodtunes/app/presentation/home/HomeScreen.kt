package com.moodtunes.app.presentation.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moodtunes.app.ui.theme.MoodTunesTypography

@Composable
fun HomeScreen() {
    Text(text = "Home", style = MoodTunesTypography.titleLarge)
}

@Composable
@Preview
fun HomePreview() {
    HomeScreen()
}