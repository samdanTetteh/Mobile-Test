package com.ijikod.uni.data.Room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DaoTest {

    private lateinit var appDatabase: UniDatabase

    /**
     * In memory implementation of database for tests
     * **/
    @Before
    fun initDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, UniDatabase::class.java).build()
    }




}