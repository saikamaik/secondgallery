package com.example.secondgallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.photo.PhotoModel
import com.example.secondgallery.R
import com.example.secondgallery.utils.Const.MEDIA_URL
import com.squareup.picasso.Picasso

open class RecyclerAdapter(
    private val photos: List<PhotoModel>,
    private val callback: Callback
) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recycler_view, parent, false)
        return MyViewHolder(itemView, callback)
    }

    override fun getItemCount() = photos.size

    interface Callback {
        fun onImageClicked(item: PhotoModel)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    class MyViewHolder(listItemView: View, private val callback: Callback) :
        RecyclerView.ViewHolder(listItemView) {

        private val firstImg: ImageView = itemView.findViewById(R.id.img)

        fun bind(photo: PhotoModel) {
            firstImg.setOnClickListener {
                callback.onImageClicked(photo)
            }

            Picasso.get()
                .load(MEDIA_URL + (photo.image?.name))
                .resize(1000, 1000)
                .centerInside()
                .into(firstImg)
        }
    }
}
