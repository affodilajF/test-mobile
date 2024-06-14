package com.example.one.di

import com.example.one.data.remote.UserApiService
import com.example.one.data.repository.UserApiRepositoryImpl
import com.example.one.domain.repository.UserApiRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideBaseUrl() = "https://reqres.in"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): UserApiService = retrofit.create(UserApiService::class.java)

    @Provides
    @Singleton
    fun provideUserApiRepository(apiService: UserApiService): UserApiRepository =
        UserApiRepositoryImpl(apiService)


}