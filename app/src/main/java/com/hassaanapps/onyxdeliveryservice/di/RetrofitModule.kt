package com.hassaanapps.onyxdeliveryservice.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            setLevel(HttpLoggingInterceptor.Level.BODY)

                        }
                    )
                    .build())
            .baseUrl("http://mdev.yemensoft.net:8087/OnyxDeliveryService/")
            .build()
    }
}