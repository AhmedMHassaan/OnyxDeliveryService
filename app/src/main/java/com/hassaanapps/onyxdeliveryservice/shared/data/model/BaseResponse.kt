package com.hassaanapps.onyxdeliveryservice.shared.data.model


import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("Data")
    val responseData: T?,
    @SerializedName("Result")
    val error: BaseResponseError?
)

data class BaseResponseError(
    @SerializedName("ErrMsg")
    val errMsg: String?,
    @SerializedName("ErrNo")
    val errNo: Int?
)
