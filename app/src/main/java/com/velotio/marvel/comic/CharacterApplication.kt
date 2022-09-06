package com.velotio.marvel.comic

import android.app.Application
import com.velotio.marvel.comic.db.CharacterDatabase

class CharacterApplication : Application() {

    companion object{
        lateinit var database: CharacterDatabase

    }
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        database = CharacterDatabase.getDatabase(applicationContext)

    }

}