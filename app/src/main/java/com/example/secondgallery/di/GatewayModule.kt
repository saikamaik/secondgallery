package com.di // TODO Следи за пакетами

import com.example.domain.gateway.LoginGateway
import dagger.Module
import dagger.Provides
import com.example.gateway.remoteDataSource.GalleryApi
import com.example.domain.gateway.PhotoGateway
import com.example.gateway.repository.RetrofitLoginGateway
import com.example.gateway.repository.RetrofitPhotoGateway
import dagger.Binds
import javax.inject.Singleton

// TODO следи за импортами

@Module(includes = [ApiModule::class])
abstract class GatewayModule {

    @Binds
    @Singleton
    abstract fun providePhotoGateway(gateway: RetrofitPhotoGateway): PhotoGateway

//    @Provides
//    @Singleton
//    fun provideLoginGateway(api: GalleryApi): LoginGateway {
//        return RetrofitLoginGateway(api)
//    }
}