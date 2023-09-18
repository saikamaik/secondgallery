package com.example.secondgallery.presentation.splashScreenActivity

import android.content.Intent
import android.os.Bundle
import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.databinding.ActivitySplashScreenBinding
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SplashScreenActivity : MvpAppCompatActivity(), SplashScreenView {

    @InjectPresenter
    lateinit var presenter: SplashScreenPresenter

    @ProvidePresenter
    fun providePresenter(): SplashScreenPresenter = App.appComponent.provideSplashScreenPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        navigateToActivity(this)

    }

    override fun navigateToActivity(activity: MvpAppCompatActivity) {
        val binding: ActivitySplashScreenBinding =
            ActivitySplashScreenBinding.inflate(layoutInflater)
        binding.webantLogo.alpha = 0f
        binding.webantLogo.animate().setDuration(1000).alpha(1f).withEndAction {
            startActivity(Intent(this, activity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}