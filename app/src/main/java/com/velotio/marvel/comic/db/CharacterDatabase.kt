package com.velotio.marvel.comic.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.velotio.marvel.comic.models.*

@Database(
    entities = [ResultData::class],
    version = 1
)
abstract class CharacterDatabase : RoomDatabase() {


    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: CharacterDatabase? = null
        fun getDatabase(context: Context): CharacterDatabase {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        CharacterDatabase::class.java,
                        "marvel_character_db"
                    )
                        .build()
                }
            }
            return INSTANCE!!

        }
    }


}