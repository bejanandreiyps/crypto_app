package com.example.cryptoapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.FragmentMovieDetailsBinding
import com.example.cryptoapp.databinding.MovieLayoutBinding
import com.example.cryptoapp.domain.movie.MovieDetailsModel
import com.example.cryptoapp.fragment.HomeScreenFragmentDirections

class MovieAdapter(
    private val callbackHold: (model: MovieDetailsModel) -> Unit,
    private val callbackClick: (movieId: Int) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    var movieList = listOf<MovieDetailsModel>()

    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(
        private val binding: MovieLayoutBinding,
        private val callbackHold: (model: MovieDetailsModel) -> Unit,
        private val callbackClick: (model: Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieDetailsModel) {
            Glide.with(binding.root.context).load("https://image.tmdb.org/t/p/w500" + item.posterPath)
                .into(binding.ivMoviePoster)

            if(item.voteAverage >= 8) {
                binding.ltvMustWatch.visibility = View.VISIBLE
            }

            if(item.isFavorite) {
                borderVisible(ContextCompat.getColor(binding.root.context, R.color.primaryColor))
            } else {
                borderInvisible()
            }

            binding.cvMoviePoster.setOnLongClickListener {
                callbackHold(item)
                true
            }
            binding.cvMoviePoster.setOnClickListener {
                callbackClick(item.id)
            }
        }

        private fun borderVisible(color: Int) {
            binding.cvMoviePoster.strokeColor = color
            binding.cvMoviePoster.strokeWidth = 4
            binding.vHeart.visibility = View.VISIBLE
            binding.vHeartBorder.visibility = View.VISIBLE
        }

        private fun borderInvisible() {
            binding.cvMoviePoster.strokeWidth = 0
            binding.vHeart.visibility = View.INVISIBLE
            binding.vHeartBorder.visibility = View.INVISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, callbackHold, callbackClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun modifyElement(movieModel: MovieDetailsModel) {
        val pos = movieList.indexOf(movieModel)
        movieList = movieList.map {
            if(movieModel.id == it.id) {
                return@map it.copy(isFavorite = !it.isFavorite)
            } else {
                it
            }
        }
        notifyItemChanged(pos)
    }
}