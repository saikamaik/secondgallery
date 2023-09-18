package com.example.secondgallery.presentation.imageDetail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.databinding.FragmentPhotoinfoBinding
import com.example.secondgallery.presentation.basemvp.BaseFragment
import com.example.secondgallery.utils.Const.BASE_URL
import com.example.secondgallery.utils.Const.IMAGE_DATE_CREATION
import com.example.secondgallery.utils.Const.IMAGE_DESCRIPTION
import com.example.secondgallery.utils.Const.IMAGE_LINK
import com.example.secondgallery.utils.Const.IMAGE_NAME
import com.example.secondgallery.utils.Const.IMAGE_USERNAME
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ImageDetailFragment :
    BaseFragment<ImageDetailView, ImageDetailPresenter, FragmentPhotoinfoBinding>(),
    ImageDetailView {

    @InjectPresenter
    override lateinit var presenter: ImageDetailPresenter

    @ProvidePresenter
    fun providePresenter(): ImageDetailPresenter = App.appComponent.provideImageDetailPresenter()


    var viewType: Int? = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /*requireActivity().navigationView.visibility = View.GONE*/
        setUpListeners()

        binding.toolbar.setNavigationOnClickListener {
            when (viewType) {
                1 -> findNavController().navigate(R.id.homeFragment)
                2 -> findNavController().navigate(R.id.profileFragment)
            }
        }
    }

    override fun setUpListeners() {
        viewType = arguments?.getInt("viewType")
        val imgLink: String? = arguments?.getString(IMAGE_LINK)
        binding.imageDateCreate.text = arguments?.getString(IMAGE_DATE_CREATION)
        binding.imageDescription.text = arguments?.getString(IMAGE_DESCRIPTION)
        binding.imageName.text = arguments?.getString(IMAGE_NAME)
        binding.imageUsername.text = arguments?.getString(IMAGE_USERNAME)
        Glide
            .with(this)
            .load(BASE_URL + "media/$imgLink")
            .into(binding.imageDetail)
    }

    override fun initializeBinding(): FragmentPhotoinfoBinding {
        return FragmentPhotoinfoBinding.inflate(layoutInflater)
    }
}