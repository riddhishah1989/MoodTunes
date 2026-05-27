package com.moodtunes.app.domain.usecase.journal

import com.moodtunes.app.domain.model.JournalEntry
import com.moodtunes.app.domain.repository.IMoodTunesRepository
import com.moodtunes.app.domain.result.DataResult
import javax.inject.Inject

class GetJournalUseCase @Inject constructor(
    private val repository: IMoodTunesRepository,
) {
    suspend operator fun invoke(
        limit: Int  = 20,
        offset: Int = 0,
    ): DataResult<List<JournalEntry>> = repository.getJournal(limit, offset)
}

// ─────────────────────────────────────────────────────────────

class AddJournalEntryUseCase @Inject constructor(
    private val repository: IMoodTunesRepository,
) {
    suspend operator fun invoke(
        mood: String,
        rating: Int,
        note: String?       = null,
        tags: List<String>  = emptyList(),
    ): DataResult<JournalEntry> {
        // Business rule — mood must not be blank
        if (mood.isBlank()) {
            return DataResult.Error("Please select a mood.")
        }

        // Business rule — rating must be 1–5
        if (rating !in 1..5) {
            return DataResult.Error("Rating must be between 1 and 5.")
        }

        // Business rule — note max 500 chars
        if ((note?.length ?: 0) > 500) {
            return DataResult.Error("Note must not exceed 500 characters.")
        }

        return repository.addJournalEntry(mood, rating, note, tags)
    }
}

// ─────────────────────────────────────────────────────────────

class DeleteJournalEntryUseCase @Inject constructor(
    private val repository: IMoodTunesRepository,
) {
    suspend operator fun invoke(
        entryId: String,
    ): DataResult<Unit> = repository.deleteJournalEntry(entryId)
}
