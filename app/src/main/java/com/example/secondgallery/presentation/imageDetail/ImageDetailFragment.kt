package com.example.secondgallery.presentation.imageDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.secondgallery.R
import com.squareup.picasso.Picasso

// TODO проверить форматирование всех файлов
class ImageDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photoinfo, container, false)
    }


    // TODO желательно иметь много маленьких методов, вместо одного большого
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar) as Toolbar

        // TODO Перечёркнутое = deprecated, старайся не использовать депрекейтед
        toolbar.setNavigationOnClickListener{ fragmentManager?.beginTransaction()?.remove(this)?.commit()}

        // TODO вынести все строки в константы
        val imgLink : String? = arguments?.getString("imageLink")
        val img: ImageView = view.findViewById(R.id.image_detail)

        val imgDate: TextView = view.findViewById(R.id.image_date_create)
        imgDate.text = arguments?.getString("imageDateCreate")

        val imgDescr: TextView = view.findViewById(R.id.image_description)
        imgDescr.text = arguments?.getString("imageDescription")

        val imgName: TextView = view.findViewById((R.id.image_name))
        imgName.text = arguments?.getString("imageName")
        Glide
            .with(this)
            .load("http://gallery.dev.webant.ru/media/$imgLink") // TODO вынести ссылку в константы
            .into(img)

    }
}