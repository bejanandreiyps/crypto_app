package com.example.cryptoapp.view_model

import android.util.Log
import androidx.lifecycle.*
import com.example.cryptoapp.MovieRepository
import com.example.cryptoapp.domain.stars.ActorModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class  MovieDetailsViewModel @Inject constructor(
    private val repo: MovieRepository
): ViewModel() {

    private var job: Job = Job()

    private val _movieTitle = MutableLiveData<String>()
    val movieTitle: LiveData<String>
        get() = _movieTitle

    private val _movieOverview = MutableLiveData<String>()
    val movieOverview: LiveData<String>
        get() = _movieOverview

    private val _movieImage = MutableLiveData<String?>()
    val movieImage: LiveData<String?>
        get() = _movieImage

    private val _actors = MutableLiveData<List<ActorModel>>()
    val actors: LiveData<List<ActorModel>>
        get() = _actors

    fun displayMovieDetails(movieId: String) {
        job.cancel()

        job = viewModelScope.launch(Dispatchers.IO) {
            try {
                val movieDetails = repo.getMovieById(movieId)
                _movieTitle.postValue(movieDetails.title)
                _movieOverview.postValue(movieDetails.overview)
                _movieImage.postValue(movieDetails.backdropPath)

            } catch (e: Exception) {
                Log.e("MovieDetailsViewModel: ", e.toString())
            }
        }
    }
}