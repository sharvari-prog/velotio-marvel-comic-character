package com.velotio.marvel.comic.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "thumbnail_tbl")
data class Thumbnail(
    var path: String = "",
    var extension: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}