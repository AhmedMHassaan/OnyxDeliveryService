package com.hassaanapps.onyxdeliveryservice.features.loginFeature.ui

import android.util.Log
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    fun login(userId: String, password: String) {
        Log.d("APP_TAG", " LoginViewModel - login: login using $userId, $password")
    }

}