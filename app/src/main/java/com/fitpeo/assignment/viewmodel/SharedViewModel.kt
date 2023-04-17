package com.fitpeo.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitpeo.assignment.model.data.PhotoModelListItem

class SharedViewModel: ViewModel() {

    private val _selectedItem = MutableLiveData<PhotoModelListItem>()

    val selectedItem: LiveData<PhotoModelListItem>
    get() = _selectedItem

    fun selectedItem(photoModelListItem: PhotoModelListItem){
        _selectedItem.postValue(photoModelListItem)
    }
}