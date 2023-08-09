package com.tech.foodorderAdminapp.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonComponent(modifier: Modifier = Modifier,
                    text: String,
                    backgroundColor: Color = Color.White,
                    foregroundColor: Color = Color.Black,
                    elevation: ButtonElevation = ButtonDefaults.buttonElevation(0.dp),
                    color: ButtonColors = ButtonDefaults.buttonColors(
                         containerColor = backgroundColor,
                         contentColor = foregroundColor
                     ),
                    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        elevation = elevation,
        shape = RoundedCornerShape(16.dp),
        colors = color
    ) {
        Text(
            text = text, style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = yeon_sung_regular
            )
        )
    }


}