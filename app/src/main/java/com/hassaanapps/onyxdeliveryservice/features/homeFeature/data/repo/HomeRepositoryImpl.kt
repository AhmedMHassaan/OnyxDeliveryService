package com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.repo

import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.mapToDomain
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.model.GetDeliveryBillsRequest
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.model.GetDeliveryBillsRequestBody
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.remote.api.HomeApi
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.DeliveryBill
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.repo.HomeRepository

class HomeRepositoryImpl(
//    private val roomDao:,
    private val api: HomeApi
) : HomeRepository {

    override suspend fun getRemoteDeliveryBills(
        deliveryNo: String?,
        languageNo: String?,
        billSerial: String?,
        processFlag: String
    ): List<DeliveryBill> {
        val response = api.getDeliveryBillsItems(
            GetDeliveryBillsRequest(
                GetDeliveryBillsRequestBody(
                    deliveryNo = deliveryNo,
                    languageNo = languageNo,
                    billSerial = billSerial,
                    processFlag = processFlag,
                )
            )
        )

        if (response?.error?.errNo == 0) {
//            val cachedItems =
            return response.responseData?.deliveryBills?.map { it.mapToDomain() }
                ?: emptyList()
        } else {
            throw Exception(response?.error?.errMsg ?: "")
        }
    }
}