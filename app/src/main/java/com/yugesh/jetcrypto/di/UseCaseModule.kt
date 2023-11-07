package com.yugesh.jetcrypto.di

import com.yugesh.jetcrypto.domain.usecase.GetCoinDetailsUseCase
import com.yugesh.jetcrypto.domain.usecase.GetCoinPriceDetailsUseCase
import com.yugesh.jetcrypto.domain.usecase.GetCoinsListUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single<GetCoinsListUseCase> {
        GetCoinsListUseCase(coinsRepo = get())
    }

    single<GetCoinDetailsUseCase> {
        GetCoinDetailsUseCase(coinsRepo = get())
    }

    single<GetCoinPriceDetailsUseCase> {
        GetCoinPriceDetailsUseCase(coinsRepo = get())
    }
}
