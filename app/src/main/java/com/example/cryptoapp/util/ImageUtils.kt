package com.example.cryptoapp.util

import com.example.cryptoapp.R

class ImageUtils {
    companion object {
        fun loadAffirmationModelModels(): List<AffirmationModel> {
            return listOf(
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
}