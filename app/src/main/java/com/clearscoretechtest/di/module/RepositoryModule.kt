package com.clearscoretechtest.di.module

import com.clearscoretechtest.data.repository.CreditInfoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CreditInfoRepository(get()) }
}