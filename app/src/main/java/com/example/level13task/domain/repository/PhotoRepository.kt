package com.example.level13task.domain.repository

import com.example.level13task.domain.model.PhotoVo

interface PhotoRepository {
    suspend fun getPhotos(): Result<List<PhotoVo>>
}