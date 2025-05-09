package com.hassaanapps.onyxdeliveryservice.di

import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.remote.api.HomeApi
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.repo.HomeRepositoryImpl
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.repo.HomeRepository
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.usecase.GetAllRemoteDeliveryBillsUseCase
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.usecase.GetLocalNewsDeliveryBillsUseCase
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.usecase.GetLocalOtherDeliveryBillsUseCase
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.ui.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {

    single<HomeApi> {
        (get() as Retrofit).create(HomeApi::class.java)
    }

    single<HomeRepository> { HomeRepositoryImpl(get(), get()) }

    single<GetAllRemoteDeliveryBillsUseCase> { GetAllRemoteDeliveryBillsUseCase(repo = get()) }
    single<GetLocalNewsDeliveryBillsUseCase> { GetLocalNewsDeliveryBillsUseCase(repo = get()) }
    single<GetLocalOtherDeliveryBillsUseCase> { GetLocalOtherDeliveryBillsUseCase(repo = get()) }

    viewModel { HomeViewModel(get(), get(), get()) }

}