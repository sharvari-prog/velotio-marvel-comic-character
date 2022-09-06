package com.velotio.marvel.comic.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result_data")
data class ResultData(
    @PrimaryKey
    var id: Int ,
    var name:String,
    var description:String,
    var imageUrl:String
)
