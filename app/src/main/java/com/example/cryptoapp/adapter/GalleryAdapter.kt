package com.example.cryptoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoapp.databinding.GalleryImageBinding
import com.example.cryptoapp.domain.gallery.GalleryModel

class GalleryAdapter : ListAdapter<GalleryModel, GalleryAdapter.GalleryViewHolder>(object :
    DiffUtil.ItemCallback<GalleryModel>() {
    override fun areItemsTheSame(
        oldItem: GalleryModel,
        newItem: GalleryModel
    ) = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: GalleryModel,
        newItem: GalleryModel
    ) = oldItem == newItem
}) {

    inner class GalleryViewHolder(private val binding: GalleryImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: GalleryModel) {
            val endPoint = "https://image.tmdb.org/t/p/w500/"
            Glide.with(binding.root.context).load(endPoint + model.imageUrl).into(binding.ivGalleryImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = GalleryImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
