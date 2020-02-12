package com.application.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.ItemTouchHelper
import com.airbnb.epoxy.EpoxyTouchHelper
import com.airbnb.mvrx.*
import com.application.R
import com.application.ui.models.SwipeableCardView
import com.application.ui.models.SwipeableCardViewModel_
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeableMethod
import kotlinx.android.synthetic.main.home_fragment.*
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : BaseMvRxFragment() {

    @Inject
    lateinit var viewModelFactory: HomeViewModel.Factory

    private val swipeController = SwipeContoller()

//    private val epoxyController by lazy {
//        simpleController(viewModel) { state ->
//            SwipeableModelGroup(mutableListOf<SwipeableCardViewModel_>().apply {
//                state.suggestions()?.forEach { suggestion ->
//                    add(
//                        SwipeableCardViewModel_()
//                            .user(suggestion)
//                            .id(suggestion.id)
//                    )
//                }
//            })
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

        setupEpoxy()
//        home_epoxy.layoutManager = getCardStackLayoutManager()
//        home_epoxy.adapter = epoxyController.adapter

//        home_epoxy.withModels {
//            withState(viewModel) { state ->
//
//                SwipeableModelGroup(mutableListOf<SwipeableCardViewModel_>().apply {
//                    state.suggestions()?.forEach { suggestion ->
//                        add(
//                            SwipeableCardViewModel_()
//                                .user(suggestion)
//                                .id(suggestion.id)
//                        )
//                    }
//                })
//            }
//        }
//        home_epoxy.withModels {
//            withState(viewModel) { state ->
//
//                SwipeableModelGroup(mutableListOf<SwipeableCardViewModel_>().apply {
//                                        state.suggestions()?.forEach { suggestion ->
//                        add(
//                            SwipeableCardViewModel_()
//                                .user(suggestion)
//                                .id(suggestion.id)
//                        )
//                    }
//                }).buildModels()
//            }

//            SwipeableCardView(requireContext())
//                .setUser(withState(viewModel) { state ->
//                    state.suggestions.invoke()?.first()
//                }!!)
//        }


    }

    private fun setupEpoxy() {

        home_epoxy.adapter = swipeController.adapter

        EpoxyTouchHelper.initSwiping(home_epoxy)
            .withDirections(ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.UP or ItemTouchHelper.DOWN)
            .withTarget(ModelGroup::class.java)
            .andCallbacks(object : EpoxyTouchHelper.SwipeCallbacks<ModelGroup>() {
                override fun onSwipeCompleted(
                    model: ModelGroup?,
                    itemView: View?,
                    position: Int,
                    direction: Int
                ) {
                    Timber.i("TESTING swipeCompleted $model $itemView $position $direction")
                }

                override fun clearView(model: ModelGroup?, itemView: View?) {
                    withState(viewModel) { state ->
                        state.suggestions.invoke()?.let { swipeController.setSwipeCards(it)} }
                }

                override fun onSwipeReleased(model: ModelGroup?, itemView: View?) {
                    
                }

            })


//        EpoxyTouchHelper.initDragging(swipeController)
//            .withRecyclerView(home_epoxy)
//            .withTarget(ModelGroup::class.java)
//            .andCallbacks( object : EpoxyTouchHelper.DragCallbacks<ModelGroup>(){
//                override fun onModelMoved(
//                    fromPosition: Int,
//                    toPosition: Int,
//                    modelBeingMoved: ModelGroup?,
//                    itemView: View?
//                ) {
//                    Timber.i("TESTING swipeCompleted $fromPosition $toPosition $modelBeingMoved $itemView")
//                }
//            })

    }

    override fun invalidate() = withState(viewModel) { state ->
        when (state.suggestions) {
            is Loading -> setUILoading(true)
            is Success -> {
                setUILoading(false)
                swipeController.setSwipeCards(state.suggestions.invoke())
            }
        }
    }

    private fun setUILoading(isLoading: Boolean) {
        swipe_card_loading_text.text = if (isLoading) "Finding people near you" else ""
        swipe_card_loading_bar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun getCardStackLayoutManager(): CardStackLayoutManager {
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

