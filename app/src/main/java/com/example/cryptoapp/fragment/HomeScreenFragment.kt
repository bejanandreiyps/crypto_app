package com.example.cryptoapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Dao
import androidx.viewpager2.widget.ViewPager2
import com.apollographql.apollo3.api.ApolloResponse
import com.example.cryptoapp.database.DatabaseProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.RickMortyQuery
import com.example.cryptoapp.adapter.GalleryAdapter
import com.example.cryptoapp.adapter.MovieAdapter
import com.example.cryptoapp.adapter.MovieStarsAdapter
import com.example.cryptoapp.adapter.RickMortyAdapter
import com.example.cryptoapp.dao.MovieDao
import com.example.cryptoapp.database.MovieDataBaseModel
import com.example.cryptoapp.databinding.FragmentHomeScreenBinding
import com.example.cryptoapp.domain.RickMortyCharacterModel
import com.example.cryptoapp.domain.gallery.GalleryModel
import com.example.cryptoapp.domain.movie.MovieDetailsModel
import com.example.cryptoapp.domain.movie.MovieModel
import com.example.cryptoapp.domain.stars.ActorModel
import com.example.cryptoapp.view_model.HomeScreenViewModel
import com.example.cryptoapp.view_model.HomeViewModelFactory
import com.example.cryptoapp.view_model.MovieApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HomeScreenFragment : Fragment() {

    @SuppressLint("StaticFieldLeak")
    private var _binding: FragmentHomeScreenBinding? = null
    private val movieDataBase by lazy {
        DatabaseProvider.getInstance(requireContext())
    }
    private val dao by lazy {
        movieDataBase?.getMovieDao()!!
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: HomeScreenViewModel by viewModels {
        HomeViewModelFactory(
            requireContext().applicationContext as MovieApplication
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.homeScreenViewModel = viewModel

//        //Gallery
//        binding.vpGallery.adapter = GalleryAdapter { movieId ->
//            onMovieCardClick(movieId)
//        }

//        //Actors
//        binding.rvStars.adapter = ActorAdapter()

        //Movies
        listOf(binding.rvPopularMovies, binding.rvTopRatedMovies, binding.rvAiringToday).forEach {
            it.adapter = MovieAdapter(
                { model -> onMovieCardHold(model, it) },
                { movieId -> onMovieCardClick(movieId) }
            )
        }
        createObservers()
        viewModel.populateGallery()
        viewModel.populateActors()
        viewModel.populatePopularMovies()
        viewModel.populateTopRatedMovies()
        viewModel.populateAiringMovies()
        //createSearchButton()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onMovieCardClick(movieId: Int) {
        findNavController().navigate(HomeScreenFragmentDirections.movieDetailsAction(movieId))
    }

    private fun onMovieCardHold(model: MovieDetailsModel, view: RecyclerView) {
        viewModel.handleMovieCardHold(model)
        (view.adapter as? MovieAdapter)?.modifyElement(model)
    }

    private fun createObservers() {
        viewModel.gallery.observe(viewLifecycleOwner) { list ->
            populateGallery(list)
            populateIndicator()
        }
        viewModel.actors.observe(viewLifecycleOwner) { list ->
            populateMovieStars(list)
        }
        viewModel.popularMovies.observe(viewLifecycleOwner) { list ->
            (binding.rvPopularMovies.adapter as MovieAdapter).movieList = list.results
        }
        viewModel.topRatedMovies.observe(viewLifecycleOwner) { list ->
            (binding.rvTopRatedMovies.adapter as MovieAdapter).movieList = list.results
        }
        viewModel.airingMovies.observe(viewLifecycleOwner) { list ->
            (binding.rvAiringToday.adapter as MovieAdapter).movieList = list.results
        }
    }

    private fun populateGallery(trendingMoviesSeries: List<GalleryModel>) {
        val galleryAdapter = GalleryAdapter()
        val gallery = trendingMoviesSeries.map {
            GalleryModel(
                it.imageUrl,
                it.title,
                it.releaseDate
            )
        }.take(6)
        galleryAdapter.submitList(gallery)
        binding.vpGallery.adapter = galleryAdapter
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

    private fun populateMovieStars(movieStars: List<ActorModel>) {
        val movieStarsAdapter = MovieStarsAdapter()
        val stars =
            movieStars.map { ActorModel(name = it.name, profilePath = it.profilePath) }
        movieStarsAdapter.movieStarList = stars
        binding.rvMovieStars.adapter = movieStarsAdapter
    }

//    private fun createSearchButton() {
//        binding.ivSearchIcon.setOnClickListener {
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.fragment_login, SearchFragment)
//                ?.addToBackStack(null)?.commit()
//        }
//    }

//    private fun populateRickMorty(rickMorty: ApolloResponse<RickMortyQuery.Data>) {
//        val rickMortyAdapter = RickMortyAdapter()
//        val rms = rickMorty.data?.characters?.results?.map {
//            RickMortyCharacterModel(
//                name = it?.name!!,
//                image = it.image!!
//            )
//        }
//        if (rms != null) {
//            rickMortyAdapter.rmList = rms
//        }
//        binding.rvRickMorty.adapter = rickMortyAdapter
//    }
}