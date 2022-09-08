package com.velotio.marvel.comic.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "result_data_table")
data class ResultsItem(
    @PrimaryKey
    val id: Int,
    val name: String = "",
    val description: String = "",
    val modified: String = "",
    val thumbnail: Thumbnail,
//    @Ignore
//    val comics: Comics
) {


}