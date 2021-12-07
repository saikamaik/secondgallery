package com.example.secondgallery.presentation.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.domain.entity.login.User
import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.Validator
import com.example.secondgallery.databinding.FragmentSignupBinding
import com.example.secondgallery.presentation.signin.SignInView
import com.example.secondgallery.presentation.welcome.WelcomeFragment
import com.example.secondgallery.utils.DateUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_signup.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.util.*

class SignUpFragment : MvpAppCompatFragment(), SignUpView {

    @InjectPresenter
    lateinit var presenter: SignUpPresenter

    @ProvidePresenter
    fun providePresenter(): SignUpPresenter = App.appComponent.provideSignUpPresenter()

    private val welcomeFragment = WelcomeFragment()
    private lateinit var validate: Validator

    private var _binding: FragmentSignupBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        validate = Validator()

        requireActivity().navigationView.visibility = View.GONE

        tv_toolbar.setOnClickListener {
            findNavController().navigate(R.id.welcomeFragment)
        }

        button_sign_in.setOnClickListener {
            findNavController().navigate(R.id.signInFragment)
        }

        button_sign_up.setOnClickListener {

            if (et_confirm_password.text.toString() != et_password.text.toString()) {
                tl_confirm_password.error = "Пароли не совпадают"
            } else if (validate.validateEmail(et_email)
                && validate.validatePassword(et_password)
                && validate.validateUsername(et_username)
            ) {
                presenter.postUser(
                    user = User(
                        null,
                        et_email.text.toString(),
                        et_username.text.toString(),
                        DateUtils.convertFromStringToDate(et_birthday.text.toString()),
                        et_password.text.toString()
                    ), requireContext()
                )
            }
        }
    }

    override fun navigateToHomeFragment() {
        findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
    }

}

