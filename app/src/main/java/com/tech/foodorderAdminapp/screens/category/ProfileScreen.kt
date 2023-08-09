package com.tech.foodorderAdminapp.screens.category

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.tech.foodorderAdminapp.common.ButtonComponent
import com.tech.foodorderAdminapp.common.TextComponent
import com.tech.foodorderAdminapp.common.TextDesignByAman
import com.tech.foodorderAdminapp.common.lato_bold
import com.tech.foodorderAdminapp.common.lato_regular
import com.tech.foodorderAdminapp.common.yeon_sung_regular
import com.tech.foodorderAdminapp.screens.TextFieldLayout
import com.tech.foodorderAdminapp.ui.theme.FoodOrderAppTheme
import com.tech.foodorderAdminapp.ui.theme.GreenColor
import com.tech.foodorderAdminapp.ui.theme.darkWhiteColor

@Composable
fun ProfileScreen(navHostController: NavHostController) {

    val scrollState = rememberScrollState()
    var userName by remember {
        mutableStateOf("")
    }
    var userAddress by remember {
        mutableStateOf("")
    }
    var userEmail by remember {
        mutableStateOf("")
    }
    var userPhone by remember {
        mutableStateOf("")
    }
    var userPassword by remember {
        mutableStateOf("")
    }
    var textFieldRead by remember {
        mutableStateOf(true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkWhiteColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(scrollState)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CategoryHeader(titleText = stringResource(R.string.admin_profile)) {
                navHostController.navigateUp()
            }
            Spacer(modifier = Modifier.height(30.dp))


            RowText {
                textFieldRead = !textFieldRead
            }
            Spacer(modifier = Modifier.height(20.dp))

            ProfileTextLayout(
                text = userName,
                trailingText = stringResource(id = R.string.name),
                textFieldRead = textFieldRead
            ) {
                userName = it
            }

            Spacer(modifier = Modifier.height(5.dp))

            ProfileTextLayout(
                text = userAddress,
                trailingText = stringResource(id = R.string.address),
                textFieldRead = textFieldRead
            ) {
                userAddress = it
            }
            Spacer(modifier = Modifier.height(5.dp))

            ProfileTextLayout(
                text = userEmail,
                trailingText = stringResource(id = R.string.email),
                textFieldRead = textFieldRead
            ) {
                userEmail = it
            }
            Spacer(modifier = Modifier.height(5.dp))

            ProfileTextLayout(
                text = userPhone,
                trailingText = stringResource(R.string.phone),
                textFieldRead = textFieldRead
            ) {
                userPhone = it
            }
            Spacer(modifier = Modifier.height(5.dp))

            ProfileTextLayout(
                text = userPassword,
                trailingText = stringResource(id = R.string.password),
                textFieldRead = textFieldRead
            ) {
                userPassword = it
            }

            Spacer(modifier = Modifier.height(30.dp))
            SaveButton()

            Spacer(modifier = Modifier.height(30.dp))

            TextDesignByAman()
        }

    }

}

@Composable
fun RowText(editTextOnClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextComponent(
            text = stringResource(R.string.edit_your_profile),
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            fontFamily = yeon_sung_regular,
            color = GreenColor
        )
        TextComponent(
            modifier = Modifier.clickable {
                editTextOnClick()
            },
            text = stringResource(R.string.click_here_to_edit),
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            fontFamily = yeon_sung_regular,
            color = GreenColor
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTextLayout(
    text: String,
    trailingText: String,
    textFieldRead: Boolean,
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
        ),
        leadingIcon = {
            TextComponent(
                modifier = Modifier.padding(start = 5.dp),
                text = trailingText,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                fontFamily = yeon_sung_regular,
                color = Color.Black
            )
        }, readOnly = textFieldRead, enabled = !textFieldRead
    )
}

@Composable
fun SaveButton() {
    ButtonComponent(
        text = stringResource(R.string.save_profile),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 1.dp,
            focusedElevation = 1.dp
        ),
        color = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = GreenColor
        )
    ) {
        //save perform here after clicked this button
    }
}

@Composable
@Preview
fun ProfileScreenPreview() {
    FoodOrderAppTheme {
        val navHostController = rememberNavController()

        ProfileScreen(navHostController = navHostController)
    }
}
