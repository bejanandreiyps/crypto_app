package com.example.cryptoapp.database

import android.content.Context
import androidx.room.Room


object DatabaseProvider {

    private const val MOVIE_DATABASE_TAG = "movie_database"
    private var database: MovieDatabase? = null

    fun getInstance(context: Context): MovieDatabase? {
        if(database == null) {
            database = Room.databaseBuilder(
                context, MovieDatabase::class.java,
                MOVIE_DATABASE_TAG
            ).fallbackToDestructiveMigration().build()
        }
        return database
    }
}