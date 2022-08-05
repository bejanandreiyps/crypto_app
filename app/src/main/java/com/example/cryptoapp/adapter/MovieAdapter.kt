package com.example.cryptoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.MovieLayoutBinding
import com.example.cryptoapp.domain.movie.MovieDetailsModel

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    var movieList = listOf<MovieDetailsModel>()

    inner class MovieViewHolder(private val binding: MovieLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieDetailsModel) {
            val endpoint = "https://image.tmdb.org/t/p/w500"

            Glide.with(binding.root.context).load(endpoint + item.posterPath)
                .into(binding.ivMoviePoster)

            if(item.voteAverage >= 8) {
                binding.ltvMustWatch.visibility = View.VISIBLE
            }

            var isFavorite = false
            binding.cvMoviePoster.setOnLongClickListener {
                binding.cvMoviePoster.strokeColor = ContextCompat.getColor(binding.root.context, R.color.primaryColor)
                if(isFavorite) {
                    isFavorite = false
                    binding.cvMoviePoster.strokeWidth = 0
                    binding.vHeart.visibility = View.INVISIBLE
                    binding.vHeartBorder.visibility = View.INVISIBLE
                } else {
                    isFavorite = true
                    binding.cvMoviePoster.strokeWidth = 8
                    binding.vHeart.visibility = View.VISIBLE
                    binding.vHeartBorder.visibility = View.VISIBLE
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}