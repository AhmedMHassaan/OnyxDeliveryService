package com.hassaanapps.onyxdeliveryservice.features.loginFeature.domain.repo

interface LoginRepository {

    suspend fun login(
        userId: String,
        password: String,
        languageNo: String
    ): String?

}