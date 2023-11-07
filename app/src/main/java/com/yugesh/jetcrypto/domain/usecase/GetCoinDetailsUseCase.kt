package com.yugesh.jetcrypto.domain.usecase

import com.yugesh.jetcrypto.domain.model.CoinDetailsModel
import com.yugesh.jetcrypto.domain.repo.CoinsRepo

class GetCoinDetailsUseCase(private val coinsRepo: CoinsRepo) {

    suspend operator fun invoke(coinId: String): CoinDetailsModel {
        return coinsRepo.getCoinDetails(coinId = coinId)
    }
}
