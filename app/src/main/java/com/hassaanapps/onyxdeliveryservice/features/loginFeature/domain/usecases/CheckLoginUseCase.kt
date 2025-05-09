package com.hassaanapps.onyxdeliveryservice.features.loginFeature.domain.usecases

import com.hassaanapps.onyxdeliveryservice.features.loginFeature.domain.repo.LoginRepository
import com.hassaanapps.onyxdeliveryservice.shared.domain.BaseFlowUseCase
import com.hassaanapps.onyxdeliveryservice.shared.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckLoginUseCase(private val repo: LoginRepository) : BaseFlowUseCase<
        CheckLoginUseCase.LoginRequest, String?>() {

    override fun execute(request: LoginRequest): Flow<Resource<String?>> = flow {
        val response = repo.login(
            userId = request.userNo,
            password = request.password,
            languageNo = request.langNo,
        )
        emit(Resource.Success(response))  // emit delivery name
    }

    data class LoginRequest(
        val userNo: String,
        val password: String,
        val langNo: String
    )
}