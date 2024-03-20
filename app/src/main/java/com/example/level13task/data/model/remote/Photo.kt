package com.example.level13task.data.model.remote

import com.google.gson.annotations.SerializedName

data class Photo(
    val author: String,
    @SerializedName("download_url")
    val downloadUrl: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)