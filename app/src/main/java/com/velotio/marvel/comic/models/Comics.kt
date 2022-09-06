package com.velotio.marvel.comic.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Comics(
    val collectionURI: String = "",
    val available: Int = 0,
    val returned: Int = 0,
    val items: List<ItemsItem>?
)