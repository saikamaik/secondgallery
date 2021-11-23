package com.example.secondgallery

import android.app.Application
import com.di.AppModule
import com.example.secondgallery.di.AppComponent
import com.example.secondgallery.di.DaggerAppComponent

class App: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}