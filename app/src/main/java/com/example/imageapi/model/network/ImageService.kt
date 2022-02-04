package com.example.imageapi.model.network

import com.example.imageapi.model.network.models.Image
import retrofit2.Response
import retrofit2.http.GET

interface ImageService {

    @GET("photos")
    suspend fun getImages(): Response<List<Image>>
}