package com.velotio.marvel.comic.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result_data_table")
data class ResultsItem(
    @PrimaryKey
    val id: Int,
    val name: String = "",
    val description: String = "",
    val modified: String = "",
) {



//    val resourceURI: String = ""
//    val events: Events = TODO()
//    val thumbnail: Thumbnail
//
//    val comics: Comics
}