package com.moodtunes.app.presentation.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moodtunes.app.R
import com.moodtunes.app.domain.local.StaticData
import com.moodtunes.app.domain.model.Genre
import com.moodtunes.app.presentation.components.CircularAppIconWithText
import com.moodtunes.app.presentation.components.CustomTextField
import com.moodtunes.app.presentation.components.MoodTunesLoadingOverlay
import com.moodtunes.app.presentation.components.MoodTunesSnackbarHost
import com.moodtunes.app.presentation.components.PrimaryButton
import com.moodtunes.app.presentation.components.rememberMoodTunesSnackbar
import com.moodtunes.app.presentation.state.UiState
import com.moodtunes.app.ui.theme.MoodTunesColors
import com.moodtunes.app.ui.theme.MoodTunesTypography

@Composable
fun SignUpScreen(onNavigationLoginScreen: () -> Unit, onSignUpSuccess: () -> Unit) {
    val signUpViewModel: SignUpViewModel = hiltViewModel()
    val uiState by signUpViewModel.uiStateSignUp.collectAsState()
    val genres by signUpViewModel.genres.collectAsState()
    val selectedGenreIds by signUpViewModel.selectedGenreIds.collectAsState()
    val snackbarHostState = rememberMoodTunesSnackbar()

    // ── Navigate on success ───────────────────────────────
    LaunchedEffect(uiState) {
        if (uiState is UiState.Success) {
            onSignUpSuccess()
        }
    }

    // ── Snackbar on error ─────────────────────────────────
    LaunchedEffect(uiState) {
        if (uiState is UiState.Error) {
            snackbarHostState.showSnackbar(
                (uiState as UiState.Error).message
            )
            signUpViewModel.resetState()
        }
    }

    Scaffold(
        snackbarHost = { MoodTunesSnackbarHost(snackbarHostState) }) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            RegisterContent(
                genres = genres,
                selectedGenreIds = selectedGenreIds,
                goBackToLogin = onNavigationLoginScreen,
                onGenreToggle = { signUpViewModel.toggleGenre(it) },
                onSignUpClick = { name, email, password ->
                    signUpViewModel.registerUser(
                        name,
                        email,
                        password,
                    )
                },
            )
            // ── Loading overlay ───────────────────────────
            MoodTunesLoadingOverlay(isLoading = uiState is UiState.Loading)
        }
    }
}

@Composable
fun RegisterContent(
    genres: List<Genre>,
    selectedGenreIds: List<String>,
    goBackToLogin: () -> Unit,
    onGenreToggle: (Genre) -> Unit,
    onSignUpClick: (name: String, email: String, password: String) -> Unit,
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        CircularAppIconWithText(outerCircleSize = 120, imageSize = 60)
        Spacer(modifier = Modifier.size(30.dp))
        Text(
            text = "Welcome to MoodTunes!",
            style = MoodTunesTypography.headlineMedium,
            color = MoodTunesColors.TextPrimary,
        )
        Spacer(modifier = Modifier.size(15.dp))
        CustomTextField(
            value = fullName,
            onValueChange = { fullName = it },
            keyboardType = KeyboardType.Text,
            hint = stringResource(R.string.hint_full_name),
        )
        Spacer(modifier = Modifier.size(15.dp))
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            keyboardType = KeyboardType.Email,
            hint = stringResource(R.string.hint_email),
        )
        Spacer(modifier = Modifier.size(15.dp))
        CustomTextField(
            value = password,
            onValueChange = { password = it },
            keyboardType = KeyboardType.Password,
            hint = stringResource(R.string.hint_password),
            isPassword = true,
        )
        Spacer(modifier = Modifier.size(15.dp))
        CustomTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            keyboardType = KeyboardType.Password,
            hint = stringResource(R.string.hint_confirm_password),
            isPassword = true,
        )
        Spacer(modifier = Modifier.size(15.dp))

        // ── Chips — only show if genres loaded ────────────
        if (genres.isNotEmpty()) {
            GenreChipGroup(
                genres = genres,
                selectedGenreIds = selectedGenreIds,
                onGenreToggle = onGenreToggle,
            )
            Spacer(modifier = Modifier.size(15.dp))
        }

        PrimaryButton(
            text = "Register",
            onClick = { onSignUpClick(fullName, email, password) },
        )
        Spacer(modifier = Modifier.size(15.dp))
        Row {
            Text(
                text = "Already have an account? ",
                color = MoodTunesColors.TextSecondary,
            )
            Text(
                text = "Sign in",
                color = MoodTunesColors.Primary,
                modifier = Modifier.clickable { goBackToLogin() },
            )
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenreChipGroup(
    genres: List<Genre>,
    selectedGenreIds: List<String>,
    onGenreToggle: (Genre) -> Unit, initialVisibleCount: Int = 8,
) {
    var expanded by remember { mutableStateOf(false) }

    val visibleGenres = if (expanded) genres else genres.take(initialVisibleCount)
    val remaining = genres.size - initialVisibleCount

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Preferred Genre", style = MoodTunesTypography.labelMedium)
        Text(text = "Select up to 5 genres", style = MoodTunesTypography.labelMedium)

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            visibleGenres.forEach { genre ->
                val isSelected = selectedGenreIds.contains(genre.id)
                FilterChip(
                    selected = isSelected,
                    onClick = { onGenreToggle(genre) },
                    label = {
                        Text(
                            text = "${genre.emoji ?: ""} ${genre.name}",
                            style = if (isSelected) {
                                MoodTunesTypography.labelMedium
                            } else {
                                MoodTunesTypography.bodyMedium
                            },
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MoodTunesColors.Primary,
                        selectedLabelColor = Color.White,
                        containerColor = MoodTunesColors.PrimaryContainer,
                        labelColor = MoodTunesColors.Primary,
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        enabled = true,
                        selected = isSelected,
                        borderColor = MoodTunesColors.CardBorder,
                        selectedBorderColor = MoodTunesColors.Primary,
                        borderWidth = 0.5.dp,
                        selectedBorderWidth = 1.dp,
                    ),
                )
            }
        }
        // ── Show More / Less ──────────────────────────────
        if (genres.size > initialVisibleCount) {
            Text(
                text = if (expanded) "Show less ↑" else "+ $remaining more genres ↓",
                style = MoodTunesTypography.labelLarge,
                color = MoodTunesColors.Primary,
                modifier = Modifier
                    .clickable { expanded = !expanded }
                    .padding(top = 4.dp),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewRegister() {
    RegisterContent(
        genres = emptyList(),
        selectedGenreIds = emptyList(),
        goBackToLogin = {},
        onGenreToggle = {},
        onSignUpClick = { _, _, _ -> },
    )
}
