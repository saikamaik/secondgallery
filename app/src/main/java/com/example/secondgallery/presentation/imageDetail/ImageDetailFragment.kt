package com.example.secondgallery.presentation.imageDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.secondgallery.databinding.FragmentPhotoinfoBinding
import com.example.secondgallery.di.BASE_URL
import com.example.secondgallery.presentation.basemvp.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_photoinfo.*

class ImageDetailFragment : Fragment() {

    private var _binding: FragmentPhotoinfoBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoinfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().navigationView.visibility = View.GONE

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        setUpUI()

    }

    private fun setUpUI() {

        val imgLink: String? = arguments?.getString(IMAGE_LINK)
        image_date_create.text = arguments?.getString(IMAGE_DATE_CREATION)
        image_description.text = arguments?.getString(IMAGE_DESCRIPTION)
        image_name.text = arguments?.getString(IMAGE_NAME)
        image_username.text = arguments?.getString(IMAGE_USERNAME)
        Glide
            .with(this)
            .load(BASE_URL + "media/$imgLink")
            .into(image_detail)

    }
}