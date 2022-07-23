package com.example.cryptoapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoapp.databinding.ActivityMainBinding
import com.example.cryptoapp.domain.CoinModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fileId = R.raw.input_data
        val cryptoList = FileUtils.getCryptoCoins(this, fileId)

        showCryptos(cryptoList)
        setOnClickListeners(cryptoList)
    }

    @SuppressLint("SetTextI18n")
    fun showCryptos(coinModelObjects: List<CoinModel>) {
        binding.coinText1.text = "ID: ${coinModelObjects[0].id}\n" +
                "Name: ${coinModelObjects[0].name}\n" +
                "Symbol: ${coinModelObjects[0].symbol}\n" +
                "Rank: ${coinModelObjects[0].rank}\n" +
                "New: ${coinModelObjects[0].isNew}\n" +
                "Active: ${coinModelObjects[0].isActive}\n" +
                "Type: ${coinModelObjects[0].type}"
        binding.coinText2.text = "ID: ${coinModelObjects[1].id}\n" +
                "Name: ${coinModelObjects[1].name}\n" +
                "Symbol: ${coinModelObjects[1].symbol}\n" +
                "Rank: ${coinModelObjects[1].rank}\n" +
                "New: ${coinModelObjects[1].isNew}\n" +
                "Active: ${coinModelObjects[1].isActive}\n" +
                "Type: ${coinModelObjects[1].type}"
        binding.coinText3.text = "ID: ${coinModelObjects[2].id}\n" +
                "Name: ${coinModelObjects[2].name}\n" +
                "Symbol: ${coinModelObjects[2].symbol}\n" +
                "Rank: ${coinModelObjects[2].rank}\n" +
                "New: ${coinModelObjects[2].isNew}\n" +
                "Active: ${coinModelObjects[2].isActive}\n" +
                "Type: ${coinModelObjects[2].type}"
    }

    private fun setOnClickListeners(coinModelObjects: List<CoinModel>) {
        binding.btcBtnClick.setOnClickListener {
            val intent = Intent(this, CoinDetailActivity::class.java)
            intent.putExtra("id_coin", coinModelObjects[0].id)
            startActivity(intent)
        }
        binding.ethBtnClick.setOnClickListener {
            val intent = Intent(this, CoinDetailActivity::class.java)
            intent.putExtra("id_coin", coinModelObjects[1].id)
            startActivity(intent)
        }
        binding.usdtBtnClick.setOnClickListener {
            val intent = Intent(this, CoinDetailActivity::class.java)
            intent.putExtra("id_coin", coinModelObjects[2].id)
            startActivity(intent)
        }
    }
}