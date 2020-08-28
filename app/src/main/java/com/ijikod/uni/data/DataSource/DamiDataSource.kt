package com.ijikod.uni.data.DataSource

import com.ijikod.uni.R
import com.ijikod.uni.Utilities.FileHelper
import com.ijikod.uni.application.UniApplication
import com.ijikod.uni.data.Model.UniModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types


const val dateFileName = "Data.json"

//Retrieve data from local json file in assets folder
private fun readDataFromCache(): List<UniModel> {
    val appContext  = UniApplication.appContext
    val json = FileHelper.getTextFromAssets(appContext, dateFileName)
        ?: return emptyList()
    val moshi = Moshi.Builder().build()
    val listType = Types.newParameterizedType(List::class.java, UniModel::class.java)
    val adapter: JsonAdapter<List<UniModel>> = moshi.adapter(listType)
    return adapter.fromJson(json) ?: emptyList()
}