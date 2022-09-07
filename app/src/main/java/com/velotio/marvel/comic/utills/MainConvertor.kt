package com.velotio.marvel.comic.utills

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.velotio.marvel.comic.models.Data
import com.velotio.marvel.comic.models.ResultsItem

@ProvidedTypeConverter
class MainConvertor(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun toResultsItemJson(meaning: List<ResultsItem>): String {
        return jsonParser.toJson(
            meaning,
            object : TypeToken<ArrayList<ResultsItem>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromResultsItemJson(json: String): List<ResultsItem> {
        return jsonParser.fromJson<ArrayList<ResultsItem>>(
            json,
            object : TypeToken<ArrayList<ResultsItem>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun fromSource(source: Data): List<ResultsItem> {
        return source.results
    }

    @TypeConverter
    fun tvSource(name: List<ResultsItem>): Data {
        return Data(name)
    }

}