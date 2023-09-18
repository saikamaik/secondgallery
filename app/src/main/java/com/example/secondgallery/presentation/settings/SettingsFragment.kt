package com.example.secondgallery.presentation.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.domain.entity.login.User
import com.example.domain.entity.login.UserPassword
import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.authActivity.secondActivity.SecondActivity
import com.example.secondgallery.databinding.FragmentSettingsBinding
import com.example.secondgallery.presentation.basemvp.BaseFragment
import com.example.secondgallery.utils.DateUtils
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SettingsFragment : BaseFragment<SettingsView, SettingsPresenter, FragmentSettingsBinding>(),
    SettingsView {

    @InjectPresenter
    override lateinit var presenter: SettingsPresenter

    @ProvidePresenter
    fun providePresenter(): SettingsPresenter = App.appComponent.provideSettingsPresenter()

    private lateinit var currentUser: User
    private var id: Int? = null
    private var password: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        requireActivity().navigationView.visibility = View.GONE
        presenter.getCurrentUser()

        setUpListeners()
    }

    override fun setUpListeners() {

        binding.tvToolbar.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }

        binding.tvToolbarSave.setOnClickListener {
            if (presenter.validate.validateSettings(binding.etUsername, binding.etBirthday, binding.etEmail, currentUser)
            ) {
                presenter.editUser(
                    user = User(
                        id,
                        binding.etEmail.text.toString(),
                        binding.etUsername.text.toString(),
                        DateUtils.convertFromStringToDate(binding.etBirthday.text.toString()),
                        password = null,
                    )
                )
            }

            if ((binding.etNewPassword.text.toString().trim() != "")
                && presenter.checkIfCorrect(
                    binding.etNewPassword,
                    binding.etConfirmPassword,
                    binding.tlConfirmPassword
                )
                && presenter.validate.validatePassword(binding.etNewPassword)
            ) {
                presenter.changePassword(
                    id!!,
                    userPassword = UserPassword(
                        binding.etOldPassword.text.toString(),
                        binding.etNewPassword.text.toString()
                    )
                )
            }
        }

        binding.tvSignOut.setOnClickListener {
            presenter.signOut()
        }

        binding.tvDelete.setOnClickListener {
            presenter.deleteUser(currentUser.id!!)
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }
    }

    override fun setUpUI(user: User) {
        currentUser = user
        id = user.id
        binding.etUsername.setText(user.username)
        binding.etEmail.setText(user.email)
        binding.etBirthday.setText(DateUtils.checkDateFormat(user.birthday.toString()))
        password = user.password
    }

    override fun navigateToWelcomeActivity() {
        startActivity(Intent(requireContext(), SecondActivity::class.java))
    }

    override fun initializeBinding(): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(layoutInflater)
    }
}