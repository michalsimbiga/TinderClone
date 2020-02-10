package com.application.domain.common.extensions

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.withState
import com.application.ui.base.MvRxViewModel

fun <S : MvRxState, A : MvRxViewModel<S>> Fragment.simpleController(
    viewModel: A,
    buildModels: EpoxyController.(state: S) -> Unit
) = MvRxEpoxyController {
    if (view == null || isRemoving) return@MvRxEpoxyController
    withState(viewModel) { state ->
        buildModels(state)
    }
}

open class MvRxEpoxyController(
    val buildModelsCallback: EpoxyController.() -> Unit = {}
) : EpoxyController() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        (recyclerView.layoutManager as? LinearLayoutManager)?.let {
            it.recycleChildrenOnDetach = true
        }
    }

    override fun buildModels() {
        buildModelsCallback()
    }
}