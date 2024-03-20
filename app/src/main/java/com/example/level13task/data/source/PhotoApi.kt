package com.example.level13task.data.source

import com.example.level13task.data.model.remote.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET

interface PhotoApi {
    @GET("list")
    suspend fun getPhotos(): Response<List<PhotoResponse>>
}