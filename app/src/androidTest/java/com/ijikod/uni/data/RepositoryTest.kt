package com.ijikod.uni.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.ijikod.uni.data.DataSource.Api
import com.ijikod.uni.data.Model.UniModel
import com.ijikod.uni.data.Room.UniDatabase
import org.hamcrest.CoreMatchers
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
        id = 8,
        description = "Make your mark.",
        entity = "Dr Martons"
    )

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        api = Api(context)
        database = UniDatabase.getDatabaseInstance(context)
        appRepository = Repository(api, database)
    }


    @Test
    suspend fun `check_read_data_from_api`(){
        val uniData  = appRepository.getDataFromServer()
        assertThat(uniData[1] , CoreMatchers.equalTo(fakeUniModel))
    }
}