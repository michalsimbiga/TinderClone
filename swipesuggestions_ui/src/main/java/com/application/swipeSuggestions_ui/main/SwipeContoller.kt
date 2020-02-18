package com.application.swipeSuggestions_ui.main

import com.airbnb.epoxy.EpoxyController
import com.application.swipeSuggestions_ui.models.SwipeableCardViewModel_
import com.example.swipesuggestions_domain.entity.Suggestion
import java.util.*

class SwipeContoller : EpoxyController() {

    private var swipeCards = Collections.emptyList<Suggestion>()
    private val swipeCardModels = mutableListOf<SwipeableCardViewModel_>()

    fun setSwipeCards(cards: List<Suggestion>) {
        this.swipeCards = cards

        for (card in swipeCards.reversed()) {

            swipeCardModels.add(
                SwipeableCardViewModel_()
                    .id(card.id)
                    .user(card)
//                    .onTouchListener(this)
            )
        }
        requestModelBuild()
    }

    override fun buildModels() {
        add(ModelGroup(swipeCardModels))
    }
}