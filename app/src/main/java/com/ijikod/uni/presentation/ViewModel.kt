package com.ijikod.uni.presentation

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.ijikod.uni.Utilities.Resource
import com.ijikod.uni.data.Model.UniModel
import com.ijikod.uni.data.Repository

/**
 * [ViewModel] class that serves as a bridge between view and repository
 * **/
class ViewModel (private val repository: Repository){

    val uniData = MutableLiveData<Resource<List<UniModel>>>()



     fun fetchData(){
        uniData.value = repository.getData()
    }
}