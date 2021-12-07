package com.example.secondgallery.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.domain.entity.login.User
import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.databinding.FragmentSettingsBinding
import com.example.secondgallery.presentation.newPhotos.NewPresenter
import com.example.secondgallery.utils.DateUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SettingsFragment: MvpAppCompatFragment(), SettingsView {

    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    @ProvidePresenter
    fun providePresenter(): SettingsPresenter = App.appComponent.provideSettingsPresenter()

    private var _binding: FragmentSettingsBinding? = null
    private val binding
        get() = _binding!!

    private var id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getCurrentUser()

        tv_toolbar.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }

        tv_toolbar_save.setOnClickListener{
            presenter.editUser(user = User(
                id,
                et_username.text.toString(),
                et_email.text.toString(),
                DateUtils.convertFromStringToDate(et_birthday.toString()),
                password = null,
            ))
            findNavController().navigate(R.id.profileFragment)
        }

        requireActivity().navigationView.visibility = View.GONE

    }

    override fun setUpUI (user: User) {

        id = user.id
        et_username.setText(user.username)
        et_email.setText(user.email)
        et_birthday.setText(DateUtils.checkDateFormat(user.birthday.toString()))

    }

}