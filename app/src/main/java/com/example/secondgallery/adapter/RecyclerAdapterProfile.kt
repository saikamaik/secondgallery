package com.example.secondgallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.domain.entity.photo.PhotoModel
import com.example.secondgallery.R

class RecyclerAdapterProfile(
    private val photos: List<PhotoModel>,
    private val callback: Callback
) : RecyclerAdapter(photos, callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recycler_view_profile, parent, false)
        return MyViewHolder(itemView, callback)
    }

}