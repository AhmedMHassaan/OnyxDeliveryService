package com.hassaanapps.onyxdeliveryservice.di

import com.hassaanapps.onyxdeliveryservice.features.loginFeature.ui.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
    viewModelOf(::LoginViewModel)

}