package com.yugesh.jetcrypto.di

import com.yugesh.jetcrypto.ui.screen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel {
        HomeViewModel(get())
    }
}
