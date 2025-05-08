package com.hassaanapps.onyxdeliveryservice.features.splashScreenFeature.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hassaanapps.onyxdeliveryservice.R


@Composable
@Preview(showBackground = true)
private fun SplashScreenPreview(modifier: Modifier = Modifier) {
    SplashScreen(2000) { }
}


@Composable
fun OnyxServiceLogo(modifier: Modifier) {

    Image(
        painter = painterResource(id = R.drawable.ic_onyx_delivery_logo),
        contentDescription = "Onyx Logo",
        modifier = modifier
            .width(200.dp)
            .height(100.dp),
        contentScale = ContentScale.Fit
    )
}


@Composable
@Preview(showBackground = true)
fun DeliveryLogo() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Image(
            painter = painterResource(id = R.drawable.ic_delivery_logo),
            contentDescription = "Delivery Illustration",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Fit
        )
    }
}