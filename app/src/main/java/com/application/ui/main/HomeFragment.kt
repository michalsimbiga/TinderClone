package com.application.ui.main

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.AutoModel
import com.airbnb.mvrx.*
import com.application.R
import com.application.adapters.SwipeDeckAdapter
import com.application.domain.common.extensions.simpleController
import com.application.ui.models.swipeableCardView
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeableMethod
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

class HomeFragment : BaseMvRxFragment() {

    @Inject
    lateinit var viewModelFactory: HomeViewModel.Factory

    private lateinit var manager: CardStackLayoutManager

    private val swipeAdapter by lazy { SwipeDeckAdapter() }

    private val stackAdapter by lazy { StackAdapter(requireContext()) }

//    private val epoxyController by lazy {
//        simpleController(viewModel) { state ->
//            state.suggestions()?.forEach { suggestion ->
//                swipeableCardView {
//                    user(suggestion)
//                    id(suggestion.id)
//                }
//            }
//        }
//    }

    private val viewModel: HomeViewModel by fragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.home_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setupCardStack()
//        home_epoxy.setControllerAndBuildModels(epoxyController)

//        home_epoxy.withModels {
//            withState(viewModel) { state ->
//                state.suggestions()?.forEach { suggestion ->
//                    swipeableCardView {
//                        user(suggestion)
//                        id(suggestion.id)
//                    }
//                }
//            }
//        }

//        home_epoxy.adapter = epoxyController.adapter

//        class OverlapDecoration : RecyclerView.ItemDecoration() {
//            private var vertOverlap = 0
//
//            override fun getItemOffsets(
//                outRect: Rect,
//                view: View,
//                parent: RecyclerView,
//                state: RecyclerView.State
//            ) {
//                vertOverlap = view.height
//                val itemPosition = parent.getChildAdapterPosition(view)
//                if (itemPosition == 0) return
//                outRect.set(0, vertOverlap, 0, 0)
//            }
//
//        }

//        home_epoxy.addItemDecoration(OverlapDecoration())
//        home_epoxy.layoutManager = LinearLayoutManager(requireContext())

        home_stack.adapter = stackAdapter

    }

    override fun invalidate() = withState(viewModel) { state ->
        when (state.suggestions) {
            is Loading -> setUILoading(true)
            is Success -> {
                setUILoading(false)
//                epoxyController.requestModelBuild()
                stackAdapter.listOfSuggestions = state.suggestions()?.toMutableList()!!
                stackAdapter.notifyDataSetChanged()
            }
        }
    }


    private fun setUILoading(isLoading: Boolean) {
        swipe_card_loading_text.text = if (isLoading) "Finding people near you" else ""
        swipe_card_loading_bar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

//    private fun setupCardStack() {
//        manager = CardStackLayoutManager(requireContext()).apply {
//            setStackFrom(StackFrom.None)
//            setVisibleCount(3)
//            setTranslationInterval(8.0f)
//            setScaleInterval(0.95f)
//            setSwipeThreshold(0.3f)
//            setMaxDegree(50.0f)
//            setCanScrollHorizontal(true)
//            setCanScrollVertical(true)
//            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
//            setOverlayInterpolator(LinearInterpolator())
//            swipe_fragment_card_stack_view.itemAnimator.apply {
//                if (this is DefaultItemAnimator) {
//                    supportsChangeAnimations = false
//                }
//            }
//        }
//
//        swipe_fragment_card_stack_view.layoutManager = manager
//        swipe_fragment_card_stack_view.adapter = swipeAdapter
//    }
}

