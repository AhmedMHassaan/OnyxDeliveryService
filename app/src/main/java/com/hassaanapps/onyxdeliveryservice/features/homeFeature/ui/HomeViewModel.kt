package com.hassaanapps.onyxdeliveryservice.features.homeFeature.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.DeliveryBill
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.usecase.GetAllRemoteDeliveryBillsUseCase
import com.hassaanapps.onyxdeliveryservice.shared.ui.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val getAllRemoteDeliveryBillsUseCase: GetAllRemoteDeliveryBillsUseCase
) : BaseViewModel() {

    private val _billItems = MutableStateFlow<List<DeliveryBill>?>(null)
    val billItemsStateFlow: StateFlow<List<DeliveryBill>?> = _billItems

    private val _error = mutableStateOf<String?>(null)
    val errorStates: State<String?> = _error

    private val _loading = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loading


    init {
        if (_billItems.value == null) {
            fetchBills()
        }

    }

    private fun fetchBills(deliveryNo: String? = "1010", langNo: String? = "1") {

        getAllRemoteDeliveryBillsUseCase.invoke(
            GetAllRemoteDeliveryBillsUseCase.GetBillsRequest(
                deliveryNo = deliveryNo,
                languageNo = langNo,
                "",
                ""
            )
        ).dataHandling(
            success = {
                _billItems.tryEmit(it)
            },
            showError = {
                _error.value = it.message
            },
            showLoading = {
                _loading.value = it
            }
        )
    }

}