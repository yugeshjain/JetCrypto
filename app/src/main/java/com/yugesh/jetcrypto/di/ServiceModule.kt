package com.yugesh.jetcrypto.di

import com.yugesh.jetcrypto.data.remote.CoinsService
import com.yugesh.jetcrypto.data.remote.CoinsServiceImpl
import org.koin.dsl.module

val serviceModule = module {
    single<CoinsService> {
        CoinsServiceImpl(get())
    }
}
