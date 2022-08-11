package com.example.cryptoapp.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cryptoapp.database.DatabaseProvider
import com.example.cryptoapp.MovieRepositoryRetrofit
import com.example.cryptoapp.adapter.MovieAdapter
import com.example.cryptoapp.dao.MovieDao
import com.example.cryptoapp.database.MovieDataBaseModel
import com.example.cryptoapp.database.MovieDatabase
import com.example.cryptoapp.databinding.FragmentSearchBinding
import com.example.cryptoapp.domain.movie.MovieDetailsModel
import com.example.cryptoapp.domain.movie.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private lateinit var movieDataBase: MovieDatabase
    private lateinit var dao : MovieDao

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mdbRepo = MovieRepositoryRetrofit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createSearchBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadSearchResults(query: String) {
        movieDataBase = DatabaseProvider.getInstance(requireContext())!!
        dao = movieDataBase.getMovieDao()
        lifecycleScope.launch(Dispatchers.IO) {
            //Search for the movies
            val searchResults1 = MovieRepositoryRetrofit.getSearch("en-US", 1, query)
            syncFavorites(searchResults1)
            val searchResults2 = MovieRepositoryRetrofit.getSearch("en-US", 2, query)
            syncFavorites(searchResults2)
            //Update UI
            launch(Dispatchers.Main) {
                displayResults((searchResults1.results + searchResults2.results).filter { it.posterPath?.isNotEmpty()
                    ?: false })
            }
        }
    }

    private fun syncFavorites(rvMovies: MovieModel) {
        lifecycleScope.launch(Dispatchers.IO) {
            for (movie in rvMovies.results) {
                val movieDataBaseModel: MovieDataBaseModel? = dao.queryAfterId(movie.id.toString())
                if (movieDataBaseModel?.movieIsFavorite == true) {
                    movie.isFavorite = true
                }
            }
        }
    }

    private fun displayResults(movies: List<MovieDetailsModel>) {
        val resultGridLayoutManager = GridLayoutManager(activity, 4)
        binding.rvResults.layoutManager = resultGridLayoutManager
        val resultsMovieAdapter = MovieAdapter (callback)
        resultsMovieAdapter.movieList = movies
        binding.rvResults.adapter = resultsMovieAdapter
    }

    private fun createSearchBar() {
        binding.etSearchField.addTextChangedListener(object : TextWatcher {

            var job: Job = Job()

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editText: Editable?) {
                job.cancel()

                job = lifecycleScope.launch(Dispatchers.IO) {
                    delay(350)
                    if(editText?.isNotEmpty() == true) {
                        loadSearchResults(editText.toString())
                    } else {
                        loadSearchResults("")
                    }
                }
            }
        })
    }

    private val callback: (movieModel: MovieDetailsModel) -> Unit = {
        lifecycleScope.launch {
            if(it.isFavorite) {
                dao.deleteOne(it.id.toString())
            } else {
                dao.insertOne(MovieDataBaseModel(id = it.id, movieTitle = it.title, movieIsFavorite = true))
            }
        }
    }
}