package com.moodtunes.app.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpTextField(
    otpText: String,
    onOtpTextChange: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    otpLength: Int = 4
) {
    BasicTextField(
        modifier = modifier,
        value = otpText,
        onValueChange = { newValue ->
            // Allow only digits and limit the length
            if (newValue.length <= otpLength && newValue.all { it.isDigit() }) {
                onOtpTextChange(newValue, newValue.length == otpLength)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(otpLength) { index ->
                    val char = otpText.getOrNull(index)
                    val isFocused = index == otpText.length

                    OtpDigitCell(
                        char = char,
                        isFocused = isFocused
                    )
                }
            }
        }
    )
}

@Composable
fun OtpDigitCell(
    char: Char?,
    isFocused: Boolean,
    modifier: Modifier = Modifier
) {
    val borderColor = if (isFocused) Color(0xFF2196F3) else Color.Gray

    Box(
        modifier = modifier
            .size(width = 45.dp, height = 55.dp)
            .border(
                width = if (isFocused) 2.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = char?.toString() ?: "",
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}
