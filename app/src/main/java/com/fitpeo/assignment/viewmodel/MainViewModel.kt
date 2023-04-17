package com.fitpeo.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitpeo.assignment.model.data.PhotoModelList
import com.fitpeo.assignment.repository.PhotoRepository
import com.fitpeo.assignment.utils.NetworkResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val photoRepository: PhotoRepository): ViewModel() {
    var pages = 1;

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    init {
        fetchPhotos()
    }

    private val _photos = MutableLiveData<NetworkResult<PhotoModelList>>()
    val photos: LiveData<NetworkResult<PhotoModelList>>
        get() = _photos

    fun fetchPhotos(){
        viewModelScope.launch{
            //Log.d("MainViewModel","Pages : $pages")
            _loading.postValue(true)
            val result = photoRepository.fetchPhotos(pages)
            _photos.postValue(appendToLiveData(result))
            _loading.postValue(false)
            pages++
        }
    }

    private fun appendToLiveData(result: NetworkResult<PhotoModelList>):NetworkResult<PhotoModelList>{
        if(result.data != null) {
            val newData = result.data ?: emptyList()
            val currentData = photos.value?.data ?: emptyList()
            val photoModelList = PhotoModelList().apply {
                addAll(currentData)
                addAll(newData)
            }
            result.data.addAll(photoModelList)
            return result
        }

        return result
    }

}