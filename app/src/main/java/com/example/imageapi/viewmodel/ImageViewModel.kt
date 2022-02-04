package com.example.imageapi.viewmodel

import androidx.lifecycle.*
import com.example.imageapi.model.network.models.Image
import com.example.imageapi.model.repository.ImageRepository
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class ImageViewModel(
    private val imageRepository: ImageRepository
) : ViewModel() {

    private var _images: MutableLiveData<List<Image>?> = MutableLiveData()
    val images: LiveData<List<Image>?> = _images

    init {
        getImages()
    }
    private fun getImages() {
        viewModelScope.launch {
            val response = imageRepository.getImages()
            _images.postValue(response)
        }
    }
    class Factory(
        private val imageRepository: ImageRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(ImageViewModel::class.java)) {
                return ImageViewModel(imageRepository) as T
            } else {
                throw RuntimeException("could not create instance of ImageViewModel")
            }
        }
    }
}