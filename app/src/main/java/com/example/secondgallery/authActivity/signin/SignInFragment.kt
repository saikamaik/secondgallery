package com.example.secondgallery.authActivity.signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.domain.entity.login.User
import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.databinding.FragmentSigninBinding
import com.example.secondgallery.presentation.basemvp.BaseFragment
import com.example.secondgallery.presentation.mainActivity.MainActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SignInFragment : BaseFragment<SignInView, SignInPresenter, FragmentSigninBinding>(),
    SignInView {

    @InjectPresenter
    override lateinit var presenter: SignInPresenter

    @ProvidePresenter
    fun providePresenter(): SignInPresenter = App.appComponent.provideSignInPresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListeners()

    }

    override fun setUpListeners() {

        binding.toolbarCancel.setOnClickListener {
            findNavController().navigate(R.id.welcomeFragment)
        }

        binding.buttonSignUp.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }

        binding.buttonSignIn.setOnClickListener {
            presenter.authUser(
                user = User(
                    null,
                    null,
                    binding.etEmail.text.toString(),
                    null,
                    binding.etPassword.text.toString()
                )
            )
        }
    }

    override fun navigateToHomeFragment() {
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }

    override fun initializeBinding(): FragmentSigninBinding {
        return FragmentSigninBinding.inflate(layoutInflater)
    }
}