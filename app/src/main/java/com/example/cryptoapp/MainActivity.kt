package com.example.cryptoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONTokener
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var jsonData = readJSONFromAsset()
        val cryptoList = object : TypeToken<List<Crypto>>() {}.type
        var cryptoObjects: List<Crypto> = Gson().fromJson(jsonData, cryptoList)
        for(i in cryptoObjects.indices) {
            Log.i("id", cryptoObjects[i].id)
            Log.i("name", cryptoObjects[i].name)
            Log.i("symbol", cryptoObjects[i].symbol)
            Log.i("rank", cryptoObjects[i].rank.toString())
            Log.i("isNew", cryptoObjects[i].isNew.toString())
            Log.i("isActive", cryptoObjects[i].isActive.toString())
            Log.i("isActive", cryptoObjects[i].type)
        }
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
}