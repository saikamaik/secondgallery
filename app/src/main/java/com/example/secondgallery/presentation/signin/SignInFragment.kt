package com.example.secondgallery.presentation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.di.ApiModule
import com.example.domain.entity.login.AuthModel
import com.example.domain.entity.login.LoginResponse
import com.example.domain.entity.login.User
import com.example.domain.gateway.LoginGateway
import com.example.gateway.remoteDataSource.GalleryApi
import com.example.secondgallery.R
import com.example.secondgallery.authorization.SessionManager
import com.example.secondgallery.di.RetrofitModule
import com.example.secondgallery.presentation.welcome.WelcomeFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class SignInFragment : Fragment() {

    private val welcomeFragment = WelcomeFragment()
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var edittextUsername: EditText
    private lateinit var edittextPassword: EditText
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

        edittextUsername = view.findViewById(R.id.et_username)
        edittextPassword = view.findViewById(R.id.et_password)

        var username: String = edittextUsername.text.toString()
        var password: String = edittextPassword.text.toString()

        toolbar = view.findViewById(R.id.toolbar_cancel)
        toolbar.setNavigationOnClickListener{
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fl_container, welcomeFragment)
                ?.commit()

            retrofitModule = RetrofitModule()
            sessionManager = activity?.let { it1 -> SessionManager(it1) }!!

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