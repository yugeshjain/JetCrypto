package com.yugesh.jetcrypto.domain.repo

import com.yugesh.jetcrypto.domain.model.CoinDetailsModel
import com.yugesh.jetcrypto.domain.model.CoinModel
import com.yugesh.jetcrypto.domain.model.CoinPriceDetailsModel

interface CoinsRepo {
    suspend fun getCoinList(): List<CoinModel>
    suspend fun getCoinDetails(coinId: String): CoinDetailsModel
    suspend fun getCoinPriceDetails(coinId: String): CoinPriceDetailsModel
}
