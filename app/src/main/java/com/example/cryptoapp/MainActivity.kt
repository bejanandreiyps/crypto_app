package com.example.cryptoapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showCryptos()
        setOnClickButtons()
    }

    fun readJSONFromAsset(): String? {
        var json: String? = null
        try {
            val  inputStream:InputStream = assets.open("input_data.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun getJSONObjects(): List<Crypto> {
        val jsonData = readJSONFromAsset()
        val cryptoList = object : TypeToken<List<Crypto>>() {}.type
        return Gson().fromJson(jsonData, cryptoList)
    }

    @SuppressLint("SetTextI18n")
    fun showCryptos() {
        //stiu ca nu i deloc bine cum am scris aici dar altfel n am reusit
        //data binding
        //toString in model
        val cryptoObjects: List<Crypto> = getJSONObjects()
        val textView1:TextView = findViewById(R.id.coin_text_1)
        val textView2:TextView = findViewById(R.id.coin_text_2)
        val textView3:TextView = findViewById(R.id.coin_text_3)
        textView1.text = "ID: ${cryptoObjects[0].id}\n" +
                "Name: ${cryptoObjects[0].name}\n" +
                "Symbol: ${cryptoObjects[0].symbol}\n" +
                "Rank: ${cryptoObjects[0].rank}\n" +
                "New: ${cryptoObjects[0].isNew}\n" +
                "Active: ${cryptoObjects[0].isActive}\n" +
                "Type: ${cryptoObjects[0].type}"
        textView2.text = "ID: ${cryptoObjects[1].id}\n" +
                "Name: ${cryptoObjects[1].name}\n" +
                "Symbol: ${cryptoObjects[1].symbol}\n" +
                "Rank: ${cryptoObjects[1].rank}\n" +
                "New: ${cryptoObjects[1].isNew}\n" +
                "Active: ${cryptoObjects[1].isActive}\n" +
                "Type: ${cryptoObjects[1].type}"
        textView3.text = "ID: ${cryptoObjects[2].id}\n" +
                "Name: ${cryptoObjects[2].name}\n" +
                "Symbol: ${cryptoObjects[2].symbol}\n" +
                "Rank: ${cryptoObjects[2].rank}\n" +
                "New: ${cryptoObjects[2].isNew}\n" +
                "Active: ${cryptoObjects[2].isActive}\n" +
                "Type: ${cryptoObjects[2].type}"
    }

    fun setOnClickButtons() {
        val btcBtnClick = findViewById<Button>(R.id.btc_btn_click)
        btcBtnClick.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)
        }
        val ethBtnClick = findViewById<Button>(R.id.eth_btn_click)
        ethBtnClick.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)
        }
        val usdtBtnClick = findViewById<Button>(R.id.usdt_btn_click)
        usdtBtnClick.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)
        }
    }
}