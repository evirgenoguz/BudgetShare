package com.evirgenoguz.spendtogether.di

import android.content.Context
import com.evirgenoguz.spendtogether.BuildConfig
import com.evirgenoguz.spendtogether.data.NetworkManager
import com.evirgenoguz.spendtogether.data.service.api.CurrencyApi
import com.evirgenoguz.spendtogether.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkManager(
        @ApplicationContext application: Context,
    ) = NetworkManager(application)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .let {
            if (BuildConfig.DEBUG) {
                it.addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
            } else
                it
        }
        .callTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .pingInterval(5, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    @Singleton
    fun provideCurrencyApi(client: Retrofit): CurrencyApi = client.create(CurrencyApi::class.java)

}