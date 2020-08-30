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
class FeedViewModel (private val repository: Repository): ViewModel(){

    val uniData = repository.uniDataList


    init {
        fetchData()
    }

    /**
     * Loa data from local database
     * **/
     fun fetchData(){
        repository.getData()
    }
}