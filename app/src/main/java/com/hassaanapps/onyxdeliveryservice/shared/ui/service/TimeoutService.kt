package com.hassaanapps.onyxdeliveryservice.shared.ui.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.hassaanapps.onyxdeliveryservice.shared.ui.SessionTimeoutManager

class TimeoutService : Service() {


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val timeUntilFinish = SessionTimeoutManager.currentTime
        Log.d("APP_TAG", " TimeoutService - onStartCommand: time untill finish is $timeUntilFinish")
        if (timeUntilFinish != SessionTimeoutManager.MAX_TIMEOUT) {
            SessionTimeoutManager.apply {
                startTimer(timeUntilFinish)
                onSessionExpired = {
                    forceLogout = true
                }
            }
        }
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}