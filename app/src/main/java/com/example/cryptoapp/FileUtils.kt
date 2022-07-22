package com.example.cryptoapp

import android.content.Context
import com.example.cryptoapp.domain.CoinDetailsModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FileUtils {
    companion object {
        fun getCryptoCoins(context: Context, fileId: Int): List<CoinModel> {
            lateinit var jsonString: String
            try {
                jsonString = context.resources.openRawResource(fileId)
                    .bufferedReader()
                    .use { it.readText() }
            } catch (exception: Exception) {
                return emptyList()
            }

            val cryptoListType = object : TypeToken<List<CoinModel>>() {}.type
            return Gson().fromJson(jsonString, cryptoListType)
        }

        fun getCryptoCoinDetails(context: Context, fileId: Int): CoinDetailsModel {
            lateinit var jsonString: String
            try {
                jsonString = context.resources.openRawResource(fileId).bufferedReader().use { it.readText() }
            } catch (exception: Exception) {
                return CoinDetailsModel()
            }

            val cryptoListType = object : TypeToken<CoinDetailsModel>() {}.type
            return Gson().fromJson(jsonString, cryptoListType)
        }
    }
}