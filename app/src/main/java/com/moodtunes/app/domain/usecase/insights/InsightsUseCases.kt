package com.moodtunes.app.domain.usecase.insights

import com.moodtunes.app.domain.model.Insights
import com.moodtunes.app.domain.repository.IMoodTunesRepository
import com.moodtunes.app.domain.result.DataResult
import javax.inject.Inject

class GetInsightsUseCase @Inject constructor(
    private val repository: IMoodTunesRepository,
) {
    suspend operator fun invoke(
        days: Int = 30,
    ): DataResult<Insights> {
        // Business rule — days must be between 7 and 365
        val validDays = days.coerceIn(7, 365)
        return repository.getInsights(validDays)
    }
}
