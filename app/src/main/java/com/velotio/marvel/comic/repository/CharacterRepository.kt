package com.velotio.marvel.comic.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.velotio.marvel.comic.api.CharacterService
import com.velotio.marvel.comic.models.ListOfCharacters

class CharacterRepository(private val characterService: CharacterService) {

    private var TAG = "@CharacterRepository:  "
    private val characterLiveData = MutableLiveData<ListOfCharacters>()

    val characters: LiveData<ListOfCharacters>
        get() = characterLiveData

    suspend fun getCharacters(ts: String, apikey: String, hash: String, limit: Int) {
        val result = characterService.getListOfCharacter(ts, apikey, hash, limit)
        if (result.body() != null) {
            characterLiveData.postValue(result.body())

        } else {
            Log.e(TAG, "getCharacters is null" + result.body())
        }

    }
}