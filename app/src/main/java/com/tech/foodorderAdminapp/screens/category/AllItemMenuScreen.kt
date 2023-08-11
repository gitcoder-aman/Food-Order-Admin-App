package com.tech.foodorderAdminapp.screens.category

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tech.foodorderAdminapp.R
import com.tech.foodorderAdminapp.common.CommonDialog
import com.tech.foodorderAdminapp.common.TextComponent
import com.tech.foodorderAdminapp.common.lato_bold
import com.tech.foodorderAdminapp.common.lato_regular
import com.tech.foodorderAdminapp.common.yeon_sung_regular
import com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.RealtimeModelResponse
import com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.ui.RealtimeViewModel
import com.tech.foodorderAdminapp.firebase.utils.ResultState
import com.tech.foodorderAdminapp.ui.theme.FoodOrderAppTheme
import com.tech.foodorderAdminapp.ui.theme.GreenColor
import com.tech.foodorderAdminapp.ui.theme.darkWhiteColor
import com.tech.foodorderAdminapp.util.showMsg
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AllItemMenuScreen(navHostController: NavHostController) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel: RealtimeViewModel = hiltViewModel()
    val res = viewModel.res.value

    val isDialog = remember {
        mutableStateOf(false)
    }
    var isUpdate = remember {
        mutableStateOf(false)
    }
    var showDialog by remember { mutableStateOf(false) }
    var responseKey by remember {
        mutableStateOf("")
    }



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

            if (isUpdate.value) {
                Update(
                    isUpdate = isUpdate,
                    itemModel = viewModel.updateRes.value,  //observe
                    viewModel = viewModel
                )
            }

            if (res.item.isNotEmpty()) {
                LazyColumn {
                    items(res.item, key = { it.key!! }) { res ->
                        ItemEachView(itemModel = res.items!!, onUpdate = {
                            isUpdate.value = true
                            viewModel.setData(res)
                        }, onDelete = {
                            showDialog = true
                            responseKey = res.key!!
                        })
                    }
                }
            }
            if (res.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            if (res.error.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = res.error)
                }
            }
        }

    }
    if (isDialog.value) {
        CommonDialog()
    }
    if (showDialog) {
        Log.d("@@@@", "res" + responseKey)
        AlertDialogShow(onClick = {
            deleteFunction(
                itemKey = responseKey,
                viewModel = viewModel,
                scope = scope,
                context = context,
                isDialog = isDialog
            )
            showDialog = false
        }, onClose = {
            showDialog = it
        })
    }

}

@Composable
fun AlertDialogShow(
    onClick: () -> Unit,
    onClose: (Boolean) -> Unit
) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        confirmButton = {
            Button(
                onClick = {
                    onClick()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenColor,
                    contentColor = Color.White
                )
            ) {
                TextComponent(
                    text = stringResource(id = R.string.yes),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = yeon_sung_regular,
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                // adding on click listener for this button
                onClick = { onClose(false) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenColor,
                    contentColor = Color.White
                )
            ) {
                // adding text to our button.
                TextComponent(
                    text = stringResource(R.string.no),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = yeon_sung_regular,
                    color = Color.White
                )
            }

        }, title = {
            TextComponent(
                text = stringResource(R.string.are_you_sure_delete_this_item),
                fontSize = 12.sp,
                fontWeight = FontWeight.W200,
                fontFamily = yeon_sung_regular,
                color = Color.Black
            )
        }, containerColor = Color.White
    )
}

