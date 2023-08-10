package com.tech.foodorderAdminapp.util

import android.content.Context
import android.widget.Toast

fun Context.showMsg(
    msg : String,
    duration : Int = Toast.LENGTH_SHORT
) = Toast.makeText(this,msg,duration).show()