package com.application.swipeSuggestions_ui.main

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.application.swipeSuggestions_ui.models.SwipeableCardViewModel_
import com.example.swipesuggestions_domain.entity.Suggestion
import java.util.*

class SwipeContoler : EpoxyController() {

    private var swipeCards = Collections.emptyList<Suggestion>()
    private val swipeCardModels = mutableListOf<SwipeableCardViewModel_>()

    fun setSwipeCards(cards: List<Suggestion>) {
        this.swipeCards = cards

        for (card in swipeCards.reversed()) {

            swipeCardModels.add(
                SwipeableCardViewModel_()
                    .id(card.id)
                    .user(card)
            )
        }
        requestModelBuild()
    }

    override fun buildModels() {
        add(ModelGroup(swipeCardModels as List<EpoxyModel<SwipeableCardViewModel_>>))
    }
}