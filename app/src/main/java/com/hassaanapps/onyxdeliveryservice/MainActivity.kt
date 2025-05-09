package com.hassaanapps.onyxdeliveryservice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hassaanapps.onyxdeliveryservice.shared.ui.SessionTimeoutManager
import com.hassaanapps.onyxdeliveryservice.shared.ui.SessionTimeoutManager.forceLogout
import com.hassaanapps.onyxdeliveryservice.shared.ui.nav.NavigationStack
import com.hassaanapps.onyxdeliveryservice.shared.ui.nav.ScreensDestinations
import com.hassaanapps.onyxdeliveryservice.shared.ui.service.TimeoutService
import com.hassaanapps.onyxdeliveryservice.shared.ui.theme.OnyxDeliveryServiceTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        setContent {
            navController = rememberNavController()

            LaunchedEffect(Unit) {
                SessionTimeoutManager.startTimer()
                SessionTimeoutManager.onSessionExpired = {
                    Log.d("APP_TAG", " MainActivity - onCreate: expired")
                    navigate(navController)
                }
            }

            OnyxDeliveryServiceTheme {
                NavigationStack(navController)

            }
        }
    }

    private fun navigate(navController: NavHostController) {
        navController.navigate(ScreensDestinations.Login.route) {
            popUpTo(0) // 0 to remove all items from stack
        }
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        SessionTimeoutManager.resetTimer()
        return super.dispatchTouchEvent(ev)
    }


    override fun onResume() {
        super.onResume()
        if (forceLogout) {
            if (::navController.isInitialized)
                navigate(navController)
            else
                finish()
        } else {
            SessionTimeoutManager.startTimer()
        }
    }

    override fun onPause() {
        super.onPause()
        startService(
            Intent(this, TimeoutService::class.java)
        )
    }
}