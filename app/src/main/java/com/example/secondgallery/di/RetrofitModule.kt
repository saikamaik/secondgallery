package com.example.secondgallery.di

import android.content.Context
import com.example.domain.gateway.LoginGateway
import com.example.secondgallery.authorization.SessionManager
import com.example.secondgallery.authorization.TokenAuthenticator
import com.example.secondgallery.authorization.TokenInterceptor
import com.example.secondgallery.utils.Const.BASE_URL
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ContextModule::class, GatewayModule::class, AppModule::class])
class RetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        context: Context,
        loginGateway: Lazy<LoginGateway>,
        sessionManager: SessionManager
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptorsAndAuthenticator(context, loginGateway, sessionManager)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConvertAndCallAdapterFactory()
            .client(httpClient)
            .build()
    }

}

fun OkHttpClient.Builder.addInterceptorsAndAuthenticator(
    context: Context,
    loginGateway: Lazy<LoginGateway>,
    sessionManager: SessionManager
): OkHttpClient.Builder {
    return this.addInterceptor(ChuckInterceptor(context))
        .addInterceptor(TokenInterceptor(sessionManager))
        .authenticator(TokenAuthenticator(loginGateway, sessionManager))
}

fun Retrofit.Builder.addConvertAndCallAdapterFactory(): Retrofit.Builder {
    return this.addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
}


