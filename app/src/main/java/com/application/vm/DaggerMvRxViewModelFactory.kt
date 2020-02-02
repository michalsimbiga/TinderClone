package com.application.vm

import androidx.fragment.app.FragmentActivity
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.application.MvRxViewModel
import com.application.appComponent

abstract class DaggerMvRxViewModelFactory<VM : MvRxViewModel<S>, S : MvRxState>(
    private val viewModelClass: Class<out MvRxViewModel<S>>
) : MvRxViewModelFactory<VM, S> {

    override fun create(viewModelContext: ViewModelContext, state: S): VM? {
        return createViewModel(viewModelContext.activity, state)
    }

    private fun <VM : MvRxViewModel<S>, S : MvRxState> createViewModel(fragmentActivity: FragmentActivity,state: S): VM {
        val viewModelFactoryMap = fragmentActivity.appComponent().viewModelFactories()
        val viewModelFactory = viewModelFactoryMap[viewModelClass]
        @Suppress("UNCHECKED_CAST")
        val castedViewModelFactory = viewModelFactory as? AssistedViewModelFactory<VM, S>
        val viewModel = castedViewModelFactory?.create(state)
        return viewModel as VM
    }
}