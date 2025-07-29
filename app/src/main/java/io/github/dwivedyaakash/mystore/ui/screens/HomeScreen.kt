package io.github.dwivedyaakash.mystore.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.dwivedyaakash.mystore.ui.components.Header
import io.github.dwivedyaakash.mystore.ui.components.ProductsGrid
import io.github.dwivedyaakash.mystore.viewModel.StoreViewModel

@Composable
fun HomeScreen(modifier: Modifier, viewModel: StoreViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC))
            .padding(8.dp)
    ) {
        Header()
        ProductsGrid(uiState.products)
    }

}
