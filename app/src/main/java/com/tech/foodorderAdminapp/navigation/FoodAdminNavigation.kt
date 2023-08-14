package com.tech.foodorderAdminapp.navigation

import android.app.Activity.RESULT_OK
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.android.gms.auth.api.identity.Identity
import com.tech.foodorderAdminapp.firebase.firebaseAuth.googleSignIn.GoogleAuthUiClient
import com.tech.foodorderAdminapp.firebase.firebaseAuth.googleSignIn.UserData
import com.tech.foodorderAdminapp.firebase.firebaseAuth.ui.AuthViewModel
import com.tech.foodorderAdminapp.screens.HomeScreen
import com.tech.foodorderAdminapp.screens.LoginScreen
import com.tech.foodorderAdminapp.screens.SignupScreen
import com.tech.foodorderAdminapp.screens.SplashScreen
import com.tech.foodorderAdminapp.screens.category.AddMenuScreen
import com.tech.foodorderAdminapp.screens.category.AllItemMenuScreen
import com.tech.foodorderAdminapp.screens.category.CreateUserAdminScreen
import com.tech.foodorderAdminapp.screens.category.OutForDeliveryScreen
import com.tech.foodorderAdminapp.screens.category.PendingOrderScreen
import com.tech.foodorderAdminapp.screens.category.ProfileScreen
import kotlinx.coroutines.launch

@Composable
fun FoodAdminNavigation(context: Context) {

    val navHostController = rememberNavController()
    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    NavHost(navController = navHostController, startDestination = splash) {

        composable(login) {
            val authViewModel: AuthViewModel = hiltViewModel()
            val state by authViewModel.state.collectAsStateWithLifecycle()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == RESULT_OK) {
                        it.lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            authViewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )
            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {  //get all the the of google signIn
                    navHostController.navigate(home)

                    val user: UserData? =
                        googleAuthUiClient.getSignedInUser()   //all data get from signedInUser
                    Log.d("@@@@", "User uid: ${user?.userId}")
                    Log.d("@@@@", "User name: ${user?.userName}")
                    Log.d("@@@@", "User photo url: ${user?.profilePictureUrl}")

                    Toast.makeText(context, "Sign in Successful", Toast.LENGTH_LONG).show()
                }
            }

            LoginScreen(state = state, onSignInClick = {
                it.lifecycleScope.launch {
                    val signInIntentSender = googleAuthUiClient.signIn()
                    launcher.launch(
                        IntentSenderRequest
                            .Builder(signInIntentSender ?: return@launch)
                            .build()
                    )
                }
            }, navHostController = navHostController)
        }
        composable(splash) {
            SplashScreen(context = context, navHostController = navHostController)
        }
        composable(signup) {
            SignupScreen(navHostController = navHostController)
        }
        composable(home) {
            HomeScreen(navHostController = navHostController)
        }
        composable(add_menu) {
            AddMenuScreen(navHostController = navHostController)
        }
        composable(all_menu_show) {
            AllItemMenuScreen(navHostController = navHostController)
        }
        composable(out_for_delivery) {
            OutForDeliveryScreen(navHostController = navHostController)
        }
        composable(profile) {
            ProfileScreen(navHostController = navHostController)
        }
        composable(create_user_admin) {
            CreateUserAdminScreen(navHostController = navHostController)
        }
        composable(pending_order) {
            PendingOrderScreen(navHostController = navHostController)
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
const val pending_order = "pending_order_screen"