package com.example.cryptoapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.FragmentMovieDetailsBinding
import com.example.cryptoapp.view_model.MovieApplication
import com.example.cryptoapp.view_model.MovieDetailsViewModel
import com.example.cryptoapp.view_model.MovieDetailsViewModelFactory

class MovieDetailsFragment: Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels()
    @SuppressLint("StaticFieldLeak")
    private lateinit var binding: FragmentMovieDetailsBinding

    private var movieId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        val safeArgs: MovieDetailsFragmentArgs by navArgs()
        movieId = safeArgs.movieId
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.movieDetailsViewModel = viewModel
        viewModel.movieImage.observe(viewLifecycleOwner) { newImage ->
            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500$newImage")
                .into(binding.ivMoviePosterDetails)
        }
        //factory viemodel(id).display
        viewModel.displayMovieDetails(movieId.toString())
    }
}