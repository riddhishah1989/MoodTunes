package com.moodtunes.app.presentation.changepassword

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moodtunes.app.ui.theme.MoodTunesTypography

@Composable
fun ChangePasswordScreen(onChangePasswordSuccess: () -> Unit) {
    Text(text = "ChangePasswordScreen", style = MoodTunesTypography.titleLarge)
}

@Composable
fun ChangePasswordContent(){

}

@Composable
@Preview
fun ChangePasswordPreview() {
    ChangePasswordScreen(onChangePasswordSuccess = {})
}