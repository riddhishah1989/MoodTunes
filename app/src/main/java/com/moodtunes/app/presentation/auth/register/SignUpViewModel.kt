package com.moodtunes.app.presentation.auth.register

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.moodtunes.app.domain.model.Genre
import com.moodtunes.app.domain.usecase.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(signUpUseCase: SignUpUseCase) : ViewModel() {

    private val _selectedGenreIds = MutableStateFlow<List<String>>(emptyList())
    val selectedGenreIds = _selectedGenreIds


    fun toggleGenre(genre: Genre) {
        val current = _selectedGenreIds.value.toMutableList()
        if (current.contains(genre.id)) {
            current.remove(genre.id) //deselect
        } else if (current.size < 5) {
            current.add(genre.id)
        }
        _selectedGenreIds.value = current
    }
}