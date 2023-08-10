package com.tech.foodorderAdminapp.data

import androidx.annotation.DrawableRes
import com.tech.foodorderAdminapp.R

data class PendingModel(
    val itemId : Int,
    val itemName : String,
    @DrawableRes val itemImage : Int,
    val customerName : String,
    val noOfQuantity : Int,
    val orderStatus : String
)
val pendingList = listOf(
    PendingModel(1,"Chhola batura", R.drawable.menu1,"Aman Kumar",3,"Accept"),
    PendingModel(2,"Momos", R.drawable.menu2,"Nikhil Kumar",2,"Dispatch"),
    PendingModel(3,"Khichdi", R.drawable.menu3,"Abhishek Kumar",1,"Dispatch"),
    PendingModel(4,"Karela bharta", R.drawable.menu4,"Raushan Kumar",1,"Accept"),
    PendingModel(5,"Katha meetha petha / kaddu halwa", R.drawable.menu5,"Rahul Kumar",2,"Dispatch"),
    PendingModel(6,"Pork jarpaa jurpie", R.drawable.menu1,"Abhishek Kumar",1,"Dispatch"),
    PendingModel(7,"Aloo shimla mirch", R.drawable.menu2,"Sonali Kumari",3,"Accept"),
    PendingModel(8,"Chhola batura", R.drawable.menu3,"Abhishek Kumar",4,"Dispatch"),
)
