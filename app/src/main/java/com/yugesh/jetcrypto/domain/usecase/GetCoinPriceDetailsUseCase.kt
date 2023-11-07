package com.yugesh.jetcrypto.domain.usecase

import com.yugesh.jetcrypto.domain.model.CoinPriceDetailsModel
import com.yugesh.jetcrypto.domain.repo.CoinsRepo

class GetCoinPriceDetailsUseCase(private val coinsRepo: CoinsRepo) {

    suspend operator fun invoke(coinId: String): CoinPriceDetailsModel {
        return coinsRepo.getCoinPriceDetails(coinId = coinId)
    }
}
