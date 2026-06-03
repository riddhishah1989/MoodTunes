package com.moodtunes.app.presentation.auth.register

import android.util.Patterns
import com.moodtunes.app.data.local.UserPreferences
import com.moodtunes.app.domain.model.Auth
import com.moodtunes.app.domain.model.Genre
import com.moodtunes.app.domain.result.DataResult
import com.moodtunes.app.domain.usecase.auth.SignUpUseCase
import com.moodtunes.app.domain.usecase.recommendation.GetGenresUseCase
import com.moodtunes.app.presentation.state.BaseViewModel
import com.moodtunes.app.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val genresUseCase: GetGenresUseCase,
    private val userPreferences: UserPreferences,
) :
    BaseViewModel() {

    private val _selectedGenreIds = MutableStateFlow<List<String>>(emptyList())
    val selectedGenreIds: StateFlow<List<String>> = _selectedGenreIds.asStateFlow()
    private val _uiStateSignUp = MutableStateFlow<UiState<Auth>>(UiState.Idle)
    val uiStateSignUp: StateFlow<UiState<Auth>> = _uiStateSignUp.asStateFlow()

    private val _genres = MutableStateFlow<List<Genre>>(emptyList())
    val genres: StateFlow<List<Genre>> = _genres.asStateFlow()

    init {
        getGenres()
    }

    fun toggleGenre(genre: Genre) {
        val current = _selectedGenreIds.value.toMutableList()
        if (current.contains(genre.id)) {
            current.remove(genre.id) //deselect
        } else if (current.size < 5) {
            current.add(genre.id)
        }
        _selectedGenreIds.value = current
    }

    fun getGenres() = launch {
        val result = genresUseCase.invoke()
        if (result is DataResult.Success) {
            _genres.value = result.data
        }
    }

    fun registerUser(
        name: String,
        email: String,
        password: String,
        selectedGenre: List<String> = emptyList()
    ) = launch {
        if (name.isEmpty()) {
            _uiStateSignUp.value = UiState.Error("Please enter your full name")
            return@launch
        } else if (email.isEmpty()) {
            _uiStateSignUp.value = UiState.Error("Please enter your email")
            return@launch
        } else if (password.isEmpty()) {
            _uiStateSignUp.value = UiState.Error("Please enter your password")
            return@launch
        } else if (!email.isValidEmail()) {
            _uiStateSignUp.value = UiState.Error("Please enter a valid email")
            return@launch
        } else if (!password.isValidPassword()) {
            _uiStateSignUp.value =
                UiState.Error("Please enter a valid password. It should contains 1 Uppercase, 1 Lowercase, 1 Digit and 1 Special character")
            return@launch
        }
        _uiStateSignUp.value = UiState.Loading
        val result = signUpUseCase.invoke(name, email, password, selectedGenre)

        if (result is DataResult.Success) {
            userPreferences.saveToken(result.data.token)
            userPreferences.saveUser(result.data.user)
        }

        _uiStateSignUp.value = when (result) {
            is DataResult.Success -> UiState.Success(result.data)
            is DataResult.Error -> UiState.Error(result.message)
        }

    }

    fun CharSequence?.isValidEmail(): Boolean {
        return !this.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    fun String.isValidPassword(): Boolean {
        val passwordRegex =
            """^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$""".toRegex()
        return passwordRegex.matches(this)
    }

    fun resetState() {
        _uiStateSignUp.value = UiState.Idle
    }
}