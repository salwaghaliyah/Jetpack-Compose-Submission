package com.dicoding.composesubmission.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.composesubmission.data.Repository
import com.dicoding.composesubmission.data.response.ResultsItem
import com.dicoding.composesubmission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<ResultsItem>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<ResultsItem>>> get() = _uiState

    suspend fun getAllCharacter() {
        viewModelScope.launch {
            repository.getAllCharacter()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{ character ->
                    _uiState.value = UiState.Success(character)
                }
        }
    }
}