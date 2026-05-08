package com.moodtunes.app.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.ui.res.stringResource
import com.moodtunes.app.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTheme
import kotlinx.coroutines.launch

data class OnboardingPage(val emoji: String, val title: String, val subtitle: String)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onFinished: () -> Unit) {
    val pages = listOf(
        OnboardingPage("🎵", stringResource(R.string.onboarding_p1_title), stringResource(R.string.onboarding_p1_subtitle)),
        OnboardingPage("🎯", stringResource(R.string.onboarding_p2_title), stringResource(R.string.onboarding_p2_subtitle)),
        OnboardingPage("💕", stringResource(R.string.onboarding_p3_title), stringResource(R.string.onboarding_p3_subtitle)),
    )
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize().background(MoodTunesColors.Background)) {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(pages[page].emoji, fontSize = 80.sp)
                Spacer(Modifier.height(32.dp))
                Text(
                    text = pages[page].title,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MoodTunesColors.TextPrimary,
                    textAlign = TextAlign.Center,
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = pages[page].subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MoodTunesColors.TextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp,
                )
            }
        }

        // Dot indicators
        Row(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 160.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            repeat(pages.size) { i ->
                Box(
                    modifier = Modifier
                        .size(if (i == pagerState.currentPage) 20.dp else 6.dp, 6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(
                            if (i == pagerState.currentPage) MoodTunesColors.Primary
                            else MoodTunesColors.CardBorder
                        )
                )
            }
        }

        // Buttons
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
                .fillMaxWidth().padding(horizontal = 32.dp, vertical = 48.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Button(
                onClick = {
                    if (pagerState.currentPage < pages.lastIndex)
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    else onFinished()
                },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape  = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MoodTunesColors.Primary),
            ) {
                Text(
                    if (pagerState.currentPage < pages.lastIndex) stringResource(R.string.btn_continue) else stringResource(R.string.btn_get_started),
                    style = MaterialTheme.typography.titleSmall, color = Color.White,
                )
            }
            if (pagerState.currentPage < pages.lastIndex) {
                TextButton(onClick = onFinished, modifier = Modifier.fillMaxWidth()) {
                    Text(stringResource(R.string.btn_skip), color = MoodTunesColors.TextTertiary,
                        style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

// ── Preview ───────────────────────────────────────────────────────────────
@OptIn(ExperimentalFoundationApi::class)
@Preview(name = "Onboarding Screen", showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    MoodTunesTheme {
        OnboardingScreen(onFinished = {})
    }
}
