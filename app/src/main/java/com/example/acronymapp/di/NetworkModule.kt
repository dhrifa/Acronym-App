package com.example.acronymapp.di

import com.example.acronymapp.data.repository.Repo
import com.example.acronymapp.data.repository.RepoImpl
import com.example.acronymapp.data.services.AcronymServices
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideLoggingIntercepter(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient{
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor( loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AcronymServices.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    @Provides
    fun provideAcronymServices(
        retrofit: Retrofit
    ):AcronymServices{
        return retrofit.create(AcronymServices::class.java)
    }

    @Provides
    fun provideRepo(
        acronymServices: AcronymServices
    ):Repo{
        return RepoImpl(acronymServices)
    }
}