package com.example.cryptoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoapp.databinding.CardLayoutBinding
import com.example.cryptoapp.databinding.MovieStarsLayoutBinding
import com.example.cryptoapp.domain.AffirmationModel
import com.example.cryptoapp.domain.stars.ActorModel
import com.example.cryptoapp.domain.stars.MovieStarModel

class MovieStarsAdapter : RecyclerView.Adapter<MovieStarsAdapter.MovieStarViewHolder>() {
   var movieStarList = listOf<ActorModel>()

   inner class MovieStarViewHolder(private val binding: MovieStarsLayoutBinding): RecyclerView.ViewHolder(binding.root) {
      fun bind(item: ActorModel) {
         val endpoint = "https://image.tmdb.org/t/p/w500"
         Glide.with(binding.root.context).load(endpoint + item.profilePath).circleCrop()
            .into(binding.ivMovieStar)
         binding.tvMovieStarName.text = item.name
         binding.tvMovieStarName.isSelected = true
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieStarViewHolder {
      val binding = MovieStarsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return MovieStarViewHolder(binding)
   }

   override fun onBindViewHolder(holder: MovieStarViewHolder, position: Int) {
      holder.bind(movieStarList[position])
   }

   override fun getItemCount(): Int {
      return movieStarList.size
   }
}