package com.tech.foodorderAdminapp.screens

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.CloudDone
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.PendingActions
import androidx.compose.material.icons.outlined.PersonAddAlt
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
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
import com.tech.foodorderAdminapp.common.TextDesignByAman
import com.tech.foodorderAdminapp.common.yeon_sung_regular
import com.tech.foodorderAdminapp.navigation.add_menu
import com.tech.foodorderAdminapp.navigation.all_menu_show
import com.tech.foodorderAdminapp.navigation.create_user_admin
import com.tech.foodorderAdminapp.navigation.out_for_delivery
import com.tech.foodorderAdminapp.navigation.profile
import com.tech.foodorderAdminapp.ui.theme.FoodOrderAppTheme
import com.tech.foodorderAdminapp.ui.theme.GreenColor
import com.tech.foodorderAdminapp.ui.theme.darkWhiteColor

@Composable
fun HomeScreen(navHostController: NavHostController) {

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkWhiteColor)
            .padding(start = 10.dp, end = 10.dp),
        contentAlignment = BottomCenter
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Header()
            Spacer(modifier = Modifier.height(10.dp))

            StatusCardLayout()
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                EachCardLayout(
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(5.dp)
                        .weight(0.5f),
                    cardName = stringResource(R.string.add_menu),
                    cardIcon = Icons.Outlined.AddCircleOutline
                ) {
                    navHostController.navigate(add_menu)
                }

                EachCardLayout(
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(5.dp)
                        .weight(0.5f),
                    cardName = stringResource(R.string.all_items_menu),
                    cardIcon = Icons.Outlined.Visibility
                ) {
                    navHostController.navigate(all_menu_show)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                EachCardLayout(
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(5.dp)
                        .weight(0.5f),
                    cardName = stringResource(R.string.profile),
                    cardIcon = Icons.Outlined.AccountCircle
                ){
                    navHostController.navigate(profile)
                }


                EachCardLayout(
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(5.dp)
                        .weight(0.5f),
                    cardName = stringResource(R.string.create_new_user),
                    cardIcon = Icons.Outlined.PersonAddAlt
                ){
                    navHostController.navigate(create_user_admin)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                EachCardLayout(
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(5.dp)
                        .weight(0.5f),
                    cardName = stringResource(R.string.order_dispatch),
                    cardIcon = Icons.Outlined.ShoppingBag
                ){
                    navHostController.navigate(out_for_delivery)
                }

                EachCardLayout(
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(5.dp)
                        .weight(0.5f),
                    cardName = stringResource(R.string.log_out),
                    cardIcon = Icons.Outlined.Logout
                )
            }
            TextDesignByAman()
        }

    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier.size(50.dp)
        )

        Text(
            text = stringResource(R.string.waves_of_food), style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.W400,
                fontFamily = yeon_sung_regular
            )
        )
    }
}

@Composable
fun StatusCardLayout() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            CardColumnContent(
                modifier = Modifier.weight(0.33f),
                icon = Icons.Outlined.PendingActions,
                centerText = stringResource(R.string.pending_order),
                bottomText = "30"
            )
            CardColumnContent(
                modifier = Modifier.weight(0.33f),
                icon = Icons.Outlined.CloudDone,
                centerText = stringResource(R.string.completed_order),
                bottomText = "10"
            )
            CardColumnContent(
                modifier = Modifier.weight(0.33f),
                icon = Icons.Outlined.MonetizationOn,
                centerText = stringResource(R.string.whole_time_earning),
                bottomText = "$100"
            )
        }
    }
}

@Composable
fun CardColumnContent(
    modifier: Modifier,
    icon: ImageVector,
    centerText: String,
    bottomText: String
) {

    Column(
        modifier = modifier.padding(15.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = icon,
            contentDescription = "",
            tint = GreenColor
        )
        Text(
            text = centerText, style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W400,
                fontFamily = yeon_sung_regular,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = bottomText, style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W400,
                fontFamily = yeon_sung_regular,
                color = if (centerText == stringResource(id = R.string.pending_order)) {
                    Color.Red
                } else if (centerText == stringResource(id = R.string.completed_order)) {
                    Color.Yellow
                } else if (centerText == stringResource(id = R.string.whole_time_earning)) {
                    GreenColor
                } else Color.Unspecified

            )
        )
    }
}

@Composable
fun EachCardLayout(
    modifier: Modifier = Modifier,
    cardName: String,
    cardIcon: ImageVector,
    onClick: () -> Unit = {}
) {

    Card(
        modifier = modifier
            .clickable { onClick() }
            .width(200.dp)
            .height(150.dp),
        shape = RoundedCornerShape(16.dp), elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = cardIcon,
                contentDescription = "",
                tint = GreenColor,
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = cardName, style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = yeon_sung_regular,
                    fontWeight = FontWeight.W400,
                    color = GreenColor
                )
            )
        }
    }
}

@Composable
@Preview
fun HomePreview() {
    FoodOrderAppTheme {
//        val navHostController = rememberNavController()
//
//        HomeScreen(navHostController = navHostController)
    }
}