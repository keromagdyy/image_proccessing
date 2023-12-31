package com.kerolosmagdy.imageproccessing.di

import com.kerolosmagdy.imageproccessing.data.api.CharactersAPI
import com.kerolosmagdy.imageproccessing.data.util.ConstantLinks
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .writeTimeout(60L, TimeUnit.SECONDS)
        }.build()
         return Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .baseUrl(ConstantLinks.BASE_URL)
             .client(client)
             .build()
    }

    @Singleton
    @Provides
    fun provideMarvelAPIService(retrofit: Retrofit): CharactersAPI{
        return retrofit.create(CharactersAPI::class.java)
    }



}













