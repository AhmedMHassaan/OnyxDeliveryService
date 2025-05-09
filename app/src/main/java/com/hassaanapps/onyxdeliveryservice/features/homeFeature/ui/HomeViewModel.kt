package com.hassaanapps.onyxdeliveryservice.features.homeFeature.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.BillsItemSelectTypes
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.DeliveryBill
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.usecase.GetAllRemoteDeliveryBillsUseCase
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.usecase.GetLocalNewsDeliveryBillsUseCase
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.usecase.GetLocalOtherDeliveryBillsUseCase
import com.hassaanapps.onyxdeliveryservice.shared.ui.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val getAllRemoteDeliveryBillsUseCase: GetAllRemoteDeliveryBillsUseCase,
    private val getLocalNewsDeliveryBillsUseCase: GetLocalNewsDeliveryBillsUseCase,
    private val getLocalOtherDeliveryBillsUseCase: GetLocalOtherDeliveryBillsUseCase,

    ) : BaseViewModel() {

    private val _billItems = MutableStateFlow<List<DeliveryBill>?>(null)
    val billItemsStateFlow: StateFlow<List<DeliveryBill>?> = _billItems

    private val _error = mutableStateOf<String?>(null)
    val errorStates: State<String?> = _error

    private val _loading = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loading


    private fun fetchRemoteBills(
        selectTypes: BillsItemSelectTypes,
        deliveryNo: String?,
        langNo: String?
    ) {

        getAllRemoteDeliveryBillsUseCase.invoke(
            GetAllRemoteDeliveryBillsUseCase.GetBillsRequest(
                selectType = selectTypes,
                deliveryNo = deliveryNo,
                languageNo = langNo,
                "",
                ""
            )
        ).dataHandling(
            success = {
                Log.d("APP_TAG", " HomeViewModel - fetchRemoteBills: ${it.size}")
                _billItems.tryEmit(it)
            },
            showError = {
                Log.d("APP_TAG", " HomeViewModel - fetchRemoteBills: error ${it.message}")
                _error.value = it.message
            },
            showLoading = {
                _loading.value = it
            }
        )
    }

    fun getLocalBills(
        selectType: BillsItemSelectTypes,
        deliveryNo: String? ,
        langNo: String?
    ) {
        val userCase = when (selectType) {
            BillsItemSelectTypes.NEW -> getLocalNewsDeliveryBillsUseCase.invoke(
                GetLocalNewsDeliveryBillsUseCase.GetBillsRequest(
                    deliveryNo = deliveryNo,
                    languageNo = langNo,
                    billSerial = "",
                    processFlag = ""
                )
            )

            BillsItemSelectTypes.OTHER -> getLocalOtherDeliveryBillsUseCase.invoke(
                GetLocalOtherDeliveryBillsUseCase.GetBillsRequest(
                    deliveryNo = deliveryNo,
                    languageNo = langNo,
                    billSerial = "",
                    processFlag = ""
                )
            )
        }
        userCase.dataHandling(
            success = {
                if (it.isEmpty()) {
                    fetchRemoteBills(selectType, deliveryNo, langNo)
                } else {
                    _billItems.tryEmit(it)
                }
            },
            showLoading = {
                _loading.value = it
            },
            showError = {
                _error.value = it.message
            }
        )

    }


}