package com.ijikod.uni.data.Room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ijikod.uni.data.Model.UniModel
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Class to text [Room] database queries
 * **/
@RunWith(AndroidJUnit4::class)
class UniDatabaseTest{

    private lateinit var dao: UniDao
    private lateinit var db: UniDatabase

    private val fakeUniModel  = UniModel(
        id = 2,
        title = "Get 35% Student Discount",
        description = "Plus save an extra 30% off on Outlet items. Hurry Limited Time Only!",
        entity = "Reebok"
    )

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, UniDatabase::class.java).build()
        dao = db.dao()
    }

    @Test
    @Throws(Exception::class)
    fun `check_saved_data`() {
        val uniData: UniModel = fakeUniModel
        val dataList = mutableListOf<UniModel>().apply {
            add(uniData)
        }
        dao.insertAllData(dataList)
        val savedData = dao.getAllData()[0].apply {
            assertThat(title, equalTo("Get 35% Student Discount"))
            assertThat(description, equalTo("Plus save an extra 30% off on Outlet items. Hurry Limited Time Only!"))
            assertThat(entity, equalTo("Reebok"))
        }

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}