package com.yugesh.jetcrypto.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinModel(
    @SerialName("id")
    val id: String,
    @SerialName("is_active")
    val isActive: Boolean? = null,
    @SerialName("is_new")
    val isNew: Boolean? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("rank")
    val rank: Int? = null,
    @SerialName("symbol")
    val symbol: String? = null,
    @SerialName("type")
    val type: String? = null
)
