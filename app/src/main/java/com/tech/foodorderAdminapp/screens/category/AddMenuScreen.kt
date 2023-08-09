package com.tech.foodorderAdminapp.screens.category

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
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
import com.tech.foodorderAdminapp.common.lato_regular
import com.tech.foodorderAdminapp.common.yeon_sung_regular
import com.tech.foodorderAdminapp.ui.theme.FoodOrderAppTheme
import com.tech.foodorderAdminapp.ui.theme.GreenColor
import com.tech.foodorderAdminapp.ui.theme.darkWhiteColor

@Composable
fun AddMenuScreen(navHostController: NavHostController) {

    var itemName by remember {
        mutableStateOf("")
    }
    var itemPrice by remember {
        mutableStateOf("")
    }
    val scrollState = rememberScrollState()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkWhiteColor)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.Center)
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            CategoryHeader(titleText = stringResource(R.string.add_items)) {
                navHostController.navigateUp()
            }

            Spacer(modifier = Modifier.height(10.dp))

            AddItemTextField(
                text = itemName,
                titleText = stringResource(id = R.string.item_name),
                onValueChange = {
                    itemName = it
                })
            Spacer(modifier = Modifier.height(5.dp))
            AddItemTextField(
                text = itemPrice,
                titleText = stringResource(id = R.string.item_price),
                onValueChange = {
                    itemPrice = it
                })

            Spacer(modifier = Modifier.height(5.dp))

            SelectItemImage()
            Spacer(modifier = Modifier.height(5.dp))

            ShortDescription()

            Spacer(modifier = Modifier.height(10.dp))
            AddItemButton(stringResource(id = R.string.add_items)){

            }

        }
    }
}

@Composable
fun CategoryHeader(titleText: String,backOnClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_left),
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(start = 10.dp)
                .clickable {
                    backOnClick()
                }
        )
        Text(
            text = titleText, style = TextStyle(
                fontSize = 40.sp,
                fontFamily = yeon_sung_regular,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center
            ), modifier = Modifier.weight(0.9f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemTextField(
    text: String,
    titleText: String,
    leadingIcon: @Composable (() -> Unit) = {},
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
        placeholder = {
            Text(
                titleText, style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = yeon_sung_regular,
                    textAlign = TextAlign.Center
                ), textAlign = TextAlign.Center
            )
        },
        leadingIcon = leadingIcon ,
        readOnly = if (titleText == stringResource(id = R.string.select_item_image)) true else false,
    )
}

@Composable
fun SelectItemImage() {

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            imageUri = it
        })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier
                .shadow(1.dp, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .background(Color.White)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.select_item_image), style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = yeon_sung_regular
                )
            )

            IconButton(onClick = {
                launcher.launch("image/*")

            }) {
                Icon(
                    painter = painterResource(id = R.drawable.pluse_round),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            }
        }
        imageUri?.let {
            bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                ImageDecoder.decodeBitmap(source)
            }
            Spacer(modifier = Modifier.height(5.dp))
            Image(
                bitmap = bitmap!!.asImageBitmap(),
                contentDescription = "",
                modifier = Modifier
                    .height(110.dp)
                    .width(175.dp)
            )
        }
    }
}

@Composable
fun ShortDescription() {

    var shortDesc by remember {
        mutableStateOf("")
    }
    var ingredients by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.short_description), style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                fontFamily = yeon_sung_regular
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        AddItemTextField(
            text = shortDesc,
            titleText = stringResource(R.string.short_description),
            onValueChange = {
                shortDesc = it
            })

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.ingredients), style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                fontFamily = yeon_sung_regular
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        AddItemTextField(
            text = ingredients,
            titleText = stringResource(R.string.ingredients),
            onValueChange = {
                ingredients = it
            })

    }
}

@Composable
fun AddItemButton(buttonText : String,addItemOnClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = addItemOnClick,
            modifier = Modifier
                .height(50.dp)
                .width(200.dp),
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
                text = buttonText, style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = yeon_sung_regular,
                    color = Color.White
                )
            )
        }
    }
}

@Composable
@Preview
fun AddMenuPreview() {
    FoodOrderAppTheme {
//        val navHostController = rememberNavController()

//        AddMenuScreen(navHostController = navHostController)
    }
}