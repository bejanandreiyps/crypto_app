package com.example.cryptoapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.cryptoapp.databinding.TeamMebersItemBinding
import com.example.cryptoapp.domain.LVItemMemberModel

class ListViewAdapter (context: Context, private val items: List<LVItemMemberModel>) :
    ArrayAdapter<LVItemMemberModel>(context, 0, items) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = TeamMebersItemBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.tvMemberName.text = items[position].name
        binding.tvMemberPosition.text = items[position].position
        return binding.root
    }
}