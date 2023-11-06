package com.yugesh.jetcrypto.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDetailsModel(
    @SerialName("contract")
    val contract: String? = null, // string
    @SerialName("contracts")
    val contracts: List<Contract>? = null,
    @SerialName("description")
    val description: String? = null, // Bitcoin is a cryptocurrency and worldwide payment system. It is the first decentralized digital currency, as the system works without a central bank or single administrator.
    @SerialName("development_status")
    val developmentStatus: String? = null, // Working product
    @SerialName("first_data_at")
    val firstDataAt: String? = null, // 2018-10-03T11:48:19Z
    @SerialName("hardware_wallet")
    val hardwareWallet: Boolean? = null, // true
    @SerialName("hash_algorithm")
    val hashAlgorithm: String? = null, // SHA256
    @SerialName("id")
    val id: String? = null, // btc-bitcoin
    @SerialName("is_active")
    val isActive: Boolean? = null, // true
    @SerialName("is_new")
    val isNew: Boolean? = null, // false
    @SerialName("last_data_at")
    val lastDataAt: String? = null, // 2019-05-03T11:00:00
    @SerialName("links")
    val links: Links? = null,
    @SerialName("links_extended")
    val linksExtended: List<LinksExtended>? = null,
    @SerialName("logo")
    val logo: String? = null, // https://static.coinpaprika.com/coin/bnb-binance-coin/logo.png
    @SerialName("message")
    val message: String? = null, // string
    @SerialName("name")
    val name: String? = null, // Bitcoin
    @SerialName("open_source")
    val openSource: Boolean? = null, // true
    @SerialName("org_structure")
    val orgStructure: String? = null, // Decentralized
    @SerialName("parent")
    val parent: Parent? = null,
    @SerialName("platform")
    val platform: String? = null, // string
    @SerialName("proof_type")
    val proofType: String? = null, // Proof of work
    @SerialName("rank")
    val rank: Int? = null, // 1
    @SerialName("started_at")
    val startedAt: String? = null, // 2009-01-03T00:00:00Z
    @SerialName("symbol")
    val symbol: String? = null, // BTC
    @SerialName("tags")
    val tags: List<Tag>? = null,
    @SerialName("team")
    val team: List<Team>? = null,
    @SerialName("type")
    val type: String? = null, // coin
    @SerialName("whitepaper")
    val whitePaper: Whitepaper? = null
)

@Serializable
data class Contract(
    @SerialName("contract")
    val contract: String? = null, // string
    @SerialName("platform")
    val platform: String? = null, // string
    @SerialName("type")
    val type: String? = null // string
)

@Serializable
data class Links(
    @SerialName("explorer")
    val explorer: List<String>? = null,
    @SerialName("facebook")
    val facebook: List<String>? = null,
    @SerialName("medium")
    val medium: List<String>? = null,
    @SerialName("reddit")
    val reddit: List<String>? = null,
    @SerialName("source_code")
    val sourceCode: List<String>? = null,
    @SerialName("website")
    val website: List<String>? = null,
    @SerialName("youtube")
    val youtube: List<String>? = null
)

@Serializable
data class LinksExtended(
    @SerialName("stats")
    val stats: Stats? = null,
    @SerialName("type")
    val type: String? = null, // explorer
    @SerialName("url")
    val url: String? = null // http://blockchain.com/explorer
)

@Serializable
data class Parent(
    @SerialName("id")
    val id: String? = null, // eth-ethereum
    @SerialName("name")
    val name: String? = null, // Ethereum
    @SerialName("symbol")
    val symbol: String? = null // ETH
)

@Serializable
data class Stats(
    @SerialName("contributors")
    val contributors: Int? = null, // 730
    @SerialName("stars")
    val stars: Int? = null, // 36613
    @SerialName("subscribers")
    val subscribers: Int? = null // 1009135
)

@Serializable
data class Tag(
    @SerialName("coin_counter")
    val coinCounter: Int? = null, // 160
    @SerialName("ico_counter")
    val icoCounter: Int? = null, // 80
    @SerialName("id")
    val id: String? = null, // blockchain-service
    @SerialName("name")
    val name: String? = null // Blockchain Service
)

@Serializable
data class Team(
    @SerialName("id")
    val id: String? = null, // vitalik-buterin
    @SerialName("name")
    val name: String? = null, // Vitalik Buterin
    @SerialName("position")
    val position: String? = null // Author
)

@Serializable
data class Whitepaper(
    @SerialName("link")
    val link: String? = null, // https://static.coinpaprika.com/storage/cdn/whitepapers/215.pdf
    @SerialName("thumbnail")
    val thumbnail: String? = null // https://static.coinpaprika.com/storage/cdn/whitepapers/217.jpg
)
