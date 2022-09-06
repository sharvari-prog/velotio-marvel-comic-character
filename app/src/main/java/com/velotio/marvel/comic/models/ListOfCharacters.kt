package com.velotio.marvel.comic.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ListOfCharacters(
                            @PrimaryKey(autoGenerate = true)
                            var id:Int,
                            val copyright: String = "",
                            val code: Int = 0,
                            val data: Data,
                            val attributionHTML: String = "",
                            val attributionText: String = "",
                            val etag: String = "",
                            val status: String = "")