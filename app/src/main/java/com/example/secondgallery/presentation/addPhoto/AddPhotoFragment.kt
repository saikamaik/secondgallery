package com.example.secondgallery.presentation.addPhoto

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.databinding.FragmentAddphotoBinding
import com.example.secondgallery.presentation.basemvp.BaseFragment
import com.example.secondgallery.utils.Const.IMAGE
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.time.LocalDateTime

class AddPhotoFragment : BaseFragment<AddPhotoView, AddPhotoPresenter, FragmentAddphotoBinding>(),
    AddPhotoView {

    @InjectPresenter
    override lateinit var presenter: AddPhotoPresenter

    @ProvidePresenter
    fun providePresenter(): AddPhotoPresenter = App.appComponent.provideAddPhotoPresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*        requireActivity().navigationView.visibility = View.GONE*/
        setUpUi()
    }

    @SuppressLint("NewApi")
    private fun setUpUi() {

        binding.toolbar.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        val uriString: String = arguments?.getString(IMAGE).toString()
        val uri: Uri = Uri.parse(uriString)

        context?.let {
            Glide.with(it)
                .load(uri)
                .into(binding.imageAdd)
        }

        val file = File(
            requireContext().getExternalFilesDir(Environment.DIRECTORY_DCIM),
            File(uri.toString()).name
        )

        val filePart: MultipartBody.Part = MultipartBody.Part.createFormData(
        "file",
        file.name,
        RequestBody.create(MediaType.parse("image/*"), file)
    )

        binding.switchNew.setOnCheckedChangeListener { _, isChecked ->
            presenter.photoTypeNew = isChecked
        }

        binding.switchPopular.setOnCheckedChangeListener { _, isChecked ->
            presenter.photoTypePopular = isChecked
        }

        binding.buttonAdd.setOnClickListener {
            presenter.postPhoto(
                filePart,
                binding.etName.text.toString(),
                binding.etDescription.text.toString(),
                LocalDateTime.now().toString(),
                presenter.photoTypeNew,
                presenter.photoTypePopular
            )
        }
    }

    override fun showToast(id: Int) {
        Toast.makeText(this.requireContext(), id, Toast.LENGTH_LONG)
            .show()
        findNavController().navigate(R.id.homeFragment)
    }

    override fun initializeBinding(): FragmentAddphotoBinding {
        return FragmentAddphotoBinding.inflate(layoutInflater)
    }

    override fun setUpListeners() {
    }

}