package com.example.secondgallery

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.secondgallery.databinding.BottomSheetBinding
import com.example.secondgallery.utils.Const.IMAGE
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.buttonMakePhoto.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)
                .cameraOnly()
                .saveDir(requireContext().getExternalFilesDir(Environment.DIRECTORY_DCIM)!!)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }

        binding.buttonSelectPhoto.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)
                .galleryOnly()
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                val imageUri: Uri = data?.data!!
                data.putExtra("result", imageUri)
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            if (resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val args = Bundle()
                val imageUri: Uri? = data?.data
                args.putString(IMAGE, imageUri.toString())
                Handler(Looper.getMainLooper()).post {
                    findNavController().navigate(
                        R.id.addPhotoFragment, args
                    )
                }
            }
        }
}