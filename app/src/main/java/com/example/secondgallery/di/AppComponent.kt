package com.example.secondgallery.di

import android.content.SharedPreferences
import com.example.gateway.repository.RetrofitLoginGateway
import com.example.secondgallery.App
import com.example.secondgallery.authActivity.signin.SignInPresenter
import com.example.secondgallery.authActivity.signup.SignUpPresenter
import com.example.secondgallery.authActivity.welcomeFragment.WelcomePresenter
import com.example.secondgallery.authorization.SessionManager
import com.example.secondgallery.presentation.addPhoto.AddPhotoPresenter
import com.example.secondgallery.presentation.homePage.HomePresenter
import com.example.secondgallery.presentation.imageDetail.ImageDetailPresenter
import com.example.secondgallery.presentation.newPhotos.NewPhotoPresenter
import com.example.secondgallery.presentation.popularPhotos.PopularPhotoPresenter
import com.example.secondgallery.presentation.profile.ProfilePresenter
import com.example.secondgallery.presentation.settings.SettingsPresenter
import com.example.secondgallery.presentation.splashScreenActivity.SplashScreenPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GatewayModule::class, RetrofitModule::class, AppModule::class])
interface AppComponent {

    fun inject(target: App)

    fun provideNewPresenter(): NewPhotoPresenter
    fun providePopularPresenter(): PopularPhotoPresenter
    fun provideSignInPresenter(): SignInPresenter
    fun provideSignUpPresenter(): SignUpPresenter
    fun provideSettingsPresenter(): SettingsPresenter
    fun provideAddPhotoPresenter(): AddPhotoPresenter
    fun provideProfilePresenter(): ProfilePresenter
    fun provideSplashScreenPresenter(): SplashScreenPresenter
    fun provideHomePresenter(): HomePresenter
    fun provideImageDetailPresenter(): ImageDetailPresenter
    fun provideWelcomePresenter(): WelcomePresenter

    fun provideRetrofitLoginGateway(): RetrofitLoginGateway
    fun provideSharedPreference(): SharedPreferences
    fun provideSessionManage(): SessionManager
}