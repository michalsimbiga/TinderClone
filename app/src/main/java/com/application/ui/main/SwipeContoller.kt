package com.application.ui.main

import com.airbnb.epoxy.EpoxyController
import com.application.data.Suggestion
import com.application.ui.models.SwipeableCardViewModel_
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
                    .show()
            )
        }
        requestModelBuild()
    }

    override fun buildModels() {
        add(ModelGroup(swipeCardModels))
    }
}