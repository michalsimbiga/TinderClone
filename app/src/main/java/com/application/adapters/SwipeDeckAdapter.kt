package com.application.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.R
import com.application.data.Suggestion
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.swipe_card_view.view.*

class SwipeDeckAdapter: RecyclerView.Adapter<SwipeDeckAdapter.ViewHolder>() {

    private val listOfUsers = mutableListOf<Suggestion>()

    fun swapData(list: List<Suggestion>) {
        listOfUsers.clear()
        listOfUsers.addAll(list as MutableList)
        notifyDataSetChanged()
    }

    fun removeFirst() {
        val positionFirst = 0
        if (listOfUsers.size > positionFirst) {
            val user = listOfUsers.removeAt(positionFirst)
            notifyItemRemoved(positionFirst)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val card = inflater.inflate(R.layout.swipe_card_view, parent, false)

        return ViewHolder(card)
    }

    override fun getItemCount(): Int {
        return listOfUsers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfUsers[position])
    }


    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(user: Suggestion) {
            Glide
                .with(view)
                .load(user.listOfPhotos.first().uri)
                .centerCrop()
                .into(view.image_holder)
        }
    }
}