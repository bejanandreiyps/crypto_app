package com.example.cryptoapp.dao

import androidx.room.*

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<MovieDataBaseModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(data: MovieDataBaseModel)

    @Query("DELETE FROM $TABLE_NAME WHERE id =:id")
    suspend fun deleteOne(id: String)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()

    @Update
    suspend fun update(lastMinuteProduct: MovieDataBaseModel)

    @Query("SELECT * FROM $TABLE_NAME where id = :id")
    suspend fun queryAfterId(id: String): MovieDataBaseModel?
}