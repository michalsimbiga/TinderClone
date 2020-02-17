package com.application.di

import android.preference.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val mainModule = module {

    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }

}
