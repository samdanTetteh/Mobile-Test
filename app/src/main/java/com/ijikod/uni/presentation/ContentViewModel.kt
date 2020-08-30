package com.ijikod.uni.presentation

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ijikod.uni.data.Model.UniModel

class ContentViewModel : ViewModel() {

    val title = MutableLiveData<String>()

    val body = MutableLiveData<String>()

    val formErrors = ObservableArrayList<FormErrors>()


    /**
     * Validate content form before upload
     * **/
    fun isFormValid(): Boolean {
        formErrors.clear()
        if (title.value?.isNullOrEmpty()!! || title.value?.length!! < 5) {
            formErrors.add(FormErrors.INVALID_TITLE)
        }

        if (body.value?.isNullOrEmpty()!!){
            formErrors.add(FormErrors.MISSING_DESC)
        }
        return formErrors.isEmpty()
    }


    /**
     * Set selected data to be displayed on detailed on content page
     * **/
    fun setSelectedData(dataItem: UniModel){
        formErrors.clear()
        title.value = dataItem.entity
        body.value = dataItem.description
    }

}

/**
 * Enum class to help with content validation
 * **/
enum class FormErrors {
    INVALID_TITLE,
    MISSING_DESC,
}