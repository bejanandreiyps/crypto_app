package com.example.cryptoapp.domain

import com.google.gson.annotations.SerializedName

data class CoinDetailsModel(
    val description: String = "",
    @SerializedName("development_status")
    val developmentStatus: String = "",
    @SerializedName("first_date_et")
    val firstDateAt: String = "",
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean = false,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String = "",
    val id: String = "",
    @SerializedName("is_active")
    val isActive: Boolean = false,
    @SerializedName("is_new")
    val isNew: Boolean = false,
    @SerializedName("links_extended")
    val linkModels: List<LinkModel> = emptyList(),
    val linkExtendedModel: List<LinkExtendedModel> = emptyList(),
    val message: String = "",
    val name: String = "",
    @SerializedName("org_structure")
    val orgStructure: String = "",
    @SerializedName("proof_type")
    val proofType: String = "",
    val rank: Int = -1,
    @SerializedName("started_at")
    val startedAt: String = "",
    val symbol: String = "",
    val tagModels: List<TagModel> = emptyList(),
    val team: List<TeamMemberModel> = emptyList(),
    val type: String = "",
    val whitePaperModel: WhitePaperModel = WhitePaperModel()
    )
{}