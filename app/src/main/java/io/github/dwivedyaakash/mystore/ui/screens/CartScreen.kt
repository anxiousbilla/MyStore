package io.github.dwivedyaakash.mystore.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.dwivedyaakash.mystore.ui.components.CartItem
import io.github.dwivedyaakash.mystore.ui.components.Header
import io.github.dwivedyaakash.mystore.viewModel.StoreUiState
import io.github.dwivedyaakash.mystore.viewModel.StoreViewModel

@Composable
fun CartScreen(viewModel: StoreViewModel, uiState: StoreUiState) {

    LaunchedEffect(uiState.cartItemCounts) {
        viewModel.getCartProducts()
    }

    Column(modifier = Modifier.padding(8.dp)) {
        Header("Cart")
        LazyColumn {
            uiState.cartProducts.forEach {
                item {
                    CartItem(
                        product = it,
                        cartItemCounts = uiState.cartItemCounts[it.id] ?: 0,
                        addToCart = { id -> viewModel.addToCart(id) },
                        removeItemFromCart = { id -> viewModel.removeItemFromCart(id) },
                        deleteAllItemsFromCart = { id -> viewModel.deleteAllItemsFromCart(id) }
                    )
                }
                item { Spacer(Modifier.height(10.dp)) }
            }
        }
    }

}
