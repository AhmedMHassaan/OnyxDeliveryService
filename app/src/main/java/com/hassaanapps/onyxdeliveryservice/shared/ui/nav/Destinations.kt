package com.hassaanapps.onyxdeliveryservice.shared.ui.nav


sealed class ScreensDestinations(val route: String) {
    data object SplashScreen : ScreensDestinations("splash")

    data object Home : ScreensDestinations("home/{deliveryName}/{userId}") {
        fun withDeliveryName(deliveryName: String, userId: String) = "home/$deliveryName/$userId"
    }

    data object Login : ScreensDestinations("login")
}