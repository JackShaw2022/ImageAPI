package com.example.imageapi.model.repository

import com.example.imageapi.model.network.ApiManager
import com.example.imageapi.model.network.models.Image
import java.lang.Exception

class ImageRepository(
    private val apiManager: ApiManager
) {
    suspend fun getImages(): List<Image>? {
        return try {
            val response = apiManager.getImages()
            if (response.isSuccessful) {
                response.body()
            } else {
                emptyList()
            }
        } catch(ex: Exception) {
            emptyList()
        }
    }
}