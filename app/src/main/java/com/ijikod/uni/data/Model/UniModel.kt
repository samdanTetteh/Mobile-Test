package com.ijikod.uni.data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * [UniModel] class hold app data and pass
 * data through app screens to be displayed.
 * **/
@Entity(tableName = "UniData")
data class UniModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String,
    val description : String,
    val entity : String
)