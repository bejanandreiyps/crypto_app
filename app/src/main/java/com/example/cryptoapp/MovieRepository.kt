package com.example.cryptoapp

import android.content.SharedPreferences
import com.example.cryptoapp.dao.MovieDao
import com.example.cryptoapp.database.MovieDataBaseModel
import com.example.cryptoapp.di.SharedPreferencesSession
import com.example.cryptoapp.domain.gallery.MovieOrSeriesModel
import com.example.cryptoapp.domain.login.CredentialsModel
import com.example.cryptoapp.domain.login.SessionModel
import com.example.cryptoapp.domain.login.TokenModel
import com.example.cryptoapp.domain.movie.MovieDetailsModel
import com.example.cryptoapp.domain.stars.MovieStarModel
import com.example.cryptoapp.domain.movie.MovieModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    @SharedPreferencesSession
    private val sharedPrefSession: SharedPreferences,
    private val movieDao: MovieDao,
    private val service: MovieService
) {

    companion object {
        private const val apiKey: String = "96d31308896f028f63b8801331250f03"
    }

    private var sessionId: String? = null

    init {
        sessionId = sharedPrefSession.getString("session_id", "")
    }

    suspend fun getToken(): TokenModel {
        return service.getToken(apiKey)
    }

    suspend fun postLogin(credentials: CredentialsModel): TokenModel {
        return service.postLogin(apiKey, credentials)
    }

    suspend fun createSession(token: TokenModel): SessionModel {
        val session = service.createSession(apiKey, token)
        sessionId = session.sessionId

        return session
    }

    suspend fun getGalleryMoviesOrSeries(): MovieOrSeriesModel {
        return service.getGalleryMoviesOrSeries(apiKey)
    }

    suspend fun getMovieStars(language: String, page: Int) : MovieStarModel {
        return service.getMovieStars(apiKey, language, page)
    }

    suspend fun getTopRatedMovies(language: String, page: Int): MovieModel {
        return service.getTopRatedMovies(apiKey, language, page)
    }

    suspend fun getPopularMovies(language: String, page: Int): MovieModel {
        return service.getPopularMovies(apiKey, language, page)
    }

    suspend fun getAiringTodayMovies(language: String, page: Int): MovieModel {
        return service.getAiringTodayMovies(apiKey, language, page)
    }

    suspend fun getMovieById(movieId: String): MovieDetailsModel =
        service.getMovieById(apiKey = apiKey, movieId = movieId)

    suspend fun getSearch(language: String, page: Int, query: String): MovieModel {
        if (query == "") {
            return MovieModel()
        }
        return service.getSearch(apiKey, language, page, query)
    }

    suspend fun handleMovieCardHold(movie: MovieDetailsModel) {
        if (movie.isFavorite) {
            movieDao.deleteOne(movie.id.toString())
        } else {
            movieDao.insertOne(MovieDataBaseModel(movie.id, movie.title))
        }
    }
}