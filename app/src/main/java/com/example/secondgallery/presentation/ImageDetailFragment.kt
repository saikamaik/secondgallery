package com.example.secondgallery.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.secondgallery.R
import com.squareup.picasso.Picasso

class ImageDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photoinfo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val toolbar: Toolbar = view.findViewById(R.id.toolbar) as Toolbar
//
//        toolbar.setNavigationOnClickListener{ fragmentManager?.beginTransaction()?.remove(this)?.commit()}
//
//        val imgLink : String? = arguments?.getString("imageLink")
//        val img: ImageView = view.findViewById(R.id.image_detail)
//
//        val imgDescr: TextView = view.findViewById(R.id.image_description)
//        imgDescr.text = arguments?.getString("imageDescription")
//
//        val imgName: TextView = view.findViewById((R.id.image_name))
//        imgName.text = arguments?.getString("imageName")
//
//        Picasso.get().load("http://gallery.dev.webant.ru/media/$imgLink")
//            .into(img)
    }
}