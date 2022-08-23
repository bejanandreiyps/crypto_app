package com.example.cryptoapp.view_model

import android.util.Log
import androidx.lifecycle.*
import com.example.cryptoapp.MovieRepositoryRetrofit
import com.example.cryptoapp.domain.stars.ActorModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class  MovieDetailsViewModel: ViewModel() {

    private var job: Job = Job()
    private val repo = MovieRepositoryRetrofit()

    //fa ca la actors cu live data
    val movieTitle = MutableLiveData<String>()
    val movieOverview = MutableLiveData<String>()
    val movieImage = MutableLiveData<String?>()

    private val _actors = MutableLiveData<List<ActorModel>>()
    val actors: LiveData<List<ActorModel>>
        get() = _actors

    fun displayMovieDetails(movieId: String) {
        job.cancel()

        job = viewModelScope.launch(Dispatchers.IO) {
            try {
                //Load movie details
                val movieDetails = repo.getMovieById(movieId)
                //Update UI
                movieTitle.postValue(movieDetails.title)
                movieOverview.postValue(movieDetails.overview)
                movieImage.postValue(movieDetails.backdropPath)
            } catch (e: Exception) {
                Log.e("MovieDetailsViewModel: ", e.toString())
            }
        }
    }
}

class MovieDetailsViewModelFactory(private val application: MovieApplication) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel() as T
    }
}