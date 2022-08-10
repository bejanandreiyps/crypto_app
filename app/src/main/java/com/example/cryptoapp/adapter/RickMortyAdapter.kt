package com.example.cryptoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoapp.databinding.RickmortyLayoutBinding
import com.example.cryptoapp.domain.RickMortyCharacterModel

class RickMortyAdapter: RecyclerView.Adapter<RickMortyAdapter.ViewHolder>() {
    var rmList = listOf<RickMortyCharacterModel>()

    inner class ViewHolder(private val binding: RickmortyLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RickMortyCharacterModel) {
            Glide.with(binding.root.context).load(item.image).circleCrop().into(binding.ivRMImage)
            binding.tvRMName.text = item.name
            binding.tvRMName.isSelected = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RickmortyLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rmList[position])
    }

    override fun getItemCount(): Int {
        return rmList.size
    }
}