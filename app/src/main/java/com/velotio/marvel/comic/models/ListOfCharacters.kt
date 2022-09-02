package com.velotio.marvel.comic.models

data class ListOfCharacters(val copyright: String = "",
                            val code: Int = 0,
                            val data: Data,
                            val attributionHTML: String = "",
                            val attributionText: String = "",
                            val etag: String = "",
                            val status: String = "")