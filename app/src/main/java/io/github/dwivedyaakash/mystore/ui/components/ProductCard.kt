package io.github.dwivedyaakash.mystore.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.github.dwivedyaakash.mystore.model.Product
import kotlin.text.uppercase

@Composable
fun ProductCard(product: Product) {

    AsyncImage(
        modifier = Modifier
            .height(200.dp)
            .padding(8.dp),
        model = product.image,
        contentDescription = product.title
    )
    Text(
        modifier = Modifier.padding(top = 8.dp),
        text = product.category.uppercase(),
        color = Color.DarkGray,
        fontSize = 14.sp
    )
    Text(
        modifier = Modifier.padding(top = 4.dp),
        text = product.title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
    Row(modifier = Modifier.padding(top = 4.dp)) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Rating",
            tint = Color(0xFFFF9900)
        )
        Text(text = " ${product.rating.rate} (${product.rating.count})")
    }

}
