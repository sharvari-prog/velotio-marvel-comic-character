package com.velotio.marvel.comic.viewmodels

import android.graphics.Movie
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velotio.marvel.comic.models.ListOfCharacters
import com.velotio.marvel.comic.models.ResultsItem
import com.velotio.marvel.comic.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainViewModel(val repository: CharacterRepository) : ViewModel() {

    private val apikey = "867cfda73983a9fff36e5cb99aac336d"
    private val privateKey = "bddd42d5a10b1864f6d4a35e9acbf027dec3ccb7"
    private val ts = "11-09-2022"
    private val hash = getMD5Value(ts +  privateKey+apikey)

    private val _state = MutableStateFlow(emptyList<ListOfCharacters>())

    val state: StateFlow<List<ListOfCharacters>>
        get() = _state

    init {
        viewModelScope.launch {
            Dispatchers.IO
            repository.getCharacters(ts, apikey, hash, 2)
        }

    }

    val characters: LiveData<ListOfCharacters>
        get() = repository.characters


    private fun getMD5Value(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }



}

