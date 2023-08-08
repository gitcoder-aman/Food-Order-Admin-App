@file:OptIn(ExperimentalMaterial3Api::class)

package com.tech.foodorderAdminapp.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.tech.foodorderAdminapp.common.TextDesignByAman
import com.tech.foodorderAdminapp.common.lato_bold
import com.tech.foodorderAdminapp.common.lato_regular
import com.tech.foodorderAdminapp.common.yeon_sung_regular
import com.tech.foodorderAdminapp.navigation.home
import com.tech.foodorderAdminapp.navigation.signup
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

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkWhiteColor),
        contentAlignment = BottomCenter
    ) {
        Column(
            modifier = Modifier
                .align(Center)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = 30.dp, start = 20.dp, end = 20.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            Header(stringResource(R.string.login_to_your_admin_dashboard))

            TextFieldLayout(
                email,
                stringResource(R.string.email),
                R.drawable.mail,
                onValueChange = {
                    email = it
                })

            Spacer(modifier = Modifier.height(10.dp))
            TextFieldLayout(
                password,
                stringResource(id = R.string.password),
                R.drawable.lock,
                onValueChange = {
                    password = it
                })

            Spacer(modifier = Modifier.height(20.dp))

            ContinueWithText()

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RowIconButton(
                    stringResource(R.string.facebook),
                    R.drawable.facebook,
                    modifier = Modifier.weight(0.5f)
                )
                Spacer(modifier = Modifier.width(10.dp))
                RowIconButton(
                    stringResource(R.string.google),
                    R.drawable.google,
                    modifier = Modifier.weight(0.5f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            LoginBtnAndText(
                stringResource(id = R.string.login),
                stringResource(id = R.string.don_t_have_an_account),
                noAccount = {
                    navHostController.navigate(signup)
                },
                login = {
                    navHostController.navigate(home)
                })

            TextDesignByAman()
        }
    }

}

@Composable
fun Header(titleText: String) {

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
            text = titleText, style = TextStyle(
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
    onValueChange: (String) -> Unit,
) {

    var passwordVisible by rememberSaveable { mutableStateOf(true) }

    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = Modifier
            .padding(0.dp)
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        textStyle = TextStyle(fontFamily = lato_regular),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Color.White,
            cursorColor = GreenColor,
            focusedTrailingIconColor = Color.Black,
            selectionColors = TextSelectionColors(
                handleColor = GreenColor,
                backgroundColor = GreenColor
            )
        ), label = {
            Text(
                textFieldTitle, style = TextStyle(
                    color = Color.Black,
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
                val image = if (passwordVisible)
                    Icons.Filled.VisibilityOff
                else Icons.Filled.Visibility

                // Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description, tint = GreenColor)
                }

            }
        },
        visualTransformation = if (textFieldTitle == stringResource(R.string.password) && passwordVisible) PasswordVisualTransformation() else {
            VisualTransformation.None
        },

        keyboardOptions = if (textFieldTitle == stringResource(R.string.password)) KeyboardOptions(
            keyboardType = KeyboardType.Password
        ) else {
            KeyboardOptions(keyboardType = KeyboardType.Text)
        }

    )
}

@Composable
fun ContinueWithText() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.or), style = TextStyle(
                fontSize = 12.sp,
                fontFamily = yeon_sung_regular,
                fontWeight = FontWeight.W400,
                color = GreenColor
            )
        )
        Text(
            text = stringResource(R.string.continue_with), style = TextStyle(
                fontSize = 20.sp,
                fontFamily = yeon_sung_regular,
                fontWeight = FontWeight.W400,
                color = GreenColor
            )
        )
    }
}

@Composable
fun RowIconButton(buttonName: String, @DrawableRes icon: Int, modifier: Modifier = Modifier) {
    Button(
        onClick = { /*TODO*/ },
        modifier = modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 1.dp, pressedElevation = 2.dp,
            disabledElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                tint = Color.Unspecified,
            )
            Text(
                text = buttonName, style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = lato_regular,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                ), textAlign = TextAlign.Center
            )
        }
    }

}

@Composable
fun LoginBtnAndText(
    btnText: String,
    accountText: String,
    noAccount: () -> Unit,
    login: () -> Unit
) {
    Column(horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Button(
            onClick = login,
            modifier = Modifier
                .height(50.dp)
                .width(150.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenColor
            ),
            shape = RoundedCornerShape(8.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 1.dp, pressedElevation = 2.dp,
                disabledElevation = 0.dp
            )
        ) {
            Text(
                text = btnText, style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = yeon_sung_regular,
                    color = Color.White
                )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = accountText, style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.W400,
                fontFamily = lato_bold,
                color = GreenColor
            ), modifier = Modifier.clickable {
                noAccount()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    FoodOrderAppTheme {
        val navHostController = rememberNavController()
        LoginScreen(navHostController = navHostController)
    }
}