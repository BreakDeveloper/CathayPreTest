package com.example.cathay

import android.app.Application
import com.example.cathay.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class App : Application() {

    companion object {
        lateinit var instance: App
        const val accessToken = "5e8267d989e9775b97ea3813afed66ed"
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        // Koin
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(instance)
            modules(appModule)
        }
    }
}