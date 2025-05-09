package com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.repo

import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.DeliveryBill

interface HomeRepository {

    suspend fun getRemoteDeliveryBills(
         deliveryNo: String?,
         languageNo: String?,
         billSerial: String?,
         processFlag: String,
         ): List<DeliveryBill>
}