package com.tech.foodorderAdminapp.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tech.foodorderAdminapp.R
import com.tech.foodorderAdminapp.ui.theme.GreenColor

@Composable
fun TextDesignByAman() {
    Text(
        text = stringResource(R.string.design_by_aman_kumar), style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.W400,
            fontFamily = yeon_sung_regular,
            color = GreenColor
        ), textAlign = TextAlign.Center, modifier = Modifier.padding(vertical = 20.dp)
    )
}