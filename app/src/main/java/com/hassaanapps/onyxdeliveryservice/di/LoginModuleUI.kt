package com.hassaanapps.onyxdeliveryservice.di

import com.hassaanapps.onyxdeliveryservice.features.loginFeature.data.api.LoginApi
import com.hassaanapps.onyxdeliveryservice.features.loginFeature.data.repo.LoginRepositoryImpl
import com.hassaanapps.onyxdeliveryservice.features.loginFeature.domain.repo.LoginRepository
import com.hassaanapps.onyxdeliveryservice.features.loginFeature.domain.usecases.CheckLoginUseCase
import com.hassaanapps.onyxdeliveryservice.features.loginFeature.ui.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val loginModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://mdev.yemensoft.net:8087/OnyxDeliveryService/")
            .build()
    }
    single<LoginApi> {
        (get() as Retrofit).create(LoginApi::class.java)
    }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single <CheckLoginUseCase>{ CheckLoginUseCase(repo = get()) }
    viewModelOf(::LoginViewModel)
}