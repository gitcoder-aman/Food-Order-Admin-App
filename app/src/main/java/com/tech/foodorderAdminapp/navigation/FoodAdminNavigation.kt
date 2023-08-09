package com.tech.foodorderAdminapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tech.foodorderAdminapp.screens.HomeScreen
import com.tech.foodorderAdminapp.screens.LoginScreen
import com.tech.foodorderAdminapp.screens.SignupScreen
import com.tech.foodorderAdminapp.screens.SplashScreen
import com.tech.foodorderAdminapp.screens.category.AddMenuScreen
import com.tech.foodorderAdminapp.screens.category.AllItemMenuScreen
import com.tech.foodorderAdminapp.screens.category.CreateUserAdminScreen
import com.tech.foodorderAdminapp.screens.category.OutForDeliveryScreen
import com.tech.foodorderAdminapp.screens.category.ProfileScreen

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
        composable(add_menu){
            AddMenuScreen(navHostController = navHostController )
        }
        composable(all_menu_show){
            AllItemMenuScreen(navHostController = navHostController )
        }
        composable(out_for_delivery){
            OutForDeliveryScreen(navHostController = navHostController )
        }
        composable(profile){
            ProfileScreen(navHostController = navHostController )
        }
        composable(create_user_admin){
            CreateUserAdminScreen(navHostController = navHostController )
        }
    }
}

const val splash = "start_screen"
const val login = "login_screen"
const val signup = "signup_screen"
const val home = "home_screen"
const val add_menu = "add_menu_screen"
const val all_menu_show = "all_menu_show_screen"
const val out_for_delivery = "out_for_delivery_screen"
const val profile = "profile_screen"
const val create_user_admin = "create_user_admin_screen"