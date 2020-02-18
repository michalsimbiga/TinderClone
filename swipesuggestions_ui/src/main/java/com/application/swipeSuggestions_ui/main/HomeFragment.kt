package com.application.swipeSuggestions_ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.ItemTouchHelper
import com.airbnb.epoxy.EpoxyTouchHelper
import com.airbnb.mvrx.*
import com.application.swipeSuggestions_ui.di.SwipeSuggestionsModules
import com.application.swipeSuggestions_ui.models.SwipeableCardViewModel_
import com.application.swipeSuggestions_ui.models.swipeableCardView
import com.example.common_ui.simpleController
import com.example.swipesuggestions_ui.R
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.SwipeableMethod
import kotlinx.android.synthetic.main.home_fragment.*
import timber.log.Timber

class HomeFragment : BaseMvRxFragment() {

    private val viewModel: HomeViewModel by fragmentViewModel()

    private val epoxyController by lazy {
        simpleController(viewModel) { state ->
            state.suggestions()?.forEach { suggestion ->
                swipeableCardView {
                    id(suggestion.id)
                    user(suggestion)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        SwipeSuggestionsModules.load()

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.home_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEpoxy()
    }

    private fun setupEpoxy() =
        with(home_epoxy) {
            layoutManager = getCardStackLayoutManager()
            adapter = epoxyController.adapter
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

    private fun getCardStackLayoutManager(): CardStackLayoutManager {
        return CardStackLayoutManager(requireContext()).apply {
            setVisibleCount(3)
            setTranslationInterval(8.0f)
            setScaleInterval(0.95f)
            setSwipeThreshold(0.6f)
            setMaxDegree(50.0f)
            setCanScrollHorizontal(true)
            setCanScrollVertical(true)
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }
    }

    override fun onDetach() {
        home_epoxy.layoutManager = null
        super.onDetach()
    }
}

