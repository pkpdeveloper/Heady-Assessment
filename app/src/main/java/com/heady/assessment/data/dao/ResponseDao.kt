package com.heady.assessment.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.heady.assessment.network.response.ResponseData
import io.reactivex.Single

@Dao
interface ResponseDao {
    @Insert(onConflict = REPLACE)
    fun insertAll(responseData: ResponseData): Single<Long>

    @Query("SELECT * FROM response_data")
    fun getData(): Single<ResponseData>


}