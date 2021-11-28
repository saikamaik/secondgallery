package com.example.secondgallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        webant_logo.alpha = 0f
        webant_logo.animate().setDuration(1000).alpha(1f).withEndAction {
            val intent = Intent(this, MainActivity::class.java)
            startActivities(arrayOf(intent))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}