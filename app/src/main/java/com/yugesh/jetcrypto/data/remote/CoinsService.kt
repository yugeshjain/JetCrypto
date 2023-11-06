package com.yugesh.jetcrypto.data.remote

import com.yugesh.jetcrypto.domain.model.CoinDetailsModel
import com.yugesh.jetcrypto.domain.model.CoinModel

interface CoinsService {
    suspend fun getCoinList() : List<CoinModel>
    suspend fun getCoinDetails(coinId: String) : CoinDetailsModel
}
