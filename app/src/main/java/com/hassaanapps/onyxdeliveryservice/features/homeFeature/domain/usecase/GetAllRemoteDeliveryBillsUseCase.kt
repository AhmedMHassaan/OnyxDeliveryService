package com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.usecase

import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.BillsItemSelectTypes
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.DeliveryBill
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.repo.HomeRepository
import com.hassaanapps.onyxdeliveryservice.shared.domain.BaseFlowUseCase
import com.hassaanapps.onyxdeliveryservice.shared.domain.usecase.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllRemoteDeliveryBillsUseCase(
    private val repo: HomeRepository
) :
    BaseFlowUseCase<GetAllRemoteDeliveryBillsUseCase.GetBillsRequest, List<DeliveryBill>>() {

    override fun execute(request: GetBillsRequest): Flow<Resource<List<DeliveryBill>>> =
        flow {
            val result = repo.getRemoteDeliveryBills(
                selectType = request.selectType,
                deliveryNo = request.deliveryNo,
                languageNo = request.languageNo,
                billSerial = request.billSerial,
                processFlag = request.processFlag,
            )

            emit(Resource.Success(result))
        }

    data class GetBillsRequest(
        val selectType: BillsItemSelectTypes,
        val deliveryNo: String?,
        val languageNo: String?,
        val billSerial: String?,
        val processFlag: String,
    )
}