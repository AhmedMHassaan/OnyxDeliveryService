package com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.remote.api

import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.model.DeliveryBillsResponse
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.model.GetDeliveryBillsRequest
import com.hassaanapps.onyxdeliveryservice.shared.data.model.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface HomeApi {

    @POST("Service.svc/GetDeliveryBillsItems")
    suspend fun getDeliveryBillsItems(
        @Body getDeliveryBillsRequest: GetDeliveryBillsRequest
    ): BaseResponse<DeliveryBillsResponse?>?
}