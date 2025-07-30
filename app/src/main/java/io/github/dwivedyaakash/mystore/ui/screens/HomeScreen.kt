package io.github.dwivedyaakash.mystore.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.dwivedyaakash.mystore.ui.components.Header
import io.github.dwivedyaakash.mystore.ui.components.ProductsGrid
import io.github.dwivedyaakash.mystore.viewModel.StoreViewModel

@Composable
fun HomeScreen(viewModel: StoreViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(8.dp)) {
        Header("Products")
        ProductsGrid(uiState.products)
    }

}
