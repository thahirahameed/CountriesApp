package com.countriesapp.di

import com.countriesapp.data.CountriesApi
import com.countriesapp.data.repo.CountriesRepoImpl
import com.countriesapp.domain.repo.CountriesRepo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()


    @Provides
    fun providesRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(CountriesApi.BASE_URL)
        .build()


    @Singleton
    @Provides
    fun provideShowsApi(retrofit: Retrofit): CountriesApi = retrofit.create(CountriesApi::class.java)

    @Provides
    @Singleton
    fun providesTubeLineRepository(countriesApi: CountriesApi): CountriesRepo{
        return CountriesRepoImpl(countriesApi)
    }
}