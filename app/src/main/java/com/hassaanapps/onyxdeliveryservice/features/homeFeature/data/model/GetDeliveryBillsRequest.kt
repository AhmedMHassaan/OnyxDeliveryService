package com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.model

import com.google.gson.annotations.SerializedName


data class GetDeliveryBillsRequest(
    @SerializedName("Value")
    val value: GetDeliveryBillsRequestBody?
)

data class GetDeliveryBillsRequestBody(
    @SerializedName("P_DLVRY_NO")
    val deliveryNo: String?,
    @SerializedName("P_LANG_NO")
    val languageNo: String?,

    @SerializedName("P_BILL_SRL")
    val billSerial: String?,

    @SerializedName("P_PRCSSD_FLG")
    val processFlag: String,
)