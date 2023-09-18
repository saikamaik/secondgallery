package com.example.secondgallery.authActivity.welcomeFragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.databinding.FragmentWelcomeBinding
import com.example.secondgallery.presentation.basemvp.BaseFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class WelcomeFragment :
    BaseFragment<WelcomeView, WelcomePresenter, FragmentWelcomeBinding>(), WelcomeView {

    @InjectPresenter
    override lateinit var presenter: WelcomePresenter

    @ProvidePresenter
    fun providePresenter(): WelcomePresenter = App.appComponent.provideWelcomePresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    override fun setUpListeners() {
        binding.buttonSignUp.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }

        binding.buttonSignIn.setOnClickListener {
            findNavController().navigate(R.id.signInFragment)
        }
    }

    override fun initializeBinding(): FragmentWelcomeBinding {
        return FragmentWelcomeBinding.inflate(layoutInflater)
    }
}