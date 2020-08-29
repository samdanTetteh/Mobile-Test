package com.ijikod.uni.data.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ijikod.uni.data.Model.UniModel

/**
 * Room implementation of [UniDatabase] to contain entities
 * **/
@Database(entities = arrayOf(UniModel::class), version = 1, exportSchema = false)
abstract class UniDatabase : RoomDatabase(){

    /**
     * [UniDao] instance to access from repository
     * **/
    abstract fun dao() : UniDao

    companion object{
        @Volatile
        private var INSTANCE: UniDatabase? = null

        /**
         * Singleton implementation of room database to avoid creation of several instances
         * **/
        fun getDatabaseInstance(context: Context): UniDatabase {
            if (INSTANCE == null) {
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UniDatabase::class.java,
                        "Unidays.db"
                    ).build()
                }
            }
            return  INSTANCE!!
        }
    }

}