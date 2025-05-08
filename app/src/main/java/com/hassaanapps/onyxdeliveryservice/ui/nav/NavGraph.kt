package com.hassaanapps.onyxdeliveryservice.ui.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hassaanapps.onyxdeliveryservice.features.splashScreenFeature.ui.SplashScreen

@Composable
fun NavigationStack(navController:NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreensDestinations.SplashScreen.route
    ) {



        composable(
            route = ScreensDestinations.Home.route,
        ) {
            Column {
                Text(
                    text = "Hello Home!",
                )
            }
        }


        composable(route = ScreensDestinations.SplashScreen.route) {
            SplashScreen(2000L) {
                navController.navigate(ScreensDestinations.Home.route) {
                    popUpTo(ScreensDestinations.SplashScreen.route) {
                        inclusive = true
                    }
                }
            }
        }



    }
}