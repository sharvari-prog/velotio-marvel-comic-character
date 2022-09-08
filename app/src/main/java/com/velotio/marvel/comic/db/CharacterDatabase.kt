package com.velotio.marvel.comic.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.velotio.marvel.comic.models.*
import com.velotio.marvel.comic.utills.MainConvertor

@Database(
    entities = [ListOfCharacters::class, Data::class, ResultsItem::class,Thumbnail::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(MainConvertor::class)
abstract class CharacterDatabase : RoomDatabase() {


    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: CharacterDatabase? = null
        fun getDatabase(context: Context): CharacterDatabase {
            val mainConvertorInstance =MainConvertor()


            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        CharacterDatabase::class.java,
                        "marvel_character_db.db"
                    ).addTypeConverter(mainConvertorInstance)
                        .build()
                }
            }
            return INSTANCE!!

        }
    }


}