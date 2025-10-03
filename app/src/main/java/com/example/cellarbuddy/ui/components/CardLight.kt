package com.example.cellarbuddy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cellarbuddy.ui.theme.VanillaCream

@Composable
fun CardLight(
    modifier: Modifier = Modifier,
    onClick: (()->Unit)? = null,
    content: @Composable ()->Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .clickable(
                onClick = { onClick?.invoke() }
            ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = VanillaCream
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ){
        content()
    }
}