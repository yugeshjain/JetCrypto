package com.yugesh.jetcrypto.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinPriceDetailsModel(
    @SerialName("beta_value")
    val betaValue: Double? = null, // 0.735327
    @SerialName("circulating_supply")
    val circulatingSupply: Long? = null, // 17007062
    @SerialName("first_data_at")
    val firstDataAt: String? = null, // 2010-11-14T07:20:41Z
    @SerialName("id")
    val id: String? = null, // btc-bitcoin
    @SerialName("last_updated")
    val lastUpdated: String? = null, // 2018-11-14T07:20:41Z
    @SerialName("max_supply")
    val maxSupply: Int? = null, // 21000000
    @SerialName("name")
    val name: String? = null, // Bitcoin
    @SerialName("quotes")
    val quotes: Quotes? = null,
    @SerialName("rank")
    val rank: Int? = null, // 1
    @SerialName("symbol")
    val symbol: String? = null, // BTC
    @SerialName("total_supply")
    val totalSupply: Long? = null // 17007062
)

@Serializable
data class Quotes(
    @SerialName("BTC")
    val btc: BTC? = null,
    @SerialName("USD")
    val usd: USD? = null
)

@Serializable
data class USD(
    @SerialName("ath_date")
    val athDate: String? = null, // 2017-12-17T12:19:00Z
    @SerialName("ath_price")
    val athPrice: Double? = null, // 20089
    @SerialName("market_cap")
    val marketCap: Long? = null, // 91094433242
    @SerialName("market_cap_change_24h")
    val marketCapChange24h: Double? = null, // 1.6
    @SerialName("percent_change_12h")
    val percentChange12h: Double? = null, // -0.09
    @SerialName("percent_change_15m")
    val percentChange15m: Double? = null, // 0
    @SerialName("percent_change_1h")
    val percentChange1h: Double? = null, // 0
    @SerialName("percent_change_1y")
    val percentChange1y: Double? = null, // -37.99
    @SerialName("percent_change_24h")
    val percentChange24h: Double? = null, // 1.59
    @SerialName("percent_change_30d")
    val percentChange30d: Double? = null, // 27.39
    @SerialName("percent_change_30m")
    val percentChange30m: Double? = null, // 0
    @SerialName("percent_change_6h")
    val percentChange6h: Double? = null, // 0
    @SerialName("percent_change_7d")
    val percentChange7d: Double? = null, // 0.28
    @SerialName("percent_from_price_ath")
    val percentFromPriceAth: Double? = null, // -74.3
    @SerialName("price")
    val price: Double? = null, // 5162.15941296
    @SerialName("volume_24h")
    val volume24h: Double? = null, // 7304207651.1585
    @SerialName("volume_24h_change_24h")
    val volume24hChange24h: Double? = null // -2.5
)

@Serializable
data class BTC(
    @SerialName("ath_date")
    val athDate: String? = null, // null
    @SerialName("ath_price")
    val athPrice: Int? = null, // null
    @SerialName("market_cap")
    val marketCap: Int? = null, // 17646575
    @SerialName("market_cap_change_24h")
    val marketCapChange24h: Double? = null, // 0.01
    @SerialName("percent_change_12h")
    val percentChange12h: Double? = null, // 0
    @SerialName("percent_change_15m")
    val percentChange15m: Double? = null, // 0
    @SerialName("percent_change_1h")
    val percentChange1h: Double? = null, // 0
    @SerialName("percent_change_1y")
    val percentChange1y: Double? = null, // 0
    @SerialName("percent_change_24h")
    val percentChange24h: Double? = null, // 0
    @SerialName("percent_change_30d")
    val percentChange30d: Double? = null, // 0
    @SerialName("percent_change_30m")
    val percentChange30m: Double? = null, // 0
    @SerialName("percent_change_6h")
    val percentChange6h: Double? = null, // 0
    @SerialName("percent_change_7d")
    val percentChange7d: Double? = null, // 0
    @SerialName("percent_from_price_ath")
    val percentFromPriceAth: Int? = null, // null
    @SerialName("price")
    val price: Int? = null, // 1
    @SerialName("volume_24h")
    val volume24h: Double? = null, // 1414951.9739396
    @SerialName("volume_24h_change_24h")
    val volume24hChange24h: Double? = null // -4.03
)
