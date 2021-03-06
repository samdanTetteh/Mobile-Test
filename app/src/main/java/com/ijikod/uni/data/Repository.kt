package com.ijikod.uni.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.ijikod.uni.Utilities.Resource
import com.ijikod.uni.data.DataSource.Api
import com.ijikod.uni.data.Model.UniModel
import com.ijikod.uni.data.Room.UniDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * [Repository] class to serve data to viewModel from either server or local database
 * **/
class Repository(private val api: Api, database: UniDatabase) {

    // Handle to database
    private val dao = database.dao()

    /**
     * Ensure single source of truth data
     * **/
     fun getData(emit : (value: Resource<List<UniModel>>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val uniDataList = MutableLiveData<Resource<List<UniModel>>>()
            uniDataList.postValue(Resource.Loading())
            val data = dao.getAllData()
            if (data.isEmpty()){
                try {
                    uniDataList.postValue(Resource.Success(getDataFromServer()))
                }catch (exception : Exception){
                    uniDataList.postValue(Resource.Error("Error: ${exception.message}"))
                }
            }else{
                uniDataList.postValue(Resource.Success(data))
            }

            // Emit data back to function to be used.
            CoroutineScope(Dispatchers.Main).launch {
                emit(uniDataList.value!!)
            }

        }

    }

    /**
     * Insert Data into local database
     * **/
    fun insertDataItem(dataItem : UniModel){
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertData(dataItem)
        }
    }

    /**
     * Load data from json file and save data to sql database
     * **/
    @WorkerThread
     fun getDataFromServer() : List<UniModel>{
        val data = api.remoteData
        dao.deleteAll()
        dao.insertAllData(data)
        return dao.getAllData()
    }

}