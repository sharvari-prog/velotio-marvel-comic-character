package com.velotio.marvel.comic.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velotio.marvel.comic.models.ListOfCharacters
import com.velotio.marvel.comic.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainViewModel(val repository: CharacterRepository) : ViewModel() {

    private val apikey = "867cfda73983a9fff36e5cb99aac336d"
    private val privateKey = "bddd42d5a10b1864f6d4a35e9acbf027dec3ccb7"
    private val ts = "02-09-2022"
    private val hash = getMD5Value(ts + apikey + privateKey)

    init {
        viewModelScope.launch {
            Dispatchers.IO
            repository.getCharacters(ts, apikey, hash, 10)
        }

    }

    val characters: LiveData<ListOfCharacters>
        get() = repository.characters


    private fun getMD5Value(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }


}