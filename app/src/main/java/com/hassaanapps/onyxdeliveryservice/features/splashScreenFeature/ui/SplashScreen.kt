package com.hassaanapps.onyxdeliveryservice.features.splashScreenFeature.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hassaanapps.onyxdeliveryservice.ui.theme.SplashScreenBG
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(delayTime: Long, onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(delayTime) // 2-second delay
        onTimeout()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SplashScreenBG)
            .padding(top = 64.dp, bottom = 32.dp),

        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            OnyxServiceLogo(
                modifier = Modifier
                    .width(200.dp)
                    .height(100.dp)
                    .align(Alignment.Center),
            )

        }

        // Delivery Illustration
        DeliveryLogo()
    }
}



