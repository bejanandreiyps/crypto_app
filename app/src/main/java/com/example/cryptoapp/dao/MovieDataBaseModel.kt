package com.example.cryptoapp.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_NAME = "movie_data_base"

@Entity(tableName = TABLE_NAME)
data class MovieDataBaseModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "movie_title")
    val movieTitle: String,
    @ColumnInfo(name = "movie_isFavorite")
    val movieIsFavorite: Boolean = false
)
