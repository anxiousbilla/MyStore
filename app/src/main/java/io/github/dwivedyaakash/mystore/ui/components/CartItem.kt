package io.github.dwivedyaakash.mystore.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.github.dwivedyaakash.mystore.R
import io.github.dwivedyaakash.mystore.model.Product

@Composable
fun CartItem(
    product: Product,
    cartItemCounts: Int,
    addToCart: (Int) -> Unit,
    removeItemFromCart: (Int) -> Unit,
    deleteAllItemsFromCart: (Int) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(12.dp),
    ) {
        AsyncImage(
            modifier = Modifier
                .width(120.dp)
                .padding(8.dp),
            model = product.image,
            contentDescription = product.title
        )
        Column(modifier = Modifier.padding(start = 12.dp)) {
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp))
                            .background(Color.Black)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .clickable(
                                onClick = { removeItemFromCart(product.id) },
                                interactionSource = interactionSource,
                                indication = null
                            ),
                        painter = painterResource(id = R.drawable.ic_remove),
                        contentDescription = "Remove one item",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = cartItemCounts.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp))
                            .background(Color.Black)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .clickable(
                                onClick = { addToCart(product.id) },
                                interactionSource = interactionSource,
                                indication = null
                            ),
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "Add one more item",
                        tint = Color.White
                    )
                }
                Icon(
                    modifier = Modifier.clickable(
                        onClick = { deleteAllItemsFromCart(product.id) },
                        interactionSource = interactionSource,
                        indication = null
                    ),
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete all items",
                    tint = Color.Red
                )
            }
        }
    }

}
