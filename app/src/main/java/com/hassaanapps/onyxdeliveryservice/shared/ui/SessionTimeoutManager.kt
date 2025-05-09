package com.hassaanapps.onyxdeliveryservice.shared.ui

import android.os.CountDownTimer

object SessionTimeoutManager {
    const val MAX_TIMEOUT = 2 * 60 * 1000L  // equal 2 min

    //    const val MAX_TIMEOUT = 5000L // 5 seconds for test
    private var timer: CountDownTimer? = null
    var onSessionExpired: (() -> Unit)? = null
    var currentTime: Long = 0L // init value

    var forceLogout = false

    fun startTimer(timeout: Long = MAX_TIMEOUT) {
        timer?.cancel()
        timer = object : CountDownTimer(timeout, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentTime = millisUntilFinished
                /*Log.d(
                    "APP_TAG",
                    " SessionTimeoutManager - onTick: tike unitle $currentTime"
                )*/
            }

            override fun onFinish() {
                onSessionExpired?.invoke()
            }
        }.start()
    }

    fun resetTimer() {
        startTimer()
    }

    fun stopTimer() {
        timer?.cancel()
    }
}
