package com.moodtunes.app.domain.usecase.auth

import com.moodtunes.app.domain.model.Auth
import com.moodtunes.app.domain.repository.IMoodTunesRepository
import com.moodtunes.app.domain.result.DataResult
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: IMoodTunesRepository) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        preferredGenres: List<String>
    ): DataResult<Auth> = repository.signUp(name, email, password, preferredGenres)
}

class SignInUseCase @Inject constructor(private val repository: IMoodTunesRepository) {
    suspend operator fun invoke(
        email: String,
        password: String,
    ): DataResult<Auth> = repository.signIn(email, password)
}

class GetProfileUseCase @Inject constructor(private val repository: IMoodTunesRepository) {
    suspend operator fun invoke(): DataResult<com.moodtunes.app.domain.model.User> =
        repository.getMe()
}

class UpdateProfileUseCase @Inject constructor(private val repository: IMoodTunesRepository) {
    suspend operator fun invoke(
        request: com.moodtunes.app.data.remote.request.UpdateProfileRequest,
    ): DataResult<com.moodtunes.app.domain.model.User> =
        repository.updateProfile(request)
}

class ChangePasswordUseCase @Inject constructor(private val repository: IMoodTunesRepository) {
    suspend operator fun invoke(
        currentPassword: String,
        newPassword: String,
    ): DataResult<Unit> {
        // Business rule — new password must be different
        if (currentPassword == newPassword) {
            return DataResult.Error("New password must be different from current password.")
        }
        return repository.changePassword(currentPassword, newPassword)
    }
}


class DeleteAccountUseCase @Inject constructor(private val repository: IMoodTunesRepository) {
    suspend operator fun invoke(
        password: String,
    ): DataResult<Unit> = repository.deleteAccount(password)
}
