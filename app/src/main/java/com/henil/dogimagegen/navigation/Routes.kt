package com.henil.dogimagegen.navigation

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object Generate : Routes("generate")
    data object Images : Routes("Images")
}