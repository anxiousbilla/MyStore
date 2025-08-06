package io.github.dwivedyaakash.mystore.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.github.dwivedyaakash.mystore.model.Product
import kotlin.text.uppercase

@Composable
fun ProductCard(product: Product, isFavourite: Boolean, onFavouriteClick: () -> Unit) {

    val isFavourite = remember { mutableStateOf(isFavourite) }
    val interactionSource = remember { MutableInteractionSource() }

    Box {
        Column {
            AsyncImage(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
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
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                modifier = Modifier
                    .background(Color.Transparent)
                    .clickable(
                        onClick = {
                            isFavourite.value = !isFavourite.value
                            onFavouriteClick()
                        },
                        interactionSource = interactionSource,
                        indication = null
                    ),
                imageVector = Icons.Default.Favorite,
                contentDescription = "Add product to favourites",
                tint = if (isFavourite.value) Color.Red else Color.LightGray
            )
        }
    }

}
