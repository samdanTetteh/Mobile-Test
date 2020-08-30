package com.ijikod.uni.presentation

import androidx.lifecycle.*
import com.ijikod.uni.Utilities.Resource
import com.ijikod.uni.data.Model.UniModel
import com.ijikod.uni.data.Repository
import kotlinx.coroutines.launch

/**
 * [ViewModel] class that serves as a bridge between view and repository
 * **/
class FeedViewModel (val repository: Repository): ViewModel(){

    private val _uniData = MutableLiveData<Resource<List<UniModel>>>()

    /**
     * [LiveData] to be observed by UI for data changes
     * **/
    val data: LiveData<Resource<List<UniModel>>> = _uniData


    private fun fetchData(){
        viewModelScope.launch {
            // Making a suspend and resume database request which will main-safe
            _uniData.value = repository.getData()
        }
    }

    /**
     *  Called by the UI to load data from repository
     * **/
    fun data() = fetchData()

}