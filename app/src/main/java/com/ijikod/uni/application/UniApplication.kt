package com.ijikod.uni.application

import android.app.Application
import android.content.Context

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