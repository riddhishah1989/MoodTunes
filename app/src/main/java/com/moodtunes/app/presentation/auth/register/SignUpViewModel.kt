package com.moodtunes.app.presentation.auth.register

import android.util.Patterns
import com.moodtunes.app.MoodTunesApp
import com.moodtunes.app.R
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
import utils.CommonUtilities.isValidEmail
import utils.CommonUtilities.isValidPassword
import utils.NetworkUtils
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val genresUseCase: GetGenresUseCase,
    private val userPreferences: UserPreferences,
    private val networkUtils: NetworkUtils,
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
        if (!networkUtils.isInternetAvailable()) {
            _uiStateSignUp.value = UiState.Error(MoodTunesApp.context.getString(R.string.error_no_internet))
            return@launch
        }

        //Validate signup data
        if (validateSignUpData(name, email, password)) return@launch

        _uiStateSignUp.value = UiState.Loading
        val result = signUpUseCase.invoke(name, email, password, selectedGenre)

        _uiStateSignUp.value = when (result) {
            is DataResult.Success -> {
                userPreferences.saveToken(result.data.token)
                userPreferences.saveUser(result.data.user)
                UiState.Success(result.data)
            }

            is DataResult.Error -> {
                UiState.Error(result.message)
            }
        }

    }

    private fun validateSignUpData(name: String, email: String, password: String): Boolean {
        val errorMsg = when {
            name.isEmpty() -> R.string.error_please_enter_your_full_name
            email.isEmpty() -> R.string.error_please_enter_your_email
            password.isEmpty() -> R.string.error_please_enter_your_password
            !email.isValidEmail() -> R.string.error_please_enter_a_valid_email
            !password.isValidPassword() -> R.string.error_please_enter_valid_password
            else -> null
        }
        return if (errorMsg != null) {
            _uiStateSignUp.value = UiState.Error(MoodTunesApp.context.getString(errorMsg))
            true
        } else {
            false
        }

    }

    fun resetState() {
        _uiStateSignUp.value = UiState.Idle
    }
}