package com.yugesh.jetcrypto.domain.repo

import com.yugesh.jetcrypto.domain.model.CoinModel

interface CoinsRepo {
    suspend fun getCoinList(): List<CoinModel>
}
