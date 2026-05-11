package com.moodtunes.app.domain.model

data class Mood(
    val id: String,
    val label: String,
    val emoji: String,
    val description: String,
    val color: Long,
    val bgColor: Long,
)

object PresetMoods {
    val all = listOf(
        Mood("happy", "Happy", "😊", "Upbeat & joyful", 0xFFFFD93D, 0xFF1A1500),
        Mood("sad", "Sad", "😢", "Reflective", 0xFF5B8FD4, 0xFF040810),
        Mood("energetic", "Energetic", "⚡", "Pumped up", 0xFFFF6B6B, 0xFF0F0404),
        Mood("calm", "Calm", "🌿", "Peaceful & serene", 0xFF5DD68A, 0xFF04100A),
        Mood("romantic", "Romantic", "💕", "Tender & warm", 0xFFFF63A5, 0xFF0F0408),
        Mood("focused", "Focused", "🎯", "In the zone", 0xFF4D96FF, 0xFF04060F),
        Mood("angry", "Angry", "😤", "Intense energy", 0xFFFF4444, 0xFF0F0404),
        Mood("anxious", "Anxious", "😰", "Overwhelmed", 0xFFC77DFF, 0xFF0A040F),
    )
}
