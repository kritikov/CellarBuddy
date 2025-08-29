package com.example.cellarbuddy.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cellarbuddy.ui.theme.Black
import com.example.cellarbuddy.ui.theme.VanillaCream

@Composable
fun ButtonLight(
    modifier: Modifier = Modifier,
    text: String = "",
    backgrounndColor: Color = VanillaCream,
    frontColor: Color = Black,
    horizontalPadding: Dp = 32.dp,
    verticalPadding: Dp = 32.dp,
    fontSize: TextUnit = 30.sp,
    onClick: () -> Unit

) {
    Button(
        modifier = Modifier
            .then(modifier),
        onClick = onClick,
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgrounndColor,
            contentColor = frontColor
        ),
        contentPadding = PaddingValues(
            horizontal = horizontalPadding,
            vertical = verticalPadding
        )
    ){
        Text(
            text = text,
            fontSize = fontSize
        )
    }

}