package com.ijikod.uni.presentation

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ijikod.uni.Utilities.Resource
import com.ijikod.uni.data.Model.UniModel
import com.ijikod.uni.data.Repository
import kotlinx.coroutines.launch

/**
 * [ViewModel] class that serves as a bridge between view and repository
 * **/
class ViewModel (private val repository: Repository): ViewModel(){

    val uniData = repository.uniDataList

    val selectedItem : MutableLiveData<UniModel> = MutableLiveData<UniModel>()

    init {
        fetchData()
    }

     fun fetchData(){
        repository.getData()
    }

    /**
     * Shared [UniModel] used by content fragment
     * **/
    fun setSelectedItem(data: UniModel){
        selectedItem.value = data
    }
}