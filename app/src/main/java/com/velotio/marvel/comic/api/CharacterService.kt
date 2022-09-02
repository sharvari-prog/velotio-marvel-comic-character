package com.velotio.marvel.comic.api

import com.velotio.marvel.comic.models.ListOfCharacters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CharacterService {

    @GET("characters")
    suspend fun getListOfCharacter(
        @Query("ts") ts: String, @Query("apikey") apikey: String,
        @Query("hash") hash:String,@Query("limit") limit :Int
    ): Response<ListOfCharacters>
}