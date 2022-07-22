package com.example.cryptoapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CoinDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coin_detail_activity)

        val idCoin = intent.getStringExtra("id_coin")
        val fileName = idCoin?.replace("-", "_")
        val fileId = resources.getIdentifier(fileName, "raw", packageName)
        val details = FileUtils.getCryptoCoinDetails(this, fileId)

        val header = findViewById<TextView>(R.id.header)
        val headerString = "${details.rank}. ${details.name} (${details.symbol})"
        header.text = headerString

        val description = findViewById<TextView>(R.id.description)
        val descriptionString = details.description
        description.text = descriptionString
    }
}