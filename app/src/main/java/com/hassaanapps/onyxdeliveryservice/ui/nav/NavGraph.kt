package com.hassaanapps.onyxdeliveryservice.ui.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hassaanapps.onyxdeliveryservice.features.loginFeature.ui.LoginScreen
import com.hassaanapps.onyxdeliveryservice.features.loginFeature.ui.LoginViewModel
import com.hassaanapps.onyxdeliveryservice.features.splashScreenFeature.ui.SplashScreen
import org.koin.compose.koinInject

@Composable
fun NavigationStack(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreensDestinations.SplashScreen.route
//        startDestination = ScreensDestinations.Login.route
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
            SplashScreen(1000L) {
                navController.navigate(ScreensDestinations.Login.route) {
                    popUpTo(ScreensDestinations.SplashScreen.route) {
                        inclusive = true
                    }
                }
            }
        }

        composable(route = ScreensDestinations.Login.route) {
            val loginViewModel: LoginViewModel = koinInject()
            LoginScreen(
                loginViewModel = loginViewModel,
                onShowMoreClick = { },
                onLanguageIconClicked = {},
                navController
            )
        }


    }
}