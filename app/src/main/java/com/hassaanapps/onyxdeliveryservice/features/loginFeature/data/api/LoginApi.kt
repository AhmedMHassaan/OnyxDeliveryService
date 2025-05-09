package com.hassaanapps.onyxdeliveryservice.features.loginFeature.data.api

import com.hassaanapps.onyxdeliveryservice.features.loginFeature.data.model.LoginRequest
import com.hassaanapps.onyxdeliveryservice.features.loginFeature.data.model.LoginResponse
import com.hassaanapps.onyxdeliveryservice.shatrd.data.model.BaseResponse
import retrofit2.http.POST

interface LoginApi {

    @POST("/Service.svc/CheckDeliveryLogin")
    suspend fun checkLogin(
        loginRequest: LoginRequest
    ): BaseResponse<LoginResponse>?
}