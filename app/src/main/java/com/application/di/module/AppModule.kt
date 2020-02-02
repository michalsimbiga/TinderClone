package com.application.di.module

import com.application.di.ViewModelKey
import com.application.ui.main.HomeViewModel
import com.application.vm.AssistedViewModelFactory
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@AssistedModule
@Module(includes = [AssistedInject_AppModule::class])
interface AppModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun helloViewModelFactory(factory: HomeViewModel.Factory): AssistedViewModelFactory<*, *>
}