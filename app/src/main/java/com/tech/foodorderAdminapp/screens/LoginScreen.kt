@file:OptIn(ExperimentalMaterial3Api::class)

package com.tech.foodorderAdminapp.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tech.foodorderAdminapp.R
import com.tech.foodorderAdminapp.common.lato_bold
import com.tech.foodorderAdminapp.common.lato_light
import com.tech.foodorderAdminapp.common.lato_regular
import com.tech.foodorderAdminapp.common.yeon_sung_regular
import com.tech.foodorderAdminapp.ui.theme.FoodOrderAppTheme
import com.tech.foodorderAdminapp.ui.theme.GreenColor
import com.tech.foodorderAdminapp.ui.theme.darkWhiteColor

@Composable
fun LoginScreen(navHostController: NavHostController) {

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkWhiteColor),
        contentAlignment = Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp, start = 20.dp, end = 20.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            Header()

            TextFieldLayout(email, stringResource(R.string.email), R.drawable.mail) {
                email = it
            }

            Spacer(modifier = Modifier.height(10.dp))
            TextFieldLayout(password, stringResource(id = R.string.password), R.drawable.lock) {
                password = it
            }
        }
    }
}

@Composable
fun Header() {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            tint = Color.Unspecified, modifier = Modifier.size(130.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.waves_of_food), style = TextStyle(
                fontSize = 50.sp,
                fontWeight = FontWeight.W400,
                fontFamily = yeon_sung_regular
            )
        )
        Text(
            text = stringResource(R.string.login_to_your_admin_dashboard), style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W400,
                fontFamily = lato_bold,
                color = GreenColor
            ), textAlign = TextAlign.Center, modifier = Modifier.padding(vertical = 20.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldLayout(
    text: String,
    textFieldTitle: String,
    @DrawableRes icon: Int,
    onValueChange: (String) -> Unit
) {

    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = Modifier
            .padding(0.dp)
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Color.White,
            cursorColor = GreenColor,
            focusedTrailingIconColor = Color.Black,
        ), placeholder = {
            Text(
                textFieldTitle, style = TextStyle(
                    color = Color.Gray,
                    fontSize = 10.sp,
                    fontFamily = lato_regular,
                    textAlign = TextAlign.Center
                ), textAlign = TextAlign.Center
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                tint = Color.Unspecified
            )
        },
        trailingIcon = {
            if (textFieldTitle == stringResource(id = R.string.password)) {
                Icon(
                    painter = painterResource(id = R.drawable.eye),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            }else{
                null
            }
        },
        visualTransformation = if (textFieldTitle == stringResource(R.string.password)) PasswordVisualTransformation() else {
            VisualTransformation.None
        },

        keyboardOptions = if (textFieldTitle == stringResource(R.string.password)) KeyboardOptions(
            keyboardType = KeyboardType.Password
        ) else {
            KeyboardOptions(keyboardType = KeyboardType.Text)
        }

    )
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    FoodOrderAppTheme {
        val navHostController = rememberNavController()
        LoginScreen(navHostController = navHostController)
    }
}