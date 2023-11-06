package com.yugesh.jetcrypto.di

import com.yugesh.jetcrypto.data.repo.CoinsRepoImpl
import com.yugesh.jetcrypto.domain.repo.CoinsRepo
import org.koin.dsl.module

val repoModule = module {
    single<CoinsRepo> {
        CoinsRepoImpl(get())
    }
}
