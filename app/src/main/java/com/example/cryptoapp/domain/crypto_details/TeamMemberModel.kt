package com.example.cryptoapp.domain.crypto_details

data class TeamMemberModel (
    val id: String = "",
    val name: String = "",
    val position: String = ""
) {
    override fun toString(): String {
        return this.name + "\n" + this.position
    }
}