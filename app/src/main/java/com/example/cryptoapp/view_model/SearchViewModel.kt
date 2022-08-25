package com.example.cryptoapp.view_model

import android.util.Log
import androidx.lifecycle.*
import com.example.cryptoapp.MovieRepository
import com.example.cryptoapp.dao.MovieDao
import com.example.cryptoapp.database.MovieDataBaseModel
import com.example.cryptoapp.domain.movie.MovieDetailsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val dao: MovieDao,
    private val repo: MovieRepository,
): ViewModel() {

    private var job: Job = Job()

    private val _movieList = MutableLiveData<List<MovieDetailsModel>>()
    val movieList: LiveData<List<MovieDetailsModel>>
        get() = _movieList

    fun loadSearchResults(query: String) {
        job.cancel()

        job = viewModelScope.launch(Dispatchers.IO) {
            try {
                val searchResults =
                    (repo.getSearch("en-US", 1, query).results +
                            repo.getSearch("en-US", 2, query).results)
                _movieList.postValue(searchResults)
            } catch (e: Exception) {
                Log.e("SearchViewModel: ", e.message.toString())
            }
        }
    }
    fun handleMovieCardHold(movie: MovieDetailsModel) {
        viewModelScope.launch(Dispatchers.IO) {
            if (movie.isFavorite) {
                dao.deleteOne(movie.id.toString())
            } else {
                dao.insertOne(MovieDataBaseModel(movie.id, movie.title))
            }
        }
    }
}