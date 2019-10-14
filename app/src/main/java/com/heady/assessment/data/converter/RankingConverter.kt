package com.heady.assessment.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.heady.assessment.network.response.Ranking


class RankingConverter {
    private val gson = Gson()
    private val type = object : TypeToken<List<Ranking>>() {

    }.type

    @TypeConverter
    fun stringToNestedData(json: String): List<Ranking> {
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun nestedDataToString(nestedData: List<Ranking>): String {
        return gson.toJson(nestedData, type)
    }
}