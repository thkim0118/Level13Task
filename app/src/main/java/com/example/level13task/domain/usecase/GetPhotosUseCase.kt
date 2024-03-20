package com.example.level13task.domain.usecase

import com.example.level13task.domain.model.PhotoVo
import com.example.level13task.domain.repository.PhotoRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {
    suspend operator fun invoke(): Result<List<PhotoVo>> = photoRepository.getPhotos()
}