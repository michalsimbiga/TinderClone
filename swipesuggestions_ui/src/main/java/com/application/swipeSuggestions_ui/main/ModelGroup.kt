package com.application.swipeSuggestions_ui.main

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelGroup
import com.application.swipeSuggestions_ui.models.SwipeableCardViewModel_
import com.example.swipesuggestions_ui.R

class ModelGroup(models: List<EpoxyModel<SwipeableCardViewModel_>>) :
    EpoxyModelGroup(R.layout.swipeable_model_group_layout, models)
