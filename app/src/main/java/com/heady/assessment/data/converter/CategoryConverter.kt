package com.heady.assessment.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.heady.assessment.network.response.Category

class CategoryConverter {
    private val gson = Gson()
    private val type = object : TypeToken<List<Category>>() {

    }.type

    @TypeConverter
    fun stringToNestedData(json: String): List<Category> {
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun nestedDataToString(nestedData: List<Category>): String {
        return gson.toJson(nestedData, type)
    }
}