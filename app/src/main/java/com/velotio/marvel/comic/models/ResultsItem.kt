package com.velotio.marvel.comic.models

data class ResultsItem(val thumbnail: Thumbnail,
                       val urls: List<UrlsItem>?,
                       val stories: Stories,
                       val series: Series,
                       val comics: Comics,
                       val name: String = "",
                       val description: String = "",
                       val modified: String = "",
                       val id: Int = 0,
                       val resourceURI: String = "",
                       val events: Events)