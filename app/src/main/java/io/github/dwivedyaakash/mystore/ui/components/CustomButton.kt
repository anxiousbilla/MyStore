package io.github.dwivedyaakash.mystore.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(title: String) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
            .background(Color(0xFF0D0D0D), RoundedCornerShape(50.dp))
            .padding(8.dp)
            .clickable(onClick = {}),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }

}
