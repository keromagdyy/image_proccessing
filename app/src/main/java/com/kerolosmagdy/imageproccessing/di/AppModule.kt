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

//@Module
//@InstallIn(SingletonComponent::class)
class AppModule {

//    @Provides
//    @Singleton
//    fun provideMarvelApi(): Retrofit {
//        val interceptor = HttpLoggingInterceptor().apply {
//            this.level = HttpLoggingInterceptor.Level.BODY
//        }
//        val client = OkHttpClient.Builder().apply {
//            this.addInterceptor(interceptor)
//                .connectTimeout(300, TimeUnit.SECONDS)
//                .readTimeout(3000, TimeUnit.SECONDS)
//                .writeTimeout(3000, TimeUnit.SECONDS)
//        }.build()
//        return Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(ConstantLinks.BASE_URL)
//            .client(client)
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideNewsAPIService(retrofit: Retrofit):CharactersAPI{
//        return retrofit.create(CharactersAPI::class.java)
//    }

}