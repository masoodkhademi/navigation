package com.mineobank.app.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mineobank.app.data.local.TokenManager
import com.mineobank.app.ui.auth.LoginScreen
import com.mineobank.app.ui.components.MineoBottomBar
import com.mineobank.app.ui.home.HomeScreen
import com.mineobank.app.ui.investment.InvestmentScreen
import com.mineobank.app.ui.profile.ProfileScreen
import com.mineobank.app.ui.services.ServicesScreen
import com.mineobank.app.ui.wallet.WalletScreen

@Composable
fun MineoNavHost(
    navController: NavHostController,
    startDestination: String,
    tokenManager: TokenManager
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            MainScaffold(navController = navController) {
                HomeScreen()
            }
        }

        composable(Screen.Wallet.route) {
            MainScaffold(navController = navController) {
                WalletScreen()
            }
        }

        composable(Screen.Services.route) {
            MainScaffold(navController = navController) {
                ServicesScreen()
            }
        }

        composable(Screen.Investment.route) {
            MainScaffold(navController = navController) {
                InvestmentScreen()
            }
        }

        composable(Screen.Profile.route) {
            MainScaffold(navController = navController) {
                ProfileScreen(
                    tokenManager = tokenManager,
                    onLogout = {
                        tokenManager.clearAll()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun MainScaffold(
    navController: NavController,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = { MineoBottomBar(navController = navController) }
    ) { paddingValues ->
        androidx.compose.foundation.layout.Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            content()
        }
    }
}
