package com.example.cryptoapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptoapp.dao.MovieDao
import com.example.cryptoapp.dao.MovieDataBaseModel

@Database(
    entities = [MovieDataBaseModel::class],
    version = 5,
    exportSchema = false
)

abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}
