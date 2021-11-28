package com.example.secondgallery.presentation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.domain.entity.login.AuthModel
import com.example.domain.entity.login.User
import com.example.domain.gateway.LoginGateway
import com.example.secondgallery.R
import com.example.secondgallery.authorization.SessionManager
import com.example.secondgallery.di.RetrofitModule
import com.example.secondgallery.presentation.homePage.HomeFragment
import com.example.secondgallery.presentation.signup.SignUpFragment
import com.example.secondgallery.presentation.welcome.WelcomeFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class SignInFragment : Fragment() {

    private val welcomeFragment = WelcomeFragment()
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var toolBarTextView: TextView
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: AppCompatButton
    private lateinit var signUpButton: AppCompatButton
    private lateinit var sessionManager: SessionManager
    private lateinit var retrofitModule: RetrofitModule
    lateinit var loginGateway: LoginGateway

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().navigationView.visibility = View.GONE

        usernameEditText = view.findViewById(R.id.et_username)
        passwordEditText = view.findViewById(R.id.et_password)

        signInButton = view.findViewById(R.id.button_sign_in)
        signUpButton = view.findViewById(R.id.button_sign_up)

        var username: String = usernameEditText.text.toString()
        var password: String = passwordEditText.text.toString()


        toolbar = view.findViewById(R.id.toolbar_cancel)
        toolBarTextView = view.findViewById(R.id.tv_toolbar)
        toolBarTextView.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fl_container, welcomeFragment)
                .commit()

//            retrofitModule = RetrofitModule()
//            sessionManager = activity?.let { it1 -> SessionManager(it1) }!!

        }

        signUpButton.setOnClickListener {
            val signUpFragment = SignUpFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fl_container, signUpFragment)
                .commit()
        }

        signInButton.setOnClickListener {
            val homeFragment = HomeFragment()
//            childFragmentManager.beginTransaction()
//                .replace(R.id.fl_container, homeFragment)
//                .commit()
        }

    }

    fun postClient(user: User) {

        var authModel: AuthModel

        loginGateway.postClient(null, user.username, null, null, listOf("password", "refresh_token"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                authModel = AuthModel("${it.id}_${it.randomId}", user.username, user.password, it.secret)
            }
            .subscribe()

    }

    fun login(authModel: AuthModel){
        loginGateway.getLogin(authModel.client_id, authModel.username, authModel.password, authModel.client_secret)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { sessionManager.saveAuthToken(it.accessToken)
            }
            .subscribe()

        }
}