package com.velotio.marvel.comic.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velotio.marvel.comic.CharacterApplication
import com.velotio.marvel.comic.api.CharacterService
import com.velotio.marvel.comic.models.ResultsItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

class CharacterViewModel : ViewModel() {



    @SuppressLint("SimpleDateFormat")
    var sdf: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
    var currentDateAndTime: String = sdf.format(Date())

    private val apikey = "867cfda73983a9fff36e5cb99aac336d"
    private val privateKey = "bddd42d5a10b1864f6d4a35e9acbf027dec3ccb7"
    private val ts = currentDateAndTime
    private val hash = getMD5Value(ts + privateKey + apikey)

    private val characterDatabase = CharacterApplication.database

    private var characterResponse: List<ResultsItem> by mutableStateOf(listOf())

    private val _isRefreshing = MutableStateFlow(false)

    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private var errorMessage: String by mutableStateOf("")

    fun getCharacterList(isFromRefresh: Boolean): List<ResultsItem> {
        viewModelScope.launch {

            val characterService = CharacterService.getRetroFitApiInstance()
            try {
                if (characterDatabase.characterDao().getDataCount()
                        .isNotEmpty() && !isFromRefresh
                ) {
                    characterResponse = characterDatabase.characterDao().getDataCount()
                    Log.e("@@@@   ", "getCharacterList: if" )

                } else {

                    Log.e("@@@@   ", "getCharacterList: else" )

                    val dataCount = characterDatabase.characterDao().getDataCount().size
                    var offSetCount = 100 + dataCount
                    if (dataCount == 0) {
                        offSetCount = 0
                    }
                    val listOfCharacters =
                        characterService.getListOfCharacter(ts, apikey, hash, 100, offSetCount)
                    characterResponse = listOfCharacters.body()?.data?.results!!

                    characterDatabase.characterDao().addData(listOfCharacters.body()!!)
                    for (i in characterResponse.indices) {
                        GlobalScope.launch {
                            characterDatabase.characterDao()
                                .addResultDataTable(characterResponse[i])
                            characterDatabase.characterDao()
                                .addThumbnailData(characterResponse[i].thumbnail)
                        }

                    }
                }

            } catch (e: Exception) {
                errorMessage = e.message.toString()

            }


        }
        return characterResponse
    }

    private fun getMD5Value(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}