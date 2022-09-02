package com.velotio.marvel.comic.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.velotio.marvel.comic.api.CharacterService
import com.velotio.marvel.comic.models.ListOfCharacters

class CharacterRepository(private val characterService: CharacterService) {

    private val characterLiveData=MutableLiveData<ListOfCharacters>()

    val characters  :LiveData<ListOfCharacters>
    get() = characterLiveData

    suspend fun getCharacters(ts: String, apiKey: String, hash: String, limit: Int) {
        val result = characterService.getListOfCharacter(ts,apiKey,hash, limit )
        if(result.body() != null){
            characterLiveData.postValue(result.body())
        }

    }
}