package com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb

data class RealtimeModelResponse(

    val items : RealtimeItems? ,
    val key : String? = ""
){
    data class RealtimeItems(
        val itemName : String = "",
        val description : String = "",
        val itemIngredients : String = "",
        val price : String = "",
        val itemImage : String = ""
    )
}
