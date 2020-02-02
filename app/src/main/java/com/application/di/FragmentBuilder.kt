package com.application.di

import com.application.ui.main.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment
}