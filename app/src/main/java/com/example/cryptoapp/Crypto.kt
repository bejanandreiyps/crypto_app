package com.example.cryptoapp

data class Crypto(
    val id:String,
    val name:String,
    val symbol:String,
    val rank:Int,
    val isNew:Boolean,
    val isActive:Boolean,
    val type:String
    ) {}
