package com.hassaanapps.onyxdeliveryservice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hassaanapps.onyxdeliveryservice.shared.ui.nav.NavigationStack
import com.hassaanapps.onyxdeliveryservice.shared.ui.theme.OnyxDeliveryServiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            OnyxDeliveryServiceTheme {
                val navController: NavHostController = rememberNavController()
                NavigationStack(navController)

            }
        }
    }
}