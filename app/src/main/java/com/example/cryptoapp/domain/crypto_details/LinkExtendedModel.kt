package com.example.cryptoapp.domain.crypto_details

data class LinkExtendedModel (
    val url: String = "",
    val type: String = "",
    val stats: StatsModel = StatsModel()
) {}