package com.clearscoretechtest.di.module

import com.clearscoretechtest.ui.viewmodel.CreditInfoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CreditInfoViewModel(get(), get()) }
}
