package com.example.cellarbuddy.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cellarbuddy.ui.theme.Black

@Composable
fun TextSimple(
    modifier: Modifier = Modifier,
    text: String = "",
    fontSize: TextUnit = 30.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Black,
    maxLines: Int = Int.MAX_VALUE,
    fontStyle: FontStyle = FontStyle.Normal
) {
    Text(
        modifier = Modifier
            .padding(8.dp)
            .then(modifier),
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = color,
        maxLines = maxLines,
        fontStyle = fontStyle
    )
}