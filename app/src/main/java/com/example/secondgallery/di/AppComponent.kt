package com.example.secondgallery.di

import com.example.secondgallery.App
import com.example.secondgallery.presentation.newPhotos.NewPresenter
import com.example.secondgallery.presentation.popularPhotos.PopularPresenter
import com.example.secondgallery.presentation.settings.SettingsPresenter
import com.example.secondgallery.presentation.signin.SignInPresenter
import com.example.secondgallery.presentation.signup.SignUpPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GatewayModule::class, RetrofitModule::class])
interface AppComponent {

    fun inject(target: App)

    fun provideNewPresenter(): NewPresenter
    fun providePopularPresenter(): PopularPresenter
    fun provideSignInPresenter(): SignInPresenter
    fun provideSignUpPresenter(): SignUpPresenter
    fun provideSettingsPresenter(): SettingsPresenter
}