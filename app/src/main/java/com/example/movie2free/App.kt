package com.example.movie2free

import android.app.Application
import com.example.movie2free.data.di.databaseModule
import com.example.movie2free.data.di.networkingModule
import com.example.movie2free.domain.di.interactionModule
import com.example.movie2free.domain.di.repositoryModule
import com.example.movie2free.presentation.view.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    private val appModules = listOf(viewModelModule)
    private val domainModule = listOf(interactionModule, repositoryModule)
    private val dataModule = listOf(networkingModule, databaseModule)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            if(BuildConfig.DEBUG) androidLogger(Level.ERROR)
            modules(appModules + domainModule + dataModule)
        }
    }
}