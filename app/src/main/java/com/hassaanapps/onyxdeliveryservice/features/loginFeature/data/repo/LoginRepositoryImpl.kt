package com.hassaanapps.onyxdeliveryservice.features.loginFeature.data.repo

import com.hassaanapps.onyxdeliveryservice.features.loginFeature.data.api.LoginApi
import com.hassaanapps.onyxdeliveryservice.features.loginFeature.data.model.LoginRequest
import com.hassaanapps.onyxdeliveryservice.features.loginFeature.data.model.LoginRequestBody
import com.hassaanapps.onyxdeliveryservice.features.loginFeature.domain.repo.LoginRepository

class LoginRepositoryImpl(
    private val api: LoginApi
) : LoginRepository {

    override suspend fun login(userId: String, password: String, languageNo: String): String? {
        val response = api.checkLogin(
            LoginRequest(
                LoginRequestBody(
                    deliveryNo = userId,
                    languageNo = languageNo,
                    password = password
                )
            )
        )

        if (response?.error?.errNo == 0) {
            return response.responseData?.deliveryName
        } else {
            throw Exception(response?.error?.errMsg)
        }
    }
}