package com.ijikod.uni.businessLogic

import androidx.databinding.ObservableArrayList
import java.util.*

/**
 * [Interactor] class to hold pure app business logic
 * **/
class Interactor {


    val formErrors = ObservableArrayList<FormErrors>()


    /**
     * Validate content form before upload
     * **/
    fun titleTextValidation(title : String) {
        when (title.isNullOrEmpty() || title.length < 3) {
            true -> {
                formErrors.add(FormErrors.INVALID_TITLE)
            }

           else -> {
               formErrors.remove(FormErrors.INVALID_TITLE)
           }
        }
    }

    fun isBodyTextValid(body: String){
         when (body.isNotEmpty()){
            true -> {
                formErrors.remove(FormErrors.MISSING_DESC)
            }

            else ->  {
                formErrors.add(FormErrors.MISSING_DESC)
            }
        }
    }
}




/**
 * Enum class to help with content validation
 * **/
enum class FormErrors {
    INVALID_TITLE,
    MISSING_DESC,
}