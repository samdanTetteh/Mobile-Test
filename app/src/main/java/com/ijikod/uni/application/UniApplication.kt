package com.ijikod.uni.application

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.ijikod.uni.data.DataSource.Api
import com.ijikod.uni.data.Repository
import com.ijikod.uni.data.Room.UniDatabase
import com.ijikod.uni.presentation.factory.ViewModelFactory

/**
 * [Application] sub class
 * **/
class UniApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
    }

    companion object {
         // singleton access to application context
         lateinit var appContext : Context
    }
}