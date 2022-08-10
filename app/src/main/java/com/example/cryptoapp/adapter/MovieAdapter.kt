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

class MovieAdapter(private val callback: (model: MovieDetailsModel) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    var movieList = listOf<MovieDetailsModel>()

    inner class MovieViewHolder(private val binding: MovieLayoutBinding, private val callback: (model: MovieDetailsModel)->Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieDetailsModel) {
            val endpoint = "https://image.tmdb.org/t/p/w500"

            Glide.with(binding.root.context).load(endpoint + item.posterPath)
                .into(binding.ivMoviePoster)

            if(item.voteAverage >= 8) {
                binding.ltvMustWatch.visibility = View.VISIBLE
            }

            if(item.isFavorite) {
                binding.cvMoviePoster.strokeColor = ContextCompat.getColor(binding.root.context, R.color.primaryColor)
                binding.cvMoviePoster.strokeWidth = 8
                binding.vHeart.visibility = View.VISIBLE
                binding.vHeartBorder.visibility = View.VISIBLE
            }

            binding.cvMoviePoster.setOnLongClickListener {
                binding.cvMoviePoster.strokeColor = ContextCompat.getColor(binding.root.context, R.color.primaryColor)
                if(item.isFavorite) {
                    callback(item)
                    item.isFavorite = false
                    binding.cvMoviePoster.strokeWidth = 0
                    binding.vHeart.visibility = View.INVISIBLE
                    binding.vHeartBorder.visibility = View.INVISIBLE
                } else {
                    callback(item)
                    item.isFavorite = true
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
        return MovieViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}