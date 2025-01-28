package com.henil.dogimagegen.navigation

import androidx.annotation.NavigationRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.henil.dogimagegen.ui.screen.genratescreen.GenerateScreen
import com.henil.dogimagegen.ui.screen.homescreen.HomeScreen
import com.henil.dogimagegen.ui.screen.imagescreen.ImageScreen

@Composable
fun Navigation(navHostController: NavHostController) {
    val context = LocalContext.current

    CompositionLocalProvider(GlobalNavController provides navHostController) {
        NavHost( navController = navHostController, startDestination = Routes.Home.route) {
            composable(route = Routes.Home.route){
                HomeScreen()
            }
            composable(route = Routes.Generate.route) {
                GenerateScreen()
            }
            composable(route = Routes.Images.route) {
                ImageScreen()
            }
        }
    }
}

val GlobalNavController = staticCompositionLocalOf<NavHostController> {
    error("NavController not provided")
}