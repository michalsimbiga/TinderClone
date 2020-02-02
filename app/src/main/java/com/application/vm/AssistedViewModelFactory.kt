package com.application.vm

import com.airbnb.mvrx.MvRxState
import com.application.MvRxViewModel

interface AssistedViewModelFactory<VM : MvRxViewModel<S>, S : MvRxState> {
    fun create(state: S): VM
}