package com.application.ui.models

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.application.R
import com.application.data.Suggestion
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.swipe_card_view.view.*
import timber.log.Timber
import kotlin.math.abs

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT)
class SwipeableCardView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    init {
        View.inflate(context, R.layout.swipe_card_view, this)
        setOnTouchListener()
    }

    @ModelProp
    fun setUser(user: Suggestion) {
        Glide
            .with(this)
            .load(user.listOfPhotos.first().uri)
            .centerCrop()
            .into(image_holder)
    }


    private fun setOnTouchListener() {
        swipe_card_card_view.setOnTouchListener(object : OnSwipeTouchHandler() {
            override fun onSwipeLeft() {
                Timber.i("TESTING swipeLeft ")
            }

            override fun onSwipeRight() {
                Timber.i("TESTING swipeRight ")
            }

        })
    }

    open class OnSwipeTouchHandler : OnTouchListener {
        private val gestureDetector = GestureDetector(GestureListener())

        fun onTouch(event: MotionEvent): Boolean {
            return gestureDetector.onTouchEvent(event)
        }

        private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
            private val swipe_treshhold = 100
            private val swipe_velocity_treshold = 100

            override fun onDown(e: MotionEvent?) = true

            override fun onFling(
                e1: MotionEvent,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                val result = false
                try {
                    val diffY = e2.y - e1.y
                    val diffX = e2.x - e1.x
                    if (abs(diffX) > abs(diffY)) {
                        if (abs(diffX) > swipe_treshhold && abs(velocityX) > swipe_velocity_treshold) {
                            if (diffX > 0) {
                                onSwipeRight()
                            } else {
                                onSwipeLeft()
                            }
                        }
                    } else {
                        // onTouch(e);
                    }
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }

                return result
            }
        }

        override fun onTouch(v: View, event: MotionEvent): Boolean {
            return gestureDetector.onTouchEvent(event)
        }

        open fun onSwipeRight() {}
        open fun onSwipeLeft() {}

    }
}