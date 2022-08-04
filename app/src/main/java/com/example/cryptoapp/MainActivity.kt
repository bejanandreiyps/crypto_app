package com.example.cryptoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoapp.adapter.RecyclerAdapter
import com.example.cryptoapp.databinding.ActivityMainBinding
import com.example.cryptoapp.domain.AffirmationModel

class MainActivity : AppCompatActivity(), RecyclerAdapter.ItemClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fileId = R.raw.input_data
        val cryptoList = FileUtils.getCryptoCoins(this, fileId)
        val images = loadAffirmationModelModels()

        val adapter = RecyclerAdapter()
        adapter.setClickListener(this)
        adapter.list = cryptoList
        adapter.images = images
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }

    override fun onClick(view: View?, position: Int) {
        val intent = Intent(this, CoinDetailActivity::class.java)
        val cryptoList = FileUtils.getCryptoCoins(this, R.raw.input_data)
        intent.putExtra("id_coin", cryptoList[position].id)
        startActivity(intent)
    }

    private fun loadAffirmationModelModels(): List<AffirmationModel> {
        return listOf<AffirmationModel>(
            AffirmationModel(R.string.btc_img, R.drawable.btc_img),
            AffirmationModel(R.string.ethereum_img, R.drawable.ethereum_img),
            AffirmationModel(R.string.usdt_img, R.drawable.usdt_img),
            AffirmationModel(R.string.avax_img, R.drawable.avax_img),
            AffirmationModel(R.string.doge_img, R.drawable.doge_img),
            AffirmationModel(R.string.dot_img, R.drawable.dot_img),
            AffirmationModel(R.string.okb_img, R.drawable.okb_img),
            AffirmationModel(R.string.qnt_img, R.drawable.qnt_img),
            AffirmationModel(R.string.rune_img, R.drawable.rune_img),
            AffirmationModel(R.string.tusd_img, R.drawable.tusd_img)
        )
    }
}
