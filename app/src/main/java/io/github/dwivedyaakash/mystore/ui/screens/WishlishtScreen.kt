package io.github.dwivedyaakash.mystore.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.dwivedyaakash.mystore.ui.components.Header

@Composable
fun WishlistScreen() {

    Column(modifier = Modifier.padding(8.dp)) {
        Header("Wishlist")
    }

}
