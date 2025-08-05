package io.github.dwivedyaakash.mystore.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.dwivedyaakash.mystore.ui.components.Header
import io.github.dwivedyaakash.mystore.ui.components.ProductsGrid
import io.github.dwivedyaakash.mystore.viewModel.StoreUiState
import io.github.dwivedyaakash.mystore.viewModel.StoreViewModel

@Composable
fun HomeScreen(viewModel: StoreViewModel, uiState: StoreUiState) {

    fun onFavouriteClick(id: Int) {
        if (uiState.favourites.contains(id)) {
            viewModel.removeFavourite(id)
        } else {
            viewModel.addFavourite(id)
        }
    }

    Column(modifier = Modifier.padding(8.dp)) {
        Header("Products")
        ProductsGrid(
            products = uiState.products,
            favourites = uiState.favourites,
            onFavouriteClick = { id -> onFavouriteClick(id) }
        )
    }

}
