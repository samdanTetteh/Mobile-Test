package com.ijikod.uni.data.Room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ijikod.uni.data.Model.UniModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DaoTest {

    private lateinit var appDatabase: UniDatabase

    private val fakeUniModel_1  = UniModel(
        title = "Get 35% Student Discount",
        description = "Plus save an extra 30% off on Outlet items. Hurry Limited Time Only!",
        entity = "Reebok"
    )

    private val fakeUniModel_2  = UniModel(
        title = "22% Student Discount",
        description = "Head to LOOKFANTASTIC - the ultimate destination for your beauty needs!",
        entity = "LOOKFANTASTIC"
    )

    /**
     * In memory implementation of database for tests
     * **/
    @Before
    fun initDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, UniDatabase::class.java).build()
    }


    /**
     * Check local saving of data
     * **/
    @Test
    fun `check_insert_localData`(){
        val dataItem = fakeUniModel_1
        appDatabase.dao().insertData(dataItem)
        val cachedData = appDatabase.dao().getAllData()
        assert(cachedData.isNotEmpty())
    }

    /**
     * Check retrieval of locally saved data
     * **/
    @Test
    fun `check_retrieval_of_data`(){
        val dataList  = listOf(fakeUniModel_1, fakeUniModel_2)
        appDatabase.dao().insertAllData(dataList)

        val retrievedData = appDatabase.dao().getAllData()
        // Check if data retrieved is the same inserted.
        assert(retrievedData == dataList.sortedWith(compareBy({ it.entity }, { it.entity })))
    }


    /**
     * Check deleting data from local database
     * **/
    @Test
    fun `check_deletion_of_data`(){
        val dataList  = listOf(fakeUniModel_1, fakeUniModel_2)
        dataList.forEach {
            appDatabase.dao().insertData(it)
        }

        appDatabase.dao().deleteAll()
        val dataRetrieved = appDatabase.dao().getAllData()
        assert(dataRetrieved.isEmpty())
    }

}