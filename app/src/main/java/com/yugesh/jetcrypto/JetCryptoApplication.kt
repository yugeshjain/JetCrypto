package com.yugesh.jetcrypto

import android.app.Application
import com.yugesh.jetcrypto.di.networkModule
import com.yugesh.jetcrypto.di.repoModule
import com.yugesh.jetcrypto.di.serviceModule
import com.yugesh.jetcrypto.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class JetCryptoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@JetCryptoApplication)
            modules(
                networkModule,
                serviceModule,
                repoModule,
                viewmodelModule
            )
        }
    }
}
