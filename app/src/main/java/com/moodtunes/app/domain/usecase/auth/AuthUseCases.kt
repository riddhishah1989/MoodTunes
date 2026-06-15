package com.moodtunes.app.domain.usecase.auth

import com.moodtunes.app.domain.model.Auth
import com.moodtunes.app.domain.model.User
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
    suspend operator fun invoke(): DataResult<User> =
        repository.getMe()
}

class UpdateProfileUseCase @Inject constructor(private val repository: IMoodTunesRepository) {
    suspend operator fun invoke(
        name: String,
        preferredGenres: List<String>
    ): DataResult<User> =
        repository.updateProfile(name, preferredGenres)
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

class ForgotPasswordUseCase @Inject constructor(private val repository: IMoodTunesRepository) {
    suspend operator fun invoke(email: String): DataResult<String> =
        repository.forgotPassword(email)
}

class VerifyOTPUseCase @Inject constructor(private val repository: IMoodTunesRepository) {
    suspend operator fun invoke(
        email: String,
        otp: String,
    ): DataResult<String> = repository.verifyOTP(email, otp)
}

class ResendOTPUseCase @Inject constructor(private val repository: IMoodTunesRepository) {
    suspend operator fun invoke(email: String): DataResult<String> =
        repository.resendOTP(email)
}

class ResetPasswordUseCase @Inject constructor(private val repository: IMoodTunesRepository) {
    suspend operator fun invoke(
        email: String,
        newPassword: String,
        confirmPassword: String,
    ): DataResult<String> =
        repository.resetPassword(email, newPassword, confirmPassword)
}

class RefreshTokenUseCase @Inject constructor(private val repository: IMoodTunesRepository) {
    suspend operator fun invoke(): DataResult<String> =
        repository.refreshToken()
}
