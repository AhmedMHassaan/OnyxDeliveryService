package com.hassaanapps.onyxdeliveryservice.features.loginFeature.domain.usecases

import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.repo.HomeRepository
import com.hassaanapps.onyxdeliveryservice.shared.domain.BaseFlowUseCase
import com.hassaanapps.onyxdeliveryservice.shared.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ClearCachedBillsUseCase(private val repo: HomeRepository) : BaseFlowUseCase<Unit?, Unit>() {
    override fun execute(request: Unit?): Flow<Resource<Unit>> = flow {
        emit(Resource.Success(repo.clearCachedBills()))
    }
}