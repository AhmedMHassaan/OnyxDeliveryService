package com.hassaanapps.onyxdeliveryservice.features.loginFeature.data.model


import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("Value")
    val value: LoginRequestBody?
)

data class LoginRequestBody(
    @SerializedName("P_DLVRY_NO")
    val deliveryNo: String?,
    @SerializedName("P_LANG_NO")
    val languageNo: String?,
    @SerializedName("P_PSSWRD")
    val password: String?
)