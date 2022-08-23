package com.example.cryptoapp.view_model

import android.app.Application
import android.content.SharedPreferences
import com.example.cryptoapp.MovieRepositoryRetrofit
import com.example.cryptoapp.dao.MovieDao
import com.example.cryptoapp.database.DatabaseProvider

class MovieApplication : Application() {

    val dao: MovieDao by lazy {
        DatabaseProvider.getInstance(this)?.getMovieDao()!!
    }

    val appContainer = MovieRepositoryRetrofit
}