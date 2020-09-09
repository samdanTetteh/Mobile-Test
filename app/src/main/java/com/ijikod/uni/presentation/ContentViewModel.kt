package com.ijikod.uni.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ijikod.uni.businessLogic.Interactor
import com.ijikod.uni.data.Model.UniModel
import com.ijikod.uni.data.Repository

class ContentViewModel(private val repository: Repository, private val userCase: Interactor) : ViewModel() {

    val selectedDataItem = MutableLiveData<UniModel>()

    val title = MutableLiveData<String>()

    val body = MutableLiveData<String>()

    val isAddData = MutableLiveData<Boolean>()

    val formErrors = userCase.formErrors


    fun isTitleValid(): Boolean = userCase.titleTextValidation(title.value!!)

    fun isBodyValid(): Boolean = userCase.bobyTextValidation(body.value!!)


    /**
     * Set selected data to be displayed on detailed on content page
     * **/
    fun setSelectedData(dataItem: UniModel){
        formErrors.clear()
        selectedDataItem.value = dataItem
        title.value = dataItem.entity
        body.value = dataItem.description
    }


    /**
     * Saving Data
     * **/
    fun saveData(){
        val data = selectedDataItem.value

        // Check if this is new data to be added
        if (data?.entity.isNullOrEmpty()) isAddData.value = true

        data?.let {
            it.entity = title.value.toString()
            it.description = body.value.toString()
        }
        if (data != null) {
            repository.insertDataItem(data)
        }
    }

}