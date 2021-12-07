package com.example.secondgallery.presentation.addPhoto

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.secondgallery.IMAGE
import com.example.secondgallery.R
import com.example.secondgallery.databinding.FragmentAddphotoBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_addphoto.*

class AddPhotoFragment : Fragment() {

    private var _binding: FragmentAddphotoBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddphotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().navigationView.visibility = View.GONE

        toolbar.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var photoTypeNew: Boolean = false
        var photoTypePopular: Boolean = false
        var image: String

        Glide
            .with(this)
            .load(arguments?.getString(IMAGE)!!)
            .into(image_add)

        switch_new.setOnCheckedChangeListener { buttonView, isChecked ->
            photoTypeNew = isChecked
        }

//        var photo: PhotoModel = PhotoModel(
//            id,
//            imageName,
//            LocalDateTime.now().toString(),
//            imageDescription,
//            photoTypeNew,
//            photoTypePopular,
//            image,
//            user
//        )

    }


}