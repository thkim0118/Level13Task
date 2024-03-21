package com.example.level13task.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.level13task.domain.usecase.GetPhotosUseCase
import com.example.level13task.presentation.main.model.MainUiState
import com.example.level13task.presentation.main.model.PhotoUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState(emptyList()))
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getPhotosUseCase.invoke().onSuccess { photoVos ->
                _uiState.update {
                    MainUiState(photoVos.map { photoVo -> PhotoUiModel.of(photoVo) })
                }
            }.onFailure {
                Log.e("MainViewModel", "Failed to get photos", it)
            }
        }
    }

    fun updateCheckState(isCheck: Boolean, index: Int) {
        _uiState.update { currentState ->
            val updatedPhotos = currentState.photos.toMutableList().apply {
                this.getOrNull(index)?.let { photoUiModel ->
                    this[index] = photoUiModel.copy(isChecked = isCheck)
                }
            }

            currentState.copy(photos = updatedPhotos)
        }
    }

    fun getPhoto(index: Int): PhotoUiModel? {
        return _uiState.value.photos.getOrNull(index)?.let { photo ->
            return photo
        }
    }
}