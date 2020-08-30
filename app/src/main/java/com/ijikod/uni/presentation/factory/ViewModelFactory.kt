package com.ijikod.uni.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ijikod.uni.data.Repository

/**
 * [ViewModelFactory] class to aid dependency injection and also helps with testing
 * **/
class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(com.ijikod.uni.presentation.FeedViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return com.ijikod.uni.presentation.FeedViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(com.ijikod.uni.presentation.ContentViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return com.ijikod.uni.presentation.ContentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}