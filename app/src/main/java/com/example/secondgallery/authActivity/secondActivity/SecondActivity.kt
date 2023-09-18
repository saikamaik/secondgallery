package com.example.secondgallery.authActivity.secondActivity

import android.os.Bundle
import com.example.secondgallery.R
import moxy.MvpAppCompatActivity

class SecondActivity : MvpAppCompatActivity(), SecondView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}