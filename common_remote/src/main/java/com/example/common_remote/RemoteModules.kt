package com.example.common_remote

import com.example.common_domain.ModuleLoader
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RemoteModules : ModuleLoader() {
    override val modules: List<Module> = listOf(remoteModule)
}

private val remoteModule = module {
    single {
        OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            connectTimeout(MAX_TIMEOUT, TimeUnit.SECONDS).readTimeout(MAX_TIMEOUT, TimeUnit.SECONDS)
        }.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(get())
            .build()
    }
}