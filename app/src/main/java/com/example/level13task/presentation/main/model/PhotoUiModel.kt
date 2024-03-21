package com.example.level13task.presentation.main.model

import com.example.level13task.domain.model.PhotoVo

data class PhotoUiModel(
    val id: Int,
    val imageUrl: String,
    val isChecked: Boolean
) {
    companion object {
        fun of(photoVo: PhotoVo) = PhotoUiModel(
            id = photoVo.id.toIntOrNull() ?: 0,
            imageUrl = photoVo.downloadUrl,
            isChecked = false
        )
    }
}
