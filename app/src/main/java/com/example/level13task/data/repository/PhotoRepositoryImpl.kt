package com.example.level13task.data.repository

import com.example.level13task.core.exception.ResponseFailException
import com.example.level13task.data.source.PhotoApi
import com.example.level13task.domain.model.PhotoVo
import com.example.level13task.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoApi: PhotoApi
) : PhotoRepository {
    override suspend fun getPhotos(): Result<List<PhotoVo>> {
        try {
            val response = photoApi.getPhotos()

            return if (response.isSuccessful) {
                Result.success(
                    response.body()?.photos?.map {
                        PhotoVo(
                            it.author,
                            it.downloadUrl,
                            it.height,
                            it.id,
                            it.url,
                            it.width
                        )
                    } ?: emptyList()
                )
            } else {
                Result.failure(ResponseFailException(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}