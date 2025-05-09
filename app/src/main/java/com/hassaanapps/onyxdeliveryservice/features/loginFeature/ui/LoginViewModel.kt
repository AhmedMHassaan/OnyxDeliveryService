package com.hassaanapps.onyxdeliveryservice.features.loginFeature.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.hassaanapps.onyxdeliveryservice.features.loginFeature.domain.usecases.CheckLoginUseCase
import com.hassaanapps.onyxdeliveryservice.features.loginFeature.domain.usecases.ClearCachedBillsUseCase
import com.hassaanapps.onyxdeliveryservice.shared.ui.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(
    private val checkLoginUseCase: CheckLoginUseCase,
    private val clearCachedBillsUseCase: ClearCachedBillsUseCase
) : BaseViewModel() {
    init {
        clearAllCachedBills()
    }

    private fun clearAllCachedBills() {
        clearCachedBillsUseCase.invoke(null).dataHandling(
            success = {}
        )
    }

    private val _successLoginState = MutableStateFlow<String?>(null)
    val successLoginState: StateFlow<String?> = _successLoginState

    private val _error = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _error

    private val _loading = mutableStateOf<Boolean>(false)
    val loadingState: State<Boolean> = _loading


    fun login(userId: String, password: String, languageNo: String) {
        clearAllCachedBills() // to check that all data is removed
        checkLoginUseCase.invoke(
            CheckLoginUseCase.LoginRequest(
                userId, password, languageNo
            )
        ).dataHandling(
            success = { deliveryName ->
                deliveryName?.let {
                    _successLoginState.value = deliveryName
                    _error.value = null // to reset error value
                } ?: run {
                    _error.value = "Empty user data"
                }

            },
            showError = {
                it.message?.let { exMsg ->
                    resetStates()
                    _error.value = exMsg
                }
            },
            showLoading = {
                _loading.value = it
            }
        )
    }

    fun resetStates() {
        _error.value = null
        _successLoginState.value = null
        _loading.value = false
    }

}