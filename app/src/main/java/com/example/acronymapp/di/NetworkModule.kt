package com.example.acronymapp.di

import com.example.acronymapp.data.repository.AcronymRepo
import com.example.acronymapp.data.repository.AcronymRepoImpl
import com.example.acronymapp.data.services.AcronymServices
import com.example.acronymapp.usecase.MeaningUseCase
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import kotlin.math.log


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
//    @Provides
//    fun provideRetrofit(
//        client: OkHttpClient,
//        moshi: Moshi
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(AcronymServices.BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create(Gson()))
//            .build()
//    }

    @Provides
    fun provideAcronymServices(
        //retrofit: Retrofit
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): AcronymServices = Retrofit.Builder()
        .baseUrl(AcronymServices.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
        .create(AcronymServices::class.java)

    @Provides
    fun provideClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideLoggingIntercepter(): HttpLoggingInterceptor =
         HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideMoshi() : Moshi =
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

//    @Provides
//    fun provideRepo(
//        acronymServices: AcronymServices
//    ): AcronymRepo {
//        return AcronymRepoImpl(acronymServices)
//    }

//    @Provides
//    fun provideMeaningUseCase(
//        repo: AcronymRepo
//    ): MeaningUseCase {
//        return MeaningUseCase(repo)
//    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    abstract fun bindRepository(
        acronymRepoImpl: AcronymRepoImpl
    ): AcronymRepo
}

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule{

    @Provides
    fun provideMeaningUseCase(repo: AcronymRepo): MeaningUseCase =
        MeaningUseCase(repo)

    @Provides
    @Named("io")
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Named("default")
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}