package com.example.secondgallery

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.secondgallery.databinding.BottomSheetBinding
import com.example.secondgallery.databinding.FragmentSignupBinding
import com.example.secondgallery.presentation.addPhoto.AddPhotoFragment
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet.*
import java.io.InputStream

const val IMAGE = "Image"

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        button_make_photo.setOnClickListener {
            ImagePicker.with(this)
                .maxResultSize(1080, 1080)
                .cameraOnly()
                .createIntent {
                    intent -> startForProfileImageResult.launch(intent)
                }
            findNavController().navigate(R.id.addPhotoFragment)
        }

        button_select_photo.setOnClickListener {
            ImagePicker.with(this)
                .maxResultSize(1080, 1080)
                .galleryOnly()
                .createIntent {
                    intent -> startForProfileImageResult.launch(intent)
                }
            findNavController().navigate(R.id.addPhotoFragment)
        }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val addPhotoFragment = AddPhotoFragment()
                val data: Intent? = result.data
                val args = Bundle()
                val imageUri: Uri = data?.data!!
                args.putString(IMAGE, imageUri.toString())
                addPhotoFragment.arguments = args
                findNavController().navigate(R.id.addPhotoFragment)}
        }

}