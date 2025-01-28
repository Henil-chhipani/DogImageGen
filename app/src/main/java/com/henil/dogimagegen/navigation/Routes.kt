package com.henil.dogimagegen.navigation

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Generate : Routes("generate")
    object Images : Routes("Images")

}