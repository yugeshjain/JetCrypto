package com.yugesh.jetcrypto.domain.usecase

import com.yugesh.jetcrypto.domain.model.CoinModel
import com.yugesh.jetcrypto.domain.repo.CoinsRepo

class GetCoinsListUseCase(private val coinsRepo: CoinsRepo) {

    suspend operator fun invoke(): List<CoinModel> {
        return coinsRepo.getCoinList()
    }
}
