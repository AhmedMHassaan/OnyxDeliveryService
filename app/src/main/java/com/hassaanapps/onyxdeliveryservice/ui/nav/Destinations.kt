package com.hassaanapps.onyxdeliveryservice.ui.nav


sealed class ScreensDestinations(val route: String) {
    data object SplashScreen: ScreensDestinations("splash")
    data object Home: ScreensDestinations("home")
}