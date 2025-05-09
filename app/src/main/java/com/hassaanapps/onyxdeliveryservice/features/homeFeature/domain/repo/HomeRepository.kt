package com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.repo

import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.BillsItemSelectTypes
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.DeliveryBill

interface HomeRepository {

    suspend fun getRemoteDeliveryBills(
        selectType: BillsItemSelectTypes,
        deliveryNo: String?,
        languageNo: String?,
        billSerial: String?,
        processFlag: String,
    ): List<DeliveryBill>

    suspend fun getLocalDeliveryBills(
        selectType: BillsItemSelectTypes,
        deliveryNo: String?,
        languageNo: String?,
        billSerial: String?,
        processFlag: String,
    ): List<DeliveryBill>


    suspend fun clearCachedBills()


}