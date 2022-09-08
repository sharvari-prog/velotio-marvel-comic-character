package com.velotio.marvel.comic.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.velotio.marvel.comic.models.Comics
import com.velotio.marvel.comic.models.ListOfCharacters
import com.velotio.marvel.comic.models.ResultsItem
import com.velotio.marvel.comic.models.Thumbnail
import retrofit2.Response

@Dao
interface CharacterDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addData(result: ListOfCharacters)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addResultDataTable(data :ResultsItem)

    @Query(value = "SELECT * FROM result_data_table")
    suspend fun getDataCount():List<ResultsItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addThumbnailData(thumbnail: Thumbnail)

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addComicsData(comics: Comics)
}