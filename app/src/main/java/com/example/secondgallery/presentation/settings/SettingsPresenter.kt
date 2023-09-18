package com.example.secondgallery.presentation.settings

import android.widget.EditText
import com.example.domain.entity.login.User
import com.example.domain.entity.login.UserPassword
import com.example.domain.gateway.LoginGateway
import com.example.secondgallery.R
import com.example.secondgallery.Validator
import com.example.secondgallery.authorization.SessionManager
import com.example.secondgallery.presentation.basemvp.BasePresenter
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@InjectViewState
class SettingsPresenter @Inject constructor(
    private val loginGateway: LoginGateway,
    private val sessionManager: SessionManager
) : BasePresenter<SettingsView>() {

    val validate: Validator = Validator()

    fun getCurrentUser() {

        loginGateway.getCurrentUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.setUpUI(it)
                viewState.setUpListeners()
            }, {
                it.printStackTrace()
            }
            )
            .let(compositeDisposable::add)

    }

    fun editUser(user: User) {
        loginGateway.editUser(id = user.id!!, user.email, user.username, user.birthday)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showToast(R.string.data_changed)
            }, {
                it.printStackTrace()
            }
            )
            .let(compositeDisposable::add)
    }

    fun changePassword(id: Int, userPassword: UserPassword) {
        loginGateway.changePassword(id, userPassword.oldPassword, userPassword.newPassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showToast(R.string.password_changed)
            }, {
                viewState.showToast(R.string.wrong_old_password)
                it.printStackTrace()
            }
            )
            .let(compositeDisposable::add)
    }

    fun deleteUser(id: Int) {
        val deleteRequest: Call<Void> = loginGateway.deleteUser(id)
        deleteRequest.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() == 204) {
                    viewState.navigateToWelcomeActivity()
                } else {
                    viewState.showToast(R.string.error)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                viewState.showToast(R.string.error)
            }
        })
    }

    fun signOut() {
        sessionManager.saveAccessToken(null)
        sessionManager.saveAuthToken(null)
        viewState.navigateToWelcomeActivity()
    }

    fun checkIfCorrect(
        newPasswordEditText: EditText,
        confirmPasswordEditText: EditText,
        confirmPasswordTextInputLayout: TextInputLayout
    ): Boolean {
        if (newPasswordEditText.text.toString() != confirmPasswordEditText.text.toString()) {
            confirmPasswordTextInputLayout.error = "Пароли не совпадают"
            return false
        }
        return true
    }
}