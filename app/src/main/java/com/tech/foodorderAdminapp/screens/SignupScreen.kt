package com.tech.foodorderAdminapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tech.foodorderAdminapp.R
import com.tech.foodorderAdminapp.common.yeon_sung_regular
import com.tech.foodorderAdminapp.navigation.home
import com.tech.foodorderAdminapp.navigation.login
import com.tech.foodorderAdminapp.navigation.signup
import com.tech.foodorderAdminapp.ui.theme.FoodOrderAppTheme
import com.tech.foodorderAdminapp.ui.theme.GreenColor
import com.tech.foodorderAdminapp.ui.theme.darkWhiteColor

@Composable
fun SignupScreen(navHostController: NavHostController) {

    val scrollState = rememberScrollState()
    var nameOfOwner by remember {
        mutableStateOf("")
    }
    var nameOfRestaurant by remember {
        mutableStateOf("")
    }
    var emailOrPhone by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var location by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkWhiteColor),
        contentAlignment = Alignment.BottomCenter
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = 30.dp, start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Header(stringResource(R.string.sign_up_here_for_you))

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Text(
                    text = stringResource(R.string.choose_your_location), style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        fontFamily = yeon_sung_regular,
                        color = GreenColor
                    )
                )
            }
            TextFieldLayout(
                text = location,
                textFieldTitle = stringResource(R.string.location),
                icon = R.drawable.location,
                onValueChange = {
                    location = it
                })
            Spacer(modifier = Modifier.height(10.dp))

            TextFieldLayout(
                text = nameOfOwner,
                textFieldTitle = stringResource(R.string.name_of_owner),
                icon = R.drawable.usernew,
                onValueChange = {
                    nameOfOwner = it
                })
            Spacer(modifier = Modifier.height(10.dp))

            TextFieldLayout(
                text = nameOfRestaurant,
                textFieldTitle = stringResource(R.string.name_of_restaurant),
                icon = R.drawable.home,
                onValueChange = {
                    nameOfRestaurant = it
                })
            Spacer(modifier = Modifier.height(10.dp))

            TextFieldLayout(
                text = emailOrPhone,
                textFieldTitle = stringResource(R.string.email_or_phone_number),
                icon = R.drawable.mail,
                onValueChange = {
                    emailOrPhone = it
                })
            Spacer(modifier = Modifier.height(10.dp))

            TextFieldLayout(
                text = password,
                textFieldTitle = stringResource(id = R.string.password),
                icon = R.drawable.lock,
                onValueChange = {
                    password = it
                })

            Spacer(modifier = Modifier.height(10.dp))

            LoginBtnAndText(stringResource(R.string.create_account),
                stringResource(R.string.already_have_an_account), noAccount = {
                    navHostController.navigate(login)
                }, login = {
                    navHostController.navigate(home)
                })

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
}

@Preview(showBackground = true)
@Composable
fun SignupPreview() {
    FoodOrderAppTheme {
        val navHostController = rememberNavController()
        SignupScreen(navHostController = navHostController)
    }
}