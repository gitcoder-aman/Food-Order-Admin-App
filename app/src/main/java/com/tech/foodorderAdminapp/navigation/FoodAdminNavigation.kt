package com.tech.foodorderAdminapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tech.foodorderAdminapp.screens.HomeScreen
import com.tech.foodorderAdminapp.screens.LoginScreen
import com.tech.foodorderAdminapp.screens.SignupScreen
import com.tech.foodorderAdminapp.screens.SplashScreen

@Composable
fun FoodAdminNavigation(){
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = splash ){
        composable(login){
            LoginScreen(navHostController = navHostController )
        }
        composable(splash){
            SplashScreen(navHostController = navHostController )
        }
        composable(signup){
            SignupScreen(navHostController = navHostController )
        }
        composable(home){
            HomeScreen(navHostController = navHostController )
        }
    }
}

const val splash = "start_screen"
const val login = "login_screen"
const val signup = "signup_screen"
const val home = "home_screen"