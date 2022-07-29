package com.example.cryptoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoapp.databinding.ActivityMainBinding
import com.example.cryptoapp.domain.Affirmation

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fileId = R.raw.input_data
        val cryptoList = FileUtils.getCryptoCoins(this, fileId)
        val images = loadAffirmations()

        val adapter = RecyclerAdapter()
        adapter.list = cryptoList
        adapter.images = images
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }
    private fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.btc_img, R.drawable.btc_img),
            Affirmation(R.string.ethereum_img, R.drawable.ethereum_img),
            Affirmation(R.string.usdt_img, R.drawable.usdt_img),
            Affirmation(R.string.avax_img, R.drawable.avax_img),
            Affirmation(R.string.doge_img, R.drawable.doge_img),
            Affirmation(R.string.dot_img, R.drawable.dot_img),
            Affirmation(R.string.okb_img, R.drawable.okb_img),
            Affirmation(R.string.qnt_img, R.drawable.qnt_img),
            Affirmation(R.string.rune_img, R.drawable.rune_img),
            Affirmation(R.string.tusd_img, R.drawable.tusd_img)
        )
    }
}