package com.ijikod.uni.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.ijikod.uni.Utilities.Resource
import com.ijikod.uni.data.DataSource.Api
import com.ijikod.uni.data.Model.UniModel
import com.ijikod.uni.data.Room.UniDatabase
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Test [Repository] functions
 * **/
class RepositoryTest {
    private lateinit var appRepository : Repository

    private lateinit var database : UniDatabase

    private lateinit var api : Api

    private val fakeUniModel  = UniModel(
        id = 2,
        title = "Get 35% Student Discount",
        description = "Plus save an extra 30% off on Outlet items. Hurry Limited Time Only!",
        entity = "Reebok"
    )

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        api = Api(context)
        database = UniDatabase.getDatabase(context)
        appRepository = Repository(api, database)
    }


    @Test
    fun `check_read_data_from_api`(){
        val uniData  = appRepository.getDataFromServer()
        assertThat(uniData[1] , CoreMatchers.equalTo(fakeUniModel))
    }
}