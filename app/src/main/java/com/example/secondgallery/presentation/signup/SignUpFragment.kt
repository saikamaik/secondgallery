package com.example.secondgallery.presentation.signup

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.secondgallery.R
import com.example.secondgallery.databinding.ActivityMainBinding
import com.example.secondgallery.presentation.welcome.WelcomeFragment

class SignUpFragment: Fragment() {

    private val welcomeFragment = WelcomeFragment()
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var binding: ActivityMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = view.findViewById(R.id.toolbar_cancel)
        toolbar.setNavigationOnClickListener{
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fl_container, welcomeFragment)
                ?.commit()
        }

    }

}