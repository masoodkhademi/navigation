package com.mineobank.app.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object Wallet : Screen("wallet")
    object Services : Screen("services")
    object Investment : Screen("investment")
    object Profile : Screen("profile")
}
