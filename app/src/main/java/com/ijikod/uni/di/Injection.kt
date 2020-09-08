package com.ijikod.uni.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.ijikod.uni.data.DataSource.Api
import com.ijikod.uni.data.Repository
import com.ijikod.uni.data.Room.UniDatabase
import com.ijikod.uni.presentation.factory.ViewModelFactory

object Injection {

    /**
     * Creates an instance of [Repository] based on the [Api] and [UniDatabase]
     */
    fun provideRepository(context: Context): Repository {
        return Repository(Api(context), UniDatabase.getDatabaseInstance(context))
    }

    /**
     * Provides the viewmodel from [ViewModelFactory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModel(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideRepository(context))
    }
}