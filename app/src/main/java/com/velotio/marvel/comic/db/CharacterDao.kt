package com.velotio.marvel.comic.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.velotio.marvel.comic.models.ResultData

@Dao
interface CharacterDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addData(result: ResultData)



    @Query("SELECT * FROM result_data")
    suspend fun getDataCount():List<ResultData>
}