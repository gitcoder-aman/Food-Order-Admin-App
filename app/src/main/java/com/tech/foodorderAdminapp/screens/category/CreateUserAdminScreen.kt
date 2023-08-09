package com.tech.foodorderAdminapp.screens.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tech.foodorderAdminapp.R
import com.tech.foodorderAdminapp.common.TextDesignByAman
import com.tech.foodorderAdminapp.screens.Header
import com.tech.foodorderAdminapp.screens.LoginScreen
import com.tech.foodorderAdminapp.ui.theme.FoodOrderAppTheme
import com.tech.foodorderAdminapp.ui.theme.darkWhiteColor

@Composable
fun CreateUserAdminScreen(navHostController: NavHostController) {
    var userEmailOrPhone by remember {
        mutableStateOf("")
    }
    var userName by remember {
        mutableStateOf("")
    }
    var userPassword by remember {
        mutableStateOf("")
    }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkWhiteColor),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = 30.dp, start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(stringResource(R.string.create_new_user_admin))

            Spacer(modifier = Modifier.height(10.dp))
            AddItemTextField(
                text = userName,
                titleText = stringResource(id = R.string.name),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.usernew),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                },
                onValueChange = {
                    userName = it
                })

            Spacer(modifier = Modifier.height(10.dp))
            AddItemTextField(
                text = userEmailOrPhone,
                titleText = stringResource(id = R.string.email_or_phone_number),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.mail),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                },
                onValueChange = {
                    userEmailOrPhone = it
                })

            Spacer(modifier = Modifier.height(10.dp))
            AddItemTextField(
                text = userPassword,
                titleText = stringResource(id = R.string.password),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.lock),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                },
                onValueChange = {
                    userPassword = it
                })

            Spacer(modifier = Modifier.height(30.dp))
            AddItemButton(stringResource(id = R.string.create_new_user)) {
                //perform here to create new user for admin
            }
        }
        TextDesignByAman()
    }
}

@Preview(showBackground = true)
@Composable
fun UserCreatePreview() {
    FoodOrderAppTheme {
        val navHostController = rememberNavController()
        CreateUserAdminScreen(navHostController = navHostController)
    }
}