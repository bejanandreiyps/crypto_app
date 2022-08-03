package com.example.cryptoapp.domain.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CredentialsModel (
    val username: String = "",
    val password: String = "",
    @SerialName("request_token")
    val requestToken: String = ""
) {}