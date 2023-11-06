package com.yugesh.jetcrypto.data.repo

import com.yugesh.jetcrypto.data.remote.CoinsService
import com.yugesh.jetcrypto.domain.model.CoinModel
import com.yugesh.jetcrypto.domain.repo.CoinsRepo

class CoinsRepoImpl(
    private val coinsService: CoinsService
) : CoinsRepo {

    override suspend fun getCoinList(): List<CoinModel> {
        return coinsService.getCoinList()
    }
}
