package io.github.dwivedyaakash.mystore.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(title: String, onClick: () -> Unit) {

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
            .clip(RoundedCornerShape(50.dp))
            .clickable(
                onClick = { onClick() },
                interactionSource = interactionSource,
                indication = ripple()
            )
            .background(Color(0xFF0D0D0D))
            .padding(8.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }

}
