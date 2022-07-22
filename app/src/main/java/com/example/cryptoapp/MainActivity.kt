package com.example.cryptoapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fileId = R.raw.input_data
        val cryptoList = FileUtils.getCryptoCoins(this, fileId)
        showCryptos(cryptoList)
        setOnClickListeners(cryptoList)
    }

    @SuppressLint("SetTextI18n")
    fun showCryptos(coinModelObjects: List<CoinModel>) {
        //stiu ca nu i deloc bine cum am scris aici dar altfel n am reusit
        //data binding
        //toString in model
        val textView1:TextView = findViewById(R.id.coin_text_1)
        val textView2:TextView = findViewById(R.id.coin_text_2)
        val textView3:TextView = findViewById(R.id.coin_text_3)
        textView1.text = "ID: ${coinModelObjects[0].id}\n" +
                "Name: ${coinModelObjects[0].name}\n" +
                "Symbol: ${coinModelObjects[0].symbol}\n" +
                "Rank: ${coinModelObjects[0].rank}\n" +
                "New: ${coinModelObjects[0].isNew}\n" +
                "Active: ${coinModelObjects[0].isActive}\n" +
                "Type: ${coinModelObjects[0].type}"
        textView2.text = "ID: ${coinModelObjects[1].id}\n" +
                "Name: ${coinModelObjects[1].name}\n" +
                "Symbol: ${coinModelObjects[1].symbol}\n" +
                "Rank: ${coinModelObjects[1].rank}\n" +
                "New: ${coinModelObjects[1].isNew}\n" +
                "Active: ${coinModelObjects[1].isActive}\n" +
                "Type: ${coinModelObjects[1].type}"
        textView3.text = "ID: ${coinModelObjects[2].id}\n" +
                "Name: ${coinModelObjects[2].name}\n" +
                "Symbol: ${coinModelObjects[2].symbol}\n" +
                "Rank: ${coinModelObjects[2].rank}\n" +
                "New: ${coinModelObjects[2].isNew}\n" +
                "Active: ${coinModelObjects[2].isActive}\n" +
                "Type: ${coinModelObjects[2].type}"
    }

    fun setOnClickListeners(coinModelObjects: List<CoinModel>) {
        val btcBtnClick = findViewById<Button>(R.id.btc_btn_click)
        btcBtnClick.setOnClickListener {
            val intent = Intent(this, CoinDetailActivity::class.java)
            intent.putExtra("id_coin", coinModelObjects[0].id)
            startActivity(intent)
        }
        val ethBtnClick = findViewById<Button>(R.id.eth_btn_click)
        ethBtnClick.setOnClickListener {
            val intent = Intent(this, CoinDetailActivity::class.java)
            intent.putExtra("id_coin", coinModelObjects[1].id)
            startActivity(intent)
        }
        val usdtBtnClick = findViewById<Button>(R.id.usdt_btn_click)
        usdtBtnClick.setOnClickListener {
            val intent = Intent(this, CoinDetailActivity::class.java)
            intent.putExtra("id_coin", coinModelObjects[2].id)
            startActivity(intent)
        }
    }
}