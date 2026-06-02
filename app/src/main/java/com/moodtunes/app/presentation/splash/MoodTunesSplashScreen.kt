package com.moodtunes.app.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moodtunes.app.R
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme
import com.moodtunes.app.ui.theme.MoodTunesTypography

@Composable
fun MoodTunesSplashScreen(
    onNavigateToSignIn: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state) {
        when (state) {
            is SplashState.GoHome -> onNavigateToHome()
            is SplashState.GoSignIn -> onNavigateToSignIn()
            is SplashState.Loading -> { /* Do nothing, just wait */
            }
        }
    }
    SplashContent()
}

@Composable
private fun SplashContent() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_music_note),
                contentDescription = "MoodTunes Logo",
                modifier = Modifier.size(120.dp),
            )
            Text(
                text = stringResource(id = R.string.app_name),
                style = MoodTunesTypography.displayLarge
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = stringResource(id = R.string.app_tagline),
                style = MoodTunesTypography.headlineLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoodTunesSplashScreenPreview() {
    MoodTunesTheme {
        SplashContent()
    }
}



