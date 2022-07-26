package com.example.cryptoapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.cryptoapp.database.DatabaseProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.adapter.GalleryAdapter
import com.example.cryptoapp.adapter.MovieAdapter
import com.example.cryptoapp.adapter.MovieStarsAdapter
import com.example.cryptoapp.databinding.FragmentHomeScreenBinding
import com.example.cryptoapp.domain.gallery.GalleryModel
import com.example.cryptoapp.domain.movie.MovieDetailsModel
import com.example.cryptoapp.domain.stars.ActorModel
import com.example.cryptoapp.view_model.HomeScreenUiState
import com.example.cryptoapp.view_model.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

@AndroidEntryPoint
class HomeScreenFragment : Fragment() {

    @SuppressLint("StaticFieldLeak")
    private lateinit var binding: FragmentHomeScreenBinding
    private val movieDataBase by lazy {
        DatabaseProvider.getInstance(requireContext())
    }

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val viewModel: HomeScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.homeScreenViewModel = viewModel

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
        createSearchButton()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun createObservers() {
//        viewModel.gallery.observe(viewLifecycleOwner) { list ->
//            populateGallery(list)
//            populateIndicator()
//        }
        lifecycleScope.launch {
            viewModel.uiState.collect {
                when(it) {
                    is HomeScreenUiState.Error -> TODO()
                    is HomeScreenUiState.Success -> {
                        populateGallery(it.galleryList)
                        populateIndicator()
                    }
                }
            }
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

    private fun createSearchButton() {
        binding.ivSearchIcon.setOnClickListener {
            findNavController().navigate(HomeScreenFragmentDirections.homeScreenToSearchAction())
        }
    }

    private fun onMovieCardClick(movieId: Int) {
        findNavController().navigate(HomeScreenFragmentDirections.homeScreenToMovieDetailsAction(movieId))
    }

    private fun onMovieCardHold(model: MovieDetailsModel, view: RecyclerView) {
        viewModel.handleMovieCardHold(model)
        (view.adapter as? MovieAdapter)?.modifyElement(model)
    }


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