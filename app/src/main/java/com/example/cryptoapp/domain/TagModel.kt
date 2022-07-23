package com.example.cryptoapp.domain

import com.google.gson.annotations.SerializedName

data class TagModel (
    val id: String = "",
    val name: String = "",
    @SerializedName("coin_counter")
    val coinCounter: Int,
    @SerializedName("ico_counter")
    val icoCounter: Int
) {}
