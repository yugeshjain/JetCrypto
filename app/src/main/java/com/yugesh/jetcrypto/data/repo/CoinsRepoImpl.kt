package com.yugesh.jetcrypto.data.repo

import com.yugesh.jetcrypto.data.remote.CoinsService
import com.yugesh.jetcrypto.domain.model.CoinDetailsModel
import com.yugesh.jetcrypto.domain.model.CoinModel
import com.yugesh.jetcrypto.domain.model.CoinPriceDetailsModel
import com.yugesh.jetcrypto.domain.repo.CoinsRepo

class CoinsRepoImpl(
    private val coinsService: CoinsService
) : CoinsRepo {

    override suspend fun getCoinList(): List<CoinModel> {
        return coinsService.getCoinList()
    }

    override suspend fun getCoinDetails(coinId: String): CoinDetailsModel {
        return coinsService.getCoinDetails(coinId = coinId)
    }

    override suspend fun getCoinPriceDetails(coinId: String): CoinPriceDetailsModel {
        return coinsService.getCoinPriceDetails(coinId = coinId)
    }
}
