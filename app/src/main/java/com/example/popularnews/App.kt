package com.example.popularnews

import android.app.Application
import com.example.popularnews.di.networkModule
import com.example.popularnews.di.repositoryModule
import com.example.popularnews.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}