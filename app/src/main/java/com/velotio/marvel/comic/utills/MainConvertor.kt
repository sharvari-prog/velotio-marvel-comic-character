package com.velotio.marvel.comic.utills

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.velotio.marvel.comic.models.Data
import com.velotio.marvel.comic.models.ResultsItem
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

@ProvidedTypeConverter
class MainConvertor(
//    private val jsonParser: JsonParser
) {
    var gson = Gson()

    @TypeConverter
    fun toResultsItemJson(meaning: List<ResultsItem>): String {


        return gson.toJson(meaning)


//        return jsonParser.toJson(
//            meaning,
//            object : TypeToken<ArrayList<ResultsItem>>() {}.type
//        ) ?: "[]"
    }

    @TypeConverter
    fun fromResultsItemJson(data: String): List<ResultsItem> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object :
            TypeToken<List<ResultsItem?>?>() {}.type
        return gson.fromJson<List<ResultsItem>>(data, listType)


//        return jsonParser.fromJson<ArrayList<ResultsItem>>(
//            json,
//            object : TypeToken<ArrayList<ResultsItem>>() {}.type
//        ) ?: emptyList()
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