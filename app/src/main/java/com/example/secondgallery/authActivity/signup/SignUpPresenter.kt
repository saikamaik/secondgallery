package com.example.secondgallery.authActivity.signup

import android.widget.EditText
import com.example.domain.entity.login.User
import com.example.domain.gateway.LoginGateway
import com.example.secondgallery.Validator
import com.example.secondgallery.authorization.SessionManager
import com.example.secondgallery.presentation.basemvp.BasePresenter
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class SignUpPresenter @Inject constructor(
    private val loginGateway: LoginGateway,
    private val sessionManager: SessionManager
) : BasePresenter<SignUpView>() {

    private var validate: Validator = Validator()

    fun regUser(user: User) {
        loginGateway.postUser(
            user.email,
            user.username,
            user.password!!,
            user.birthday
        )
            .flatMap {
                loginGateway.postClient(
                    user.username
                ).doOnSuccess {
                    sessionManager.saveClientInfo("${it.id}_${it.randomId}", it.secret!!)
                }
            }
            .flatMap {
                loginGateway.getLogin(
                    "${it.id}_${it.randomId}",
                    user.username,
                    user.password!!,
                    it.secret
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                viewState.navigateToHomeFragment()
                getCurrentUser()
            }
            .subscribe({
                sessionManager.saveAccessToken(it.access_token)
                sessionManager.saveAuthToken(it.refresh_token)
            }, {
                it.printStackTrace()
            }).let(compositeDisposable::add)

    }

    private fun getCurrentUser() {
        loginGateway.getCurrentUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                sessionManager.saveId(id = it.id!!)
            }
            .subscribe({

            }, {
                it.printStackTrace()
            }
            )
            .let(compositeDisposable::add)
    }

    fun validateSignUp(
        confirmPasswordEditText: EditText,
        passwordEditText: EditText,
        confirmPasswordTextInputLayout: TextInputLayout,
        emailEditText: EditText,
        usernameEditText: EditText,
        birthdayEditText: EditText
    ): Boolean {
        if (confirmPasswordEditText.text.toString() != passwordEditText.text.toString()) {
            confirmPasswordTextInputLayout.error = "Пароли не совпадают"
            return false
        } else if (validate.validateEmail(emailEditText)
            && validate.validatePassword(passwordEditText)
            && validate.validateUsername(usernameEditText)
            && validate.validateDate(birthdayEditText)
        ) return true
        return false
    }
}