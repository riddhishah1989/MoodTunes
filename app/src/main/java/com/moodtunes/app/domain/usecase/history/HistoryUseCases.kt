package com.moodtunes.app.domain.usecase.history

import com.moodtunes.app.domain.model.Session
import com.moodtunes.app.domain.repository.IMoodTunesRepository
import com.moodtunes.app.domain.result.DataResult
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val repository: IMoodTunesRepository,
) {
    suspend operator fun invoke(
        limit: Int  = 20,
        offset: Int = 0,
    ): DataResult<List<Session>> = repository.getHistory(limit, offset)
}

// ─────────────────────────────────────────────────────────────

class GetSessionUseCase @Inject constructor(
    private val repository: IMoodTunesRepository,
) {
    suspend operator fun invoke(
        sessionId: String,
    ): DataResult<Session> = repository.getSession(sessionId)
}

// ─────────────────────────────────────────────────────────────

class DeleteSessionUseCase @Inject constructor(
    private val repository: IMoodTunesRepository,
) {
    suspend operator fun invoke(
        sessionId: String,
    ): DataResult<Unit> = repository.deleteSession(sessionId)
}

// ─────────────────────────────────────────────────────────────

class ClearHistoryUseCase @Inject constructor(
    private val repository: IMoodTunesRepository,
) {
    suspend operator fun invoke(): DataResult<Unit> =
        repository.clearHistory()
}
