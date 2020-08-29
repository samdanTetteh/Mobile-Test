package com.ijikod.uni.data.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ijikod.uni.data.Model.UniModel

/**
 * Data access object for querying local database data
 * **/
@Dao
interface UniDao {

    @Query("Select * from unidata")
    fun getAllData() :List<UniModel>

    /**
     * save single [UniModel] to database
     * **/
    @Insert
    fun insertData(data : UniModel)

    /**
     * save a [List] of [UniModel] to database
     * **/
    @Insert
    fun insertAllData(data: List<UniModel>)

    /**
     * Delete all [UniModel] data from database
     * **/
    @Query("Delete from UniData")
    fun deleteAll()

}