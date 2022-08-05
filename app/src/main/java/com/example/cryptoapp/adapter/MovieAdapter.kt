package com.example.cryptoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoapp.databinding.MovieLayoutBinding
import com.example.cryptoapp.domain.top_rated_movies.MovieDetailsModel

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    var movieList = listOf<MovieDetailsModel>()

    inner class MovieViewHolder(private val binding: MovieLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieDetailsModel) {
            val endpoint = "https://image.tmdb.org/t/p/w500"
            Glide.with(binding.root.context).load(endpoint + item.posterPath)
                .into(binding.ivMoviePoster)
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