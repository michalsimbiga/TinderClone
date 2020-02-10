package com.application.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.application.R
import com.application.data.Suggestion
import com.bumptech.glide.Glide

class StackAdapter(private val context: Context) : BaseAdapter() {

    var listOfSuggestions: MutableList<Suggestion> = mutableListOf()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflatedView =
            LayoutInflater.from(context).inflate(R.layout.swipe_card_view, parent, false)
        val imageView: ImageView = inflatedView.findViewById(R.id.image_holder)

        Glide
            .with(context)
            .load(listOfSuggestions[position].listOfPhotos.first().uri)
            .centerCrop()
            .into(imageView)

        return inflatedView
    }

    override fun getItem(position: Int): Suggestion = listOfSuggestions[position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getCount(): Int = listOfSuggestions.size
}