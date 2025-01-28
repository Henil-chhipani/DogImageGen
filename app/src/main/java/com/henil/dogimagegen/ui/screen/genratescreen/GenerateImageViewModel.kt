package com.henil.dogimagegen.ui.screen.genratescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henil.dogimagegen.data.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateImageViewModel @Inject constructor(
    private val repository: DogRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(GenerateImageUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: GenerateImageUiEvent) {
        when (event) {
            is GenerateImageUiEvent.GenerateImage -> generateImage()
        }
    }

    private fun generateImage() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val response = repository.fetchAndStoreDogImage()
            _uiState.update {
                it.copy(
                    isLoading = false,
                    imageUrl = response.message,
                    status = response.status
                )
            }
        }
    }
}

data class GenerateImageUiState(
    val isLoading: Boolean = false,
    val imageUrl: String = "",
    val error: String = "",
    val status: String = ""
)

sealed class GenerateImageUiEvent {
    data object GenerateImage : GenerateImageUiEvent()
}