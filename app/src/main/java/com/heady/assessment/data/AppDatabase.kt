package com.heady.assessment.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.heady.assessment.data.converter.CategoryConverter
import com.heady.assessment.data.converter.RankingConverter
import com.heady.assessment.data.dao.ResponseDao
import com.heady.assessment.network.response.ResponseData

@Database(entities = [ResponseData::class], version = 2)
@TypeConverters(value = [CategoryConverter::class, RankingConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun responseDao(): ResponseDao
}