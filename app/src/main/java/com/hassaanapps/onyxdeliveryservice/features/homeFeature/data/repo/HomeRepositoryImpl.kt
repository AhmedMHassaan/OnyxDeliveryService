package com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.repo

import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.local.dao.DeliveryBillsDao
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.mappers.mapToDomain
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.mappers.mapToEntity
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.model.GetDeliveryBillsRequest
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.model.GetDeliveryBillsRequestBody
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.remote.api.HomeApi
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.BillsItemSelectTypes
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.DeliveryBill
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.repo.HomeRepository

class HomeRepositoryImpl(
    private val localBollsDao: DeliveryBillsDao,
    private val api: HomeApi
) : HomeRepository {

    override suspend fun getRemoteDeliveryBills(
        selectType: BillsItemSelectTypes,
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
            val entities = response.responseData?.deliveryBills?.map {
                it.mapToDomain().mapToEntity()
            }

            localBollsDao.cacheAllBills(entities ?: emptyList())


            val cachedItems = when (selectType) {
                BillsItemSelectTypes.NEW -> localBollsDao.getNewBills()
                BillsItemSelectTypes.OTHER -> localBollsDao.getOthersBills()
            }
            return cachedItems.map { it.mapToDomain() }


        } else {
            throw Exception(response?.error?.errMsg ?: "")
        }
    }

    override suspend fun getLocalDeliveryBills(
        selectType: BillsItemSelectTypes,
        deliveryNo: String?,
        languageNo: String?,
        billSerial: String?,
        processFlag: String
    ): List<DeliveryBill> {
        val localBolls = when (selectType) {
            BillsItemSelectTypes.NEW -> localBollsDao.getNewBills()
            BillsItemSelectTypes.OTHER -> localBollsDao.getOthersBills()
        }
        return localBolls.map { it.mapToDomain() }

    }

    override suspend fun clearCachedBills() {
        return localBollsDao.clearAllBills()
    }
}