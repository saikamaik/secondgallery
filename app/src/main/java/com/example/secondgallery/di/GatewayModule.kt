package com.example.secondgallery.di

import com.example.domain.gateway.LoginGateway
import com.example.domain.gateway.PhotoGateway
import com.example.gateway.repository.RetrofitLoginGateway
import com.example.gateway.repository.RetrofitPhotoGateway
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
abstract class GatewayModule {

    @Binds
    @Singleton
    abstract fun providePhotoGateway(gateway: RetrofitPhotoGateway): PhotoGateway

    @Binds
    @Singleton
    abstract fun provideLoginGateway(gateway: RetrofitLoginGateway): LoginGateway

}