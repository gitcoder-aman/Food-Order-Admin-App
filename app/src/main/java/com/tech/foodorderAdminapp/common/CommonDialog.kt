package com.tech.foodorderAdminapp.common

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun CommonDialog() {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        CircularProgressIndicator()
    }
}