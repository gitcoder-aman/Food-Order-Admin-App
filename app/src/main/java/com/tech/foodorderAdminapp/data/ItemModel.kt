package com.tech.foodorderAdminapp.data

import androidx.annotation.DrawableRes
import com.tech.foodorderAdminapp.R

data class ItemModel(
    val itemId : String,
    val itemName : String,
    @DrawableRes val itemImage : Int,
    val itemPrice : String
)
val itemList = listOf(
    ItemModel("1","Spacy Fresh Crab", R.drawable.menu1,"$35"),
    ItemModel("2","Spacy Fresh Crab", R.drawable.menu2,"$45"),
    ItemModel("3","Spacy Fresh Crab", R.drawable.menu3,"$15"),
    ItemModel("4","Spacy Fresh Crab", R.drawable.menu4,"$25"),
    ItemModel("5","Spacy Fresh Crab", R.drawable.menu1,"$35"),
    ItemModel("6","Spacy Fresh Crab", R.drawable.menu2,"$45"),
    ItemModel("7","Spacy Fresh Crab", R.drawable.menu3,"$15"),
    ItemModel("8","Spacy Fresh Crab", R.drawable.menu4,"$25"),
    ItemModel("9","Spacy Fresh Crab", R.drawable.menu4,"$25"),
    ItemModel("10","Spacy Fresh Crab", R.drawable.menu4,"$25"),
    ItemModel("11","Spacy Fresh Crab", R.drawable.menu4,"$25"),
    ItemModel("12","Spacy Fresh Crab", R.drawable.menu4,"$25"),
    ItemModel("13","Spacy Fresh Crab", R.drawable.menu4,"$25"),
    ItemModel("14","Spacy Fresh Crab", R.drawable.menu4,"$25"),
    ItemModel("15","Spacy Fresh Crab", R.drawable.menu4,"$25"),
)
