package com.velotio.marvel.comic.models

data class Stories(val collectionURI: String = "",
                   val available: Int = 0,
                   val returned: Int = 0,
                   val items: List<ItemsItem>?)