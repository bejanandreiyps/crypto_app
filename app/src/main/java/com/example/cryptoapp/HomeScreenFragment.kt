package com.example.cryptoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.cryptoapp.adapter.GalleryAdapter
import com.example.cryptoapp.adapter.MovieAdapter
import com.example.cryptoapp.adapter.MovieStarsAdapter
import com.example.cryptoapp.databinding.FragmentHomeScreenBinding
import com.example.cryptoapp.domain.gallery.GalleryModel
import com.example.cryptoapp.domain.gallery.MovieOrSeriesModel
import com.example.cryptoapp.domain.stars.ActorModel
import com.example.cryptoapp.domain.stars.MovieStarModel
import com.example.cryptoapp.domain.movie.MovieDetailsModel
import com.example.cryptoapp.domain.movie.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HomeScreenFragment : Fragment() {

    private var _binding: FragmentHomeScreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val repo = MovieRepositoryRetrofit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            val galleryMoviesSeries = repo.getGalleryMoviesOrSeries()
            val movieStars = repo.getMovieStars("en-US", 1)
            val topRatedMovies = repo.getTopRatedMovies("en-US", 1)
            val popularMovies = repo.getPopularMovies("en-US", 1)
            val airingTodayMovies = repo.getAiringTodayMovies("en-US", 1)
            launch(Dispatchers.Main) {
                populateGallery(galleryMoviesSeries)
                populateIndicator(6)
                populateMovieStars(movieStars)
                populateTopRatedMovies(topRatedMovies)
                populatePopularMovies(popularMovies)
                populateAiringTodayMovies(airingTodayMovies)
                createSearchButton()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createSearchButton() {
        binding.ivSearchIcon.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container_view_tag, SearchFragment())
                ?.addToBackStack(null)?.commit()
        }
    }

    private fun populateGallery(trendingMoviesSeries: MovieOrSeriesModel) {
        val galleryAdapter = GalleryAdapter()
        val gallery = trendingMoviesSeries.results.map { GalleryModel(it.backdropPath, it.title, it.releaseDate) }.take(6)
        galleryAdapter.submitList(gallery)
        binding.vpGallery.adapter = galleryAdapter
    }

    private fun populateIndicator(size: Int) {
        binding.vpGallery.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                binding.ivGalleryIndicator.onPageScrollStateChanged(state)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.ivGalleryIndicator.onPageSelected(position)
            }
        })
        binding.ivGalleryIndicator.setPageSize(
            size
        )
        binding.ivGalleryIndicator.notifyDataChanged()
    }

    private fun populateMovieStars(movieStars: MovieStarModel) {
        val movieStarsAdapter = MovieStarsAdapter()
        val stars = movieStars.results.map { ActorModel(name = it.name, profilePath = it.profilePath) }
        movieStarsAdapter.movieStarList = stars
        binding.rvMovieStars.adapter = movieStarsAdapter
    }

    private fun populateTopRatedMovies(topRatedMovies: MovieModel) {
        val movieAdapter = MovieAdapter()
        val movies = topRatedMovies.results.map { MovieDetailsModel(title = it.title, posterPath = it.posterPath, voteAverage = it.voteAverage) }
        movieAdapter.movieList = movies
        binding.rvTopRatedMovies.adapter = movieAdapter
    }

    private fun populatePopularMovies(popularMovies: MovieModel) {
        val popularMovieAdapter = MovieAdapter()
        val movies = popularMovies.results.map { MovieDetailsModel(title = it.title, posterPath = it.posterPath, voteAverage = it.voteAverage) }
        popularMovieAdapter.movieList = movies
        binding.rvPopularMovies.adapter = popularMovieAdapter
    }

    private fun populateAiringTodayMovies(airingMovies: MovieModel) {
        val airingMovieAdapter = MovieAdapter()
        val movies = airingMovies.results.map { MovieDetailsModel(title = it.title, posterPath = it.posterPath, voteAverage = it.voteAverage) }
        airingMovieAdapter.movieList = movies
        binding.rvAiringToday.adapter = airingMovieAdapter
    }
}