package com.example.level13task.presentation.main.model

import com.example.level13task.domain.model.PhotoVo

data class PhotoUiModel(
    val imageUrl: String,
    val isCheck: Boolean
) {
    companion object {
        fun of(photoVo: PhotoVo) = PhotoUiModel(
            imageUrl = photoVo.downloadUrl,
            isCheck = false
        )
    }
}
