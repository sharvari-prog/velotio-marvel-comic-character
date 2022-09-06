package com.velotio.marvel.comic.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velotio.marvel.comic.api.CharacterService
import com.velotio.marvel.comic.models.ResultsItem
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest

class CharacterViewModel :ViewModel() {

    private val apikey = "867cfda73983a9fff36e5cb99aac336d"
    private val privateKey = "bddd42d5a10b1864f6d4a35e9acbf027dec3ccb7"
    private val ts = "11-09-2022"
    private val hash = getMD5Value(ts +  privateKey+apikey)

    var characterResponse:List<ResultsItem> by mutableStateOf(listOf())
    var errorMessage:String by mutableStateOf("")

    fun getCharacterList(){
        viewModelScope.launch {

            val characterService=CharacterService.getRetroFitApiInstance()
            try{
                val listOfCharacters= characterService.getListOfCharacter(ts, apikey, hash, 2)
                characterResponse=listOfCharacters
            }catch (e:Exception){
                errorMessage=e.message.toString()
            }



        }
    }

    private fun getMD5Value(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}