package com.example.secondgallery.authActivity.signup

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.example.domain.entity.login.User
import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.databinding.FragmentSignupBinding
import com.example.secondgallery.presentation.basemvp.BaseFragment
import com.example.secondgallery.presentation.mainActivity.MainActivity
import com.example.secondgallery.utils.DateUtils
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SignUpFragment : BaseFragment<SignUpView, SignUpPresenter, FragmentSignupBinding>(),
    SignUpView {

    @InjectPresenter
    override lateinit var presenter: SignUpPresenter

    @ProvidePresenter
    fun providePresenter(): SignUpPresenter = App.appComponent.provideSignUpPresenter()

    override fun initializeBinding(): FragmentSignupBinding {
        return FragmentSignupBinding.inflate(layoutInflater)
    }

    override fun setUpListeners() {

        binding.toolbarCancel.setOnClickListener {
            findNavController().navigate(R.id.welcomeFragment)
        }

        binding.buttonSignIn.setOnClickListener {
            findNavController().navigate(R.id.signInFragment)
        }

        binding.buttonSignUp.setOnClickListener {
            if (presenter.validateSignUp(
                    binding.etConfirmPassword,
                    binding.etPassword,
                    binding.tlConfirmPassword,
                    binding.etEmail,
                    binding.etUsername,
                    binding.etBirthday
                )
            ) {
                presenter.regUser(
                    user = User(
                        null,
                        binding.etEmail.text.toString(),
                        binding.etUsername.text.toString(),
                        DateUtils.convertFromStringToDate(binding.etBirthday.text.toString()),
                        binding.etPassword.text.toString()
                    )
                )
            }
        }

    }

    override fun navigateToHomeFragment() {
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }

}

