package com.heady.assessment.data

import android.content.Context
import androidx.room.Room

object DataManager {
    private lateinit var database: AppDatabase

    fun init(context: Context) {
        database =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "heady-assessment"
            ).build()
    }

    fun getDataBase(): AppDatabase {
        return database
    }
}