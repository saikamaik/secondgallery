package com.example.secondgallery.di

import android.content.Context
import com.di.AppModule
import com.example.secondgallery.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [AppModule::class])
class ContextModule {

    @Provides
    @Singleton
    fun provideContext(app: App): Context = app.applicationContext
}