package com.application.di

import androidx.databinding.DataBindingComponent
import com.application.di.module.BindingModule
import dagger.Subcomponent

@Subcomponent(modules = [BindingModule::class])
interface BindingComponent: DataBindingComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): BindingComponent
    }
}