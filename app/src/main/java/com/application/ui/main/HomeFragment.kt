package com.application.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import com.airbnb.mvrx.*
import com.application.R
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

    private val epoxyController by lazy {
        simpleController(viewModel) { state ->
            state.suggestions()?.forEach { suggestion ->
                swipeableCardView {
                    user(suggestion)
                    id(suggestion.id)
                }
            }
        }
    }

    private val viewModel: HomeViewModel by fragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.home_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        home_epoxy.layoutManager = getCardStackLayoutManager()
        home_epoxy.adapter = epoxyController.adapter
    }

    override fun invalidate() = withState(viewModel) { state ->
        when (state.suggestions) {
            is Loading -> setUILoading(true)
            is Success -> {
                setUILoading(false)
                epoxyController.requestModelBuild()
            }
        }
    }

    private fun setUILoading(isLoading: Boolean) {
        swipe_card_loading_text.text = if (isLoading) "Finding people near you" else ""
        swipe_card_loading_bar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun getCardStackLayoutManager() : CardStackLayoutManager{
        return CardStackLayoutManager(requireContext()).apply {
            setStackFrom(StackFrom.None)
            setVisibleCount(3)
            setTranslationInterval(8.0f)
            setScaleInterval(0.95f)
            setSwipeThreshold(0.3f)
            setMaxDegree(50.0f)
            setCanScrollHorizontal(true)
            setCanScrollVertical(true)
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }
    }
}