fun deleteFunction(
    itemKey: String,
    viewModel: RealtimeViewModel,
    scope: CoroutineScope,
    context: Context,
    isDialog: MutableState<Boolean>
) {

    scope.launch(Dispatchers.Main) {
        viewModel.delete(itemKey).collect { collector ->
            when (collector) {
                is ResultState.Success -> {
                    context.showMsg(msg = collector.data)
                    isDialog.value = false
                }

                is ResultState.Failure -> {
                    context.showMsg(msg = collector.msg.toString())
                    isDialog.value = false
                }

                is ResultState.Loading -> {
                    isDialog.value = true
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Update(
    isUpdate: MutableState<Boolean>,
    itemModel: RealtimeModelResponse,
    viewModel: RealtimeViewModel,
) {
    val itemName = remember {
        mutableStateOf(itemModel.items?.itemName)
    }
    val itemIngredients = remember {
        mutableStateOf(itemModel.items?.itemIngredients)
    }
    val description = remember {
        mutableStateOf(itemModel.items?.description)
    }
    val itemPrice = remember {
        mutableStateOf(itemModel.items?.price)
    }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    if (isUpdate.value) {
        AlertDialog(onDismissRequest = { isUpdate.value = false },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(value = itemName.value!!, onValueChange = {
                        itemName.value = it
                    }, placeholder = {
                        Text(text = stringResource(id = R.string.item_name))
                    })
                    Spacer(modifier = Modifier.height(10.dp))

                    TextField(value = itemPrice.value!!, onValueChange = {
                        itemPrice.value = it
                    }, placeholder = {
                        Text(text = stringResource(id = R.string.item_price))
                    })
                    TextField(value = description.value!!, onValueChange = {
                        description.value = it
                    }, placeholder = {
                        Text(text = stringResource(R.string.description))
                    })
                    TextField(value = itemIngredients.value!!, onValueChange = {
                        itemIngredients.value = it
                    }, placeholder = {
                        Text(text = stringResource(id = R.string.ingredients))
                    })
                }
            }, confirmButton = {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(onClick = {
                        scope.launch(Dispatchers.Main) {
                            viewModel.update(
                                RealtimeModelResponse(
                                    RealtimeModelResponse.RealtimeItems(
                                        itemName = itemName.value!!,
                                        price = itemPrice.value!!,
                                        description = description.value!!,
                                        itemIngredients = itemIngredients.value!!
                                    ),
                                    key = itemModel.key
                                )

                            ).collect {
                                when (it) {
                                    is ResultState.Success -> {
                                        context.showMsg(msg = it.data)
                                        isUpdate.value = false
                                    }

                                    is ResultState.Failure -> {
                                        context.showMsg(msg = it.msg.toString())
                                    }

                                    is ResultState.Loading -> {
                                    }
                                }
                            }
                        }
                    }) {
                        TextComponent(
                            text = stringResource(R.string.update),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
                            fontFamily = yeon_sung_regular,
                            color = Color.White
                        )
                    }
                }
            })
    }
}

@Composable
fun ItemEachView(
    itemModel: RealtimeModelResponse.RealtimeItems,
    onUpdate: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(top = 5.dp, start = 5.dp, end = 5.dp, bottom = 5.dp)
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
                painter = painterResource(R.drawable.menu1),
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
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W600,
                        fontFamily = yeon_sung_regular
                    ), maxLines = 1
                )
                Text(
                    text = itemModel.itemIngredients, style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W200,
                        fontFamily = yeon_sung_regular
                    ), maxLines = 1
                )
                Text(
                    text = itemModel.description, style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W200,
                        fontFamily = yeon_sung_regular
                    ), maxLines = 1
                )

                Text(
                    text = itemModel.price, style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = yeon_sung_regular,
                        color = GreenColor,
                    ), maxLines = 1
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                    .weight(0.33f), verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                IconButton(onClick = {  //update functionality data from firebase
                    onUpdate()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                }

                IconButton(onClick = {  //delete functionality data from firebase
                    onDelete()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.trash),
                        contentDescription = "",
                        tint = GreenColor,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                }

            }
        }
    }
}

@Composable
fun AllMenuPreview() {
    FoodOrderAppTheme {
        val navHostController = rememberNavController()

        AllItemMenuScreen(navHostController = navHostController)
    }
}