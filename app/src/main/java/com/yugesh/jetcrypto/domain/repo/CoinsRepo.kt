package com.yugesh.jetcrypto.domain.repo

import com.yugesh.jetcrypto.domain.model.CoinDetailsModel
import com.yugesh.jetcrypto.domain.model.CoinModel

interface CoinsRepo {
    suspend fun getCoinList(): List<CoinModel>
    suspend fun getCoinDetails(coinId: String): CoinDetailsModel
}
