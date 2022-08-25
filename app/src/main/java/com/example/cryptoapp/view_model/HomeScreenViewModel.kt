package com.example.cryptoapp.view_model

import androidx.lifecycle.*
import com.example.cryptoapp.MovieRepository
import com.example.cryptoapp.RickMortyQuery
import com.example.cryptoapp.domain.gallery.GalleryModel
import com.example.cryptoapp.domain.movie.MovieDetailsModel
import com.example.cryptoapp.domain.movie.MovieModel
import com.example.cryptoapp.domain.stars.ActorModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repo: MovieRepository,
): ViewModel() {
    private val _gallery = MutableLiveData<List<GalleryModel>>()
    val gallery: LiveData<List<GalleryModel>>
        get() = _gallery

    private val _actors = MutableLiveData<List<ActorModel>>()
    val actors: LiveData<List<ActorModel>>
        get() = _actors

    private val _popularMovies = MutableLiveData<MovieModel>()
    val popularMovies: LiveData<MovieModel>
        get() = _popularMovies

    private val _topRatedMovies = MutableLiveData<MovieModel>()
    val topRatedMovies: LiveData<MovieModel>
        get() = _topRatedMovies

    private val _airingMovies = MutableLiveData<MovieModel>()
    val airingMovies: LiveData<MovieModel>
        get() = _airingMovies

    private val _rms = MutableLiveData<List<RickMortyQuery.Characters>>()
    val rms: LiveData<List<RickMortyQuery.Characters>>
        get() = _rms

    fun populateGallery() {
        viewModelScope.launch(Dispatchers.IO) {
            _gallery.postValue(repo.getGalleryMoviesOrSeries().results.map {
                GalleryModel(
                    it.backdropPath,
                    it.title,
                    it.releaseDate
                )
            })
        }
    }

    fun populateActors() {
        viewModelScope.launch(Dispatchers.IO) {
            _actors.postValue(repo.getMovieStars("en-US", 1).results.map {
                ActorModel(
                    name = it.name,
                    profilePath = it.profilePath
                )
            })
        }
    }

    fun populatePopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _popularMovies.postValue(repo.getPopularMovies("en_US", 1))
        }
    }

    fun populateTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _topRatedMovies.postValue(repo.getTopRatedMovies("en_US", 1))
        }
    }

    fun populateAiringMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _airingMovies.postValue(repo.getAiringTodayMovies("en_US", 1))
        }
    }

    fun handleMovieCardHold(movie: MovieDetailsModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.handleMovieCardHold(movie)
        }
    }

//    fun populateRickMorty() {
//        viewModelScope.launch(Dispatchers.IO) {
//            _rms.postValue()
//        }
//    }
}
