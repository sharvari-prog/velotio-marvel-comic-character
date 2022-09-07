package com.velotio.marvel.comic.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.velotio.marvel.comic.utills.MainConvertor

@Entity(tableName = "data_table")
data class Data(
    val results: List<ResultsItem>
){

    @PrimaryKey
    var id:Int = 0
}