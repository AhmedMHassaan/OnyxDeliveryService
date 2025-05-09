package com.hassaanapps.onyxdeliveryservice.shared.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hassaanapps.onyxdeliveryservice.shared.domain.model.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart


abstract class BaseViewModel : ViewModel() {

    /*val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("APP_TAG", " BaseViewModel - CoroutineExceptionHandler: error happended $throwable")
    }*/


    inline fun <T> Flow<Resource<T>>.dataHandling(

        crossinline success: (data: T) -> Unit,
        crossinline showLoading: (loading: Boolean) -> Unit = {},
        crossinline showError: (error: Throwable) -> Unit = {},
    ): Job {


        return this.onEach {
            when (it) {
                is Resource.Success -> {
                    success.invoke(it.data)
                }

                is Resource.Loading -> {
                    showLoading.invoke(it.isLoading)
                }

                is Resource.Error -> {
                    Log.d("APP_TAG", "BaseViewModel - dataHandling: error here is ${it.error}")

//                    when (it.error) {
                    /*is GoToLoginException -> sendThrowableToCoroutineHandler(
                        coroutineExceptionHandler,
                        it.error
                    )
                    */
                    showError.invoke(it.error)
//                    }
                }
            }
        }.catch {
            Log.d("APP_TAG", "BaseViewModel - dataHandling: error catch here is $it")

            showError.invoke(it)

        }.onStart {
            showLoading.invoke(true)
        }

            .onCompletion {
                showLoading.invoke(false)

            }.launchIn(viewModelScope)
    }


    /*suspend fun sendThrowableToCoroutineHandler(
        exceptionHandler: CoroutineExceptionHandler, error: Throwable
    ) {

        exceptionHandler.handleException(
            currentCoroutineContext(), error
        )
    }*/

}