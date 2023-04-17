package com.fitpeo.assignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fitpeo.assignment.api.PhotoService
import com.fitpeo.assignment.model.data.PhotoModelList
import com.fitpeo.assignment.utils.NetworkResult
import javax.inject.Inject

class PhotoRepository @Inject constructor(private val photoService: PhotoService) {

    suspend fun fetchPhotos(page:Int): NetworkResult<PhotoModelList>{
        val result = photoService.fetchPhotos(page)
        return if (result.isSuccessful){
            val responseBody = result.body()
            if(responseBody != null) {
                NetworkResult.Success(responseBody)
            }else{
                NetworkResult.Error("Something went wrong")
            }
        }else{
            NetworkResult.Error("Something went wrong")
        }
    }
}