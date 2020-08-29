package com.ijikod.uni.data.DataSource

import android.content.Context
import com.ijikod.uni.R
import com.ijikod.uni.Utilities.FileHelper
import com.ijikod.uni.application.UniApplication
import com.ijikod.uni.data.Model.UniModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types


const val dateFileName = "Data.json"

/**
 * Mock Api class to retrieve data from file
 * **/
class Api(context: Context) {

    var remoteData = emptyList<UniModel>()

    init {
        remoteData = readData(context)
    }

    /**
     * Retrieve data from local json file in assets folder
     * **/
    private fun readData(context: Context): List<UniModel> {
        val json = FileHelper.getTextFromAssets(context, dateFileName)
            ?: return emptyList()
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, UniModel::class.java)
        val adapter: JsonAdapter<List<UniModel>> = moshi.adapter(listType)
        return adapter.fromJson(json) ?: emptyList()
    }
}

