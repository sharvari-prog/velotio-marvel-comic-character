package com.velotio.marvel.comic.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velotio.marvel.comic.CharacterApplication
import com.velotio.marvel.comic.api.CharacterService
import com.velotio.marvel.comic.db.CharacterDao
import com.velotio.marvel.comic.db.CharacterDatabase
import com.velotio.marvel.comic.models.ResultData
import com.velotio.marvel.comic.models.ResultsItem
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.math.log

class CharacterViewModel : ViewModel() {

    private val apikey = "867cfda73983a9fff36e5cb99aac336d"
    private val privateKey = "bddd42d5a10b1864f6d4a35e9acbf027dec3ccb7"
    private val ts = "11-09-2022"
    private val hash = getMD5Value(ts + privateKey + apikey)

    private val characterDatabase = CharacterApplication.database

    private var characterResponse: List<ResultsItem> by mutableStateOf(listOf())
    private var dbResponse : List<ResultData> by mutableStateOf(listOf())
//    var favourites: MutableList<ResultData> by mutableStateOf(mutableListOf())
    private var errorMessage: String by mutableStateOf("")

    fun getCharacterList(): List<ResultData> {
        viewModelScope.launch {

            val characterService = CharacterService.getRetroFitApiInstance()
            try {

                if (characterDatabase.characterDao().getDataCount().isNotEmpty()) {
                    Log.e("@@ is not empty ", "getCharacterList: ")
                    for (i in characterDatabase.characterDao().getDataCount().indices) {
                        dbResponse.toMutableList().add(characterDatabase.characterDao().getDataCount()[i])
//                        dbResponse.add(characterDatabase.characterDao().getDataCount()[i])
                    }
                } else {
                    val listOfCharacters = characterService.getListOfCharacter(ts, apikey, hash, 10)
                    characterResponse = listOfCharacters.body()?.data?.results!!

                    for (i in characterResponse.indices) {
                        var imageUrl =
                            characterResponse[i].thumbnail.path + "." + characterResponse[i].thumbnail.extension

                        val resultData = ResultData(
                            characterResponse[i].id, characterResponse[i].name,
                            characterResponse[i].description, imageUrl
                        )
                        characterDatabase.characterDao().addData(resultData)
                        dbResponse.toMutableList().add(resultData)

                    }
                }

            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.e("@@ Exception ", "getCharacterList: " + e.message )

            }


        }
        Log.e("@@ Size of dbResponse", "getCharacterList: " + dbResponse.size)
        return dbResponse
    }

    private fun getMD5Value(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}