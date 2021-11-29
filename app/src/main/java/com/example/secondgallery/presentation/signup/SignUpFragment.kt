package com.example.secondgallery.presentation.signup

import android.R.attr.password
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.secondgallery.R
import com.example.secondgallery.Validator
import com.example.secondgallery.presentation.homePage.HomeFragment
import com.example.secondgallery.presentation.signin.SignInFragment
import com.example.secondgallery.presentation.welcome.WelcomeFragment
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.regex.Pattern

class SignUpFragment: Fragment() {

    private val welcomeFragment = WelcomeFragment()
    private lateinit var toolBarTextView: TextView
    private lateinit var signInButton: AppCompatButton
    private lateinit var signUpButton: AppCompatButton
    private lateinit var usernameEditText: EditText
    private lateinit var birthdayEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var confirmPasswordTextInputLayout: TextInputLayout
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var validate: Validator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        validate = Validator()

        requireActivity().navigationView.visibility = View.GONE

        usernameEditText = view.findViewById(R.id.et_username)
        birthdayEditText = view.findViewById(R.id.et_birthday)
        emailEditText = view.findViewById(R.id.et_email)
        passwordEditText = view.findViewById(R.id.et_password)
        confirmPasswordEditText = view.findViewById(R.id.et_confirm_password)

        confirmPasswordTextInputLayout = view.findViewById(R.id.tl_confirm_password)

        signInButton = view.findViewById(R.id.button_sign_in)
        signUpButton = view.findViewById(R.id.button_sign_up)

        var username: String = usernameEditText.text.toString()
        var birthday: Date
        var email: String = emailEditText.text.toString()
        val password: String = passwordEditText.text.toString()
        val confirmPassword: String = confirmPasswordEditText.text.toString()

        toolbar = view.findViewById(R.id.toolbar_cancel)
        toolBarTextView = view.findViewById(R.id.tv_toolbar)
        toolBarTextView.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fl_container, welcomeFragment)
                .commit()
        }

        signInButton.setOnClickListener {
            val signInFragment = SignInFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fl_container, signInFragment)
                .commit()
        }


        signUpButton.setOnClickListener {

            if (confirmPasswordEditText.text.toString() != passwordEditText.text.toString()) {
                confirmPasswordTextInputLayout.error = "Пароли не совпадают"
            } else if (validate.validateEmail(emailEditText)
                    and validate.validatePassword(passwordEditText)
                    and validate.validateUsername(usernameEditText)
                ) {
                    val homeFragment = HomeFragment()
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fl_container, homeFragment)
                        .commit()
                }
            }
        }


    }

