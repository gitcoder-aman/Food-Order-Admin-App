package com.tech.foodorderAdminapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tech.foodorderAdminapp.R
import com.tech.foodorderAdminapp.common.lato_bold
import com.tech.foodorderAdminapp.common.lato_regular
import com.tech.foodorderAdminapp.common.yeon_sung_regular
import com.tech.foodorderAdminapp.navigation.login
import com.tech.foodorderAdminapp.ui.theme.GreenColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController : NavHostController) {

    LaunchedEffect(key1 = Unit, block = {  //navigate to home screen after 2 sec
        delay(2000)
        navHostController.navigate(login)
    })
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.waves_of_food), style = TextStyle(
                    fontSize = 50.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = yeon_sung_regular
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.admin_dashboard), style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = lato_bold,
                    color = GreenColor
                )
            )
        }
        Text(
            text = stringResource(R.string.design_by_aman_kumar), style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W400,
                fontFamily = yeon_sung_regular,
                color = GreenColor
            ), textAlign = TextAlign.Center, modifier = Modifier.padding(vertical = 20.dp)
        )
    }
}