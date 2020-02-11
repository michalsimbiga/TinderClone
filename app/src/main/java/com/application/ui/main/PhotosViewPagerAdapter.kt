package com.application.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.application.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.suggestion_photo_layout.view.*

class PhotosViewPagerAdapter(private val listOfPhotos: List<String>) :
    PagerAdapter() {

    init {
        notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`
    override fun getCount() = listOfPhotos.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view =
            LayoutInflater.from(container.context)
                .inflate(R.layout.suggestion_photo_layout, container, false)
        loadImage(container, listOfPhotos[position], view.suggestion_photo)
        container.addView(view)
        return view
    }

    private fun loadImage(container: ViewGroup, url: String, imageView: ImageView) {
        Glide
            .with(container)
            .load(url)
            .optionalCenterCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView)
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) =
        container.removeView(obj as View)
}