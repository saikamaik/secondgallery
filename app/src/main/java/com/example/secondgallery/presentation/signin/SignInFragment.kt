package com.example.secondgallery.presentation.signin

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.domain.entity.login.User
import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.databinding.FragmentSigninBinding
import com.example.secondgallery.presentation.welcome.WelcomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_signin.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SignInFragment : MvpAppCompatFragment(), SignInView {

    @InjectPresenter
    lateinit var presenter: SignInPresenter

    @ProvidePresenter
    fun providePresenter(): SignInPresenter = App.appComponent.provideSignInPresenter()

    private var _binding: FragmentSigninBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().navigationView.visibility = View.GONE

        tv_toolbar.setOnClickListener {
            findNavController().navigate(R.id.welcomeFragment)
        }

        button_sign_up.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }

        button_sign_in.setOnClickListener {
            presenter.postClient(
                user = User(
                    null,
                    null,
                    et_email.text.toString(),
                    null,
                    et_password.text.toString()
                ), requireContext()
            )
        }

    }

    override fun navigateToHomeFragment() {
        findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
    }


}