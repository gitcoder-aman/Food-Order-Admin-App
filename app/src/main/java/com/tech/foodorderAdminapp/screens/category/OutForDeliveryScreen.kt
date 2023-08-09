package com.tech.foodorderAdminapp.screens.category

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tech.foodorderAdminapp.R
import com.tech.foodorderAdminapp.common.TextComponent
import com.tech.foodorderAdminapp.common.lato_bold
import com.tech.foodorderAdminapp.common.lato_regular
import com.tech.foodorderAdminapp.common.yeon_sung_regular
import com.tech.foodorderAdminapp.ui.theme.FoodOrderAppTheme
import com.tech.foodorderAdminapp.ui.theme.darkWhiteColor

@Composable
fun OutForDeliveryScreen(navHostController: NavHostController) {

    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkWhiteColor)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .verticalScroll(scrollState)
                .align(Alignment.Center)
        ) {
            CategoryHeader(titleText = stringResource(R.string.out_for_delivery)) {
                navHostController.navigateUp()
            }
            Spacer(modifier = Modifier.height(10.dp))

            StatusCardView(
                "Nikhil Kumar",
                stringResource(R.string.cash),
                stringResource(id = R.string.not_received),
                statusColor = Color.Red
            )

            StatusCardView(
                "Aman Kumar",
                stringResource(R.string.online),
                stringResource(R.string.received),
                statusColor = Color.Green
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusCardView(customerName: String, payment: String, orderStatus: String, statusColor: Color) {
    Card(
        onClick = { },
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(vertical = 5.dp)
            ) {
                TextComponent(
                    text = customerName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    fontFamily = yeon_sung_regular,
                    color = Color.Black
                )
                TextComponent(
                    text = payment,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W200,
                    fontFamily = lato_regular,
                    color = Color.Gray
                )
                TextComponent(
                    text = orderStatus,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                    fontFamily = lato_regular,
                    color = statusColor
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(8.dp)
            ) {
                TextComponent(
                    text = stringResource(R.string.delivered),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = lato_bold,
                    color = Color.Black
                )
                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                        .shadow(1.dp, shape = CircleShape)
                        .background(color = statusColor)
                )
            }
        }
    }
}

@Composable
@Preview
fun OutForDeliveryPreview() {
    FoodOrderAppTheme {
        val navHostController = rememberNavController()

        OutForDeliveryScreen(navHostController = navHostController)
    }
}