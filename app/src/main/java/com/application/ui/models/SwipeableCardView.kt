package com.application.ui.models

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.application.R
import com.application.data.Suggestion
import com.application.ui.main.PhotosViewPagerAdapter
import kotlinx.android.synthetic.main.swipe_card_view.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT)
class SwipeableCardView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    init {
        View.inflate(context, R.layout.swipe_card_view, this)
        setClickListeners()
    }

    @ModelProp
    fun setUser(user: Suggestion) {
        swipe_card_view_pager.adapter = PhotosViewPagerAdapter(user.listOfPhotos.map { it.uri })
        tab_layout.setupWithViewPager(swipe_card_view_pager)
    }

    private fun setClickListeners() {
        right_layout.setOnClickListener { swipe_card_view_pager.arrowScroll(View.FOCUS_RIGHT) }
        left_layout.setOnClickListener { swipe_card_view_pager.arrowScroll(View.FOCUS_LEFT) }
    }
}