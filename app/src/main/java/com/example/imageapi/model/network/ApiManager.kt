package com.example.imageapi.model.network

class ApiManager {

    private var imageService: ImageService =
        RetrofitInstance.providesRetrofit().create(ImageService::class.java)

    suspend fun getImages() = imageService.getImages()
}