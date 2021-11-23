package com.example.secondgallery.di

import com.di.GatewayModule
import com.example.secondgallery.App
import com.example.secondgallery.presentation.newPhotos.NewPresenter
import com.example.secondgallery.presentation.popularPhotos.PopularPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GatewayModule::class, RetrofitModule::class])
interface AppComponent {

    fun inject(target: App)

    fun provideNewPresenter(): NewPresenter
    fun providePopularPresenter(): PopularPresenter

}