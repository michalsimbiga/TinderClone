package com.application.ui.main

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelGroup
import com.application.R

class ModelGroup(models: List<EpoxyModel<*>>) :
    EpoxyModelGroup(R.layout.swipeable_model_group_layout, models)