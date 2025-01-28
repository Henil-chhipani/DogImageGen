package com.henil.dogimagegen.ui.screen.imagescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henil.dogimagegen.data.repository.DogRepository
import com.henil.dogimagegen.ui.screen.genratescreen.GenerateImageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: DogRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ImageUiState())
    val uiState = _uiState.asStateFlow()

    init {
        // Load all images when the ViewModel is initialized
        getAllImages()
    }

    fun onEvent(event: ImageUiEvent) {
        when (event) {
            is ImageUiEvent.OnImageClicked -> {
                // Handle image click event (e.g., navigation, sharing, etc.)
                Log.d("ImageViewModel", "Image clicked: ${event.url}")
            }

            ImageUiEvent.GetAllImages -> {
                getAllImages()
            }

            ImageUiEvent.ClearAllImage -> {
                clearAllImages()
            }
        }
    }

    private fun getAllImages() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                repository.getAllImagesFlow()
                    .collect { imageUrls ->
                        _uiState.update {
                            it.copy(
                                listOfImageUrls = imageUrls,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.localizedMessage ?: "Unknown error"
                    )
                }
            }
        }
    }

    private fun clearAllImages() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                repository.clearAllImages()
                _uiState.update {
                    it.copy(
                        listOfImageUrls = emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.localizedMessage ?: "Unknown error"
                    )
                }
            }
        }
    }
}

data class ImageUiState(
    val listOfImageUrls: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class ImageUiEvent {
    data class OnImageClicked(val url: String) : ImageUiEvent()
    data object GetAllImages : ImageUiEvent()
    data object ClearAllImage : ImageUiEvent()
}