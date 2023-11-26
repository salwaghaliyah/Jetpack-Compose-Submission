package com.dicoding.composesubmission.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.composesubmission.data.Repository
import com.dicoding.composesubmission.data.response.ResultsItem
import com.dicoding.composesubmission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel (private val repository: Repository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<ResultsItem>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<ResultsItem>> get() = _uiState

    fun getCharacterById(charId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getCharacterById(charId))
        }
    }
}