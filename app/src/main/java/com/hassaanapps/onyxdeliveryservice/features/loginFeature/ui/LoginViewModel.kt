package com.hassaanapps.onyxdeliveryservice.features.loginFeature.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.hassaanapps.onyxdeliveryservice.features.loginFeature.domain.usecases.CheckLoginUseCase
import com.hassaanapps.onyxdeliveryservice.shared.ui.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(
    private val checkLoginUseCase: CheckLoginUseCase
) : BaseViewModel() {

    private val _successLoginState = mutableStateOf<String?>(null)
    val successLoginState: State<String?> = _successLoginState

    private val _error = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _error

    private val _loading = mutableStateOf<Boolean>(false)
    val loadingState: State<Boolean> = _loading


    fun login(userId: String, password: String, languageNo: String) {

        checkLoginUseCase.invoke(
            CheckLoginUseCase.LoginRequest(
                userId, password, languageNo
            )
        ).dataHandling(
            success = { deliveryName ->
                deliveryName?.let {
                    _successLoginState.value = deliveryName
                    resetStates()
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

    private fun resetStates() {
        _error.value = null
    }

}