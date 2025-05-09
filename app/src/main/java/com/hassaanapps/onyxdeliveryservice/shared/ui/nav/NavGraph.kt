package com.hassaanapps.onyxdeliveryservice.shared.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.ui.HomeScreen
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.ui.HomeViewModel
import com.hassaanapps.onyxdeliveryservice.features.loginFeature.ui.LoginScreen
import com.hassaanapps.onyxdeliveryservice.features.loginFeature.ui.LoginViewModel
import com.hassaanapps.onyxdeliveryservice.features.splashScreenFeature.ui.SplashScreen
import org.koin.compose.koinInject

@Composable
fun NavigationStack(navController: NavHostController) {
    NavHost(
        navController = navController,
//        startDestination = ScreensDestinations.SplashScreen.route
        startDestination = ScreensDestinations.Home.withDeliveryName("أحمد محمد حسان")
    ) {


        composable(
            route = ScreensDestinations.Home.route,
        ) { backStackEntry ->

            val deliveryName = backStackEntry.arguments?.getString("deliveryName") ?: ""
            val homeViewModel: HomeViewModel = koinInject()

            HomeScreen(homeViewModel = homeViewModel, deliveryName = deliveryName)
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
                navController
            )
        }


    }
}