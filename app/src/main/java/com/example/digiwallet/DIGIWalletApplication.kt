package com.example.digiwallet

import android.app.Application
import com.example.core.auth.di.authModule
import com.example.core.data.di.dataModule
import com.example.core.domain.di.domainModule
import com.example.digiwallet.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class DIGIWalletApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DIGIWalletApplication)
            androidLogger(Level.DEBUG)
            modules(
                appModule,
                domainModule,
                dataModule,
                authModule
            )
        }
    }
}
