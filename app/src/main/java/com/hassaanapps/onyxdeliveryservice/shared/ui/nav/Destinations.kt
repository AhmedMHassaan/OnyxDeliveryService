package com.hassaanapps.onyxdeliveryservice.shared.ui.nav


sealed class ScreensDestinations(val route: String) {
    data object SplashScreen: ScreensDestinations("splash")

    data object Home : ScreensDestinations("home/{deliveryName}") {
        fun withDeliveryName(deliveryName: String) = "home/$deliveryName"
    }

    data object Login: ScreensDestinations("login")
}