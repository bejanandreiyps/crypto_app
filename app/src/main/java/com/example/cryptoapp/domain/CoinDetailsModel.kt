package com.example.cryptoapp.domain

import com.google.gson.annotations.SerializedName
import java.util.*

data class CoinDetailsModel(
    val id: String = "",
    val name: String = "",
    val symbol: String = "",
    val rank: Int = 0,
    @SerializedName("is_new")
    val isNew: Boolean = false,
    @SerializedName("is_active")
    val isActive: Boolean = false,
    val type: String = "",
    val tags: List<TagModel> = emptyList(),
    val team: List<TeamMemberModel> = emptyList(),
    val description: String = "",
    val message: String = "",
    @SerializedName("open_source")
    val openSource: Boolean = false,
    @SerializedName("started_at")
    val startedAt: String = "",
    @SerializedName("development_status")
    val developmentStatus: String = "",
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean = false,
    @SerializedName("proof_type")
    val proofType: String = "",
    @SerializedName("org_structure")
    val orgStructure: String = "",
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String = "",
    val links: LinkModel = LinkModel(),
    @SerializedName("links_extended")
    val linksExtended: List<LinkExtendedModel> = emptyList(),
    val whitePaperModel: WhitePaperModel = WhitePaperModel(),
    @SerializedName("first_data_at")
    val firstDateAt: Date = Date(),
    @SerializedName("last_data_at")
    val lastDateAt: Date = Date()
    )
{}