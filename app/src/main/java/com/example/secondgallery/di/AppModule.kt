package com.example.secondgallery.di

import android.content.Context
import android.content.SharedPreferences
import com.example.secondgallery.App
import com.example.secondgallery.authorization.SessionManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class AppModule(
    private val app: App
) {

    @Provides
    @Singleton
    fun provideApp(): App = app

    @Provides
    @Singleton
    fun provideSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            context.applicationInfo.toString(),
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideSessionManager(
        context: Context,
        sharedPreferences: SharedPreferences
    ): SessionManager {
        return SessionManager(context, sharedPreferences)
    }
}
