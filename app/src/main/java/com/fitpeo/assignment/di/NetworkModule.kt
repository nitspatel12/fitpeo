package com.fitpeo.assignment.di

import com.fitpeo.assignment.api.PhotoService
import com.fitpeo.assignment.network.RetrofitHelper
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit{
        return RetrofitHelper.getRetrofit()
    }

    @Singleton
    @Provides
    fun providesPhotoService(retrofit: Retrofit): PhotoService{
        return retrofit.create(PhotoService::class.java)
    }
}