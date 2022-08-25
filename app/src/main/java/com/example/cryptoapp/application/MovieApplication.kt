package com.example.cryptoapp.application

import android.app.Application
import com.example.cryptoapp.MovieRepository
import com.example.cryptoapp.dao.MovieDao
import com.example.cryptoapp.database.DatabaseProvider
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApplication : Application() {

    val dao: MovieDao by lazy {
        DatabaseProvider.getInstance(this)?.getMovieDao()!!
    }

    val appContainer = MovieRepository
}