package com.velotio.marvel.comic.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.velotio.marvel.comic.models.ListOfCharacters
import com.velotio.marvel.comic.models.ResultsItem
import retrofit2.Response

@Dao
interface CharacterDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(result: ListOfCharacters)

    @Query(value = "SELECT * FROM result_data_table")
    suspend fun getDataCount():List<ResultsItem>


}