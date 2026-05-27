package com.moodtunes.app.data.remote.request

data class AddJournalRequest(
    val mood: String,
    val rating: Int,
    val note: String?       = null,
    val tags: List<String>  = emptyList(),
)
