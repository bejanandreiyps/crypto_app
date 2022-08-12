package com.example.cryptoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.apollographql.apollo3.api.ApolloResponse
import com.example.cryptoapp.database.DatabaseProvider
import com.example.cryptoapp.MovieRepositoryRetrofit
import com.example.cryptoapp.R
import com.example.cryptoapp.RickMortyQuery
import com.example.cryptoapp.adapter.GalleryAdapter
import com.example.cryptoapp.adapter.MovieAdapter
import com.example.cryptoapp.adapter.MovieStarsAdapter
import com.example.cryptoapp.adapter.RickMortyAdapter
import com.example.cryptoapp.apollo.apolloClient
import com.example.cryptoapp.database.MovieDataBaseModel
import com.example.cryptoapp.databinding.FragmentHomeScreenBinding
import com.example.cryptoapp.domain.RickMortyCharacterModel
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
    //TODO: use by lazy{} if you can , see Bogdan's case
    private val movieDataBase by lazy {
        DatabaseProvider.getInstance(requireContext())
    }
    private val dao by lazy {
        movieDataBase?.getMovieDao()!!
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val repo = MovieRepositoryRetrofit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            val rickMorty = apolloClient.query(RickMortyQuery()).execute()

            launch(Dispatchers.Main) {
                populateGallery(repo.getGalleryMoviesOrSeries())
                populateIndicator()
                populateRickMorty(rickMorty)
                populateMovieStars(repo.getMovieStars("en-US", 1))

                populateMovieRecyclerView(repo.getTopRatedMovies("en-US", 1), binding.rvTopRatedMovies)
                populateMovieRecyclerView(repo.getPopularMovies("en-US", 1), binding.rvPopularMovies)
                populateMovieRecyclerView(repo.getAiringTodayMovies("en-US", 1), binding.rvAiringToday)

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
        val gallery = trendingMoviesSeries.results.map {
            GalleryModel(
                it.backdropPath,
                it.title,
                it.releaseDate
            )
        }.take(6)
        galleryAdapter.submitList(gallery)
        binding.vpGallery.adapter = galleryAdapter
    }

    private fun populateRickMorty(rickMorty: ApolloResponse<RickMortyQuery.Data>) {
        val rickMortyAdapter = RickMortyAdapter()
        val rms = rickMorty.data?.characters?.results?.map {
            RickMortyCharacterModel(
                name = it?.name!!,
                image = it.image!!
            )
        }
        if (rms != null) {
            rickMortyAdapter.rmList = rms
        }
        binding.rvRickMorty.adapter = rickMortyAdapter
    }

    private fun populateIndicator() {
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
            6
        )
        binding.ivGalleryIndicator.notifyDataChanged()
    }

    private fun populateMovieStars(movieStars: MovieStarModel) {
        val movieStarsAdapter = MovieStarsAdapter()
        val stars =
            movieStars.results.map { ActorModel(name = it.name, profilePath = it.profilePath) }
        movieStarsAdapter.movieStarList = stars
        binding.rvMovieStars.adapter = movieStarsAdapter
    }

    private fun populateMovieRecyclerView(movieList: MovieModel, view: RecyclerView) {
        val movieAdapter = MovieAdapter { model -> callback(model, view) }
        syncFavorites(movieList) {
            lifecycleScope.launch(Dispatchers.Main) {
                movieAdapter.movieList = it
            }
        }
        view.adapter = movieAdapter
    }

    private val callback: (movieModel: MovieDetailsModel, view: RecyclerView) -> Unit = { model, view->
        lifecycleScope.launch {
            if(model.isFavorite) {
                dao.deleteOne(model.id.toString())
            } else {
                dao.insertOne(MovieDataBaseModel(id = model.id, movieTitle = model.title, movieIsFavorite = true))
            }
        }
        (view.adapter as? MovieAdapter)?.modifyElement(model)
    }

    private fun syncFavorites(rvMovies: MovieModel, callback: (model: List<MovieDetailsModel>)->Unit) {
        lifecycleScope.launch(Dispatchers.IO) {
            val movies = rvMovies.results.map { movie ->
                dao.queryAfterId(movie.id.toString())?.let {
                    return@map movie.copy(isFavorite = true)
                }
                return@map movie
            }
            callback(movies)
        }
    }
}