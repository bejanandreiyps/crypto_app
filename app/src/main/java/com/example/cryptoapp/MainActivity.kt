package com.example.cryptoapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONTokener
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val jsonData = readJSONFromAsset()
        val cryptoList = object : TypeToken<List<Crypto>>() {}.type
        val cryptoObjects: List<Crypto> = Gson().fromJson(jsonData, cryptoList)
        showCryptos(cryptoObjects)
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

    @SuppressLint("SetTextI18n")
    fun showCryptos(cryptoObjects: List<Crypto>) {
        //stiu ca nu i deloc bine cum am scris aici dar altfel n am reusit
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
}