package com.example.cryptoapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.cryptoapp.databinding.TagItemBinding
import com.example.cryptoapp.domain.crypto_details.GridItemTagModel

class GridAdapter(context: Context, private val items: List<GridItemTagModel>) :
    ArrayAdapter<GridItemTagModel>(context, 0, items) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = TagItemBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.tvTagItem.text = items[position].text
        return binding.root
    }
}