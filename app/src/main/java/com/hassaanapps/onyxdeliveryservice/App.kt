package com.hassaanapps.onyxdeliveryservice

import android.app.Application
import com.hassaanapps.onyxdeliveryservice.di.homeModule
import com.hassaanapps.onyxdeliveryservice.di.localModule
import com.hassaanapps.onyxdeliveryservice.di.loginModule
import com.hassaanapps.onyxdeliveryservice.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(retrofitModule, loginModule, homeModule, localModule)
        }
    }
}