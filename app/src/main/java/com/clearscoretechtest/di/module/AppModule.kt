package com.clearscoretechtest.di.module

import android.content.Context
import com.clearscoretechtest.BuildConfig
import com.clearscoretechtest.data.api.WebService
import com.clearscoretechtest.utils.Const.BASE_URL
import com.clearscoretechtest.utils.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), BASE_URL) }
    single { provideWebService(get()) }
    single { provideNetworkHelper(androidContext())}
}


private fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

private fun provideWebService(retrofit: Retrofit): WebService =
    retrofit.create(WebService::class.java)

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

