package com.example.secondgallery.presentation.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.secondgallery.R
import com.example.secondgallery.databinding.FragmentWelcomeBinding
import kotlinx.android.synthetic.main.fragment_signin.button_sign_up
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding
        get() = _binding!!

    // todo Можно вынести в BaseFragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // todo Лучше избавляться от желтухи
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // todo Желательно держать этот метод максимально чистым
        // todo Ты можешь вынести эти листенеры в какие-нибудь методы типа setupListeners()
        button_sign_up.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }

        button_sign_in.setOnClickListener {
            findNavController().navigate(R.id.signInFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}