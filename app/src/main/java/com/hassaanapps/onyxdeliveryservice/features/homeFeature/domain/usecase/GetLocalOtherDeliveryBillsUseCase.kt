package com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.usecase

import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.BillsItemSelectTypes
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.DeliveryBill
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.repo.HomeRepository
import com.hassaanapps.onyxdeliveryservice.shared.domain.BaseFlowUseCase
import com.hassaanapps.onyxdeliveryservice.shared.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLocalOtherDeliveryBillsUseCase(
    private val repo: HomeRepository
) :
    BaseFlowUseCase<GetLocalOtherDeliveryBillsUseCase.GetBillsRequest, List<DeliveryBill>>() {

    override fun execute(request: GetBillsRequest): Flow<Resource<List<DeliveryBill>>> =
        flow {
            val result = repo.getLocalDeliveryBills(
                selectType = BillsItemSelectTypes.OTHER,
                deliveryNo = request.deliveryNo,
                languageNo = request.languageNo,
                billSerial = request.billSerial,
                processFlag = request.processFlag,
            )

            emit(Resource.Success(result))
        }

    data class GetBillsRequest(
        val deliveryNo: String?,
        val languageNo: String?,
        val billSerial: String?,
        val processFlag: String,
    )
}