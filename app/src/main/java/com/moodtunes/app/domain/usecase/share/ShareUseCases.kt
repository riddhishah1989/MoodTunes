package com.moodtunes.app.domain.usecase.share

import com.moodtunes.app.domain.model.ShareResult
import com.moodtunes.app.domain.repository.IMoodTunesRepository
import com.moodtunes.app.domain.result.DataResult
import javax.inject.Inject

class GenerateShareLinkUseCase @Inject constructor(
    private val repository: IMoodTunesRepository,
) {
    suspend operator fun invoke(
        sessionId: String,
    ): DataResult<ShareResult> {
        // Business rule — sessionId must not be blank
        if (sessionId.isBlank()) {
            return DataResult.Error("Session ID is required to generate a share link.")
        }
        return repository.generateShareLink(sessionId)
    }
}
