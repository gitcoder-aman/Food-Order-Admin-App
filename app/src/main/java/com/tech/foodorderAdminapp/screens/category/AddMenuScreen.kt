package com.tech.foodorderAdminapp.screens.category

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tech.foodorderAdminapp.R
import com.tech.foodorderAdminapp.common.CommonDialog
import com.tech.foodorderAdminapp.common.lato_regular
import com.tech.foodorderAdminapp.common.yeon_sung_regular
import com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.model.RealtimeModelResponse
import com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.ui.RealtimeViewModel
import com.tech.foodorderAdminapp.firebase.utils.ResultState
import com.tech.foodorderAdminapp.ui.theme.FoodOrderAppTheme
import com.tech.foodorderAdminapp.ui.theme.GreenColor
import com.tech.foodorderAdminapp.ui.theme.darkWhiteColor
import com.tech.foodorderAdminapp.util.Variables
import com.tech.foodorderAdminapp.util.showMsg
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AddMenuScreen(navHostController: NavHostController) {

    var itemName by remember {
        mutableStateOf("")
    }
    var itemPrice by remember {
        mutableStateOf("")
    }
    var itemIngredients by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val scrollState = rememberScrollState()

    val scope = rememberCoroutineScope()
    val viewModel: RealtimeViewModel = hiltViewModel()
    val context = LocalContext.current

    val isDialog = remember {
        mutableStateOf(false)
    }
    val focusRequester = FocusRequester()
    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
    }

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
                modifier = Modifier.focusRequester(focusRequester),
                text = itemName,
                titleText = stringResource(id = R.string.item_name),
                onValueChange = {
                    itemName = it
                }, icon = R.drawable.food_blank
            )

            Spacer(modifier = Modifier.height(5.dp))
            AddItemTextField(
                text = itemPrice,
                titleText = stringResource(id = R.string.item_price),
                onValueChange = {
                    itemPrice = it
                }, icon = R.drawable.currency
            )

            Spacer(modifier = Modifier.height(5.dp))

            SelectItemImageSection()

            Spacer(modifier = Modifier.height(5.dp))

            ShortDescription(itemIngredients, description, onDescChange = { desc ->
                description = desc
            }, onItemIngredientsChange = { item ->
                itemIngredients = item
            })

            Spacer(modifier = Modifier.height(10.dp))
            AddItemButton(stringResource(id = R.string.add_items)) {

                if (itemName.isNotEmpty() && itemPrice.isNotEmpty()) {
                    //here perform through button
                    //in save data in firebase realtime database
                    scope.launch(Dispatchers.Main) {

                        viewModel.insert(

                            RealtimeModelResponse.RealtimeItems(
                                itemName = itemName,
                                price = itemPrice,
                                description = description,
                                itemIngredients = itemIngredients,
                                itemImage = ""
                            ), bitmap = Variables.bitmap!!
                        ).collect {
                            when (it) {
                                is ResultState.Success -> {
                                    context.showMsg(msg = it.data)
                                    isDialog.value = false
                                    itemName = ""
                                    itemPrice = ""
                                    itemIngredients = ""
                                    description = ""
                                    Variables.bitmap = null
                                }

                                is ResultState.Failure -> {
                                    context.showMsg(msg = it.msg.toString())
                                    isDialog.value = false
                                }

                                is ResultState.Loading -> {
                                    isDialog.value = true
                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(context, "Item Name & Price must insert.", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }
    if (isDialog.value) {
        CommonDialog()
    }
}

@Composable
fun CategoryHeader(titleText: String, backOnClick: () -> Unit) {
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
    modifier: Modifier = Modifier,
    text: String,
    titleText: String,
    @DrawableRes icon: Int,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier
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
        keyboardOptions = KeyboardOptions(
            keyboardType = if (titleText == stringResource(id = R.string.item_price)) KeyboardType.Number else KeyboardType.Text
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
        leadingIcon = {
            Icon(painter = painterResource(icon), contentDescription = "", tint = GreenColor)
        },

        readOnly = if (titleText == stringResource(id = R.string.select_item_image)) true else false,
    )
}

@Composable
fun SelectItemImageSection() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var imageUri by remember {
            mutableStateOf<Uri?>(null)
        }
        val context = LocalContext.current

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = {
                imageUri = it
            })
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
            Variables.bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                ImageDecoder.decodeBitmap(source)
            }

            Spacer(modifier = Modifier.height(5.dp))
            Image(
                bitmap = Variables.bitmap!!.asImageBitmap(),
                contentDescription = "",
                modifier = Modifier
                    .height(110.dp)
                    .width(175.dp)
            )
        }
        Log.d("@@@@", "GetImageFromGallery: ${Variables.bitmap}")
    }
}

@Composable
fun ShortDescription(
    itemIngredients: String,
    description: String,
    onDescChange: (String) -> Unit,
    onItemIngredientsChange: (String) -> Unit
) {

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
            text = description,
            titleText = stringResource(R.string.short_description),
            onValueChange = {
                onDescChange(it)
            }, icon = R.drawable.description
        )

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
            text = itemIngredients,
            titleText = stringResource(R.string.ingredients),
            onValueChange = {
                onItemIngredientsChange(it)
            }, icon = R.drawable.ingredients
        )

    }
}

@Composable
fun AddItemButton(buttonText: String, addItemOnClick: () -> Unit) {
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
fun AddMenuPreview() {
    FoodOrderAppTheme {
//        val navHostController = rememberNavController()

//        AddMenuScreen(navHostController = navHostController)
    }
}