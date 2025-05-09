package com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.model


import com.google.gson.annotations.SerializedName

data class DeliveryBillsResponse(
    @SerializedName("DeliveryBills")
    val deliveryBills: List<DeliveryBillsResponseItem>?
)

data class DeliveryBillsResponseItem(
    @SerializedName("BILL_AMT")
    val billAmount: String?,
    @SerializedName("BILL_DATE")
    val billDate: String?,
    @SerializedName("BILL_NO")
    val billNumber: String?,
    @SerializedName("BILL_SRL")
    val billSerial: String?,
    @SerializedName("BILL_TIME")
    val billTime: String?,
    @SerializedName("BILL_TYPE")
    val billType: String?,
    @SerializedName("CSTMR_ADDRSS")
    val customerAddress: String?,
    @SerializedName("CSTMR_APRTMNT_NO")
    val customerApartmentNumber: String?,
    @SerializedName("CSTMR_BUILD_NO")
    val customerBuildNumber: String?,
    @SerializedName("CSTMR_FLOOR_NO")
    val customerFloorNumber: String?,
    @SerializedName("CSTMR_NM")
    val customerName: String?,
    @SerializedName("DLVRY_AMT")
    val deliveryAmount: String?,
    @SerializedName("DLVRY_STATUS_FLG")
    val deliveryStatusFlag: String?,
    @SerializedName("LATITUDE")
    val latitude: String?,
    @SerializedName("LONGITUDE")
    val longitude: String?,
    @SerializedName("MOBILE_NO")
    val mobileNumber: String?,
    @SerializedName("RGN_NM")
    val regionName: String?,
    @SerializedName("TAX_AMT")
    val taxAmount: String?
)