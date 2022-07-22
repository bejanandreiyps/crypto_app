package com.example.cryptoapp

data class CoinModel(
    val id:String,
    val name:String,
    val symbol:String,
    val rank:Int,
    val isNew:Boolean,
    val isActive:Boolean,
    val type:String
    ) {}

fun CoinModel.toCrypto(): CoinModel {
    return CoinModel (
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        isNew = isNew,
        isActive = isActive,
        type = type
    )
}