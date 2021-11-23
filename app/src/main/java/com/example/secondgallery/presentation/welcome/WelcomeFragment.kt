package com.example.secondgallery.presentation.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.secondgallery.R
import com.example.secondgallery.presentation.signin.SignInFragment
import com.example.secondgallery.presentation.signup.SignUpFragment
import moxy.MvpAppCompatFragment

class WelcomeFragment: Fragment() {

    private lateinit var signUpButton: Button
    lateinit var signInButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpButton = signUpButton.findViewById(R.id.button_sign_up)
        signInButton = signInButton.findViewById(R.id.button_sign_in)

        signUpButton.setOnClickListener {
            val signUpFragment = SignUpFragment()
            childFragmentManager.beginTransaction()
                .replace(R.id.fl_container, signUpFragment)
                .commit()
        }

        signInButton.setOnClickListener {
            val signInFragment = SignInFragment()
            childFragmentManager.beginTransaction()
                .replace(R.id.fl_container, signInFragment)
                .commit()
        }
    }
}