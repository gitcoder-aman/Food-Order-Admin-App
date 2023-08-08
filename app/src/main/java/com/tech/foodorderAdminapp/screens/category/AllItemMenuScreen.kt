package com.tech.foodorderAdminapp.screens.category

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.tech.foodorderAdminapp.common.lato_bold
import com.tech.foodorderAdminapp.common.lato_regular
import com.tech.foodorderAdminapp.common.yeon_sung_regular
import com.tech.foodorderAdminapp.data.ItemModel
import com.tech.foodorderAdminapp.data.itemList
import com.tech.foodorderAdminapp.ui.theme.FoodOrderAppTheme
import com.tech.foodorderAdminapp.ui.theme.GreenColor
import com.tech.foodorderAdminapp.ui.theme.LightGreenColor
import com.tech.foodorderAdminapp.ui.theme.darkWhiteColor

@Composable
fun AllItemMenuScreen(navHostController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkWhiteColor)
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.Center)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            CategoryHeader(titleText = stringResource(R.string.all_items)) {
                navHostController.navigateUp()
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {
                items(itemList, key = { it.itemId }) {
                    ItemEachView(itemModel = it)
                }
            }
        }

    }
}

@Composable
fun ItemEachView(itemModel: ItemModel) {

    var count by remember {
        mutableStateOf(1)
    }

    Card(
        modifier = Modifier
            .padding(top = 5.dp, start = 5.dp, end = 5.dp)
            .width(350.dp)
            .height(100.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(itemModel.itemImage),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .weight(0.33f)
                    .padding(5.dp)
                    .shadow(elevation = 1.dp, shape = RoundedCornerShape(16.dp))
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                    .weight(0.33f),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = itemModel.itemName, style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = yeon_sung_regular
                    )
                )
                Text(
                    text = itemModel.itemPrice, style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = yeon_sung_regular,
                        color = GreenColor,
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                    .weight(0.33f), verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = {
                        if (count > 1) count--
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_minus),
                            contentDescription = "",
                            tint = Color.Unspecified
                        )
                    }

                    Text(
                        text = count.toString(), style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W200,
                            fontFamily = lato_regular,
                            color = Color.Black,
                        )
                    )
                    IconButton(onClick = {
                        count++
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_plus),
                            contentDescription = "",
                            tint = Color.Unspecified
                        )
                    }
                }
                Icon(
                    painter = painterResource(id = R.drawable.trash),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun AllMenuPreview() {
    FoodOrderAppTheme {
        val navHostController = rememberNavController()

        AllItemMenuScreen(navHostController = navHostController)
    }
}