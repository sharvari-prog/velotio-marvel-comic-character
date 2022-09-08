package com.velotio.marvel.comic.api

import com.velotio.marvel.comic.models.ListOfCharacters
import com.velotio.marvel.comic.models.ResultsItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface CharacterService {

    @GET("characters")
    suspend fun getListOfCharacter(
        @Query("ts") ts: String, @Query("apikey") apikey: String,
        @Query("hash") hash:String,@Query("limit") limit :Int,@Query("offset") offset :Int
    ): Response<ListOfCharacters>


    companion object{
        var characterService:CharacterService?=null

        fun getRetroFitApiInstance():CharacterService{

            if(characterService==null){
                characterService=Retrofit.Builder()
                    .baseUrl("http://gateway.marvel.com/v1/public/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(CharacterService::class.java)
            }
            return characterService!!

        }
    }
}