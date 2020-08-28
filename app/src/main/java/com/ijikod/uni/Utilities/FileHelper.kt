package com.ijikod.uni.Utilities

import android.content.Context

/**
 * Helper class to with local file access utility methods
 * **/
class FileHelper {


    companion object{
        fun getTextFromAssets(context: Context, fileName: String): String? {
            return context.assets.open(fileName).use {
                it.bufferedReader().use {
                    it.readText()
                }
            }
        }
    }

}