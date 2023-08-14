package com.tech.foodorderAdminapp.firebase.firebaseAuth

data class AuthUserModel(
    var email : String = "",
    var password : String = "",
    var ownerName : String = "",
    val restaurantName : String = "",
    var ownerAddress : String = "",
    var ownerPhone : String = "",
    var uid : String = "",
)
