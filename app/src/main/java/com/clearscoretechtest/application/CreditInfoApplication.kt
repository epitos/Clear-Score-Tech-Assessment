package com.clearscoretechtest.application

import android.app.Application
import com.clearscoretechtest.di.module.appModule
import com.clearscoretechtest.di.module.repositoryModule
import com.clearscoretechtest.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


open class CreditInfoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CreditInfoApplication)
            modules(provideDependencies())
        }
    }
    open fun provideDependencies() = listOf(appModule, repositoryModule, viewModelModule)
}