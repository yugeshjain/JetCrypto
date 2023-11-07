package com.yugesh.jetcrypto.di

import com.yugesh.jetcrypto.domain.usecase.GetCoinDetailsUseCase
import com.yugesh.jetcrypto.domain.usecase.GetCoinPriceDetailsUseCase
import com.yugesh.jetcrypto.domain.usecase.GetCoinsListUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory<GetCoinsListUseCase> {
        GetCoinsListUseCase(coinsRepo = get())
    }

    factory<GetCoinDetailsUseCase> {
        GetCoinDetailsUseCase(coinsRepo = get())
    }

    factory<GetCoinPriceDetailsUseCase> {
        GetCoinPriceDetailsUseCase(coinsRepo = get())
    }
}
