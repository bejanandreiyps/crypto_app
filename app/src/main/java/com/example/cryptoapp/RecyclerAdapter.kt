package com.example.cryptoapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.databinding.CardLayoutBinding
import com.example.cryptoapp.domain.Affirmation
import com.example.cryptoapp.domain.CoinModel

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    lateinit var listener: ItemClickListener
    var list = listOf<CoinModel>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var images = listOf<Affirmation>()

    inner class ViewHolder(private val binding: CardLayoutBinding, listener: ItemClickListener): RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {
        @SuppressLint("ResourceAsColor")
        fun bind(item: CoinModel, affirmation: Affirmation) {
            binding.coinId.text = item.toStringID()
            binding.coinPropertiesColumn1.text = item.toStringColumn1()
            binding.coinPropertiesColumn2.text = item.toStringColumn2()
            binding.image.setImageResource(affirmation.imageResourceId)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onClick(v, adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], images[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ItemClickListener {
        fun onClick(view: View?, position: Int)
    }

    fun setClickListener(listenerItem: ItemClickListener) {
        listener = listenerItem
    }
}
