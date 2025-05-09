package com.hassaanapps.onyxdeliveryservice.features.loginFeature.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("DeliveryName")
    val deliveryName: String?
)
