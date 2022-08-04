package com.example.cryptoapp.domain.crypto

data class CoinModel(
    val id:String,
    val name:String,
    val symbol:String,
    val rank:Int,
    val isNew:Boolean,
    val isActive:Boolean,
    val type:String
    ) {
    fun toStringID() : String {
        return "ID: ${this.id}"
    }
    fun toStringColumn1() : String {
        return "Name: ${this.name}\n" +
                "Symbol: ${this.symbol}\n" +
                "Rank: ${this.rank}"

    }
    fun toStringColumn2() : String {
        return "New: ${this.isNew} \n" +
                "Active: ${this.isActive}\n" +
                "Type: ${this.type}"
    }
}
