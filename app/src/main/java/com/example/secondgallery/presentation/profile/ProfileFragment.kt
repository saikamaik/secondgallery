package com.example.secondgallery.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.secondgallery.R
import com.example.secondgallery.databinding.FragmentProfileBinding
import com.example.secondgallery.databinding.FragmentSettingsBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_photoinfo.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.toolbar

class ProfileFragment: Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        icon_settings.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
        }

        requireActivity().navigationView.visibility = View.VISIBLE

    }

}