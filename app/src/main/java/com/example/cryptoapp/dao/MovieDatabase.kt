package com.example.cryptoapp.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieDataBaseModel::class],
    version = 5,
    exportSchema = false
)

abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}
