package com.codeinger.myapplication.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.codeinger.myapplication.network.repository.PostRepository
import com.codeinger.myapplication.network.request.PostRequest
import com.codeinger.myapplication.utils.NetworkConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun getRetrofitServiceInstance(retrofit: Retrofit) : PostRequest{
        return retrofit.create(PostRequest::class.java)
    }

    @Provides
    @Singleton
    fun getPostRepository(retrofit: Retrofit) : PostRepository {
        return PostRepository(getRetrofitServiceInstance(retrofit))
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePreferences(
        application: Application
    ): SharedPreferences {
        return application.getSharedPreferences(
            "store", Context.MODE_PRIVATE
        )
    }
}