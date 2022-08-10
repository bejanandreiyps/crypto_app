package com.example.cryptoapp

import android.content.Context
import androidx.room.Room
import com.example.cryptoapp.dao.MovieDatabase


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